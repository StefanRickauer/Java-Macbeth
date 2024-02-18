package com.rickauer.macbeth.abstractsyntaxtrees;

import com.rickauer.macbeth.syntacticAnalyzer.SourcePosition;

public class ProcFormalParameter extends FormalParameter{
	
	public Identifier identifier;
	public FormalParameterSequence formalParameterSequence;
	
	public ProcFormalParameter(Identifier identifierAST, FormalParameterSequence formalParameterSequenceAST, SourcePosition position) {
		super(position);
		identifier = identifierAST;
		formalParameterSequence = formalParameterSequenceAST;
	}
	
	public Object visit(Visitor visitor, Object object) {
		return visitor.visitProcFormalParameter(this, object);
	}
	
	public boolean equals(Object formalParameterAST) {
		if(formalParameterAST instanceof ProcFormalParameter) {
			ProcFormalParameter procFormalParameterAST = (ProcFormalParameter) formalParameterAST;
			return formalParameterSequence.equals(procFormalParameterAST);
		} else
			return false;
	}
}
