package com.rickauer.macbeth.abstractsyntaxtrees;

import com.rickauer.macbeth.syntacticAnalyzer.SourcePosition;

public class StringDeclaration extends Declaration {
	
	public Identifier identifier;
	
	public StringDeclaration(Identifier identifierAST, SourcePosition position) {
		super(position);
		identifier = identifierAST;
	}
	
	public Object visit(Visitor visitor, Object object) {
		return visitor.visitStringDeclaration(this, object);
	}
}
