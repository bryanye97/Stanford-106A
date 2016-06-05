/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

	private String incorrectGuesses = "";
/** Resets the display so that only the scaffold appears */
	public void reset() {	
		double scaffoldX = getWidth()/2 - UPPER_ARM_LENGTH;
		double scaffoldTopY = getHeight()/2 - BODY_LENGTH - HEAD_RADIUS*2 - ROPE_LENGTH;
		double scaffoldBottomY = scaffoldTopY + SCAFFOLD_HEIGHT;
		GLine scaffold = new GLine (scaffoldX, scaffoldTopY, scaffoldX, scaffoldBottomY);
		add(scaffold);
		double beamExtensionX = scaffoldX + BEAM_LENGTH;
		GLine beam = new GLine (scaffoldX, scaffoldTopY, beamExtensionX, scaffoldTopY);
		add(beam);
		double ropeExtensionY = scaffoldTopY + ROPE_LENGTH;
		GLine rope = new GLine(beamExtensionX, scaffoldTopY, beamExtensionX, ropeExtensionY);
		add(rope);
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
				double x = getWidth()/4;
				double y = getHeight() - HEAD_RADIUS*2;
				GLabel hiddenWord = new GLabel(word, x, y);
				hiddenWord.setFont("Halvetica-24");
				if (getElementAt(x,y) != null){
					remove(getElementAt(x,y));
				}
				add(hiddenWord);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {
		incorrectGuesses += letter;
		double x = getWidth()/4;
		double y = getHeight() - HEAD_RADIUS;
		GLabel incorrectGuessLabel = new GLabel(incorrectGuesses, x, y);
		add(incorrectGuessLabel);
		
		int incorrectGuessNumber = incorrectGuesses.length();
		switch (incorrectGuessNumber) {
		case 1: 
			addHead();
			break;
		case 2: 
			addBody();
			break;
		case 3:
			addLeftArm();
			break;
		case 4:
			addRightArm();
			break;
		case 5:
			addLeftLeg();
			break;
		case 6:
			addRightLeg();
			break;
		case 7:
			addLeftFoot();
			break;
		case 8:
			addRightFoot();
			break;
		default:
			break;
		}
	}
	
	private void addHead() {
		double x = getWidth()/2 - HEAD_RADIUS + UPPER_ARM_LENGTH;
		double y = getHeight()/2 - BODY_LENGTH - HEAD_RADIUS*2;
		GOval head = new GOval (x, y, HEAD_RADIUS*2, HEAD_RADIUS*2);
		add(head);
		
	}
	private void addBody() {
		double x = getWidth()/2 + UPPER_ARM_LENGTH;
		double y1 = getHeight()/2 - BODY_LENGTH;
		double y2 = getHeight()/2;
		GLine body = new GLine(x,y1,x,y2);
		add(body);
		
		
	}
	private void addLeftArm() {
		double armStartX = getWidth()/2 + UPPER_ARM_LENGTH/2 + HEAD_RADIUS;
		double armEndX = getWidth()/2 + UPPER_ARM_LENGTH/2 + HEAD_RADIUS - UPPER_ARM_LENGTH;
		double upperArmHeightY = getHeight()/2 - BODY_LENGTH + ARM_OFFSET_FROM_HEAD;
		GLine upperLeftArm = new GLine (armStartX, upperArmHeightY, armEndX, upperArmHeightY);
		add(upperLeftArm);
		double lowerArmEndY = upperArmHeightY + LOWER_ARM_LENGTH;
		GLine lowerLeftArm = new GLine (armEndX, upperArmHeightY, armEndX, lowerArmEndY);
		add(lowerLeftArm);
	}
	private void addRightArm() {
		double armStartX = getWidth()/2 + UPPER_ARM_LENGTH/2 + HEAD_RADIUS;
		double armEndX = getWidth()/2 + UPPER_ARM_LENGTH/2 + HEAD_RADIUS + UPPER_ARM_LENGTH;
		double upperArmHeightY = getHeight()/2 - BODY_LENGTH + ARM_OFFSET_FROM_HEAD;
		GLine upperRightArm = new GLine (armStartX, upperArmHeightY, armEndX, upperArmHeightY);
		add(upperRightArm);
		double lowerArmEndY = upperArmHeightY + LOWER_ARM_LENGTH;
		GLine lowerRightArm = new GLine (armEndX, upperArmHeightY, armEndX, lowerArmEndY);
		add(lowerRightArm);
	}
	private void addLeftLeg() {
		double hipStartX = getWidth()/2 + UPPER_ARM_LENGTH/2 + HEAD_RADIUS;
		double hipEndX = hipStartX - HIP_WIDTH;
		double hipHeightY = getHeight()/2;
		GLine leftHip = new GLine(hipStartX, hipHeightY, hipEndX, hipHeightY);
		add(leftHip);
		double leftLegY = hipHeightY + LEG_LENGTH;
		GLine leftLeg = new GLine(hipEndX, hipHeightY, hipEndX, leftLegY);
		add(leftLeg);
	}
	private void addRightLeg() {
		double hipStartX = getWidth()/2 + UPPER_ARM_LENGTH/2 + HEAD_RADIUS;
		double hipEndX = hipStartX + HIP_WIDTH;
		double hipHeightY = getHeight()/2;
		GLine leftHip = new GLine(hipStartX, hipHeightY, hipEndX, hipHeightY);
		add(leftHip);
		double leftLegEndY = hipHeightY + LEG_LENGTH;
		GLine leftLeg = new GLine(hipEndX, hipHeightY, hipEndX, leftLegEndY);
		add(leftLeg);

	}
	private void addLeftFoot() {
		double footStartX = getWidth()/2 + UPPER_ARM_LENGTH/2 + HEAD_RADIUS - HIP_WIDTH;
		double footEndX = footStartX - FOOT_LENGTH;
		double footHeightY = getHeight()/2 + LEG_LENGTH;
		GLine leftFoot = new GLine(footStartX, footHeightY, footEndX, footHeightY);
		add(leftFoot);
	}
	private void addRightFoot() {
		double footStartX = getWidth()/2 + UPPER_ARM_LENGTH/2 + HEAD_RADIUS + HIP_WIDTH;
		double footEndX = footStartX + FOOT_LENGTH;
		double footHeightY = getHeight()/2 + LEG_LENGTH;
		GLine rightFoot = new GLine(footStartX, footHeightY, footEndX, footHeightY);
		add(rightFoot);
	}

	
/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;

}
