/*
 * File: PythagoreanTheorem.java
 * Name: 
 * Section Leader: 
 * -----------------------------
 * This file is the starter file for the PythagoreanTheorem problem.
 */

import acm.program.*;

public class PythagoreanTheorem extends ConsoleProgram {
	public void run() {
		println("Enter values to compute Pythagorean Theorem");
		int a = readInt ("a: "); 
		int b = readInt ("b: ");
		double c = computePythagoreanTheorem(a,b);
		println("c: "+ c);
	}
	
	private double computePythagoreanTheorem(int x, int y)  {
		double z = Math.sqrt((double)x*x + y*y);
		return z;
	}
}
