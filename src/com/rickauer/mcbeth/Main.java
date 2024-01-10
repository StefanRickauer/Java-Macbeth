package com.rickauer.mcbeth;

public class Main {
	public static void main(String[] args) {
		
		// Global try-catch-block for error handling. All other methods throw RuntimeExceptions in order to be processed here. 
		try {
			System.out.println("McBeth-Compiler alive and kicking.");
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
