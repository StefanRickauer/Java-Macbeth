package com.rickauer.macbeth.syntacticAnalyzer;


public final class Scanner {
	
	private SourceFile sourceFile;
	private char currentCharacter;		
	private StringBuffer currentSpelling;
	private boolean isScanningToken;
	
	
	public Scanner(SourceFile sourceFile) {
		this.sourceFile = sourceFile;
		currentCharacter = sourceFile.fetchNextCharacter();
	}
	
	public Token scanSourceCode() {
		Token token; 
		SourcePosition position;
		int kind;
		
		isScanningToken = false;
		while (currentCharacter == ' ' || currentCharacter == '\n' || currentCharacter == '\r' || currentCharacter == '\t') {
			scanSeparator();
		}
		
		isScanningToken = true;
		currentSpelling = new StringBuffer("");
		position = new SourcePosition();
		position.start = sourceFile.getCurrentLine();
		
		kind = scanToken();
		
		position.finish = sourceFile.getCurrentLine();
		token = new Token(kind, currentSpelling.toString(), position);
		
		return token;
	}
	
	private void scanSeparator() {
		switch (currentCharacter) {
		case ' ', '\n', '\r', '\t' -> consumeCharacter();
		default -> throw new AssertionError();
		}
	}
	
	private void consumeCharacter() {
		if (isScanningToken) {
			currentSpelling.append(currentCharacter);
		}
		currentCharacter = sourceFile.fetchNextCharacter();
	}
		
	private int scanToken() {
		switch (currentCharacter) {
		
		case 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
		'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
		'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' -> {
			consumeCharacter();
			while (isLetter(currentCharacter) || isDigit(currentCharacter))
				consumeCharacter();
			return Token.IDENTIFIER;
			}
			
		case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> {
			consumeCharacter();
			while(isDigit(currentCharacter))
				consumeCharacter();
			return Token.INTLITERAL;
			}
			
		case '+', '*' -> {
			consumeCharacter();
			while(isOperator(currentCharacter))
				consumeCharacter();
			return Token.OPERATOR;
			}
			
		case '\'' -> {
			consumeCharacter();
			consumeCharacter();	// the quote
			if (currentCharacter == '\'') {
				consumeCharacter();
				return Token.CHARLITERAL;
				} else {
				return Token.ERROR;
				}
			}
			
		case '.' -> {
			consumeCharacter();
			return Token.DOT;
			}
			
		case '<' -> {
			consumeCharacter();
			if (currentCharacter == '-') {
				consumeCharacter();
				return Token.BECOMES;
				}
			return Token.ERROR;
			}
			
		case '[' -> {
			consumeCharacter();
			return Token.LBRACKET;
			}
			
		case ']' -> {
			consumeCharacter();
			return Token.RBRACKET;
			}
			
		case SourceFile.EOT -> { 
			return Token.EOT; 
			}
			
		default -> {
			consumeCharacter();
			return Token.ERROR; 
			}
		}
	}
	
	private static boolean isLetter(char symbol) {
		return ((symbol >= 'a' && symbol <= 'z') || (symbol >= 'A' && symbol <= 'Z'));
	}
	
	private static boolean isDigit(char symbol) {
		return (symbol >= '0' && symbol <= '9');
	}
	
	private static boolean isOperator(char symbol) {
		return (symbol == '+' || symbol == '*');
	}
}
