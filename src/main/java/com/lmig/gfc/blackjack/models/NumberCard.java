package com.lmig.gfc.blackjack.models;

public class NumberCard extends Card {
	//This is the NumberCard subclass to Card

	private int value;

	public NumberCard(Suits suit, int value) {
		super(suit);
		this.value = value;

	}

	@Override
	public String getFace() {
		return String.valueOf(value);
	}

	@Override 
	public int getValue() {
		return value;
	}

}
