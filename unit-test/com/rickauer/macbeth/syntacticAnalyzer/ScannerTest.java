package com.rickauer.macbeth.syntacticAnalyzer;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public final class ScannerTest {
	
	static Scanner scannerTestSeriesOne, scannerTestSeriesTwo;
	static Class<? extends Scanner> clazz;
	static String allCharactersLowerCase, allCharactersUpperCase, digits, operators;
	
	@BeforeAll
	private static void initializeTestData() {
		
		String currentDirectory = System.getProperty("user.dir");
		
		try {
			scannerTestSeriesOne = new Scanner(new SourceFile(currentDirectory + "/resources/test data/test-lowerCaseAlphabeth"));
			scannerTestSeriesTwo = new Scanner(new SourceFile(currentDirectory + "/resources/test data/test-misc"));
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}
		clazz = scannerTestSeriesOne.getClass();
		allCharactersLowerCase = "abcdefghijklmnopqrstuvwxyz";
		allCharactersUpperCase = allCharactersLowerCase.toUpperCase();
		digits = "0123456789";
		operators = "<-+*";
	}
	
	@Test
	void isLetterTest() throws IllegalAccessException, InvocationTargetException {
		
		for (char currentCharacter : allCharactersLowerCase.toCharArray()) {
			assertTrue( (boolean) getScannerPredicate("isLetter").invoke(scannerTestSeriesOne, currentCharacter));
		}
		
		for (char currentCharacter : allCharactersUpperCase.toCharArray()) {
			assertTrue( (boolean) getScannerPredicate("isLetter").invoke(scannerTestSeriesOne, currentCharacter));
		}
		
		for (char digit : digits.toCharArray()) {
			assertFalse( (boolean) getScannerPredicate("isLetter").invoke(scannerTestSeriesOne, digit));
		}
		
		for (char operator : operators.toCharArray()) {
			assertFalse( (boolean) getScannerPredicate("isLetter").invoke(scannerTestSeriesOne, operator));
		}
	}
	
	@Test
	void isDigitTest() throws IllegalAccessException, InvocationTargetException {
		
		for (char digit : digits.toCharArray()) {
			assertTrue( (boolean) getScannerPredicate("isDigit").invoke(scannerTestSeriesOne, digit));
		}
		
		for (char currentCharacter : allCharactersLowerCase.toCharArray()) {
			assertFalse( (boolean) getScannerPredicate("isDigit").invoke(scannerTestSeriesOne, currentCharacter));
		}
		
		for (char currentCharacter : allCharactersUpperCase.toCharArray()) {
			assertFalse( (boolean) getScannerPredicate("isDigit").invoke(scannerTestSeriesOne, currentCharacter));
		}
		
		for (char operator : operators.toCharArray()) {
			assertFalse( (boolean) getScannerPredicate("isDigit").invoke(scannerTestSeriesOne, operator));
		}
	}
	
	@Test
	void isOperatorTest() throws IllegalAccessException, InvocationTargetException {

		for (char operator : operators.toCharArray()) {
			assertTrue( (boolean) getScannerPredicate("isOperator").invoke(scannerTestSeriesOne, operator));
		}
		
		for (char currentCharacter : allCharactersLowerCase.toCharArray()) {
			assertFalse( (boolean) getScannerPredicate("isOperator").invoke(scannerTestSeriesOne, currentCharacter));
		}
		
		for (char currentCharacter : allCharactersUpperCase.toCharArray()) {
			assertFalse( (boolean) getScannerPredicate("isOperator").invoke(scannerTestSeriesOne, currentCharacter));
		}
		
		for (char digit : digits.toCharArray()) {
			assertFalse( (boolean) getScannerPredicate("isOperator").invoke(scannerTestSeriesOne, digit));
		}	
	}
	
	private static Method getScannerPredicate(String methodName) {
		
		Method method = null;
		
		try {
			method = clazz.getDeclaredMethod(methodName, char.class);
			method.setAccessible(true);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return method;
	}
	
	@Test
	void consumeCharacterTest() throws IllegalAccessException, InvocationTargetException, NoSuchFieldException, SecurityException {
		
		Field scannerCurrentCharacter = clazz.getDeclaredField("currentCharacter");
		scannerCurrentCharacter.setAccessible(true);
		
		for (char expectedCharacter : allCharactersLowerCase.toCharArray()) {
			assertEquals(expectedCharacter, scannerCurrentCharacter.get(scannerTestSeriesOne));
			getScannerConsumeCharacter().invoke(scannerTestSeriesOne);
		}
	}
	
	private static Method getScannerConsumeCharacter() {
		
		Method method = null;
		
		try {
			method = clazz.getDeclaredMethod("consumeCharacter");
			method.setAccessible(true);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return method;
	}
	
	@Test
	void scanTokenTest() throws IllegalAccessException, InvocationTargetException {
		assertEquals(Token.CHARLITERAL, getScannerScanToken().invoke(scannerTestSeriesTwo));
		assertEquals(Token.INTLITERAL, getScannerScanToken().invoke(scannerTestSeriesTwo));
		assertEquals(Token.IDENTIFIER, getScannerScanToken().invoke(scannerTestSeriesTwo));
		assertEquals(Token.OPERATOR, getScannerScanToken().invoke(scannerTestSeriesTwo));
		assertEquals(Token.BECOMES, getScannerScanToken().invoke(scannerTestSeriesTwo));
		assertEquals(Token.DOT, getScannerScanToken().invoke(scannerTestSeriesTwo));
		assertEquals(Token.LBRACKET, getScannerScanToken().invoke(scannerTestSeriesTwo));
		assertEquals(Token.RBRACKET, getScannerScanToken().invoke(scannerTestSeriesTwo));
	}
	
	private static Method getScannerScanToken() {
		Method method = null;
		
		try {
			method = clazz.getDeclaredMethod("scanToken");
			method.setAccessible(true);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return method;
	}
}
