package com.rickauer.macbeth.abstractsyntaxtrees;

import com.rickauer.macbeth.syntacticAnalyzer.SourcePosition;

public abstract class Vname extends AST {
	
	public boolean variable, indexed;
	public int offset;
	public TypeDenoter typeDenoter;
	
	public Vname(SourcePosition position) {
		super(position);
		variable = false;
		typeDenoter = null;
	}
}
