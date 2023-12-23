package com.rickauer.mcbeth.utility;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReader {
	
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
