package com.rickauer.mcbeth.utility;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

class FileReaderTest {

	@Test
	void readFileTest() {
		String userDirectory = System.getProperty("user.dir");
		String expected = "This is a test. { } ( ) + - . : = This was a test.";
		
		assertEquals(expected, FileReader.readFile(userDirectory + "/McBeth/resources/test data/readTest.txt"));
	}
	
	@Test
	void readFileWithInvalidPathTest() {
		assertThrows(NoSuchFileException.class, () -> {
			Files.readAllBytes(Paths.get("invalidPath"));
		}, "NoSuchFileException was expected");
	}

}
