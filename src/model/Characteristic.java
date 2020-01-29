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
		return "Characteristic: " + name + ", value: " + value;
	}
	
	@Override
	public int compareTo(Characteristic o) {
		return 0;
	}
	
	public String getName() {
		return name;
	}
	public int getValue() {
		return value;
	}

}
