package com.rickauer.macbeth;

import com.rickauer.macbeth.abstractsyntaxtrees.Declaration;
import com.rickauer.macbeth.abstractsyntaxtrees.Operator;
import com.rickauer.macbeth.abstractsyntaxtrees.TypeDenoter;
import com.rickauer.macbeth.abstractsyntaxtrees.Visitor;
import com.rickauer.macbeth.syntacticAnalyzer.SourcePosition;

public class UnaryOperatorDeclaration extends Declaration {
	public Operator operator;
	public TypeDenoter argument, result;
	
	public UnaryOperatorDeclaration(Operator operatorAST, TypeDenoter argumentAST, TypeDenoter resultAST, SourcePosition position) {
		super(position);
		operator = operatorAST;
		argument = argumentAST;
		result = resultAST;
	}
	
	public Object visit(Visitor visitor, Object object)	 {
		return visitor.visitUnaryOperatorDeclaration(this, object);
	}
}
