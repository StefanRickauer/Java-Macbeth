package com.rickauer.macbeth.abstractsyntaxtrees;

import com.rickauer.macbeth.syntacticAnalyzer.SourcePosition;

public class CallExpression extends Expression {
	
	public Identifier identifier;
	public ActualParameterSequence actualParameterSequence;
	
	public CallExpression(Identifier identifierAST, ActualParameterSequence actualParameterSequenceAST, SourcePosition position) {
		super(position);
		identifier = identifierAST;
		actualParameterSequence = actualParameterSequenceAST;
	}
	
	@Override
	public Object visit(Visitor visitor, Object object) {
		return visitor.visitCallExpression(this, object);
	}
}
