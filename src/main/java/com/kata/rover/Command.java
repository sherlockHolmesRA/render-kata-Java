package com.kata.rover;

public class Command {

    private int x;
    private int y;
    private Direction direction;
    private char[] navigations;
    
	public Command(int x, int y, Direction direction, char[] navigations) {
		super();
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.navigations = navigations;
	}


    public char[] getNavigations() {
        return navigations;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public void setNavigations(char[] navigations) {
		this.navigations = navigations;
	}

	static String navigationInputFormat = "^[LlrRmM]*$";


}