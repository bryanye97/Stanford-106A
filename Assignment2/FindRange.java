/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	private static final int sentinel = 0;
	
	public void run() {
		println("This program finds the largest an smallest numbers");
		int smallestNumber = 0;
		int largestNumber = 0;
		while (true) {
			int number = readInt("? ");
			if (number == sentinel) {
				break;
			} else if (number > largestNumber) {
				largestNumber = number;
			} else if (number < smallestNumber) {
				smallestNumber = number;
			}	
		}
		println("smallest: " + smallestNumber);
		println("largest: " + largestNumber);
	}
}

