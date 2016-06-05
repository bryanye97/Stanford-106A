/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;
	
	private double vx, vy;
	
	private int BRICK_COUNTER = NBRICKS_PER_ROW * NBRICK_ROWS;
	
	private static final int DELAY = 10;
	
	private RandomGenerator rgen = RandomGenerator.getInstance();
	


/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
		for (int turns = 0; turns < NTURNS; turns ++) {
			newRound();
			playRound();
			if (BRICK_COUNTER == 0) {
				ball.setVisible(false);
				winGame();
			} else if (BRICK_COUNTER > 0) {
				removeAll();
			}
		}
		loseGame();
	}
	private void newRound() {
		BRICK_COUNTER = NBRICKS_PER_ROW * NBRICK_ROWS;
		addBricks();
		addPaddle();
		addBall();	
	}
	
	private void playRound() {
		waitForClick();
		initializeBallVelocity();
		moveBall();
	}
	

	
	private void addBricks() {
		for (int column = 0; column < NBRICK_ROWS; column++) {
			for (int row = 0; row < NBRICKS_PER_ROW; row ++) {
				int x = (BRICK_WIDTH + BRICK_SEP) * column ;
				int y = BRICK_Y_OFFSET + (BRICK_HEIGHT + BRICK_SEP)*row;
				GRect brick = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
				switch (row) {
				case 0:
				case 1:
					brick.setColor(Color.RED);
					break;
				case 2:
				case 3:
					brick.setColor(Color.ORANGE);
					break;
				case 4:
				case 5:
					brick.setColor(Color.YELLOW);
					break;
				case 6:
				case 7:
					brick.setColor(Color.GREEN);
					break;
				case 8:
				case 9:
					brick.setColor(Color.CYAN);
					break;
				default:
					break;
				}
				brick.setFilled(true);
				add(brick);
			}
		}

	}
	
	private GRect paddle ;
	
	private void addPaddle() {
		paddle = new GRect((getWidth() - PADDLE_WIDTH)/2, getHeight() - PADDLE_Y_OFFSET, PADDLE_WIDTH, PADDLE_HEIGHT);
		add(paddle);
		addMouseListeners();
	}
	
	public void mouseMoved(MouseEvent e) {
		paddle.setLocation(e.getX() - PADDLE_WIDTH/2, getHeight() - PADDLE_Y_OFFSET);
	}
	
	private GOval ball;
	
	private void addBall() {
		ball = new GOval(getWidth()/2 - BALL_RADIUS, getHeight()/2 - BALL_RADIUS, 2*BALL_RADIUS, 2*BALL_RADIUS);
		ball.setFilled(true);
		add(ball);
	}
	

	
	private void initializeBallVelocity() {
		vy = 3.0;
		vx = rgen.nextDouble(1.0, 3.0);
		if (rgen.nextBoolean(0.5)) vx = -vx;		
	}
	
	private void moveBall() {
		while (true) {
			ball.move(vx,vy);
			if (ball.getX() <= 0 || ball.getX() + 2*BALL_RADIUS >= getWidth()
					) {
				vx = -vx;
			}
			
			GObject collider = getCollidingObject();
			if (collider == paddle) {
				vy = -vy;
			} else if (collider != null) {
				remove(collider);
				BRICK_COUNTER--;
				vy = -vy;
			}
			pause(DELAY);
			if (ball.getY() >= getHeight()) {
				break;
			}
		}
			
	}
	
	private GObject getCollidingObject() {
		if (getElementAt(ball.getX(), ball.getY()) != null) {
			return getElementAt(ball.getX(), ball.getY());
		} else if (getElementAt(ball.getX() + 2*BALL_RADIUS, ball.getY()) != null) {
			return getElementAt(ball.getX() + 2*BALL_RADIUS, ball.getY());
		} else if (getElementAt(ball.getX(), ball.getY() + 2*BALL_RADIUS) != null) {
			return getElementAt(ball.getX(), ball.getY() + 2*BALL_RADIUS);
		} else if (getElementAt(ball.getX() + 2*BALL_RADIUS, ball.getY() + 2*BALL_RADIUS) != null) {
			return getElementAt(ball.getX() + 2*BALL_RADIUS, ball.getY() + 2*BALL_RADIUS);
		} else {
			return null;
		}
	}
	
	GLabel resultLabel;
	
	private void winGame() {
		resultLabel = new GLabel("You win!");
		add(resultLabel, (getWidth() - resultLabel.getWidth())/2, getHeight()/2);
	}
	
	private void loseGame() {
		resultLabel = new GLabel("You lose!");
		add(resultLabel, (getWidth() - resultLabel.getWidth())/2, getHeight()/2);
	}
		
	
	
	

	
	
	}


