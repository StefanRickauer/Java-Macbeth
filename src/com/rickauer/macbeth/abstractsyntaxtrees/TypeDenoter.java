package com.rickauer.macbeth.abstractsyntaxtrees;

import com.rickauer.macbeth.syntacticAnalyzer.SourcePosition;

public abstract class TypeDenoter extends AST {
	
	public TypeDenoter(SourcePosition position) {
		super(position);
	}
	
	public abstract boolean equals(Object object);
}
