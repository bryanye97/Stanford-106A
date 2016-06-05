/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {	
	private static final double BIG_RED_RADIUS = 72;
	private static final double  MIDDLE_WHITE_RADIUS = 0.65 * 72;
	private static final double  SMALL_RED_RADIUS = 0.3 * 72;
			
	public void run() {
		
		
		int x = getWidth()/2;
		int y = getHeight()/2;
		GOval bigRedCircle = new GOval(x - BIG_RED_RADIUS , y  - BIG_RED_RADIUS, BIG_RED_RADIUS*2, BIG_RED_RADIUS*2);
		GOval middleWhiteCircle = new GOval(x - MIDDLE_WHITE_RADIUS, y - MIDDLE_WHITE_RADIUS, MIDDLE_WHITE_RADIUS*2, MIDDLE_WHITE_RADIUS*2);
		GOval smallRedCircle = new GOval(x - SMALL_RED_RADIUS, y - SMALL_RED_RADIUS , SMALL_RED_RADIUS*2, SMALL_RED_RADIUS*2);
		
		bigRedCircle.setColor(Color.RED);
		middleWhiteCircle.setColor(Color.WHITE);
		smallRedCircle.setColor(Color.RED);
		bigRedCircle.setFilled(true);
		middleWhiteCircle.setFilled(true);
		smallRedCircle.setFilled(true);
		
		
		
		add(bigRedCircle);
		add(middleWhiteCircle);
		add(smallRedCircle);
		
	};
}
