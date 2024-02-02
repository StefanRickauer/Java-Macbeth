package com.rickauer.macbeth.syntacticAnalyzer;

import com.rickauer.macbeth.ErrorReporter;
import com.rickauer.macbeth.abstractsyntaxtrees.ActualParameter;
import com.rickauer.macbeth.abstractsyntaxtrees.ActualParameterSequence;
import com.rickauer.macbeth.abstractsyntaxtrees.AssignCommand;
import com.rickauer.macbeth.abstractsyntaxtrees.CallCommand;
import com.rickauer.macbeth.abstractsyntaxtrees.Command;
import com.rickauer.macbeth.abstractsyntaxtrees.ConstantActualParameter;
import com.rickauer.macbeth.abstractsyntaxtrees.EmptyActualParameterSequence;
import com.rickauer.macbeth.abstractsyntaxtrees.Expression;
import com.rickauer.macbeth.abstractsyntaxtrees.Identifier;
import com.rickauer.macbeth.abstractsyntaxtrees.Program;
import com.rickauer.macbeth.abstractsyntaxtrees.SequentialCommand;
import com.rickauer.macbeth.abstractsyntaxtrees.SingleActualParameterSequence;
import com.rickauer.macbeth.abstractsyntaxtrees.Vname;

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
		Command commandAST = null;
		
		SourcePosition commandPosition = new SourcePosition();
		start(commandPosition);
		
		switch(currentToken.kind) {
			case Token.IDENTIFIER -> {
				Identifier identifierAST = parseIdentifier();
				if(currentToken.kind == Token.LBRACKET) {
					acceptIt();
					ActualParameterSequence parameterSequenceAST = parseActualParameterSequence();
					accept(Token.RBRACKET);
					finish(commandPosition);
					commandAST = new CallCommand(identifierAST, parameterSequenceAST, commandPosition);
				} else {
					Vname vnameAST = parseRestOfVname(identifierAST);
					accept(Token.BECOMES);
					Expression expressionAST = parseExpression();
					finish(commandPosition);
					commandAST = new AssignCommand(vnameAST, expressionAST, commandPosition);
				}
			}
		}
	}
	
	Identifier parseIdentifier() throws SyntaxError {
		Identifier identifier;
		
		if(currentToken.kind == Token.IDENTIFIER) {
			previousTokenPosition = currentToken.position;
			String spelling = currentToken.spelling;
			identifier = new Identifier(spelling, previousTokenPosition);
			currentToken = lexeicalAnalyser.scanSourceCode();
		} else {
			identifier = null;
			syntacticError("identifier expected here", "");
		}
		return identifier;
	}
	
	ActualParameterSequence parseActualParameterSequence() throws SyntaxError {

		ActualParameterSequence actualParameterSequenceAST;
		SourcePosition actualPosition = new SourcePosition();
		
		start(actualPosition);
		if(currentToken.kind == Token.RBRACKET) {
			finish(actualPosition);
			actualParameterSequenceAST = new EmptyActualParameterSequence(actualPosition);
		} else {
			actualParameterSequenceAST = parseProperActualParameterSequence();
		}
		return actualParameterSequenceAST;
	}
	
	ActualParameterSequence parseProperActualParameterSequence() throws SyntaxError {
		
		ActualParameterSequence actualParameterSequenceAST = null;
		SourcePosition actualPosition = new SourcePosition();
		
		start(actualPosition);
		ActualParameter actualParameterAST = parseActualParameter();

		// Extension point: In case you want to support more than one parameters, add if-else here and add class MultipleActualParameterSequence
		finish(actualPosition);
		actualParameterSequenceAST = new SingleActualParameterSequence(actualParameterAST, actualPosition);
		
		return actualParameterSequenceAST;
	}
	
	ActualParameter parseActualParameter() throws SyntaxError {
		
		ActualParameter actualParameterAST = null;
		SourcePosition actualPosition = new SourcePosition();
		
		start(actualPosition);
		
		switch(currentToken.kind) {
			case Token.IDENTIFIER, Token.INTLITERAL, Token.CHARLITERAL, Token.OPERATOR, Token.LBRACKET, Token.RBRACKET -> {
				Expression expressionAST = parseExpression();
				finish(actualPosition);
				actualParameterAST = new ConstantActualParameter(expressionAST, actualPosition);
			}
		}
	}
	// TODO: Implement parseExpression(), parseRestOfVname() as well as the remaining logic 
}
