package com.rickauer.macbeth.abstractsyntaxtrees;

import com.rickauer.macbeth.syntacticAnalyzer.SourcePosition;

public class SequentialCommand extends Command {
	
	public Command commandOne, commandTwo;
	
	public SequentialCommand(Command commandOneAST, Command commandTwoAST, SourcePosition position) {
		super (position);
		commandOne = commandOneAST;
		commandTwo = commandTwoAST;
	}
	
	@Override
	public Object visit(Visitor visitor, Object object) {
		return visitor.visitSequentialCommand(this, object);
	}
}
