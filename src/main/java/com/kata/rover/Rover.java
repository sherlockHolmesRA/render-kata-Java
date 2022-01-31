package com.kata.rover;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;
import java.net.MalformedURLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Rover {

	public static void RoverRunning(File file, PrintStream output) throws MalformedURLException, IOException {

		Reader reader = new FileReader(file);

		BufferedReader bReader = new BufferedReader(reader);

		List<String> lines = bReader.lines().collect(Collectors.toList());
		
		for (int index = 1; index < lines.size(); index += 2) {

            while (! lines.get(index + 1).isEmpty()) {

                String position = lines.get(index);
				String instructions = lines.get(index + 1);

                final String currentLocation = position.substring(0, position.length()-1);
                final String startHeading = position.substring(position.length() - 1);
                final Direction currentHeading = Direction.valueOf(startHeading);

                final String currentInstruction = instructions.substring(0, 1);
                final String remainingInstructions = instructions.substring(1);
                lines.set(index + 1, remainingInstructions);

                String endHeading = currentHeading.name();
                String endPosition = currentLocation + " " + endHeading;
                
                if ("R".equals(currentInstruction)) {
                    lines.set(index + 1, endPosition);
                }

                
                if ("M".equals(currentInstruction)) {
                    String[] rawCoordinates = currentLocation.split(" ");
                    int xPos = Integer.parseInt(rawCoordinates[0]);
                    int yPos = Integer.parseInt(rawCoordinates[0]);

                    if (Direction.W.equals(currentHeading)) {
                        xPos -= 1;
                    }

                    if (Direction.E.equals(currentHeading)) {
                        xPos += 1;
                    }

                    if (Direction.S.equals(currentHeading)) {
                        yPos -= 1;
                    }

                    if (Direction.N.equals(currentHeading)) {
                        yPos += 1;
                    }

                    String endLocation = xPos + " " + yPos;
                    lines.set(index, endLocation + " " + currentHeading.name());
                }
            }
		}	

		IntStream.range(0, lines.size()).filter(n -> (n % 2) == 1).mapToObj(n -> lines.get(n)).forEach(output::println);

		bReader.close();
	}

	public static void main(String[] args) throws MalformedURLException, IOException {

		SpringApplication.run(Rover.class, args);

		File file = new File("input.txt");

		RoverRunning(file, System.out);
	}

}
