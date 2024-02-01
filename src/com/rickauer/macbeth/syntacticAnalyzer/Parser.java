package com.rickauer.macbeth.syntacticAnalyzer;

import com.rickauer.macbeth.ErrorReporter;
import com.rickauer.macbeth.abstractsyntaxtrees.Command;
import com.rickauer.macbeth.abstractsyntaxtrees.Program;
import com.rickauer.macbeth.abstractsyntaxtrees.SequentialCommand;

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
	
	public Program parseProgram() {
		
		previousTokenPosition.start = 0;
		previousTokenPosition.finish = 0;
		currentToken = lexeicalAnalyser.scanSourceCode();
		
		try {
			Command cAST = parseCommand();
			Program programAST = new Program(cAST, previousTokenPosition);
			if (currentToken.kind != Token.EOT) {
				syntacticError("\"%\" not expected after end of program", currentToken.spelling);
			}
			return programAST;
		} catch (SyntaxError s) {
			return null;
		}
	}
	
	Command parseCommand() throws SyntaxError {
		Command commandAST = null;
		
		SourcePosition commandPosition = new SourcePosition();
		start(commandPosition);
		commandAST = parseSingleCommand();
		while(currentToken.kind == Token.DOT) {
			acceptIt();
			Command c2AST = parseSingleCommand();
			finish(commandPosition);
			commandAST = new SequentialCommand(commandAST, c2AST, commandPosition);
		}
		return commandAST;
	}
	
	Command parseSingleCommand() throws SyntaxError {
		
		// TODO: Implement method
		
		return null;	// get rid of compiler warning until method is implemented
	}
	// TODO: Implement remaining logic
	
	
}
