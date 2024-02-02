package com.rickauer.macbeth.abstractsyntaxtrees;

import com.rickauer.macbeth.syntacticAnalyzer.SourcePosition;

public class AssignCommand extends Command {
	
	public Vname vname;
	public Expression expression;
	
	public AssignCommand(Vname vnameAST, Expression expressionAST, SourcePosition position) {
		super(position);
		vname = vnameAST;
		expression = expressionAST;
	}
	
	public Object visit(Visitor visitor, Object object) {
		return visitor.visitAssignCommand(this, object);
	}
}
