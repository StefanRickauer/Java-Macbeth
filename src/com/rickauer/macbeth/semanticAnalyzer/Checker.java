package com.rickauer.macbeth.semanticAnalyzer;

import com.rickauer.macbeth.ErrorReporter;
import com.rickauer.macbeth.abstractsyntaxtrees.AssignCommand;
import com.rickauer.macbeth.abstractsyntaxtrees.BinaryExpression;
import com.rickauer.macbeth.abstractsyntaxtrees.BinaryOperatorDeclaration;
import com.rickauer.macbeth.abstractsyntaxtrees.CallCommand;
import com.rickauer.macbeth.abstractsyntaxtrees.CallExpression;
import com.rickauer.macbeth.abstractsyntaxtrees.CharacterExpression;
import com.rickauer.macbeth.abstractsyntaxtrees.CharacterLiteral;
import com.rickauer.macbeth.abstractsyntaxtrees.ConstantActualParameter;
import com.rickauer.macbeth.abstractsyntaxtrees.Declaration;
import com.rickauer.macbeth.abstractsyntaxtrees.EmptyActualParameterSequence;
import com.rickauer.macbeth.abstractsyntaxtrees.EmptyCommand;
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
import com.rickauer.macbeth.abstractsyntaxtrees.StringDeclaration;
import com.rickauer.macbeth.abstractsyntaxtrees.SubscriptVname;
import com.rickauer.macbeth.abstractsyntaxtrees.Terminal;
import com.rickauer.macbeth.abstractsyntaxtrees.TypeDeclaration;
import com.rickauer.macbeth.abstractsyntaxtrees.UnaryExpression;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitConstantActualParameter(ConstantActualParameter ast, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitEmptyActualParameterSequence(EmptyActualParameterSequence ast, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitSingleActualParameterSequence(SingleActualParameterSequence ast, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitEmptyCommand(EmptyCommand ast, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitIntegerExpression(IntegerExpression ast, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitIntegerLiteral(IntegerLiteral ast, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitOperator(Operator ast, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitBinaryExpression(BinaryExpression ast, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitCharacterExpression(CharacterExpression ast, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitCharacterLiteral(CharacterLiteral ast, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitCallExpression(CallExpression ast, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitUnaryExpression(UnaryExpression ast, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitVnameExpression(VnameExpression ast, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitSimpleVname(SimpleVname ast, Object object) {
		// TODO Auto-generated method stub
		return null;
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
	
	// TODO implement remaining logic
}
