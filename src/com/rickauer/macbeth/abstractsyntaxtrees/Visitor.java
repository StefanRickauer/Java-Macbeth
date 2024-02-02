package com.rickauer.macbeth.abstractsyntaxtrees;

public interface Visitor {
	// TODO: Implement remaining logic
	
	public abstract Object visitProgram(Program ast, Object object);
	public abstract Object visitSequentialCommand(SequentialCommand ast, Object object);
	public abstract Object visitIdentifier(Identifier ast, Object object);
	public abstract Object visitCallCommand(CallCommand ast, Object object);
	public abstract Object visitAssignCommand(AssignCommand ast, Object object);
	public abstract Object visitEmptyActualParameterSequence(EmptyActualParameterSequence ast, Object object);
	public abstract Object visitSingleActualParameterSequence(SingleActualParameterSequence ast, Object object);
	public abstract Object visitConstantActualParameter(ConstantActualParameter ast, Object object);
}
