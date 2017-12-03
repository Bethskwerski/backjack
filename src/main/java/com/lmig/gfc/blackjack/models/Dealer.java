package com.lmig.gfc.blackjack.models;


public class Dealer {

	// Class for the house

	private Hand dealerHand;

	public Dealer() {
		dealerHand = new Hand();
	}

	public Hand getDealerHand() {
		return dealerHand;
	}
public void addToHand(Card card) {
	dealerHand.accept(card);
	
}
}
