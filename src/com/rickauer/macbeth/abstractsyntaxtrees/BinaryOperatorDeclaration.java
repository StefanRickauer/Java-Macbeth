package com.rickauer.macbeth.abstractsyntaxtrees;

import com.rickauer.macbeth.syntacticAnalyzer.SourcePosition;

public class BinaryOperatorDeclaration extends Declaration {
	
	public Operator operator;
	public TypeDenoter argumentOne, argumentTwo, result;
	
	public BinaryOperatorDeclaration(Operator operatorAST, TypeDenoter argumentOneAST, TypeDenoter argumentTwoAST, TypeDenoter resultAST, SourcePosition position) {
		super(position);
		operator = operatorAST;
		argumentOne = argumentOneAST;
		argumentTwo = argumentTwoAST;
		result = resultAST;
	}
	
	@Override
	public Object visit(Visitor visitor, Object object) {
		return visitor.visitBinaryOperatorDeclaration(this, object);
	}
}
