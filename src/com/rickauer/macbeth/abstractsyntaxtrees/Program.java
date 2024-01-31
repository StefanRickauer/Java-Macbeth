package com.rickauer.macbeth.abstractsyntaxtrees;

import com.rickauer.macbeth.syntacticAnalyzer.SourcePosition;

public class Program extends AST {
	
	public Command command;
	
	public Program(Command cAST, SourcePosition position) {
		super (position);
		command = cAST;
	}
	
	public Object visit(Visitor visitor, Object o) {
		return visitor.visitProgram(this, o);
	}
}
