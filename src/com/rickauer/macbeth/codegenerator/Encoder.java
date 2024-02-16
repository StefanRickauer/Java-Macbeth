package com.rickauer.macbeth.codegenerator;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.rickauer.macbeth.ErrorReporter;
import com.rickauer.macbeth.abstractsyntaxtrees.AssignCommand;
import com.rickauer.macbeth.abstractsyntaxtrees.BinaryExpression;
import com.rickauer.macbeth.abstractsyntaxtrees.BinaryOperatorDeclaration;
import com.rickauer.macbeth.abstractsyntaxtrees.CallCommand;
import com.rickauer.macbeth.abstractsyntaxtrees.CallExpression;
import com.rickauer.macbeth.abstractsyntaxtrees.CharacterExpression;
import com.rickauer.macbeth.abstractsyntaxtrees.CharacterLiteral;
import com.rickauer.macbeth.abstractsyntaxtrees.ConstantActualParameter;
import com.rickauer.macbeth.abstractsyntaxtrees.EmptyActualParameterSequence;
import com.rickauer.macbeth.abstractsyntaxtrees.EmptyCommand;
import com.rickauer.macbeth.abstractsyntaxtrees.Identifier;
import com.rickauer.macbeth.abstractsyntaxtrees.IntegerExpression;
import com.rickauer.macbeth.abstractsyntaxtrees.IntegerLiteral;
import com.rickauer.macbeth.abstractsyntaxtrees.NumberDeclaration;
import com.rickauer.macbeth.abstractsyntaxtrees.Operator;
import com.rickauer.macbeth.abstractsyntaxtrees.Program;
import com.rickauer.macbeth.abstractsyntaxtrees.SequentialCommand;
import com.rickauer.macbeth.abstractsyntaxtrees.SimpleVname;
import com.rickauer.macbeth.abstractsyntaxtrees.SingleActualParameterSequence;
import com.rickauer.macbeth.abstractsyntaxtrees.StringDeclaration;
import com.rickauer.macbeth.abstractsyntaxtrees.SubscriptVname;
import com.rickauer.macbeth.abstractsyntaxtrees.TypeDeclaration;
import com.rickauer.macbeth.abstractsyntaxtrees.UnaryExpression;
import com.rickauer.macbeth.abstractsyntaxtrees.Visitor;
import com.rickauer.macbeth.abstractsyntaxtrees.VnameExpression;

public final class Encoder implements Visitor {

	private ErrorReporter reporter;
	private int nextInstructionAddress;
	
	public Encoder(ErrorReporter reporter) {
		this.reporter = reporter;
		nextInstructionAddress = Machine.CB;
		elaborateStandardEnvironment();
	}
	
	public final void encodeRun(Program programAST, boolean showingTable) {
		programAST.visit(this, new Frame(0, 0));
		emit(Machine.HALTop, 0, 0, 0);
	}
	
	private void emit(int opCode, int length, int registerNumber, int operand) {
		
		Instruction nextInstruction = new Instruction();
		
		if (length > 255) {
			reporter.reportRestriction("Length of operand can't exceed 255 words.");
			length = 255;
		}
		
		nextInstruction.opCode = opCode;
		nextInstruction.length = length;
		nextInstruction.registerNumber = registerNumber;
		nextInstruction.operand = operand;
		
		if (nextInstructionAddress == Machine.PB) {
			reporter.reportRestriction("Too many instructions for code segment");
		} else {
			Machine.code[nextInstructionAddress] = nextInstruction;
			nextInstructionAddress++;
		}
	}
	
	private void patch(int address, int operand) {
		Machine.code[address].operand = operand;
	}
	
	@Override
	public Object visitProgram(Program ast, Object object) {
		return ast.visit(this, object);
	}

	@Override
	public Object visitSequentialCommand(SequentialCommand ast, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitIdentifier(Identifier ast, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitCallCommand(CallCommand ast, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitAssignCommand(AssignCommand ast, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitEmptyActualParameterSequence(EmptyActualParameterSequence ast, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitSingleActualParameterSequence(SingleActualParameterSequence ast, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitConstantActualParameter(ConstantActualParameter ast, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitEmptyCommand(EmptyCommand ast, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitIntegerExpression(IntegerExpression ast, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitIntegerLiteral(IntegerLiteral ast, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitOperator(Operator ast, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitBinaryExpression(BinaryExpression ast, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitCharacterExpression(CharacterExpression ast, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitCharacterLiteral(CharacterLiteral ast, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitCallExpression(CallExpression ast, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitUnaryExpression(UnaryExpression ast, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitVnameExpression(VnameExpression ast, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitSimpleVname(SimpleVname ast, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitSubscriptVname(SubscriptVname ast, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitNumberDeclaration(NumberDeclaration ast, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitStringDeclaration(StringDeclaration ast, Object object) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private final void elaborateStandardEnvironment() {
		// TODO: Implement Method
	}
	// TODO: Implement remaining class

	@Override
	public Object visitBinaryOperatorDeclaration(BinaryOperatorDeclaration ast, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitTypeDeclaration(TypeDeclaration ast, Object object) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void saveObjectProgram(String objectName) {
		FileOutputStream objectFile = null;
		DataOutputStream objectStream = null;
		
		int address;
		
		try {
			objectFile = new FileOutputStream(objectName);
			objectStream = new DataOutputStream(objectFile);
			
			address = Machine.CB;
			for (address = Machine.CB; address < nextInstructionAddress; address++) {
				Machine.code[address].write(objectStream);
			}
			objectFile.close();
			objectStream.close();
		} catch (FileNotFoundException e) {
			System.err.println("Error opening object file: " + e);
		} catch (IOException e) {
			System.err.println("Error writing object file: " + e);
		}
	}
}
