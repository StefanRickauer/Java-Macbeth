package com.rickauer.macbeth.syntacticAnalyzer;

import com.rickauer.macbeth.ErrorReporter;

// TODO: Refactor identifiers when class is complete

public class Parser {
	private Scanner lexeicalAnalyser;
	private ErrorReporter errorReporter;
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
			syntacticError("\"%\" expected here", Token.spell(tokenExpected));
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
	
	void syntacticError(String messageTemplate, String expectedToken) throws SyntaxError {
		SourcePosition position = currentToken.position;
		errorReporter.reportError(messageTemplate, expectedToken, position);
		throw new SyntaxError();
	}
	// TODO: Implement remaining logic
}
