package com.rickauer.macbeth.abstractsyntaxtrees;

import com.rickauer.macbeth.syntacticAnalyzer.SourcePosition;

public class Identifier extends Terminal {
	
	public TypeDenoter type;
	public AST declaration;
	
	public Identifier(String spelling, SourcePosition position) {
		super(spelling, position);
		type = null;
		declaration = null;
	}
	
	public Object visit(Visitor visitor, Object object) {
		return visitor.visitIdentifier(this, object);
	}
}
