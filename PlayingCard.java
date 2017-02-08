package poker;

import java.lang.reflect.Array;
import java.util.Random;
import java.util.ArrayList;


public class PlayingCard {
	
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
	public static String toString(PlayingCard card){
		String string = "";
		string += getType(card);
		string += getSuit(card);
		
		return string;
	}
	
	public static void shuffle(ArrayList<PlayingCard> Deck){
		int i = 0, j, k;
		PlayingCard temp = new PlayingCard(0, 0, "A", 'A');
		Random rnd = new Random();
		//rnd.setSeed(44);
		while(i < 2704){
				j = rnd.nextInt(52);
				k = rnd.nextInt(52);
				temp = Deck.get(j);
				Deck.set(j, Deck.get(k));
				Deck.set(k, temp);
			i++;
		}
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
	public static String getType(PlayingCard card){
		return card.type;
	}
	
	// returrns the suit of the card passsed as an argument 
	public static char getSuit(PlayingCard card){
		return card.suit;
	}
	
	// Creates the deck of cards unshuffled
	public static void createDeck(ArrayList<PlayingCard> Deck){
		int i = 0; // counter variable for the 52 cards in a deck
		int j =0; // counter variable for the 13 cards in a suit gets reset once it hits 13
		int[] gameValues = {14,2,3,4,5,6,7,8,9,10,11,12,13};
		String[] Type = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
		
		for(i = 0; i < 52; i++){
			
			if(j == 13){
				j=0;
			}
			//first 13 cards will be the suit of hearts
			if(i < 13){
				Deck.add(new PlayingCard(j+1, gameValues[j], Type[j], HEARTS));
			}
			// the second group of 13 cars will be the suit of daimonds
			if(i<=13 && i < 26){
				Deck.add(new PlayingCard(j+1, gameValues[j], Type[j], DIAMONDS));
			}
			// the third set of 13 cards will be the suit of spades
			if(i<=26 && i <39){
				Deck.add(new PlayingCard(j+1, gameValues[j], Type[j], SPADES));
			}
			//the last 13 cards will be the suit of clubs
			if(i <=39){
				Deck.add(new PlayingCard(j+1, gameValues[j], Type[j], CLUBS));
			}
			j++;		
			
	}
}
	
	public static void main(String Args[]){
		// testsing to see if it works in main
		int i = 0;
		ArrayList<PlayingCard> Deck = new ArrayList<PlayingCard>();
		createDeck(Deck);
		
		System.out.println("Unshuffled Deck: \n\n");
		// for loop to print out the string of every card in the deck
		for(i = 0; i < 52; i++){
			PlayingCard card = Deck.get(i);
			String string = toString(card);
			System.out.println(string);
		}
		shuffle(Deck);
		System.out.println("Shuffled Deck: \n\n");
		for(i = 0; i < 52; i++){
			PlayingCard card = Deck.get(i);
			String string = toString(card);
			System.out.println(string);
		}
	}
}
