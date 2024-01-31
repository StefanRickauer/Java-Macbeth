package com.rickauer.macbeth.abstractsyntaxtrees;

import com.rickauer.macbeth.syntacticAnalyzer.SourcePosition;

public abstract class Command extends AST {
	public Command (SourcePosition position) {
		super(position);
	}
}
