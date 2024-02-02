package com.rickauer.macbeth.abstractsyntaxtrees;

import com.rickauer.macbeth.syntacticAnalyzer.SourcePosition;

public abstract class Expression extends AST{
	
	public TypeDenoter typeDenoter;
	
	public Expression(SourcePosition position) {
		super(position);
		typeDenoter = null;
	}
}
