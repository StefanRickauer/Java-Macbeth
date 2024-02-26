package com.rickauer.macbeth.abstractsyntaxtrees;

import com.rickauer.macbeth.syntacticAnalyzer.SourcePosition;

public class VariableDeclaration extends Declaration {
	
	public Identifier identifier;
	public TypeDenoter typeDenoter;
	
	public VariableDeclaration(Identifier ast, TypeDenoter typeDenoterAST, SourcePosition position) {
		super(position);
		identifier = ast;
		typeDenoter = typeDenoterAST;
	}
	
	@Override
	public Object visit(Visitor visitor, Object object) {
		return visitor.visitVariableDeclaration(this, object);
	}
}
