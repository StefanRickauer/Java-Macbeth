package com.rickauer.macbeth.syntacticAnalyzer;

import com.rickauer.macbeth.ErrorReporter;
import com.rickauer.macbeth.abstractsyntaxtrees.ActualParameter;
import com.rickauer.macbeth.abstractsyntaxtrees.ActualParameterSequence;
import com.rickauer.macbeth.abstractsyntaxtrees.AssignCommand;
import com.rickauer.macbeth.abstractsyntaxtrees.BinaryExpression;
import com.rickauer.macbeth.abstractsyntaxtrees.CallCommand;
import com.rickauer.macbeth.abstractsyntaxtrees.CharacterExpression;
import com.rickauer.macbeth.abstractsyntaxtrees.CharacterLiteral;
import com.rickauer.macbeth.abstractsyntaxtrees.Command;
import com.rickauer.macbeth.abstractsyntaxtrees.ConstantActualParameter;
import com.rickauer.macbeth.abstractsyntaxtrees.EmptyActualParameterSequence;
import com.rickauer.macbeth.abstractsyntaxtrees.EmptyCommand;
import com.rickauer.macbeth.abstractsyntaxtrees.Expression;
import com.rickauer.macbeth.abstractsyntaxtrees.Identifier;
import com.rickauer.macbeth.abstractsyntaxtrees.IntegerExpression;
import com.rickauer.macbeth.abstractsyntaxtrees.IntegerLiteral;
import com.rickauer.macbeth.abstractsyntaxtrees.Operator;
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
			
			case Token.DOT, Token.EOT -> {
				finish(commandPosition);
				commandAST = new EmptyCommand(commandPosition);
			}
			
			default -> {
				syntacticError("\"%\" cannot start a command", currentToken.spelling);
			}
		}
		return commandAST;
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
			
			default -> {
				syntacticError("\"%\" cannot start an acutal parameter", currentToken.spelling);
			}
		}
		return actualParameterAST;
	}
	
	Expression parseExpression() throws SyntaxError {
		
		// In case Macbeth is going to support if-else expressions or loops, add logic here within a switch statement
		Expression expressionAST = parseSecondaryExpression();
		
		return expressionAST;
	}
	
	Expression parseSecondaryExpression() throws SyntaxError {
		
		Expression expressionAST = null;
		SourcePosition position = new SourcePosition();
		start(position);
		expressionAST = parsePrimaryExpression();
		while(currentToken.kind == Token.OPERATOR) {
			Operator operatorAST = parseOperator();
			Expression expression2AST = parsePrimaryExpression();
			expressionAST = new BinaryExpression(expressionAST, operatorAST, expression2AST, position);
		}
		return expressionAST;
	}
	
	Expression parsePrimaryExpression() throws SyntaxError {
		
		Expression expressionAST = null;
		SourcePosition position = new SourcePosition();
		start(position);
		
		switch(currentToken.kind) {
			case Token.INTLITERAL -> {
				IntegerLiteral integerLiteralAST = parseIntegerLiteral();
				finish(position);
				expressionAST = new IntegerExpression(integerLiteralAST, position);
			}
			
			case Token.CHARLITERAL -> {
				CharacterLiteral characterLiteralAST = parseCharacterLiteral();
				finish(position);
				expressionAST = new CharacterExpression(characterLiteralAST, position);
			}
			
			case Token.IDENTIFIER -> {
				Identifier identifierAST = parseIdentifier();
				if(currentToken.kind == Token.LBRACKET) {
					acceptIt();
					ActualParameterSequence actualParameterSequenceAST = parseActualParameterSequence();
					accept(Token.RBRACKET);
					finish(position);
					expressionAST = new CallExpression(identifierAST, actualParameterSequenceAST, position);
				} else {
					Vname vnameAST = parseRestOfVname(identifierAST);
					finish(position);
					expressionAST = new VnameExpression(vnameAST, position);
				}
			}
			
			case Token.OPERATOR -> {
				Operator operatorAST = parseOperator();
				Expression exprAST = parsePrimaryExpression();
				finish(position);
				expressionAST  new UnaryExpression(operatorAST, exprAST, position);
			}
			
			case Token.LBRACKET -> {
				acceptIt();
				expressionAST = parseExpression();
				accept(Token.RBRACKET);
			}
			
			default -> {
				syntacticError("\"%\" cannot start an expression", currentToken.spelling);
			}
		}
		return expressionAST;
	}
	
	Operator parseOperator() throws SyntaxError {
		
		Operator operator = null;
		
		if(currentToken.kind == Token.OPERATOR) {
			previousTokenPosition = currentToken.position;
			operator = new Operator(currentToken.spelling, previousTokenPosition);
			currentToken = lexeicalAnalyser.scanSourceCode();
		} else {
			syntacticError("operator expected here", "");
		}
		return operator;
	}
	
	// TODO: Implement remaining logic 
}
