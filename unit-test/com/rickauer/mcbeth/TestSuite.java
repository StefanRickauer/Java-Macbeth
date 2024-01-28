package com.rickauer.mcbeth;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import com.rickauer.mcbeth.syntacticAnalyzer.*;
import com.rickauer.mcbeth.utility.*;

@Suite
@SelectClasses({ScannerTest.class, SourceFileTest.class, FileReaderTest.class})
public class TestSuite {
	// No implementation needed. 
}
