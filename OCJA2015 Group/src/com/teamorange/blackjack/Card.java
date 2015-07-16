/**
 * Created 16 Jul 2015
 * @author Kevin Phair
 * 
 */
package com.teamorange.blackjack;

/**
 * 
 * @author Mubasher Mohammed, Kevin Phair, Jenn Quinn, Seamus O'Toole
 * @version 1.0
 */
public class Card {

	enum Suit { DIAMONDS, HEARTS, CLUBS, SPADES }
	enum Owner { DECK, DEALER, PLAYER}
	int faceValue;		// 1 -13
	
	public int getPlayerScore() {
		
		if (this.faceValue > 10) {
			return 10;
		} else if (this.faceValue == 1){
			return 11;
		} else {
			return this.faceValue;
		}
	}
	
	
}
