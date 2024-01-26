package com.rickauer.mcbeth.syntacticAnalyzer;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

final class ScannerTest {
	
	static Scanner scanner;
	static String allCharactersLowerCase, allCharactersUpperCase, digits, operators;
	
	@BeforeAll
	void initializeTestData() {
		
		String currentDirectory = System.getProperty("user.dir");
		
		try {
			scanner = new Scanner(new SourceFile(currentDirectory + "/resources/test data/test-program.mcb"));
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}
		
		allCharactersLowerCase = "abcdefghijklmnopqrstuvwxyz";
		allCharactersUpperCase = allCharactersLowerCase.toUpperCase();
		digits = "0123456789";
		operators = "<-+*";
	}
	
	@Test
	void isLetterTest() {
		
		for (char currentCharacter : allCharactersLowerCase.toCharArray()) {
			assertTrue(scanner.isLetter(currentCharacter));
		}
		
		for (char currentCharacter : allCharactersUpperCase.toCharArray()) {
			assertTrue(scanner.isLetter(currentCharacter));
		}
		
		for (char digit : digits.toCharArray()) {
			assertFalse(scanner.isLetter(digit));
		}
		
		for (char operator : operators.toCharArray()) {
			assertFalse(scanner.isLetter(operator));
		}
	}
	
	@Test
	void isDigitTest() {
		
		for (char digit : digits.toCharArray()) {
			assertTrue(scanner.isDigit(digit));
		}
		
		for (char currentCharacter : allCharactersLowerCase.toCharArray()) {
			assertFalse(scanner.isDigit(currentCharacter));
		}
		
		for (char currentCharacter : allCharactersUpperCase.toCharArray()) {
			assertFalse(scanner.isDigit(currentCharacter));
		}
		
		for (char operator : operators.toCharArray()) {
			assertFalse(scanner.isDigit(operator));
		}
	}
	
	@Test
	void isOperatorTest() {

		for (char operator : operators.toCharArray()) {
			assertTrue(scanner.isOperator(operator));
		}
		
		for (char currentCharacter : allCharactersLowerCase.toCharArray()) {
			assertFalse(scanner.isOperator(currentCharacter));
		}
		
		for (char currentCharacter : allCharactersUpperCase.toCharArray()) {
			assertFalse(scanner.isOperator(currentCharacter));
		}
		
		for (char digit : digits.toCharArray()) {
			assertFalse(scanner.isOperator(digit));
		}	
	}
}
