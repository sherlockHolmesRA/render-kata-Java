package com.kata.rover;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
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
