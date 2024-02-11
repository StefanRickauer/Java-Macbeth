package com.rickauer.macbeth;


import com.rickauer.macbeth.abstractsyntaxtrees.Program;
import com.rickauer.macbeth.syntacticAnalyzer.Parser;
import com.rickauer.macbeth.syntacticAnalyzer.Scanner;
import com.rickauer.macbeth.syntacticAnalyzer.SourceFile;

public class IntegrationTestMain {
	
	public static void main(String[] args) {
		
		try {
			System.out.println("Starting integration test...");
			
			if (sequentialCommandTest().contains("SequentialCommand")) {
				System.out.println("Successfully parsed sequential commands.");
			} else {
				System.out.println("Error parsing sequential commands.");
			}
			
			System.out.println("Finished integration test.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String sequentialCommandTest() {
		String currentDirectory = System.getProperty("user.dir");
		Scanner scanner = new Scanner(new SourceFile(currentDirectory + "/resources/test data/test-tokensWithKeywords"));
		Parser parser = new Parser(scanner);
		Program program = parser.parseProgram();
		return program.command.getClass().toString();
	}
}
