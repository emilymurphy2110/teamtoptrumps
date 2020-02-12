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
		String s = "";
//		s+="[" + this.name + "]";
//		for(int i = 0; i<characteristics.length;i++) {
//			s+= "\n" + (i+1) + " - " + characteristics[i].toString();
		s+="[" + this.name + "]" + "\n";
		for(int i = 0; i<characteristics.length;i++) {
			s+= (i+1) + " - " + characteristics[i].toString()+ "\n";
		}
//		System.out.println("CardToString");
		return s;
	}

	
	public String getName() {
		return name;
	}
	public Characteristic[] getCharacteristics() {
		return characteristics;
	}
	

}
