package com.rickauer.macbeth.utility;
// TODO check necessity of this enum and delete if not needed
public enum ExitCode {
	SUCCESS 				(0),
	INVALID_CMD_LINE_ARG 	(64),
	INPUT_OUTPUT_ERROR 		(74);
	
	private final int exitCode;
	
	ExitCode(final int exitCode) {
		this.exitCode = exitCode;
	}
	
	public int getValue() {
		return exitCode;
	}
}
