package com.rickauer.macbeth.abstractsyntaxtrees;

import com.rickauer.macbeth.syntacticAnalyzer.SourcePosition;

public abstract class FormalParameterSequence extends AST {
	
	public FormalParameterSequence(SourcePosition position) {
		super(position);
	}
	
	public abstract boolean equals(Object formalParameterSequenceAST);
}
