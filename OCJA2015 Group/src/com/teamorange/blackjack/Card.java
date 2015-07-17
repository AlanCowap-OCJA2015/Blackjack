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
	int rank;		// 1 -13
	String[] rankNames = { 
		"", "Ace", "Two", "Three", "Four", 
		"Five", "Six", "Seven", "Eight", 
		"Nine", "Ten", "Jack", "Queen", "King"
	};
	Suit suit;
	Owner owner;

	public Card(Suit suit, int value) {
		this.rank= value;
		this.suit = suit;
		this.owner = Owner.DECK;		
	}

	public void showCard() {
		System.out.println(getRankName(rank) + " of " + suit.name());
	}
	
	public String getRankName(int r) {
		return rankNames[r];
	}
	
	public int getCardScore() {
		
		if (this.rank > 10) {
			return 10;
		} else if (this.rank == 1){
			return 11;
		} else {
			return this.rank;
		}
	}
	
	public void setOwner(Owner owner) {
		if (this.owner == Owner.DECK) this.owner = owner;
	}

	public Owner getOwner() {
		return this.owner;
	}
}
