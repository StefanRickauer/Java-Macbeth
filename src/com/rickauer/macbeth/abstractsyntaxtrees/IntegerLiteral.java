package com.rickauer.macbeth.abstractsyntaxtrees;

import com.rickauer.macbeth.syntacticAnalyzer.SourcePosition;

public class IntegerLiteral extends Terminal {
	
	public IntegerLiteral(String spelling, SourcePosition position) {
		super(spelling, position);
	}
	
	public Object visit(Visitor visitor, Object object) {
		return visitor.visitIntegerLiteral(this, object);
	}
}
