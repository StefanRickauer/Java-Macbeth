package com.rickauer.macbeth;

import com.rickauer.macbeth.abstractsyntaxtrees.Program;
import com.rickauer.macbeth.semanticAnalyzer.Checker;
import com.rickauer.macbeth.syntacticAnalyzer.Parser;
import com.rickauer.macbeth.syntacticAnalyzer.Scanner;

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
			compileProgram(sourceFile, objectName, false, false);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	private static boolean compileProgram(String source, String object, boolean showAST, boolean showTable) {
		System.out.println("Macbeth Compiler - Version: 0.2\n" +
				"===============================\n\n" +
				"There's no art\n"
				+ "To find the mind's construction in the face.");
		
		// TODO: Implement remaining logic
	}
}
