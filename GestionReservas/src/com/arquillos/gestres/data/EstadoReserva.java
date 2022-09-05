/*
 *
 *
 */
package com.arquillos.gestres.data;

/**
 *
 * @author Antonio Jesús Arquillos Álvarez
 */
public enum EstadoReserva {  
	DESCONOCIDO(1, "DESCONOCIDO", "#B08B59"),
	PENDIENTE(2, "PENDIENTE", "#B08B59"),
	APROBADA(3, "APROBADA", "#22AA99"),
	DENEGADA(4, "DENEGADA", "#DD5511");

	private String literal;
	private String color;
	private int value;

	private EstadoReserva(int value, String literal, String color) {
		this.value = value;
		this.literal = literal;
		this.color = color;
	}

	public String getColor() {
		return color;
	}

  public int getValue() { 
  	return value; 
  }	

  public String getLiteral() { 
  	return literal; 
  }	

	@Override
	public String toString() {
		return literal;
	}   
}