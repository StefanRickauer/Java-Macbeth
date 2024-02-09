package com.rickauer.macbeth.abstractsyntaxtrees;

import com.rickauer.macbeth.syntacticAnalyzer.SourcePosition;

public class NumberDeclaration extends Declaration {
	
	public Identifier identifier;
	
	public NumberDeclaration(Identifier identifierAST, SourcePosition position) {
		super(position);
		identifier = identifierAST;
	}
	
	public Object visit(Visitor visitor, Object object) {
		return visitor.visitNumberDeclaration(this, object);
	}
}
