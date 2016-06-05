/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import acm.util.*;
import java.io.*;
import java.util.*;


	public class HangmanLexicon {

		private ArrayList <String> wordList = new ArrayList <String> ();	

		public HangmanLexicon() {
			try {
				BufferedReader hangmanWords = new BufferedReader(new FileReader("HangmanLexicon.txt"));
				while(true) {
					String line = hangmanWords.readLine();
					if(line == null) break;
					wordList.add(line);
				}
				hangmanWords.close();
			} catch (IOException ex) {
				throw new ErrorException(ex);
			}
		}


	
		public String getWord(int index) {
			return wordList.get(index);
		}

		
		public int getWordCount() {
			return wordList.size();
		}	
	}
	

