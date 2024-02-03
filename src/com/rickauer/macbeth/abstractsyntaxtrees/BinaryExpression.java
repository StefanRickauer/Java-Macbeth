package com.rickauer.macbeth.abstractsyntaxtrees;

import com.rickauer.macbeth.syntacticAnalyzer.SourcePosition;

public class BinaryExpression extends Expression {
	
	public Expression expressionOne, expressionTwo;
	public Operator operator;
	
	public BinaryExpression(Expression expressionOneAST, Operator operatorAST, Expression expressionTwoAST, SourcePosition position) {
		super(position);
		operator = operatorAST;
		expressionOne = expressionOneAST;
		expressionTwo = expressionTwoAST;
	}
	
	public Object visit(Visitor visitor, Object object) {
		return visitor.visitBinaryExpression(this, object);
	}
}
