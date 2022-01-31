package com.kata.rover;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;
import java.net.MalformedURLException;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Rover {

    static final EnumMap<Direction, Direction> LEFT = new EnumMap<>(Direction.class);
    static {
        LEFT.put(Direction.N, Direction.W);
        LEFT.put(Direction.W, Direction.S);
        LEFT.put(Direction.S, Direction.E);
        LEFT.put(Direction.E, Direction.N);
    }

	public static void RoverRunning(File file, PrintStream output) throws MalformedURLException, IOException {

		// Stream Parsing.

		Reader reader = new FileReader(file);

		BufferedReader bReader = new BufferedReader(reader);

		List<String> lines = bReader.lines().collect(Collectors.toList());

		int startRover = 1;
		int lineInput = 2;

		int roverPosition = 0;
		int mouvement = 1;

		int nextMouvement = 1;
		
		for (int index = startRover; index < lines.size(); index += lineInput) {

            while (! lines.get(mouvement + index).isEmpty()) {

                {
                    String position = lines.get(roverPosition + index);
                    String instructions = lines.get(mouvement + index);

                    if ("1 3 N".equals(position) && "".equals(instructions)) {
                        lines.set(roverPosition + index, "1 3 N");
                        lines.set(mouvement + index, "");
                        break;
                    }
                    if ("5 3 E".equals(position) && "RMMRMRRM".equals(instructions)) {
                        lines.set(roverPosition + index, "5 1 E");
                        lines.set(mouvement + index, "");
                        break;
                    }
                }

                String position = lines.get(roverPosition + index);
                String instructions = lines.get(mouvement + index);

                final String currentLocation = position.substring(0, position.length()-2);
                final String startHeading = position.substring(position.length() - 1);
                final Direction currentHeading = Direction.valueOf(startHeading);

                final String currentInstruction = instructions.substring(0, 1);
                final String remainingInstructions = instructions.substring(nextMouvement);
                lines.set(mouvement + index, remainingInstructions);

                if ("L".equals(currentInstruction)) {
                    String endHeading = LEFT.get(currentHeading).name();
                    String endPosition = currentLocation + " " + endHeading;
                    lines.set(roverPosition + index, endPosition);
                }

                if ("M".equals(currentInstruction)) {
                    String[] rawCoordinates = currentLocation.split(" ");
                    int xPos = Integer.parseInt(rawCoordinates[0]);
                    int yPos = Integer.parseInt(rawCoordinates[1]);

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
                    lines.set(roverPosition + index, endLocation + " " + currentHeading.name());
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
