package com.rickauer.mcbeth.abstractsyntaxtrees;

import com.rickauer.mcbeth.utility.Visitor;

public abstract class AST {
	public abstract Object visit(Visitor v, Object arg);
}
