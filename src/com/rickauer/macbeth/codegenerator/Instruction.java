package com.rickauer.macbeth.codegenerator;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;

public class Instruction {
	
	public int opCode, registerNumber, length, operand;
	
	public Instruction() {
		opCode = 0;
		registerNumber = 0;
		length = 0;
		operand = 0;
	}
	
	public void write(DataOutputStream output) throws IOException {
		output.write(opCode);
		output.write(registerNumber);
		output.write(length);
		output.write(operand);
	}
	
	public static Instruction read(DataInputStream input) throws IOException {
		Instruction instruction = new Instruction();
		try {
			instruction.opCode = input.readInt();
			instruction.registerNumber = input.readInt();
			instruction.length = input.readInt();
			instruction.operand = input.readInt();
			
			return instruction;
		} catch (EOFException s) {
			return null;
		}
	}
}
