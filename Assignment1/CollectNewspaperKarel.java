/*
 * File: CollectNewspaperKarel.java
 * --------------------------------
 * At present, the CollectNewspaperKarel subclass does nothing.
 * Your job in the assignment is to add the necessary code to
 * instruct Karel to walk to the door of its house, pick up the
 * newspaper (represented by a beeper, of course), and then return
 * to its initial position in the upper left corner of the house.
 */

import stanford.karel.*;

public class CollectNewspaperKarel extends SuperKarel {

	// You fill in this part
	public void run() {
		pickUpNewspaper();
		pickUp();
		goBack();
		
	
}
	
	private void pickUpNewspaper() {
		move();
		move();
		turnRight();
		move();
		turnLeft();
		move();
		
	}
	
	private void pickUp() {
		pickBeeper ();
		turnAround();
	}
	
	private void goBack () {
		for (int i = 0; i < 3; i++) {
			move();
		}
		turnRight();
		move();
		turnRight();
	}
}
