package com.rickauer.macbeth;

import com.rickauer.macbeth.abstractsyntaxtrees.Program;
import com.rickauer.macbeth.semanticAnalyzer.Checker;
import com.rickauer.macbeth.syntacticAnalyzer.Parser;
import com.rickauer.macbeth.syntacticAnalyzer.Scanner;
import com.rickauer.macbeth.syntacticAnalyzer.SourceFile;

public class Macbeth {
	
	private static String objectName = "obj.beth";
	
	private static Scanner scanner;
	private static Parser parser;
	private static Checker checker;
//	private static Encoder encoder;
	private static ErrorReporter errorReporter;
	
	private static Program programAST;
	
	public static void main(String[] args) {
		
		if(args.length != 1) {
			System.out.println("Usage: macbeth <source file>");
			System.exit(1);
		}
		
		String sourceFile = args[0];
		boolean compiledSuccessfully;
		
		try {
			compiledSuccessfully = compileProgram(sourceFile, objectName, false, false);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	private static boolean compileProgram(String sourceName, String object, boolean showAST, boolean showTable) {
		System.out.println("Macbeth Compiler - Version: 0.2\n" +
				"===============================\n\n" +
				"There's no art\n"
				+ "To find the mind's construction in the face.");
		System.out.println("Starting Syntax Analysis ...");
		
		SourceFile sourceFile = new SourceFile(sourceName);
		
		if(sourceFile == null) {
			System.err.println("Can't open source file: " + sourceName);
			System.exit(1);
		}
		
		scanner = new Scanner(sourceFile);
		errorReporter = new ErrorReporter();
		parser = new Parser(scanner, errorReporter);
		checker = new Checker(errorReporter);
		encoder = new Encoder(errorReporter);
		
		programAST = parser.parseProgram();
		
		if(errorReporter.numberOfErrors == 0) {
			System.out.println("Starting Context Analysis ...");
			checker.check(programAST);
			
			if (errorReporter.numberOfErrors == 0) {
				encoder.encodeRun(programAST, showTable);
			}
		}
		
		boolean successful = (errorReporter.numberOfErrors == 0);
		
		if (successful) {
			encoder.saveObjectProgram(objectName);
			System.out.println("Successfully compiled " + sourceName);
		} else {
			System.err.println("Failed compiling " + sourceName);
		}
		
		return successful;
	}
}
