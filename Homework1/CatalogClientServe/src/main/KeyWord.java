package main;

import java.io.Serializable;

public class KeyWord implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String name;
	String text;
	
	public KeyWord(String name,String text) {
		this.name = name;
		this.text = text;
	}
	
}
