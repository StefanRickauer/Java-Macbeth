package com.rickauer.macbeth.abstractsyntaxtrees;

import com.rickauer.macbeth.codegenerator.RuntimeEntity;
import com.rickauer.macbeth.syntacticAnalyzer.SourcePosition;

public abstract class AST {
	
	private SourcePosition position;
	public RuntimeEntity entity;

	public abstract Object visit(Visitor v, Object arg);
	
	public AST (SourcePosition position) {
		this.position = position;
		entity = null;
	}
	
	public SourcePosition getPosition() {
		return position;
	}
}
