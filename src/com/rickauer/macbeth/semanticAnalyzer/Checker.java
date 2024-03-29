package com.rickauer.macbeth.semanticAnalyzer;

import com.rickauer.macbeth.ErrorReporter;
import com.rickauer.macbeth.StandardEnvironment;
import com.rickauer.macbeth.UnaryOperatorDeclaration;
import com.rickauer.macbeth.abstractsyntaxtrees.AssignCommand;
import com.rickauer.macbeth.abstractsyntaxtrees.BinaryExpression;
import com.rickauer.macbeth.abstractsyntaxtrees.BinaryOperatorDeclaration;
import com.rickauer.macbeth.abstractsyntaxtrees.CallCommand;
import com.rickauer.macbeth.abstractsyntaxtrees.CallExpression;
import com.rickauer.macbeth.abstractsyntaxtrees.CharacterExpression;
import com.rickauer.macbeth.abstractsyntaxtrees.CharacterLiteral;
import com.rickauer.macbeth.abstractsyntaxtrees.ConstantActualParameter;
import com.rickauer.macbeth.abstractsyntaxtrees.ConstantFormalParameter;
import com.rickauer.macbeth.abstractsyntaxtrees.Declaration;
import com.rickauer.macbeth.abstractsyntaxtrees.EmptyActualParameterSequence;
import com.rickauer.macbeth.abstractsyntaxtrees.EmptyCommand;
import com.rickauer.macbeth.abstractsyntaxtrees.EmptyFormalParameterSequence;
import com.rickauer.macbeth.abstractsyntaxtrees.FormalParameter;
import com.rickauer.macbeth.abstractsyntaxtrees.FormalParameterSequence;
import com.rickauer.macbeth.abstractsyntaxtrees.Identifier;
import com.rickauer.macbeth.abstractsyntaxtrees.IntegerExpression;
import com.rickauer.macbeth.abstractsyntaxtrees.IntegerLiteral;
import com.rickauer.macbeth.abstractsyntaxtrees.NumberDeclaration;
import com.rickauer.macbeth.abstractsyntaxtrees.Operator;
import com.rickauer.macbeth.abstractsyntaxtrees.ProcDeclaration;
import com.rickauer.macbeth.abstractsyntaxtrees.ProcFormalParameter;
import com.rickauer.macbeth.abstractsyntaxtrees.Program;
import com.rickauer.macbeth.abstractsyntaxtrees.SequentialCommand;
import com.rickauer.macbeth.abstractsyntaxtrees.SimpleVname;
import com.rickauer.macbeth.abstractsyntaxtrees.SingleActualParameterSequence;
import com.rickauer.macbeth.abstractsyntaxtrees.SingleFormalParameterSequence;
import com.rickauer.macbeth.abstractsyntaxtrees.StringDeclaration;
import com.rickauer.macbeth.abstractsyntaxtrees.SubscriptVname;
import com.rickauer.macbeth.abstractsyntaxtrees.Terminal;
import com.rickauer.macbeth.abstractsyntaxtrees.TypeDeclaration;
import com.rickauer.macbeth.abstractsyntaxtrees.TypeDenoter;
import com.rickauer.macbeth.abstractsyntaxtrees.UnaryExpression;
import com.rickauer.macbeth.abstractsyntaxtrees.VariableDeclaration;
import com.rickauer.macbeth.abstractsyntaxtrees.Visitor;
import com.rickauer.macbeth.abstractsyntaxtrees.VnameExpression;
import com.rickauer.macbeth.syntacticAnalyzer.SourcePosition;

public class Checker implements Visitor {
	
	private SymbolTable symbolTable;
	private static SourcePosition dummyPosition = new SourcePosition();
	private ErrorReporter reporter;
	
	public Checker(ErrorReporter reporter) {
		this.reporter = reporter;
		this.symbolTable = new SymbolTable();
		establishStandardEnvironment();
	}
	
	private void establishStandardEnvironment() {
		// TODO: Implement Method
	}
	
	public void check(Program ast) {
		ast.visit(this, null);
	}
	
	private void reportUndeclared(Terminal leaf) {
		reporter.reportError("\"%\" is not declared", leaf.spelling, leaf.getPosition());
	}

	@Override
	public Object visitProgram(Program ast, Object object) {
		ast.command.visit(this, null);
		return null;
	}

	@Override
	public Object visitSequentialCommand(SequentialCommand ast, Object object) {
		ast.commandOne.visit(this, null);
		ast.commandTwo.visit(this, null);
		return null;
	}

	@Override
	public Object visitIdentifier(Identifier ast, Object object) {
		Declaration binding = symbolTable.retrieve(ast.spelling);
		if (binding != null)
			ast.declaration = binding;
		return binding;
	}

	@Override
	public Object visitCallCommand(CallCommand ast, Object object) {
		
		Declaration binding = (Declaration) ast.identifier.visit(this, null);
		if (binding == null) 
			reportUndeclared(ast.identifier);
		else if (binding instanceof ProcDeclaration) {
			ast.actualParameterSequence.visit(this, ((ProcDeclaration) binding).formalParameterSequence);
		} else if (binding instanceof ProcFormalParameter) {
			ast.actualParameterSequence.visit(this, ((ProcFormalParameter) binding).formalParameterSequence);
		} else 
			reporter.reportError("\"%\" is not a procedure identifier", ast.identifier.spelling, ast.identifier.getPosition());
		
		return null;
	}

	@Override
	public Object visitAssignCommand(AssignCommand ast, Object object) {
		TypeDenoter vType = (TypeDenoter) ast.vname.visit(this, null);
		TypeDenoter eType = (TypeDenoter) ast.expression.visit(this, null);
		if (!ast.vname.variable)
			reporter.reportError("LHS of assignment is not a variable", "", ast.vname.getPosition());
		if (!eType.equals(vType))
			reporter.reportError("Assignment incompatiblity", "", ast.getPosition());
		return null;
	}

	@Override
	public Object visitConstantActualParameter(ConstantActualParameter ast, Object object) {
		FormalParameter formalParameter = (FormalParameter) object;
		TypeDenoter eType = (TypeDenoter) ast.expression.visit(this, null);
		
		if ( !(formalParameter instanceof ConstantFormalParameter))
			reporter.reportError("Constant actual parameter not expected here", "", ast.getPosition());
		else if ( !(eType.equals(((ConstantFormalParameter) formalParameter).typeDenoter)))
			reporter.reportError("Wrong type for const actual parameter", "", ast.expression.getPosition());
				
		
		return null;
	}

	@Override
	public Object visitEmptyActualParameterSequence(EmptyActualParameterSequence ast, Object object) {
		FormalParameterSequence formalParameterSequence = (FormalParameterSequence) object;
		if (! (formalParameterSequence instanceof EmptyFormalParameterSequence))
			reporter.reportError("Too few parameters", "", ast.getPosition());
		return null;
	}

	@Override
	public Object visitSingleActualParameterSequence(SingleActualParameterSequence ast, Object object) {
		FormalParameterSequence formalParameterSequence = (FormalParameterSequence) object;
		if (! (formalParameterSequence instanceof SingleFormalParameterSequence))
			reporter.reportError("Incorrect number of actual parameters", "", ast.getPosition());
		else
			ast.actualParameter.visit(this, ((SingleFormalParameterSequence) formalParameterSequence).formalParameter);
		
		return null;
	}

	@Override
	public Object visitEmptyCommand(EmptyCommand ast, Object object) {
		return null;
	}

	@Override
	public Object visitIntegerExpression(IntegerExpression ast, Object object) {
		ast.typeDenoter = StandardEnvironment.numberType;
		return ast.typeDenoter;
	}

	@Override
	public Object visitIntegerLiteral(IntegerLiteral ast, Object object) {
		return StandardEnvironment.numberType;
	}

	@Override
	public Object visitOperator(Operator operator, Object object) {
		Declaration binding = symbolTable.retrieve(operator.spelling);
		if (binding != null) 
			operator.declaration = binding;
		return binding;
	}

	@Override
	public Object visitBinaryExpression(BinaryExpression ast, Object object) {
		
		TypeDenoter expressionOneType = (TypeDenoter) ast.expressionOne.visit(this, null);
		TypeDenoter expressionTwoType = (TypeDenoter) ast.expressionTwo.visit(this, null);
		Declaration binding = (Declaration) ast.operator.visit(this, null);
		
		if (binding == null)
			reporter.reportError("\"%\" is not binary operator", ast.operator.spelling, ast.operator.getPosition());
		else {
			if (! (binding instanceof BinaryOperatorDeclaration))
				reporter.reportError("\"%\" is not a binary operator", ast.operator.spelling, ast.operator.getPosition());
			
			BinaryOperatorDeclaration binaryOpBinding = (BinaryOperatorDeclaration) binding;
			
			if (binaryOpBinding.argumentOne == StandardEnvironment.anyType) {
				if (!expressionOneType.equals(expressionTwoType))
					reporter.reportError("Incompatible argument types for \"%\"", ast.operator.spelling, ast.getPosition());
				else if (!expressionOneType.equals(binaryOpBinding.argumentOne))
					reporter.reportError("Wrong argument type for \"%\"", ast.operator.spelling, ast.expressionOne.getPosition());
				else if (!expressionTwoType.equals(binaryOpBinding.argumentTwo))
					reporter.reportError("Wrong argument type for \"%\"", ast.operator.spelling, ast.expressionTwo.getPosition());
				
				ast.typeDenoter = binaryOpBinding.result;
			}
		}
		return ast.typeDenoter;
	}

	@Override
	public Object visitCharacterExpression(CharacterExpression ast, Object object) {
		ast.typeDenoter = StandardEnvironment.charType;
		return ast.typeDenoter;
	}

	@Override
	public Object visitCharacterLiteral(CharacterLiteral ast, Object object) {
		return StandardEnvironment.charType;
	}

	@Override
	public Object visitCallExpression(CallExpression ast, Object object) {
		// TODO: Implement method
		return null;
	}

	@Override
	public Object visitUnaryExpression(UnaryExpression ast, Object object) {
		TypeDenoter expressionType = (TypeDenoter) ast.expression.visit(this, null);
		Declaration binding = (Declaration) ast.operator.visit(this, null);
		
		if (binding == null) {
			reportUndeclared(ast.operator);
			ast.typeDenoter = StandardEnvironment.errorType;
		} else if ( ! (binding instanceof UnaryOperatorDeclaration))
			reporter.reportError("\"%\" is not a unary operator", ast.operator.spelling, ast.operator.getPosition());
		else {
			UnaryOperatorDeclaration unaryBinding = (UnaryOperatorDeclaration) binding;
			if (! expressionType.equals(unaryBinding.argument)) {
				reporter.reportError("Wrong argument type for \"%\"", ast.operator.spelling, ast.operator.getPosition());
			}
			ast.typeDenoter = unaryBinding.result;
		}
		return ast.typeDenoter;
	}

	@Override
	public Object visitVnameExpression(VnameExpression ast, Object object) {
		ast.typeDenoter = (TypeDenoter) ast.vname.visit(this, null);
		return ast.typeDenoter;
	}

	@Override
	public Object visitSimpleVname(SimpleVname ast, Object object) {
		ast.variable = false;
		ast.typeDenoter = StandardEnvironment.errorType;
		Declaration binding = (Declaration) ast.identifier.visit(this, null);
		
		if (binding == null)
			reportUndeclared(ast.identifier);
		else 		// no constants and no formal parameters
			if (binding instanceof VariableDeclaration) {
				ast.typeDenoter = ((VariableDeclaration) binding).typeDenoter;
				ast.variable = true;
			} else
				reporter.reportError("\"%\" is not a variable identifier", ast.identifier.spelling, ast.identifier.getPosition());
		return ast.typeDenoter;
	}

	@Override
	public Object visitSubscriptVname(SubscriptVname ast, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitNumberDeclaration(NumberDeclaration ast, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitStringDeclaration(StringDeclaration ast, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitBinaryOperatorDeclaration(BinaryOperatorDeclaration ast, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitTypeDeclaration(TypeDeclaration ast, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitProcDeclaration(ProcDeclaration ast, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitProcFormalParameter(ProcFormalParameter ast, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitConstantFormalParameter(ConstantFormalParameter ast, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitEmptyFormalParameterSequence(EmptyFormalParameterSequence ast, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitSingleFormalParameterSequence(SingleFormalParameterSequence ast, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitUnaryOperatorDeclaration(UnaryOperatorDeclaration ast, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitVariableDeclaration(VariableDeclaration ast, Object object) {
		// TODO Auto-generated method stub
		return null;
	}
	
	// TODO implement remaining logic
}
