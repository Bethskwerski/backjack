package com.lmig.gfc.blackjack.models;

public class AceCard extends Card {
	//This the AceCard subclass to card

	public AceCard(Suits suit) {
		super(suit);

	}

	@Override
	public String getFace() {
		return "A";
	}

	@Override
	public int getValue() {
		return 11;
	}

}
