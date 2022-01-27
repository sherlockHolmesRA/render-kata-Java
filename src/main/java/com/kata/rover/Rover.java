package com.kata.rover;

public class Rover {
	
	private RoverPosition position;

	public Rover(RoverPosition position) {
		this.position = position;
	}

	public RoverPosition getRoverPosition() {
		return position;
	}

	public void setRoverPosition(RoverPosition position) {
		this.position = position;
	}
	
	public void receiveCommands(String commands) throws Exception {
        for (char command : commands.toCharArray()) {
            if (!roverControl(command)) {
                break;
            }
        }
    }
	public boolean roverControl(char command) throws Exception {
        switch(Character.toUpperCase(command)) {
            case 'M':
                return true;
            case 'L':
                this.position.changeDirectionLeft();
                return true;
            case 'R':
            	this.position.changeDirectionLeft();
                return true;
            default:
                throw new Exception("Command " + command + " is unknown");
        }
    }
	
	public String getPosition() {
        return getRoverPosition().toString();
    }

}






