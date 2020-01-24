package model;

import java.util.Arrays;

public class Card {
	
	private String name;
	private Characteristic[] characteristics;
	public Card(String name, Characteristic[] characteristics) {
		super();
		this.name = name;
		this.characteristics = characteristics;
	}
	
	@Override
	public String toString() {
		return "Card chosen is this card" + name + ", with the characteristics " + Arrays.toString(characteristics);
	}

	public String getName() {
		return name;
	}
	public Characteristic[] getCharacteristics() {
		return characteristics;
	}
	

}
