package com.rickauer.macbeth.abstractsyntaxtrees;

import com.rickauer.macbeth.syntacticAnalyzer.SourcePosition;

public class EmptyActualParameterSequence extends ActualParameterSequence {
	
	public EmptyActualParameterSequence(SourcePosition position) {
		super(position);
	}
	
	public Object visit(Visitor visitor, Object object) {
		return visitor.visitEmptyActualParameterSequence(this, object);
	}
}
