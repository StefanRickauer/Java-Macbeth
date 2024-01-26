package com.rickauer.mcbeth.syntacticAnalyzer;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

final class ScannerTest {
	
	static Scanner scanner;
	static String allCharactersLowerCase, allCharactersUpperCase, digits, operators;
	
	@BeforeAll
	static void initializeTestData() {
		
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
			
			//	(cast to return type)   (object of class of method, argument)
			assertTrue( (boolean) getIsLetter().invoke(scanner, currentCharacter));
		}
		
		for (char currentCharacter : allCharactersUpperCase.toCharArray()) {
			assertTrue( (boolean) getIsLetter().invoke(scanner, currentCharacter));
		}
		
		for (char digit : digits.toCharArray()) {
			assertFalse( (boolean) getIsLetter().invoke(scanner, digit));
		}
		
		for (char operator : operators.toCharArray()) {
			assertFalse( (boolean) getIsLetter().invoke(scanner, operator));
		}
	}
	
	@Test
	void isDigitTest() throws IllegalAccessException, InvocationTargetException {
		
		for (char digit : digits.toCharArray()) {
			assertTrue( (boolean) getIsDigit().invoke(scanner, digit));
		}
		
		for (char currentCharacter : allCharactersLowerCase.toCharArray()) {
			assertFalse( (boolean) getIsDigit().invoke(scanner, currentCharacter));
		}
		
		for (char currentCharacter : allCharactersUpperCase.toCharArray()) {
			assertFalse( (boolean) getIsDigit().invoke(scanner, currentCharacter));
		}
		
		for (char operator : operators.toCharArray()) {
			assertFalse( (boolean) getIsDigit().invoke(scanner, operator));
		}
	}
	
	@Test
	void isOperatorTest() throws IllegalAccessException, InvocationTargetException {

		for (char operator : operators.toCharArray()) {
			assertTrue( (boolean) getIsOperator().invoke(scanner, operator));
		}
		
		for (char currentCharacter : allCharactersLowerCase.toCharArray()) {
			assertFalse( (boolean) getIsOperator().invoke(scanner, currentCharacter));
		}
		
		for (char currentCharacter : allCharactersUpperCase.toCharArray()) {
			assertFalse( (boolean) getIsOperator().invoke(scanner, currentCharacter));
		}
		
		for (char digit : digits.toCharArray()) {
			assertFalse( (boolean) getIsOperator().invoke(scanner, digit));
		}	
	}
	
	private Method getIsLetter() {
		Method method = null;
		try {	
			//	     class of method					 (method name,  class of argument)
			method = scanner.getClass().getDeclaredMethod("isLetter", char.class);
			
			// to access private method
			method.setAccessible(true);		
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
		return method;
	}
	
	private Method getIsDigit() {
		Method method = null;
		try {
			method = scanner.getClass().getDeclaredMethod("isDigit", char.class);
			method.setAccessible(true);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
		return method;
	}
	
	private Method getIsOperator() {
		Method method = null;
		try {
			method = scanner.getClass().getDeclaredMethod("isOperator", char.class);
			method.setAccessible(true);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
		return method;
	}
}
