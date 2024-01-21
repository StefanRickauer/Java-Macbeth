package com.rickauer.mcbeth.syntacticAnalyzer;

public class Scanner {
	private char currentChar;		// first source character
	
	// Kind and spelling of the current token
	private byte currentKind;
	private StringBuffer currentSpelling;
	
	private void takeIt() {
		currentSpelling.append(currentChar);
		//currentChar = next source character;
	}
	
	private boolean isDigit(char c) {
		// returns true if character is digit
		return false; 	// dummy value
	}
	
	private boolean isLetter(char c) {
		// returns true if character is letter
		return false; 	// dummy value
	}
	
	private boolean isWhitespace(char c) {
		// returns true if character is white space
		return false; 	// dummy value
	}
	
	@SuppressWarnings("removal")
	private byte scanToken() {
		// as above
		return new Byte(currentKind); // dummy value
	}
	
	private void scanSeparator() {
		// as above
	}
	
	public Token scan() {
		while (currentChar == '!' || currentChar == ' ' || currentChar == '\n') {
			scanSeparator();
		}
		
		currentSpelling = new StringBuffer("");
		currentKind = scanToken();
		return new Token(currentKind, currentSpelling.toString());
	}
}
