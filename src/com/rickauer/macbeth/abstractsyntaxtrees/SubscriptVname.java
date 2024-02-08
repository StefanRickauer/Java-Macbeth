package com.rickauer.macbeth.abstractsyntaxtrees;

import com.rickauer.macbeth.syntacticAnalyzer.SourcePosition;

public class SubscriptVname extends Vname {
	
	public Expression expression;
	public Vname vname;
	
	public SubscriptVname(Vname vnameAST, Expression expressionAST, SourcePosition position) {
		super(position);
		expression = expressionAST;
		vname = vnameAST;
	}
	
	public Object visit(Visitor visitor, Object object) {
		return visitor.visitSubscriptVname(this, object);
	}
}
