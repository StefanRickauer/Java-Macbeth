package com.rickauer.macbeth.abstractsyntaxtrees;

import com.rickauer.macbeth.syntacticAnalyzer.SourcePosition;

public class CharacterExpression extends Expression {
	
	public CharacterLiteral characterLiteral;
	
	public CharacterExpression(CharacterLiteral characterLiteralAST, SourcePosition position) {
		super(position);
		characterLiteral = characterLiteralAST;
	}
	
	@Override
	public Object visit(Visitor visitor, Object object) {
		return visitor.visitCharacterExpression(this, object);
	}
}
