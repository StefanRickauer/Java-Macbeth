package com.rickauer.mcbeth.syntacticAnalyzer;

public class Scanner {
	
	private SourceFile sourceFile;
	
	private char currentChar;		
	private StringBuffer currentSpelling;
	private boolean isScanningToken;
	
	private boolean isLetter(char c) {
		return ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'));
	}
	
	private boolean isDigit(char c) {
		return (c >= '0' && c <= '9');
	}
	
	private boolean isOperator(char c) {
		return (c == '<' || c == '-' || c == '+' || c == '*');
	}
	
	public Scanner(SourceFile sourceFile) {
		this.sourceFile = sourceFile;
		currentChar = sourceFile.getSource();
	}
	
	private void takeIt() {
		if (isScanningToken) {
			currentSpelling.append(currentChar);
		}
		currentChar = sourceFile.getSource();
	}
	
	private void scanSeparator() {
		switch (currentChar) {
		case ' ':
		case '\n':
		case '\r':
		case '\t':
			takeIt();
			break;
		}
	}
	
	private int scanToken() {
		switch (currentChar) {
		case 'a': case 'b': case 'c': case 'd': case 'e': case 'f': case 'g': case 'h': case 'i': case 'j':
		case 'k': case 'l': case 'm': case 'n': case 'o': case 'p': case 'q': case 'r': case 's': case 't':
		case 'u': case 'v': case 'w': case 'x': case 'y': case 'z':
		case 'A': case 'B': case 'C': case 'D': case 'E': case 'F': case 'G': case 'H': case 'I': case 'J':
		case 'K': case 'L': case 'M': case 'N': case 'O': case 'P': case 'Q': case 'R': case 'S': case 'T':
		case 'U': case 'V': case 'W': case 'X': case 'Y': case 'Z':
			takeIt();
		while (isLetter(currentChar) || isDigit(currentChar))
			takeIt();
		return Token.IDENTIFIER;
		
		case '0': case '1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9':
			takeIt();
			while(isDigit(currentChar))
				takeIt();
			return Token.INTLITERAL;
			
		case '+': case '*':
			takeIt();
			return Token.OPERATOR;
			
		case '.':
			takeIt();
			return Token.DOT;
			
		case '<':
			takeIt();
			if (currentChar == '-') {
				takeIt();
				return Token.BECOMES;
			}
			return Token.ERROR;
			
		case '[':
			takeIt();
			return Token.LBRACKET;
			
		case ']':
			takeIt();
			return Token.RBRACKET;
			
		case SourceFile.EOT:
			return Token.EOT;
			
		default:
			takeIt();
			return Token.ERROR;
		}
	}
	
	public Token scan() {
		Token token; 
		int kind;
		
		isScanningToken = false;
		while (currentChar == ' ' || currentChar == '\n' || currentChar == '\r' || currentChar == '\t') {
			scanSeparator();
		}
		
		isScanningToken = true;
		currentSpelling = new StringBuffer("");
		
		kind = scanToken();
		
		token = new Token(kind, currentSpelling.toString());
		
		return token;
	}
}
