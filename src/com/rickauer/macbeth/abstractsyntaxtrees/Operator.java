package com.rickauer.macbeth.abstractsyntaxtrees;

import com.rickauer.macbeth.syntacticAnalyzer.SourcePosition;

public class Operator extends Terminal {
	
	public Declaration declaration;
	
	public Operator(String spelling, SourcePosition position) {
		super(spelling, position);
		declaration = null;
	}
	
	@Override
	public Object visit(Visitor visitor, Object object) {
		return visitor.visitOperator(this, object);
	}
}
