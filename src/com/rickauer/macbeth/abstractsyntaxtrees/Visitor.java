package com.rickauer.macbeth.abstractsyntaxtrees;

public interface Visitor {
	// TODO: Implement remaining logic
	
	public abstract Object visitProgram(Program ast, Object object);
	public abstract Object visitSequentialCommand(SequentialCommand ast, Object object);
}
