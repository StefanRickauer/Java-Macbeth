package com.rickauer.macbeth.syntacticAnalyzer;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

// TODO: Finish Parser and ParserTest

public final class ParserTest {
	static Scanner scanner;
	static Parser parser;
	static Class<? extends Parser> clazz;
	static int[] tokensWithoutKeywordsExpected;

	@BeforeAll
	public static void initializeTestData() {
		String currentDirectory = System.getProperty("user.dir");
		scanner = new Scanner(new SourceFile(currentDirectory + "/resources/test data/test-tokensWithoutKeywords"));
		tokensWithoutKeywordsExpected = new int[] {Token.IDENTIFIER, Token.BECOMES, Token.INTLITERAL, Token.DOT, Token.IDENTIFIER, Token.BECOMES, Token.CHARLITERAL, Token.DOT};
		parser = new Parser(scanner);
		clazz = parser.getClass();
	}
	
	@Test
	void testAcceptIt() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {

		Field parserCurrentToken = clazz.getDeclaredField("currentToken");
		parserCurrentToken.setAccessible(true);
		parserCurrentToken.set(parser, scanner.scanSourceCode());
		
		for (int token : tokensWithoutKeywordsExpected) {
			Token actual = (Token)parserCurrentToken.get(parser);
			assertEquals(token, actual.kind);
			parser.acceptIt();
		}
	}
}
