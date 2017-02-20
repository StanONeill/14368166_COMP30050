package poker;

import java.lang.reflect.Array;
import java.util.Random;
import java.util.ArrayList;

public class PlayingCard implements Comparable<PlayingCard>{
	
	// Initial Declaration of Variables
	static public final char HEARTS = 'H';
	static public final char DIAMONDS = 'D';
	static public final char SPADES = 'S';
	static public final char CLUBS = 'C';
	private int faceValue;
	private int gameValue;
	private String type;
	private char suit;
	
	// Each playing card is an object with 4 arguments that we will make an ArrayList of
	PlayingCard(int faceValue, int gameValue, String type, char suit ){
		this.faceValue = faceValue;
		this.gameValue= gameValue;
		this.type = type;
		this.suit = suit;
	}
	
	// Takes a card object and makes a string of it's type and suit
	public String toString(){
		String string = "";
		string += this.getType();
		string += this.getSuit();
		return string;
	}
	
	// returns the FaceVlaue of the card passed as an argument 
	public int getFaceValue(){
		return this.faceValue;
	}
	//returns the game value of the card passed as an argument 
	public int getGameValue(){
		return this.gameValue;
	}
	
	// returns the type of the card passed as an argument 
	public String getType(){
		return this.type;
	}
	
	// returns the suit of the card passsed as an argument 
	public char getSuit(){
		return this.suit;
	}

	@Override
	public int compareTo(PlayingCard card) {
		
	Integer a = this.gameValue;
	Integer b = card.gameValue;
		
	return a.compareTo(b);
	}
}	
	
	
	
