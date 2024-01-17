package com.rickauer.mcbeth.syntacticAnalyzer;

public class Token {
	public byte kind;
	public String spelling;
	
	public Token(byte kind, String spelling) {
		this.kind = kind;
		this.spelling = spelling;
		
		if (kind == IDENTIFIER) {
			for (int i = NUMBER; i <= SHOW; i++ ) {
				if (spelling.equals(spellings[i])) {
					this.kind = (byte)i;
					break;
				}
			}
		}
	}
	
	public final static byte IDENTIFIER = 0, INTLITERAL = 1, OPERATOR = 2, NUMBER = 3, STRING = 4, SHOW = 5, DOT = 6, BECOMES = 7, LBRACKET = 8, RBRACKET = 9, EOT = 10;
	
	private final static String[] spellings = { "<identifier>", "<integer-literal>", "<operator>", "number", "string", "show", ".", "<-", "[", "]", "<eot>" }; 
}
