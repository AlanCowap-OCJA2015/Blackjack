/**
 * Created 16 Jul 2015
 * @author Kevin Phair
 * 
 */
package com.teamorange.blackjack;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Mubasher Mohammed, Kevin Phair, Jenn Quinn, Seamus O'Toole
 * @version 1.0
 *
 */
public class BlackJackApp {

	static int currentCard = 0;

	Card[] deck = new Card[52];
	Scanner scan = new Scanner(System.in);

	public static void main(String[] args){
		BlackJackApp ba = new BlackJackApp();
		ba.runApp();
	} //end main

	public boolean playOneHand(){
		initaliseDeck();
		shuffleDeck();
		int playerScore = 0;
		int dealerScore = 0;
		this.drawCard(Card.Owner.DEALER);
		System.out.println();
		this.drawCard(Card.Owner.PLAYER);
		for(int i=0;;++i){
			this.drawCard(Card.Owner.PLAYER);
			playerScore = this.getScore(Card.Owner.PLAYER);
			System.out.println("Player's score is " + playerScore);
			if (playerScore > 21){
				System.out.println("Player is bust. Dealer wins!");
				return true;
			}else if (playerScore == 21){
				System.out.println("Blackjack! Player wins.");
				return false;
			}else if (i == 3){
				System.out.println("Five card Charlie! Player wins.");
			}else if (playerScore > 16){
				System.out.println("Player sticks at " + playerScore);
				break;
			}
		}
		System.out.println();
		for(;;){
			this.drawCard(Card.Owner.DEALER);
			dealerScore = this.getScore(Card.Owner.DEALER);
			System.out.println("Dealer's score is " + dealerScore);
			if (dealerScore > 21){
				System.out.println("Dealer is bust. Player wins!");
				return false;
			} else if (dealerScore >= playerScore){
				System.out.println("Dealer Wins!");
				return true;
			}
		}
	}

	private void runApp(){
		int numGames = 11;
		int player1Wins = 0; 
		int player2Wins = 0; 


		for (int i = 0; i < numGames; ++i){
			if (i%2 == 0){
				System.out.println("\nStart of new game. Adam is the dealer.\n");
				if (this.playOneHand()){
					player1Wins++;
				} else {
					player2Wins++;
				}
			} else {
				System.out.println("\nStart of new game. Bob is the dealer.\n");
				if (this.playOneHand()){
					player2Wins++;
				} else {
					player1Wins++;
				}
			}
			System.out.printf("\nCurrent scores \n Adam: %d \n Bob: %d\n", player1Wins, player2Wins);
			if (i == numGames - 1) {
				if (player1Wins > player2Wins) {
					System.out.println("Adam was the winner out of " + numGames + " games.");
				} else {
					System.out.println("Bob was the winner out of " + numGames + " games.");
				}
				System.out.println("That is the end of the tournament.");
				break;
			} else {
				System.out.println("Press enter to show next round");
				try {
					System.in.read();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			try {
				System.in.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	public void initaliseDeck() {

		currentCard = 0;

		for (int i = 0; i < 13; ++i) {
			deck[i] = new Card(Card.Suit.HEARTS, (i + 1));
			deck[i + 13] = new Card(Card.Suit.DIAMONDS, (i + 1));
			deck[i + 26] = new Card(Card.Suit.SPADES, (i + 1));
			deck[i + 39] = new Card(Card.Suit.CLUBS, (i + 1));
		}
	}

	public void shuffleDeck() {
		Card firstCard = null;
		Card secondCard = null;
		int pick1;
		int pick2;

		for (int i = 0; i < 100; ++i) {
			pick1 = new Random().nextInt(52);
			pick2 = new Random().nextInt(52);
			firstCard = deck[pick1];
			secondCard = deck[pick2];

			deck[pick2] = firstCard;
			deck[pick1] = secondCard;
		}
	}

	public void drawCard(Card.Owner owner) {
		deck[currentCard].setOwner(owner);
		System.out.print(owner.name() + " draws ");
		deck[currentCard].showCard();
		currentCard++;
	}

	public int getScore(Card.Owner owner) {
		int score = 0;
		boolean isAce = false;
		for (int i = 0; i < deck.length; ++i) {
			if (deck[i].getOwner() == owner) {
				score += deck[i].getCardScore();
				if(deck[i].getCardScore() == 11){
					isAce = true;
				}
			}
		}
		if(isAce && score > 21){
			score -= 10;
		}
		return score;
	}
}
