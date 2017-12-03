package com.lmig.gfc.blackjack.models;

public class Player {

	/* Class for our player
	 * I started with my player having  wallet
	 * However, I decided it really wasn't a wallet but a bankroll.
	 *A bankroll represents the amount of money a player has on the table.
	 *It does not represent the amount of money the player has on their person.
	 *For that reason- I moved it to the game
	 */ 


	private Hand playerHand;

	public Player() {
		playerHand = new Hand();
		
	}
	public Hand getPlayerHand() {
		return playerHand;
	}
	public void addToHand(Card card) {
		playerHand.accept(card);
		
	}

	

}
