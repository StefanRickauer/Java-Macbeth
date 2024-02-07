package com.rickauer.macbeth.abstractsyntaxtrees;

import com.rickauer.macbeth.syntacticAnalyzer.SourcePosition;

public class ConstantActualParameter extends ActualParameter {
	
	public Expression expression;
	
	public ConstantActualParameter(Expression expressionAST, SourcePosition position) {
		super(position);
		expression = expressionAST;
	}
	
	@Override
	public Object visit(Visitor visitor, Object object) {
		return visitor.visitConstantActualParameter(this, object);
	}
}
