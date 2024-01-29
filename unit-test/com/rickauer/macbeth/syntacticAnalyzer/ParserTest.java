package com.rickauer.macbeth.syntacticAnalyzer;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public final class ParserTest {
	static Scanner scanner;
	static Parser parser;
	static Class<? extends Parser> clazz;

	@BeforeAll
	public static void initializeTestData() {
		String currentDirectory = System.getProperty("user.dir");
		scanner = new Scanner(new SourceFile(currentDirectory + "/resources/test data/test-tokensWithoutKeywords"));
		parser = new Parser(scanner);
		clazz = parser.getClass();
	}
	
	@Test
	void testAcceptIt() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {

		Field parserCurrentToken = clazz.getDeclaredField("currentToken");
		parserCurrentToken.setAccessible(true);
		parserCurrentToken.set(parser, scanner.scanSourceCode());
		
		Token actual = (Token)parserCurrentToken.get(parser);
		assertEquals(Token.IDENTIFIER, actual.kind);
		
		parser.acceptIt();
		actual = (Token)parserCurrentToken.get(parser);
		assertEquals(Token.BECOMES, actual.kind);
		
		parser.acceptIt();
		actual = (Token)parserCurrentToken.get(parser);
		assertEquals(Token.INTLITERAL, actual.kind);
		
		parser.acceptIt();
		actual = (Token)parserCurrentToken.get(parser);
		assertEquals(Token.DOT, actual.kind);
		
		// TODO: Finish test method and test class (along with parser class)

	}

}
