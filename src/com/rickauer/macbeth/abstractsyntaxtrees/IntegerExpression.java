package com.rickauer.macbeth.abstractsyntaxtrees;

import com.rickauer.macbeth.syntacticAnalyzer.SourcePosition;

public class IntegerExpression extends Expression {
	
	public IntegerLiteral integerLiteral;
	
	public IntegerExpression(IntegerLiteral integerLiteralAST, SourcePosition position) {
		super(position);
		integerLiteral = integerLiteralAST;
	}
	
	public Object visit(Visitor visitor, Object object) {
		return visitor.visitIntegerExpression(this, object);
	}
}
