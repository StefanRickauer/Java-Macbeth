package com.rickauer.macbeth.utility;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReader {
	
	// Suppress default constructor to enforce non instanciability
	private FileReader() {
		throw new AssertionError();
	}
	
	public static String readFile(String path) {	
		try {
			byte[] source = Files.readAllBytes(Paths.get(path));
			return new String(source, Charset.defaultCharset());
		} catch (IOException e) {
			e.printStackTrace();
			ExitCode exitCode = ExitCode.INPUT_OUTPUT_ERROR;
			System.exit(exitCode.getValue());
		}
		
		return "";
	}
}
