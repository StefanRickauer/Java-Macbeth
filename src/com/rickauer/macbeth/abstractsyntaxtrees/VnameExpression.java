package com.rickauer.macbeth.abstractsyntaxtrees;

import com.rickauer.macbeth.syntacticAnalyzer.SourcePosition;

public class VnameExpression extends Expression {
	
	public Vname vname;
	
	public VnameExpression(Vname vnameAST, SourcePosition position) {
		super(position);
		vname = vnameAST;
	}
	
	public Object visit(Visitor visitor, Object object) {
		return visitor.visitVnameExpression(this, object);
	}
}
