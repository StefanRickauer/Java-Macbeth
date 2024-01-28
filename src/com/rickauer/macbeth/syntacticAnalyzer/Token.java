package com.rickauer.macbeth.syntacticAnalyzer;

public final class Token {
	
	public int kind;
	public String spelling;
	public SourcePosition position;
	
	public Token(int kind, String spelling, SourcePosition position) {
		
		if (kind == Token.IDENTIFIER) {
			int currentKind = firstKeyword;
			boolean searching = true;
			
			while (searching) {
				int comparison = tokenTable[currentKind].compareTo(spelling);
				if (comparison == 0) {
					this.kind = currentKind;
					searching = false;
				}
				else if (comparison > 0 || currentKind == lastKeyword) {
					this.kind = Token.IDENTIFIER;
					searching = false;
				}
				else {
					currentKind++;
				}
			}
		} 
		else {
			this.kind = kind;
		}
		
		this.spelling = spelling;
		this.position = position;
	}
	
	public static String spell(int kind) {
		return tokenTable[kind];
	}
	
	@Override
	public String toString() {
		return "Kind=" + kind + ", spelling=" + spelling + ", position=" + position;
	}
	
	public static final int

			CHARLITERAL =  0,
			INTLITERAL 	=  1,
			IDENTIFIER 	=  2,  
			OPERATOR 	=  3, 
		
			NUMBER 		=  4, 
			SHOW 		=  5, 
			STRING 		=  6, 
		
			BECOMES 	=  7,
			DOT 		=  8,  
		
			LBRACKET 	=  9, 
			RBRACKET 	= 10,
		
			EOT 		= 11,
			ERROR 		= 12;
	
	private static final String[] tokenTable = new String[] { 
			"<char>",
			"<int>",
			"<identifier>", 
			"<operator>", 
			"number", 
			"show", 
			"string",
			"<-", 
			".", 
			"[", 
			"]", 
			"" 
	}; 
	
	private static final int firstKeyword = Token.NUMBER, lastKeyword = Token.STRING;
}
