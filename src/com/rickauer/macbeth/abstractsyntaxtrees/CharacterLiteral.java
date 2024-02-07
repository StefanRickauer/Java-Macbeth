package com.rickauer.macbeth.abstractsyntaxtrees;

import com.rickauer.macbeth.syntacticAnalyzer.SourcePosition;

public class CharacterLiteral extends Terminal {
	
	public CharacterLiteral(String spelling, SourcePosition position) {
		super(spelling, position);
	}
	
	@Override
	public Object visit(Visitor visitor, Object object) {
		return visitor.visitCharacterLiteral(this, object);
	}
}
