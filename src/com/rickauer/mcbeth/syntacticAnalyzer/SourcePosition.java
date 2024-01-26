package com.rickauer.mcbeth.syntacticAnalyzer;

public final class SourcePosition {
	public int start, finish;
	
	public SourcePosition() {
		start = 0;
		finish = 0;
	}
	
	public SourcePosition(int start, int finish) {
		this.start = start;
		this.finish = finish;
	}
	
	@Override
	public String toString() {
		return "(" + start + ", " + finish + ")";
	}
}
