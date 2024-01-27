package com.rickauer.mcbeth.syntacticAnalyzer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

final class SourceFileTest {

	static SourceFile source;
	static String expected;
	
	@BeforeAll
	private static void initializeTestData() {
		
		String currentDirectory = System.getProperty("user.dir");
		source = new SourceFile(currentDirectory + "/resources/test data/test-string");
		
		expected = "This is my test string.\r\nThis is line 2.\r\nAnd finally, this is line 3. Have a nice day!";
	}
	
	@Test
	void getSourceTest() {
		for (char expectedCharacter : expected.toCharArray()) {
			assertEquals(expectedCharacter, source.fetchNextCharacter());	
		}
	}
}
