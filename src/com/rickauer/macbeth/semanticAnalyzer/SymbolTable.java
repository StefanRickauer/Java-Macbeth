package com.rickauer.macbeth.semanticAnalyzer;

import com.rickauer.macbeth.abstractsyntaxtrees.Declaration;

public class SymbolTable {
	
	private int level;
	private IdEntry latest;
	
	public SymbolTable() {
		level = 0;
		latest = null;
	}
	
	public void openScope() {
		level++;
	}
	
	public void closeScope() {
		IdEntry entry, local;
		
		entry = this.latest;
		while(entry.level == this.level) {
			local = entry;
			entry = local.previous;
		}
		this.level--;
		this.latest = entry;
	}
	
	public void enter(String id, Declaration attr) {
		IdEntry entry = this.latest;
		boolean present = false, searching = true;
		
		while (searching) {
			if (entry == null || entry.level < this.level) 
				searching = false;
			else if (entry.id.equals(id)) {
				present = true;
				searching = false;
			} else 
				entry = entry.previous;
		}
		attr.duplicated = present;
		
		entry = new IdEntry(id, attr, this.level, this.latest);
		this.latest = entry;
	}
	
	public Declaration retrieve(String id) {
		
		IdEntry entry;
		Declaration attr = null;
		boolean present = false, searching = true;
		
		entry = this.latest;
		while (searching) {
			if (entry == null) {
				searching = false;
			} else if (entry.id.equals(id)) {
				present = true;
				searching = false;
				attr = entry.attr;
			} else {
				entry = entry.previous;
			}
		}
		return attr;
	}
}
