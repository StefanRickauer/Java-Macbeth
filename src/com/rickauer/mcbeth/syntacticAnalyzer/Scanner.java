package com.rickauer.mcbeth.syntacticAnalyzer;

public class Scanner {
	
	private SourceFile sourceFile;
	
	private char currentCharacter;		
	private StringBuffer currentSpelling;
	private boolean isScanningToken;
	
	boolean isLetter(char symbol) {
		return ((symbol >= 'a' && symbol <= 'z') || (symbol >= 'A' && symbol <= 'Z'));
	}
	
	boolean isDigit(char symbol) {
		return (symbol >= '0' && symbol <= '9');
	}
	
	boolean isOperator(char symbol) {
		return (symbol == '<' || symbol == '-' || symbol == '+' || symbol == '*');
	}
	
	public Scanner(SourceFile sourceFile) {
		this.sourceFile = sourceFile;
		currentCharacter = sourceFile.fetchNextCharacter();
	}
	
	private void consumeCharacter() {
		if (isScanningToken) {
			currentSpelling.append(currentCharacter);
		}
		currentCharacter = sourceFile.fetchNextCharacter();
	}
	
	private void scanSeparator() {
		switch (currentCharacter) {
		case ' ':
		case '\n':
		case '\r':
		case '\t':
			consumeCharacter();
			break;
		default:	
			throw new IllegalStateException("Did not recognize separator.");
		}
	}
	
	private int scanToken() {
		switch (currentCharacter) {
		case 'a': case 'b': case 'c': case 'd': case 'e': case 'f': case 'g': case 'h': case 'i': case 'j':
		case 'k': case 'l': case 'm': case 'n': case 'o': case 'p': case 'q': case 'r': case 's': case 't':
		case 'u': case 'v': case 'w': case 'x': case 'y': case 'z':
		case 'A': case 'B': case 'C': case 'D': case 'E': case 'F': case 'G': case 'H': case 'I': case 'J':
		case 'K': case 'L': case 'M': case 'N': case 'O': case 'P': case 'Q': case 'R': case 'S': case 'T':
		case 'U': case 'V': case 'W': case 'X': case 'Y': case 'Z':
			consumeCharacter();
		while (isLetter(currentCharacter) || isDigit(currentCharacter))
			consumeCharacter();
		return Token.IDENTIFIER;
		
		case '0': case '1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9':
			consumeCharacter();
			while(isDigit(currentCharacter))
				consumeCharacter();
			return Token.INTLITERAL;
			
		case '+': case '*':
			consumeCharacter();
			return Token.OPERATOR;
			
		case '\'':
			consumeCharacter();
			consumeCharacter();	// the quote
			if (currentCharacter == '\'') {
				consumeCharacter();
				return Token.CHARLITERAL;
			} else {
				return Token.ERROR;
			}
			
		case '.':
			consumeCharacter();
			return Token.DOT;
			
		case '<':
			consumeCharacter();
			if (currentCharacter == '-') {
				consumeCharacter();
				return Token.BECOMES;
			}
			return Token.ERROR;
			
		case '[':
			consumeCharacter();
			return Token.LBRACKET;
			
		case ']':
			consumeCharacter();
			return Token.RBRACKET;
			
		case SourceFile.EOT:
			return Token.EOT;
			
		default:
			consumeCharacter();
			return Token.ERROR;
		}
	}
	
	public Token scan() {
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
}
