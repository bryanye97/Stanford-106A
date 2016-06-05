/*
 * File: ProgramHierarchy.java
 * Name: 
 * Section Leader: 
 * ---------------------------
 * This file is the starter file for the ProgramHierarchy problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class ProgramHierarchy extends GraphicsProgram {	
	private static final int boxWidth = 150;
	private static final int boxHeight = 50;
	public void run() {
		appendProgram();
		appendConsole();
		appendGraphics();
		appendDialog();
		GLine consoleLine = new GLine(getWidth()/2, (getHeight() + boxHeight)/2, getWidth()/2, getHeight()/2 + boxHeight);
		add(consoleLine);
		GLine graphicsLine = new GLine(getWidth()/2, (getHeight() + boxHeight)/2, getWidth()/2 - boxHeight/2 - boxWidth, getHeight()/2 + boxHeight);
		add(graphicsLine);
		GLine dialogLine = new GLine(getWidth()/2, (getHeight() + boxHeight)/2, getWidth()/2 + boxHeight/2 + boxWidth, getHeight()/2 + boxHeight);
		add(dialogLine);
	}
	private void appendProgram() {
		double x = (getWidth() - boxWidth)/2;
		double y = (getHeight() - boxHeight)/2;
		GRect programBox = new GRect(x, y, boxWidth, boxHeight);
		add(programBox);
		GLabel programLabel = new GLabel("Program");
		add(programLabel, (getWidth() - programLabel.getWidth())/2, (getHeight() + programLabel.getAscent())/2);
	}
	
	private void appendConsole() {
		double x = (getWidth() - boxWidth)/2;
		double y = getHeight()/2 + boxHeight;
		GRect consoleBox = new GRect(x, y, boxWidth, boxHeight);
		add(consoleBox);
		GLabel consoleLabel = new GLabel("ConsoleProgram");
		add(consoleLabel, (getWidth() - consoleLabel.getWidth())/2, (getHeight() + consoleLabel.getAscent())/2 + 1.5*boxHeight);
	}
	
	private void appendGraphics() {
		double x = (getWidth() - boxWidth - boxHeight)/2 - boxWidth ;
		double y = getHeight()/2 + boxHeight;
		GRect graphicsBox = new GRect(x, y, boxWidth, boxHeight);
		add(graphicsBox);
		GLabel graphicsLabel = new GLabel("GraphicsProgram");
		add(graphicsLabel, (getWidth() - graphicsLabel.getWidth() - boxHeight)/2 - boxWidth, (getHeight() + graphicsLabel.getAscent())/2 + 1.5*boxHeight);
	}
	
	private void appendDialog() {
		double x = (getWidth() - boxWidth + boxHeight)/2 + boxWidth;
		double y = getHeight()/2 + boxHeight;
		GRect dialogBox = new GRect(x, y, boxWidth, boxHeight);
		add(dialogBox);
		GLabel dialogLabel = new GLabel("ConsoleProgram");
		add(dialogLabel, (getWidth() - dialogLabel.getWidth() + boxHeight)/2 + boxWidth, (getHeight() + dialogLabel.getAscent())/2 + 1.5*boxHeight);
	}
	




}

