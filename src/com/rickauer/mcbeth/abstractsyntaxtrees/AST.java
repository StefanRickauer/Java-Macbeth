package com.rickauer.mcbeth.abstractsyntaxtrees;

import com.rickauer.mcbeth.utility.Visitor;
// TODO add subclasses
public abstract class AST {
	public abstract Object visit(Visitor v, Object arg);
}
