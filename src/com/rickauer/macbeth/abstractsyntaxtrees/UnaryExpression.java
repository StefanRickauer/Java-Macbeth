package com.rickauer.macbeth.abstractsyntaxtrees;

import com.rickauer.macbeth.syntacticAnalyzer.SourcePosition;

public class UnaryExpression extends Expression {
	
	public Expression expression;
	public Operator operator;
	
	public UnaryExpression(Operator operatorAST, Expression expressionAST, SourcePosition position) {
		super(position);
		operator = operatorAST;
		expression = expressionAST;
	}
	
	public Object visit(Visitor visitor, Object object) {
		return visitor.visitUnaryExpression(this, object);
	}
}
