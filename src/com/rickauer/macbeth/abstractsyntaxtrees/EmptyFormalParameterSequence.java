package com.rickauer.macbeth.abstractsyntaxtrees;

import com.rickauer.macbeth.syntacticAnalyzer.SourcePosition;

public class EmptyFormalParameterSequence extends FormalParameterSequence {
	
	public EmptyFormalParameterSequence(SourcePosition position) {
		super(position);
	}
	
	public Object visit(Visitor visitor, Object object) {
		return visitor.visitEmptyFormalParameterSequence(this, object);
	}
	
	public boolean equals(Object formalParameterSequenceAST) {
		return (formalParameterSequenceAST instanceof EmptyFormalParameterSequence);
	}
}
