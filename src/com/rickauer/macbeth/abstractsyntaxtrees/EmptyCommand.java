package com.rickauer.macbeth.abstractsyntaxtrees;

import com.rickauer.macbeth.syntacticAnalyzer.SourcePosition;

public class EmptyCommand extends Command {
	
	public EmptyCommand(SourcePosition position) {
		super(position);
	}
	
	public Object visit(Visitor visitor, Object object) {
		return visitor.visitEmptyCommand(this, object);
	}
}
