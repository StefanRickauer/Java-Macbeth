package com.rickauer.macbeth.abstractsyntaxtrees;

import com.rickauer.macbeth.syntacticAnalyzer.SourcePosition;

public class SingleFormalParameterSequence extends FormalParameterSequence {
	
	public FormalParameter formalParameter;
	
	public SingleFormalParameterSequence(FormalParameter formalParameterAST, SourcePosition position) {
		super(position);
		formalParameter = formalParameterAST;
	}
	
	public Object visit(Visitor visitor, Object object) {
		return visitor.visitSingleFormalParameterSequence(this, object);
	}
	
	public boolean equals(Object formalParameterAST) {
		if (formalParameterAST instanceof SingleFormalParameterSequence) {
			SingleFormalParameterSequence singleFormalParameterSequenceAST = (SingleFormalParameterSequence) formalParameterAST;
			return formalParameter.equals(singleFormalParameterSequenceAST);
		} else {
			return false;
		}
	}
}
