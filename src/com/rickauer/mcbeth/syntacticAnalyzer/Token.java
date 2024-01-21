package com.rickauer.mcbeth.syntacticAnalyzer;

public class Token {
	public int kind;
	public String spelling;
	
	public Token(int kind, String spelling) {
		
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
	}
	
	public static String spell(int kind) {
		return tokenTable[kind];
	}
	
	@Override
	public String toString() {
		return "Kind=" + kind + ", spelling=" + spelling;
	}
	
	public static final int
			// literals, identifier, operator
			CHARLITERAL =  0,
			INTLITERAL 	=  1,
			IDENTIFIER 	=  2,  
			OPERATOR 	=  3, 
		
			// keywords (required to be in alphabetical order)
			NUMBER 		=  4, 
			SHOW 		=  5, 
			STRING 		=  6, 
		
			// punctuation
			BECOMES 	=  7,
			DOT 		=  8,  
		
			// bracket
			LBRACKET 	=  9, 
			RBRACKET 	= 10,
		
			// special token
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
