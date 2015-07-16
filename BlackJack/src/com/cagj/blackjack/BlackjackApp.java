/**
 * 
 */
package com.cagj.blackjack;

/**
 * @author User1
 *
 */
public class BlackjackApp {
	Card[] deck = new Card[52];
	
	public static void main(String[] args) {
		BlackjackApp bja = new BlackjackApp();
		bja.runGame();
	}
	
	void runGame(){
		System.out.print("Playing Game");
	}
	
	void createDeck(){
		for(int i = 0; i < deck.length; ++i){
			deck[i] = new Card();
		}
	}

}
