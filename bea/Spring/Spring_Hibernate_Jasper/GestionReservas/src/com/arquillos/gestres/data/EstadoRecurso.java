/*
 * 
 * 
 */

package com.arquillos.gestres.data;

public enum EstadoRecurso {	
	DESCONOCIDO(1, "DESCONOCIDO", "#000000"),
	MALO(2, "MALO", "#B08B59"),
	ACEPTABLE(3, "ACEPTABLE", "#898951"),
	BUENO(4, "BUENO", "#5C8D87"),
	EXCELENTE(5, "EXCELENTE", "#7083A8");

	private String literal;
	private String color;
	private int value;

	private EstadoRecurso(int value, String literal, String color) {
		this.value = value;
		this.literal = literal;
		this.color = color;
	}

	public String getLiteral() {
		return literal;
	}

	public String getColor() {
		return color;
	}

  public int getValue() { 
  	return value; 
  }		

	@Override
	public String toString() {
		return literal;	
	}	
}