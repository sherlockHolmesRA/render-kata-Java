package com.kata.rover;

import org.junit.*;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@RunWith(Parameterized.class)
class RoverTests {
	
	@Test
    public void testSampleProgram() throws IOException {
        check("5 5\n1 2 N\nLMLMLMLMM\n3 3 E\nMMRMMRMRRM\n", "1 3 N\n5 1 E\n");
    }

    @Test
    public void testReversedSample() throws IOException {
        check("5 5\n3 3 E\nMMRMMRMRRM\n1 2 N\nLMLMLMLMM", "5 1 E\n1 3 N\n");
    }

    @Test
    public void testWithNoInstructions() throws IOException {
        // If the rovers don't have any instructions, then they should stay put.
        check("5 5\n1 2 N\n\n3 3 E\n\n", "1 2 N\n3 3 E\n");
    }

    @Parameters
    Object[][] simplePrograms () {
        return new Object[][] {
                {"1 2 N", "L", "1 2 W"},
                {"1 2 W", "L", "1 2 S"},
                {"1 2 S", "L", "1 2 E"},
                {"1 2 E", "L", "1 2 N"},
                {"1 2 W", "M", "0 2 W"},
                {"1 1 W", "L", "1 1 S"},
                {"1 1 S", "M", "1 0 S"},
                {"0 1 E", "M", "1 1 E"},
                {"1 1 N", "M", "1 2 N"},
        } ;
    }
    
    @Test
    public void testSimpleProgram(String start, String instructions, String end) throws IOException {
        String [] data = { "5 5", start, instructions };
        StringBuilder input = new StringBuilder();
        for(String line : data) {
            input.append(line).append(System.lineSeparator());
        }
        
        final String expectedOutput = end + System.lineSeparator();
        check(input.toString(), expectedOutput);
    }

    private void check(String input, String expectedOutput) throws IOException {
    	
    	InputStream in = new ByteArrayInputStream(input.getBytes());
    	
    	BufferedReader bfReader = new BufferedReader(new InputStreamReader(in));

        String actual = new String(bfReader.lines().collect(Collectors.joining()));
        
        Assert.assertEquals(actual, expectedOutput);
    }
}
