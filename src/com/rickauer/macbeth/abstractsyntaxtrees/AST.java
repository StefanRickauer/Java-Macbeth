package com.rickauer.macbeth.abstractsyntaxtrees;

import com.rickauer.macbeth.utility.Visitor;
// TODO add subclasses
public abstract class AST {
	public abstract Object visit(Visitor v, Object arg);
}
