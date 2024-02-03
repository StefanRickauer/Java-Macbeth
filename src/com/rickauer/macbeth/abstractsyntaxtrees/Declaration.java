package com.rickauer.macbeth.abstractsyntaxtrees;

import com.rickauer.macbeth.syntacticAnalyzer.SourcePosition;

public abstract class Declaration extends AST {
	
	public boolean duplicated;
	
	public Declaration(SourcePosition position) {
		super(position);
		duplicated = false;
	}
}
