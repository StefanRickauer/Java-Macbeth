package com.rickauer.macbeth.abstractsyntaxtrees;

import com.rickauer.macbeth.syntacticAnalyzer.SourcePosition;

public abstract class FormalParameter extends Declaration {
	
	public FormalParameter(SourcePosition position) {
		super(position);
	}
	
	public abstract boolean equals(Object formalParameterAST);
}
