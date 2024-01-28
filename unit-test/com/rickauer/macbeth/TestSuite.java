package com.rickauer.macbeth;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import com.rickauer.macbeth.syntacticAnalyzer.*;
import com.rickauer.macbeth.utility.*;

@Suite
@SelectClasses({ScannerTest.class, SourceFileTest.class, FileReaderTest.class})
public class TestSuite {
	// No implementation needed. 
}
