package com.rickauer.macbeth.abstractsyntaxtrees;

import com.rickauer.macbeth.syntacticAnalyzer.SourcePosition;

public abstract class Terminal extends AST{
	
	public String spelling;
	
	public Terminal(String spelling, SourcePosition position) {
		super(position);
		this.spelling = spelling;
	}
}
