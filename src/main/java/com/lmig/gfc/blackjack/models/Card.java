package com.lmig.gfc.blackjack.models;

public abstract class Card {
	//The Card class is the super class for the AceCard, NumberCard, and FaceCArd classes. 
	private Suits suit;

	public Card(Suits suit) {
		this.suit = suit;
	}

	public Suits getSuit() {
		return suit;
	}
	public abstract String getFace();
	public abstract int getValue();

}
