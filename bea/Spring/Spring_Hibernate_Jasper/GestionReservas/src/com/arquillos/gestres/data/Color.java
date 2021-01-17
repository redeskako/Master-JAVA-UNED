/*
 * 
 * 
 */

package com.arquillos.gestres.data;

/**
 *
 * @author Antonio Jesús Arquillos Álvarez
 */
public enum Color {  
	ROJO(1, "ROJO", "#CC3333"),
	NARANJA(2, "NARANJA", "#EE8800"),
	VERDE(3, "VERDE", "#109618"),
	AZUL(4, "AZUL", "#3366CC"),
	VIOLETA(5, "VIOLETA", "#994499"),
	DEFECTO(6, "DEFECTO", "#000000");

	private int value;
    private String literal;
	private String hexColor;

	private Color(int value, String literal, String hexColor) {
		this.value = value;
		this.literal = literal;
		this.hexColor = hexColor;
	}

	public String getLiteral() {
		return literal;
	}

	public String getHexColor() {
		return hexColor;
	}

  public int getValue() { 
  	return value; 
  }	

  public void setValue(int value) { 
  	this.value = value; 
  }	

  @Override
	public String toString() {
		return literal;
	}	
}