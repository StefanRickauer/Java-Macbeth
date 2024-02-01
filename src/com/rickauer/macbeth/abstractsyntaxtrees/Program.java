package com.rickauer.macbeth.abstractsyntaxtrees;

import com.rickauer.macbeth.syntacticAnalyzer.SourcePosition;

public class Program extends AST {
	
	public Command command;
	
	public Program(Command cAST, SourcePosition position) {
		super (position);
		command = cAST;
	}
	
	@Override
	public Object visit(Visitor visitor, Object object) {
		return visitor.visitProgram(this, object);
	}
}
