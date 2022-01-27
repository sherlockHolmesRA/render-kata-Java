package com.kata.rover;

public class RoverPosition {
	
	private int x;
	private int y;
	private Direction direction;
	
	public RoverPosition(int x2, int y2, Direction direction){
		this.x = x2;
		this.y = y2;
		this.direction = direction;
	}

	public void changeDirectionLeft() {
		this.direction = Direction.W;
	}
	
	public void changeDirectionRight() {
		this.direction = Direction.E;
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
		
	}

}
