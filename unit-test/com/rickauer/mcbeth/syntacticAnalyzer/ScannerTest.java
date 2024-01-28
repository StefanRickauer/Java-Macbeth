package com.rickauer.mcbeth.syntacticAnalyzer;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public final class ScannerTest {
	
	static Scanner scanner;
	static String allCharactersLowerCase, allCharactersUpperCase, digits, operators;
	
	@BeforeAll
	private static void initializeTestData() {
		
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
	void isLetterTest() throws IllegalAccessException, InvocationTargetException {
		
		for (char currentCharacter : allCharactersLowerCase.toCharArray()) {
			assertTrue( (boolean) getScannerPredicate("isLetter").invoke(scanner, currentCharacter));
		}
		
		for (char currentCharacter : allCharactersUpperCase.toCharArray()) {
			assertTrue( (boolean) getScannerPredicate("isLetter").invoke(scanner, currentCharacter));
		}
		
		for (char digit : digits.toCharArray()) {
			assertFalse( (boolean) getScannerPredicate("isLetter").invoke(scanner, digit));
		}
		
		for (char operator : operators.toCharArray()) {
			assertFalse( (boolean) getScannerPredicate("isLetter").invoke(scanner, operator));
		}
	}
	
	@Test
	void isDigitTest() throws IllegalAccessException, InvocationTargetException {
		
		for (char digit : digits.toCharArray()) {
			assertTrue( (boolean) getScannerPredicate("isDigit").invoke(scanner, digit));
		}
		
		for (char currentCharacter : allCharactersLowerCase.toCharArray()) {
			assertFalse( (boolean) getScannerPredicate("isDigit").invoke(scanner, currentCharacter));
		}
		
		for (char currentCharacter : allCharactersUpperCase.toCharArray()) {
			assertFalse( (boolean) getScannerPredicate("isDigit").invoke(scanner, currentCharacter));
		}
		
		for (char operator : operators.toCharArray()) {
			assertFalse( (boolean) getScannerPredicate("isDigit").invoke(scanner, operator));
		}
	}
	
	@Test
	void isOperatorTest() throws IllegalAccessException, InvocationTargetException {

		for (char operator : operators.toCharArray()) {
			assertTrue( (boolean) getScannerPredicate("isOperator").invoke(scanner, operator));
		}
		
		for (char currentCharacter : allCharactersLowerCase.toCharArray()) {
			assertFalse( (boolean) getScannerPredicate("isOperator").invoke(scanner, currentCharacter));
		}
		
		for (char currentCharacter : allCharactersUpperCase.toCharArray()) {
			assertFalse( (boolean) getScannerPredicate("isOperator").invoke(scanner, currentCharacter));
		}
		
		for (char digit : digits.toCharArray()) {
			assertFalse( (boolean) getScannerPredicate("isOperator").invoke(scanner, digit));
		}	
	}
	
	private static Method getScannerPredicate(String methodName) {
		
		Class<? extends Scanner> clazz = scanner.getClass();
		Method method = null;
		
		try {
			method = clazz.getDeclaredMethod(methodName, char.class);
			method.setAccessible(true);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return method;
	}
}
