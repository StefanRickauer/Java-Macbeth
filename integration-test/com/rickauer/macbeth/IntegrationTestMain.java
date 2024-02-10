package com.rickauer.macbeth;

import com.rickauer.macbeth.abstractsyntaxtrees.Program;
import com.rickauer.macbeth.syntacticAnalyzer.Parser;
import com.rickauer.macbeth.syntacticAnalyzer.Scanner;
import com.rickauer.macbeth.syntacticAnalyzer.SourceFile;

public class IntegrationTestMain {
	
	public static void main(String[] args) {
		String currentDirectory = System.getProperty("user.dir");
		Scanner scanner = new Scanner(new SourceFile(currentDirectory + "/resources/test data/test-tokensWithKeywords"));
		Parser parser = new Parser(scanner);
		Program program;
		
		try {
			System.out.println("Starting integration test...");
			program = parser.parseProgram();
			System.out.println(program.command.getClass());
			System.out.println("Finished integration test.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
