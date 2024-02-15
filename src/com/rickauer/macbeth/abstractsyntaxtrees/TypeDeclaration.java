package com.rickauer.macbeth.abstractsyntaxtrees;

import com.rickauer.macbeth.syntacticAnalyzer.SourcePosition;

public class TypeDeclaration extends Declaration {
	
	public Identifier identifier;
	public TypeDenoter typeDenoter;
	
	public TypeDeclaration(Identifier identifierAST, TypeDenoter typeDenoterAST, SourcePosition position) {
		super(position);
		identifier = identifierAST;
		typeDenoter = typeDenoterAST;
	}
	
	@Override
	public Object visit(Visitor visitor, Object object) {
		return visitor.visitTypeDeclaration(this, object);
	}
}
