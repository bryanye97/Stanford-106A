/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {
	
	private HangmanLexicon hangmanLexicon = new HangmanLexicon();
	
	private HangmanCanvas canvas;
	
	private String word;
	
	private String hiddenWord;
	
	private String userGuess;
	
	private RandomGenerator rgen = RandomGenerator.getInstance();
	
	private int guessCounter = 8;
	
	 public void init() {
         canvas = new HangmanCanvas();
         add(canvas);
	 }

    public void run() {

		canvas.reset();
		setup();
		
		while (guessCounter > 0) {
			userGuess = readLine("Your guess: ");
			if (userGuess.length() != 1) {
				println("Please enter one character at a time!");
			} else {
				char userGuessCharacter = userGuess.charAt(0);
				
				if (Character.isLowerCase(userGuessCharacter)) {
					userGuessCharacter = Character.toUpperCase(userGuessCharacter);
					
					if (word.indexOf(userGuessCharacter) != -1) {
						println("That guess is correct.");
						for (int i = 0; i < word.length(); i++) {
							if (userGuessCharacter == word.charAt(i)) {
								hiddenWord = hiddenWord.substring(0,i) + userGuessCharacter + hiddenWord.substring(i+1);
							}
						}
						if (hiddenWord.equals(word)) {
							println("You guessed the word: " + word);
							println("You win");
							break;
						}
						println("The word now looks like this: " + hiddenWord);
						canvas.displayWord(hiddenWord);
					} else {
						println("There are no " + userGuessCharacter + "'s in the word");
						canvas.noteIncorrectGuess(userGuessCharacter);
						guessCounter--;
						println("The word now looks like this: " + hiddenWord);
						canvas.displayWord(hiddenWord);
						println("You have " + guessCounter + " guesses left.");
					}
				}
			}
		}	
		if (guessCounter == 0) {
			println("You're completely hung.");
			println("The word was: " + word);
			println("You lose.");
		}
    }
    
    private void setup() {
		println("Welcome to Hangman!");
		int wordLexiconIndex = rgen.nextInt(0, (hangmanLexicon.getWordCount() - 1));
		word = hangmanLexicon.getWord(wordLexiconIndex);
		hiddenWord = stringToUnderscores(word);
		println("The word now looks like this: " + hiddenWord);
    }
    
    private String stringToUnderscores(String word) {
    	String underscoreString = "";
    	for (int i=0; i < word.length(); i++) {
    		underscoreString += "_";
    	}
    	return underscoreString;
    }

}
