package com.rickauer.macbeth.abstractsyntaxtrees;

import com.rickauer.macbeth.syntacticAnalyzer.SourcePosition;

public class ConstantFormalParameter extends FormalParameter {
	
	public Identifier identifier;
	public TypeDenoter typeDenoter;
	
	public ConstantFormalParameter(Identifier identifierAST, TypeDenoter typeDenoterAST, SourcePosition position) {
		super(position);
		identifier = identifierAST;
		typeDenoter = typeDenoterAST;
	}
	
	public Object visit(Visitor visitor, Object object) {
		return visitor.visitConstantFormalParameter(this, object);
	}
	
	public boolean equals(Object formalParameterAST) {
		if (formalParameterAST instanceof ConstantFormalParameter) {
			ConstantFormalParameter constantFormalParameterAST = (ConstantFormalParameter) formalParameterAST;
			return typeDenoter.equals(constantFormalParameterAST);
		} else {
			return false;
		}
	}
}
