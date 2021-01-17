/**
* 2014, Antonio Jesús Arquillos Álvarez
* Máster en Diseño y Desarrollo de Aplicaciones Web
* Universidad Nacional de Educación a Distancia
*/


package oca;

public class CasillaInfo {
	
	public static final int CASILLA_INICIO = 1;
	public static final int CASILLA_NORMAL = 2;
	public static final int CASILLA_OCA = 3;
	public static final int CASILLA_PUENTE = 4;
	public static final int CASILLA_POSADA = 5;
	public static final int CASILLA_DADOS = 6;
	public static final int CASILLA_POZO = 7;
	public static final int CASILLA_LABERINTO = 8;
	public static final int CASILLA_CARCEL = 9;
	public static final int CASILLA_MUERTE = 10;	
	public static final int CASILLA_FIN = 11;
	
	private int indice; 
	public void setIndice( int indice ) {	  
		this.indice = indice;
	}
	public int getIndice() {
	    return indice;
	}

	private int tipo;
	public void setTipo( int tipo ) {	  
		this.tipo = tipo;
	}
	public int getTipo() {
	    return tipo;
	}

	private int indiceDestino;
	public void setIndiceDestino( int indiceDestino ) {	  
		this.indiceDestino = indiceDestino;
	}
	public int getIndiceDestino() {
	    return indiceDestino;
	}

	private int rondasSinJugar;
	public void setRondasSinJugar( int rondasSinJugar ) {	  
		this.rondasSinJugar = rondasSinJugar;
	}
	public int getRondasSinJugar() {
	    return rondasSinJugar;
	}

	private boolean repetirTurno;
	public void setRepetirTurno( boolean repetirTurno ) {	  
		this.repetirTurno = repetirTurno;
	}
	public boolean getRepetirTurno() {
	    return repetirTurno;
	}
	
	private int coordenadaX;
	public void setCoordenadaX( int coordenadaX ) {	  
		this.coordenadaX = coordenadaX;
	}
	public int getCoordenaX() {
	    return coordenadaX;
	}

	private int coordenadaY;
	public void setCoordenadaY( int coordenadaY ) {	  
		this.coordenadaY = coordenadaY;
	}
	public int getCoordenaY() {
	    return coordenadaY;
	}
  
	@Override
	public String toString() {
		return "Casilla [ indice=" + indice + ", tipo=" + tipo + ", destino="
        + indiceDestino + ", rondas sin jugar=" + rondasSinJugar + ", repetir turno="
		+ repetirTurno + " ]";
  }
} 