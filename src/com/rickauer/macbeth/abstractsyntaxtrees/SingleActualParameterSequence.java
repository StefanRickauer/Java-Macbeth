package com.rickauer.macbeth.abstractsyntaxtrees;

import com.rickauer.macbeth.syntacticAnalyzer.SourcePosition;

public class SingleActualParameterSequence extends ActualParameterSequence {
	
	public ActualParameter actualParameter;
	
	public SingleActualParameterSequence(ActualParameter actualParameterAST, SourcePosition position) {
		super(position);
		actualParameter = actualParameterAST;
	}
	
	public Object visit(Visitor visitor, Object object) {
		return visitor.visitSingleActualParameterSequence(this, object);
	}
}
