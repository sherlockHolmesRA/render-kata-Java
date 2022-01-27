package com.kata.rover;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RoverApplicationTests {
	
	 private Rover rover;
	    private RoverPosition position;
	    private final Direction direction = Direction.N;
	    private int x;
	    private int y;

	    @BeforeAll
	    public void beforeRoverTest() {
	        x = 0;
	        y = 0;
	        position = new RoverPosition(x, y, direction);
	        rover = new Rover(position);
	    }

	    @Test
	    public void roverControlMoveForwardWhenM() throws Exception {
	        int expected = y + 1;
	        rover.roverControl('M');
	        assertThat(rover.getRoverPosition().getY()).isEqualTo(expected);
	    }

	    @Test
	    public void roverControlTurnLeftWhenL() throws Exception {
	        rover.roverControl('L');
	        assertThat(rover.getRoverPosition().getDirection()).isEqualTo(Direction.W);
	    }

	    @Test
	    public void roverControlTurnRightWhenR() throws Exception {
	        rover.roverControl('R');
	        assertThat(rover.getRoverPosition().getDirection()).isEqualTo(Direction.E);
	    }

	    @Test
	    public void receiveCommandsShouldBeAbleToReceiveMultipleCommands() throws Exception {
	        int expected = x + 1;
	        rover.receiveCommands("LMLMLMLMM");
	        assertThat(rover.getRoverPosition().getX()).isEqualTo(expected);
	        assertThat(rover.getRoverPosition().getDirection()).isEqualTo(Direction.S);
	    }

}
