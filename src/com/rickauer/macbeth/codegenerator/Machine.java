package com.rickauer.macbeth.codegenerator;

public final class Machine {
	public static final int MAX_ROUTINE_LEVEL = 7;
	
	public static final int 
		LOADop = 0,
		LOADAop = 1, 
		LOADIop = 2,
		LOADLop = 3,
		STOREop = 4,
		STOREIop = 5,
		CALLop = 6,
		CALLIop = 7,
		RETURNop = 8,
		PUSHop = 10,
		POPop = 11,
		JUMPop = 12,
		JUMPIop = 13,
		JUMPIFop = 14,
		HALTop = 15;
	
	public static Instruction[] code = new Instruction[1024];
	
	public static final int
		CB = 0,
		PB = 1024,
		PT = 1052;
	
	public static final int 
		CBr = 0,
		CTr = 1,
		PBr = 2,
		PTr = 3,
		SBr = 4,
		STr = 5,
		HBr = 6,
		HTr = 7,
		LBr = 8,
		L1r = LBr + 1,
		L2r = LBr + 2,
		L3r = LBr + 3,
		L4r = LBr + 4,
		L5r = LBr + 5,
		L6r = LBr + 6,
		CPr = 15;
	
	public static final int
		characterSize = 1,
		numberSize = 1,
		
		maxNumberRep = 32767;
	
	public static final int 
		idDisplacement = 1,
		addDisplacement = 2,
		mulDisplacement = 3,
		eolDisplacement = 4,
		eofDisplacement = 5;
}
