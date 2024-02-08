package com.rickauer.macbeth.abstractsyntaxtrees;

import com.rickauer.macbeth.syntacticAnalyzer.SourcePosition;

public class SimpleVname extends Vname {
	
	public Identifier identifier;
	
	public SimpleVname(Identifier identifierAST, SourcePosition position) {
		super(position);
		identifier = identifierAST;
	}
	
	public Object visit(Visitor visitor, Object object) {
		return visitor.visitSimpleVname(this, object);
	}
}
