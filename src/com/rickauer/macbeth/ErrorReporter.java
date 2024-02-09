package com.rickauer.macbeth;

import com.rickauer.macbeth.syntacticAnalyzer.SourcePosition;

public class ErrorReporter {
	int numberOfErrors;
	
	public ErrorReporter() {
		numberOfErrors = 0;
	}
	
	public void reportError(String message, String tokenName, SourcePosition position) {
		System.out.println("ERROR: ");
		
		for (int i = 0; i < message.length(); i++) {
			if (message.charAt(i) == '%') {
				System.out.print(tokenName);
			} else {
				System.out.print(message.charAt(i));
			}
		}
		System.out.println(" " + position.start + ".." + position.finish );
		numberOfErrors++;
	}
	
	public void reportRestriction(String message) {
		System.out.println("RESTRICTION: " + message);
	}
}
