package com.rickauer.macbeth.abstractsyntaxtrees;

import com.rickauer.macbeth.syntacticAnalyzer.SourcePosition;

public class ProcDeclaration extends Declaration {
	
	public Identifier identifier;
	public FormalParameterSequence formalParameterSequence;
	public Command command;
	
	public ProcDeclaration(Identifier identifier, FormalParameterSequence formalParameterSequenceAST, Command commandAST, SourcePosition position) {
		super(position);
		this.identifier = identifier;
		formalParameterSequence = formalParameterSequenceAST;
		command = commandAST;
	}
	
	public Object visit(Visitor visitor, Object object) {
		return visitor.visitProcDeclaration(this, object);
	}
}
