package com.rickauer.macbeth.syntacticAnalyzer;

public class SyntaxError extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6471103618556394808L;

	SyntaxError() {
		super();
	}
	
	SyntaxError(String errorMessage) {
		super(errorMessage);
	}
}
