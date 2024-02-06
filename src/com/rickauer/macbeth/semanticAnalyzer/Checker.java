package com.rickauer.macbeth.semanticAnalyzer;

import com.rickauer.macbeth.abstractsyntaxtrees.AssignCommand;
import com.rickauer.macbeth.abstractsyntaxtrees.BinaryExpression;
import com.rickauer.macbeth.abstractsyntaxtrees.CallCommand;
import com.rickauer.macbeth.abstractsyntaxtrees.CharacterExpression;
import com.rickauer.macbeth.abstractsyntaxtrees.ConstantActualParameter;
import com.rickauer.macbeth.abstractsyntaxtrees.EmptyActualParameterSequence;
import com.rickauer.macbeth.abstractsyntaxtrees.EmptyCommand;
import com.rickauer.macbeth.abstractsyntaxtrees.Identifier;
import com.rickauer.macbeth.abstractsyntaxtrees.IntegerExpression;
import com.rickauer.macbeth.abstractsyntaxtrees.IntegerLiteral;
import com.rickauer.macbeth.abstractsyntaxtrees.Operator;
import com.rickauer.macbeth.abstractsyntaxtrees.Program;
import com.rickauer.macbeth.abstractsyntaxtrees.SequentialCommand;
import com.rickauer.macbeth.abstractsyntaxtrees.SingleActualParameterSequence;
import com.rickauer.macbeth.abstractsyntaxtrees.Visitor;

public class Checker implements Visitor {

	@Override
	public Object visitProgram(Program ast, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitSequentialCommand(SequentialCommand ast, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitIdentifier(Identifier ast, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitCallCommand(CallCommand ast, Object object) {
		// TODO Auto-generated method stub
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
	
	// TODO implement remaining logic
}
