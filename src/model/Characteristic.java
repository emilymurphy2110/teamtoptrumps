package model;

public class Characteristic implements Comparable<Characteristic> {
	
	private String name;
	private int value;
	public Characteristic(String name, int value) {
		super();
		this.name = name;
		this.value = value;
		
	}
	@Override
	public String toString() {
		return "Characteristic chosen is " + name + ", and the value is " + value;
	}
	
	@Override
	public int compareTo(Characteristic o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public String getName() {
		return name;
	}
	public int getValue() {
		return value;
	}

}
