package com.rickauer.macbeth;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import com.rickauer.macbeth.syntacticAnalyzer.*;

@Suite
@SelectClasses({ScannerTest.class, SourceFileTest.class, ParserTest.class})
public class TestSuite {
	// No implementation needed. 
}
