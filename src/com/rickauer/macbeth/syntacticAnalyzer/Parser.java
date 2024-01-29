package com.rickauer.macbeth.syntacticAnalyzer;

// TODO: Refactor identifiers when class is complete

public class Parser {
	private Scanner lexeicalAnalyser;
	private Token currentToken;
	private SourcePosition previousTokenPosition;
	
	public Parser(Scanner lexer) {
		this.lexeicalAnalyser = lexer;
		previousTokenPosition = new SourcePosition();
	}
	
	void accept(int tokenExpected) throws SyntaxError {
		if (currentToken.kind == tokenExpected) {
			previousTokenPosition = currentToken.position;
			currentToken = lexeicalAnalyser.scanSourceCode();
		} else {
			throw new SyntaxError("Expected: " + Token.spell(tokenExpected));
		}
	}
	
	void acceptIt() {
		previousTokenPosition = currentToken.position;
		currentToken = lexeicalAnalyser.scanSourceCode();
	}
	
	void start(SourcePosition position) {
		position.start = currentToken.position.start;
	}
	
	void finish(SourcePosition position) {
		position.finish = previousTokenPosition.finish;
	}
	
	// TODO: Implement remaining logic
}
