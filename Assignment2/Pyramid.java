/*
 * File: Pyramid.java
 * Name: 
 * Section Leader: 
 * ------------------
 * This file is the starter file for the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Pyramid extends GraphicsProgram {

/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

/** Width of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 14;
	
	public void run() {
		for (int row = 0; row < BRICKS_IN_BASE; row++) {
			int numberOfBricksInRow = BRICKS_IN_BASE - row;
			for (int column = 0; column < numberOfBricksInRow; column++) {
				int x = (getWidth() - BRICK_WIDTH * numberOfBricksInRow)/2 + column * BRICK_WIDTH ;
				int y = getHeight() - (row+1) * BRICK_HEIGHT;
				GRect brick = new GRect(x, y, BRICK_WIDTH,BRICK_HEIGHT);
				add(brick);
			}
			
		}
	}
}

