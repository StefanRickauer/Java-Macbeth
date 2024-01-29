package com.rickauer.macbeth.syntacticAnalyzer;

import java.io.File;
import java.io.FileReader;

public final class SourceFile {
	
	public static final char EOL = '\n';
	public static final char EOT = '\u0000';
	
	File sourceFile;
	FileReader source;		
	int currentLine;
	
	public SourceFile(String filename) {
		try {
			sourceFile = new File(filename);
			source = new FileReader(sourceFile);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	char fetchNextCharacter() {
		try {
			int c = source.read();
			
			if (c == -1) {
				c = EOT;
			} else if (c == EOL) {
				currentLine++;
			}
			return (char) c;
		} catch (Exception e) {
			return EOT;
		}
	}
	
	int getCurrentLine() {
		return currentLine;
	}
}
