package com.rickauer.macbeth.abstractsyntaxtrees;

import com.rickauer.macbeth.syntacticAnalyzer.SourcePosition;

public class CallCommand extends Command {
	
	public Identifier identifier;
	public ActualParameterSequence actualParameterSequence;
	
	public CallCommand(Identifier identifierAST, ActualParameterSequence actualParameterSequenceAST, SourcePosition position) {
		super(position);
		identifier = identifierAST;
		actualParameterSequence = actualParameterSequenceAST;
	}
	
	public Object visit(Visitor visitor, Object object) {
		return visitor.visitCallCommand(this, object);
	}
}
