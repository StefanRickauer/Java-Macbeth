package com.rickauer.macbeth.utility;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

public final class FileReaderTest {

	@SuppressWarnings("static-method")
	@Test
	void readFileTest() {
		String userDirectory = System.getProperty("user.dir");
		String expected = "This is a test. { } ( ) + - . : = This was a test.";
		
		assertEquals(expected, FileReader.readFile(userDirectory + "/resources/test data/readTest.txt"));
	}
	
	@SuppressWarnings("static-method")
	@Test
	void readFileWithInvalidPathTest() {
		assertThrows(NoSuchFileException.class, () -> {
			Files.readAllBytes(Paths.get("invalidPath"));
		}, "NoSuchFileException was expected");
	}

}
