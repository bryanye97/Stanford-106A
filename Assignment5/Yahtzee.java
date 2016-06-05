/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;
import java.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
	public static void main(String[] args) {
		new Yahtzee().start(args);
	}
	
	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		while (true) {
			if (nPlayers > MAX_PLAYERS) {
				nPlayers = dialog.readInt("You can only enter up to " + MAX_PLAYERS + " number of players. Enter number of players");
			} else {
				break;
			}
		}
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
	}

	private void playGame() {
		categoryScores = new int[N_CATEGORIES][nPlayers];
		for (int turns = 0; turns < N_SCORING_CATEGORIES; turns ++) {
			for (int player = 1; player <= nPlayers; player++) {
				initializeFirstRoll(player);
				initializeSecondAndThirdRoll(player);
				selectCategory(player);
			}
		}
	totalResults();
	calculateWinner();
	}
		
	private void initializeFirstRoll(int playerNumber) {
		for(int i = 0; i < N_DICE; i++) {
			int dieRoll = rollDice();
			dieResults[i] = dieRoll;
		}
		display.printMessage(playerNames[playerNumber - 1] + "'s turn! Click the " + "\"Roll Dice\" " + "button to roll the dice.");
		display.waitForPlayerToClickRoll(playerNumber);
		display.displayDice(dieResults);
	}
	
	private void initializeSecondAndThirdRoll(int playerNumber) {
		for (int i=0; i < 2; i++) {
			display.printMessage("Select the dice you wish to re-roll and click " + "\"Roll Again\"");
			display.waitForPlayerToSelectDice();
			for (int j = 0; j < N_DICE; j++) {
				if (display.isDieSelected(j) == true) {
					int dieRoll = rollDice();
					dieResults[j] = dieRoll;
				}
			}
			display.displayDice(dieResults);
		}
		
	}
	
	private void selectCategory(int playerNumber) {
		display.printMessage("Select a category for this roll");
		while (true) {
			category = display.waitForPlayerToSelectCategory();
			if (checkCategory(playerNumber)) {
				calculateCategoryScore(playerNumber);
				break;
			}
			display.printMessage("You have already selected this category. Please select another one");
		}
	}
	
	private boolean checkCategory(int playerNumber) {
		if (categoryScores[category-1][playerNumber-1] == 0 && category != UPPER_SCORE && category != UPPER_BONUS && category != LOWER_SCORE && category != TOTAL) {
			return true;
		}
		return false;
	}
	
	private void calculateCategoryScore(int playerNumber) {
		int score = 0;
		
		if (category >= ONES && category <= SIXES) {
			for (int i = 0; i <N_DICE; i++) {
				score += category;
			}
		} else if (category == THREE_OF_A_KIND || category == FOUR_OF_A_KIND) {
			if (checkSpecialCategory()) {
				for (int i = 0; i <N_DICE; i++) {
					score += dieResults[i];
				}
			} else {
				score = 0;
			}
		} else if (category == FULL_HOUSE) {
			if (checkSpecialCategory()) {
				score += 25;
			} else {
				score = 0;
			}
		} else if (category == SMALL_STRAIGHT) {
			if (checkSpecialCategory()) {
				score += 30;
			} else {
				score = 0;
			}
		} else if (category == LARGE_STRAIGHT) {
			if (checkSpecialCategory()) {
				score += 40;
			} else {
				score = 0;
			}
		} else if (category == YAHTZEE) {
			if (checkSpecialCategory()) {
				score += 50;
			} else {
				score = 0;
			}
		} else if (category == CHANCE) {
			for (int i=0; i<N_DICE; i++) {
				score += dieResults[i];
			}
		}
		display.updateScorecard(category, playerNumber, score);
		categoryScores[category-1][playerNumber-1] = score;
	}
	
	private boolean checkSpecialCategory() {
		ArrayList <Integer> one = new ArrayList<Integer>();
		ArrayList <Integer> two = new ArrayList<Integer>();
		ArrayList <Integer> three = new ArrayList<Integer>();
		ArrayList <Integer> four = new ArrayList<Integer>();
		ArrayList <Integer> five = new ArrayList<Integer>();
		ArrayList <Integer> six = new ArrayList<Integer>();
		
		for (int i=0; i<N_DICE; i++) {
			if (dieResults[i] == 1) {
				one.add(1);
			} else if (dieResults[i] == 2) {
				two.add(1);
			} else if (dieResults[i] == 3) {
				three.add(1);
			} else if (dieResults[i] == 4) {
				four.add(1);
			} else if (dieResults[i] == 5) {
				five.add(1);
			} else if (dieResults[i] == 6) {
				six.add(1);
			}
		}
		
		if (category == THREE_OF_A_KIND) {
			if (one.size() >= 3 || two.size() >= 3 || three.size() >= 3 || four.size() >= 3 || five.size() >= 3 || six.size() >= 3) {
				return true;
			}
		} else if (category == FOUR_OF_A_KIND) {
			if (one.size() >= 4 || two.size() >= 4 || three.size() >= 4 || four.size() >= 4 || five.size() >= 4 || six.size() >= 4) {
				return true;
			}
		} else if (category == FULL_HOUSE) {
			if (one.size() == 3 || two.size() == 3 || three.size() == 3 || four.size() == 3 || five.size() == 3 || six.size() == 3) {
				if (one.size() == 2 || two.size() == 2 || three.size() == 2 || four.size() == 2 || five.size() == 2 || six.size() == 2) {
					return true;
				}
			}
		} else if (category == SMALL_STRAIGHT) {
			if ((one.size() >= 1 && two.size() >= 1 && three.size() >= 1 && four.size() >= 1 ) || (two.size() >= 1 && three.size() >= 1 && four.size() >= 1 && five.size() >= 1 ) || (three.size() >= 1 && four.size() >= 1 && five.size() >= 1 && six.size() >= 1)) {
				return true;
			}
		} else if (category == LARGE_STRAIGHT) {
			if ((one.size() >= 1 && two.size() >= 1 && three.size() >= 1 && four.size() >= 1 && five.size() >= 1 ) || (two.size() >= 1 && three.size() >= 1 && four.size() == 1 && five.size() >= 1 && six.size() >= 1 )) {
				return true;
			}
		} else if (category == YAHTZEE) {
			if (one.size() >= 5 || two.size() >= 5 || three.size() >= 5 || four.size() >= 5 || five.size() >= 5 || six.size() >= 5) {
				return true;
			}
		} 
		return false;
	}
	
	private void totalResults() {
		int result = 0;
		
		for (int i = 0; i < nPlayers; i++) {
			for (int j = 0; j < SIXES; j++) {
				result += categoryScores[j][i];
			}
			categoryScores[UPPER_SCORE - 1][i] = result;
			display.updateScorecard(UPPER_SCORE, i+1, result);
			
			if (categoryScores[UPPER_SCORE - 1][i] > 63) {
				categoryScores[UPPER_BONUS - 1][i] = 35;
				display.updateScorecard(UPPER_BONUS, i, 35);
			} else {
				categoryScores[UPPER_BONUS - 1][i] = 0;
				display.updateScorecard(UPPER_BONUS, i, 0);
			}
			
			result = 0;
			
			for (int j = 8; j < CHANCE; j++) {
				result += categoryScores[j][i];
			}
			categoryScores[LOWER_SCORE - 1][i] = result;
			display.updateScorecard(LOWER_SCORE, i+1, result);
			
			categoryScores[TOTAL-1][i] = categoryScores[UPPER_SCORE - 1][i] + categoryScores[UPPER_BONUS - 1][i] + categoryScores[LOWER_SCORE - 1][i];
			
			display.updateScorecard(TOTAL, i+1, categoryScores[TOTAL-1][i]);
			
			result = 0;
		}
	}
	
	private void calculateWinner() {
		int winner = 0;
		int score = 0;
		
		for (int i = 0; i < nPlayers; i++) {
			if (categoryScores[TOTAL-1][i] > score) {
				score = categoryScores[TOTAL-1][i];
				winner = i;
			}
		}
		
		display.printMessage("Congratulations, " + playerNames[winner] + " you're the winner with a total score of " + score +"!");
	}
	private int rollDice() {
		return rgen.nextInt(1,6);
	}

	
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();
	private int[] dieResults = new int [N_DICE];
	private int [][] categoryScores;
	private int category;
	private int [][] selectedCategories;

}
