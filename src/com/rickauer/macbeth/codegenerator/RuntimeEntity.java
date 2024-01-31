package com.rickauer.macbeth.codegenerator;

public abstract class RuntimeEntity {
	
	public final static int maxRoutineLevel = 7;
	
	public int size;
	
	public RuntimeEntity() {
		size = 0;
	}
	
	public RuntimeEntity(int size) {
		this.size = size;
	}
}
