package es.uned2013.parchis.parchis0_1;//es.uned.2013.parchis -> 2013 identificador no válido

/**
 * Clase base para definir los colores que se utilizarán en el programa. Los colores
 * de las fichas y las casillas más el color neutro para las casillas que no tengan
 * ningún color.
 * @author alef
 */


public enum Colores {
	AMARILLO(0), AZUL(1), ROJO(2), VERDE(3), NEUTRO(-1);
	
	private final int id;
	
	/** Constructor del color
	 * @param id: Identificador del color (int)
	 */
	Colores(int id) {
		this.id = id;
	}
	
	/** Devuelve el identificador del color
	 * @return id: Identificador del color (int)
	 */
	public int getId() {
		return id;
	}
	
	/** Método de clase que recibe como parámetro un identificador de color
	 * y retorna el color correspondiente, en forma de constante de enumeración.
	 * @param id: Identificador del color (int)
	 * @return color: Color representado por una constante de enumeración (Colores)
	 */
	public static Colores getColor(int id) {
		Colores color;
		
		switch(id) {
			case 0: 
			case 1:
			case 2:
			case 3:
				color = Colores.values()[id];
				break;
			case -1:
				color = NEUTRO;
				break;
			default:
				color = null;
		}
		
		return color;
	}
	
	/**
	 * Calcula el número de casilla de salida para el color,
	 * sabiendo que existe una diferencia de 17 casillas 
	 * entre salidas de colores consecutivos, y que el 
	 * desplazamiento inicial es de 5 casillas.
	 * 
	 * AMARILLO:	casillaSalida = 5
	 * AZUL:		casillaSalida = 22
	 * ROJO:		casillaSalida = 39
	 * VERDE:		casillaSalida = 56
	 * 
	 * @return id identificador de la casilla de salida
	 */
	public int getCasillaSalida() {
		return (id >= 0 ? 17 * id + 5: -1);
	}
	
	/**
	 * Calcula el número de la casilla de casa para el color.
	 * 
	 * AMARILLO:	casillaCasa = 100
	 * AZUL:		casillaCasa = 200
	 * ROJO:		casillaCasa = 300
	 * VERDE:		casillaCasa = 400
	 * 
	 * @return id identificador de la casilla casa
	 */
	public int getCasillaCasa() {
		return (id >= 0 ? 100 * (id + 1): -1);
	}
	
	/**
	 * Calcula el número de la casilla de conexión con el pasillo para el color.
	 * 
	 * AMARILLO:	casillaConexionPasillo = 68
	 * AZUL:		casillaConexionPasillo = 17
	 * ROJO:		casillaConexionPasillo = 34
	 * VERDE:		casillaConexionPasillo = 51
	 * 
	 * @return id identificador de la casilla conexión con el pasillo
	 */
	public int getCasillaConexionPasillo() {
		return (id >= 0? 
				((getCasillaSalida() - 5) > 0? getCasillaSalida() - 5: 68): -1);
	}
	
	/**
	 * Calcula el número de la casilla inicial del pasillo para el color.
	 * 
	 * AMARILLO:	casillaInicioPasillo = 101
	 * AZUL:		casillaInicioPasillo = 201
	 * ROJO:		casillaInicioPasillo = 301
	 * VERDE:		casillaInicioPasillo = 401
	 * 
	 * @return id identificador de la casilla casa inicial de pasillo
	 */
	public int getCasillaInicioPasillo() {
		return (id >= 0? getCasillaCasa() + 1: -1);
	}
	
	/**
	 * Calcula el número de la casilla final del pasillo para el color.
	 * 
	 * AMARILLO:	casillaFinalPasillo = 107
	 * AZUL:		casillaFinalPasillo = 207
	 * ROJO:		casillaFinalPasillo = 307
	 * VERDE:		casillaFinalPasillo = 407
	 * 
	 * @return id identificador de la casilla final del pasillo
	 */
	public int getCasillaFinalPasillo() {
		return (id >= 0? getCasillaCasa() + 7: -1);
	}
	
	/**
	 * Calcula el n�mero de la casilla final para el color.
	 * 
	 * AMARILLO:	casillaFinal = 108
	 * AZUL:		casillaFinal = 208
	 * ROJO:		casillaFinal = 308
	 * VERDE:		casillaFinal = 408
	 * 
	 * @return id identificador de la casilla final
	 */
	public int getCasillaFinal() {
		return (id >= 0? getCasillaFinalPasillo() + 1: -1);
	}
}
