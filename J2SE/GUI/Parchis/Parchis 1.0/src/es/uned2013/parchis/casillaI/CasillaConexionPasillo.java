package es.uned2013.parchis.casillaI;

import es.uned2013.parchis.parchis0_1.Ficha;
import es.uned2013.parchis.parchis0_1.Colores;

public class CasillaConexionPasillo extends CasillaSeguro{
	
private boolean esConexionPasillo; //Indica si es una casilla de conexion al pasillo.

	
	// Constructor1	
		public CasillaConexionPasillo(Colores color, int numero, boolean esSeguro, boolean esConexionPasillo,
				boolean esBarrera, int tieneFicha, Ficha ficha1) {
				// Inicializa todos los atributos excepto ficha2, inicialmente no hay
				// ninguna casilla del recorrido con dos fichas. 
				super(color, numero, esSeguro, esBarrera, tieneFicha, ficha1);
			
				this.esConexionPasillo = esConexionPasillo;
	
				
			}
			
			// Constructor2	sin ficha para cuando se crean casillas no se requiere ficha
			public CasillaConexionPasillo(Colores color, int numero, boolean esSeguro, boolean esConexionPasillo,
				boolean esBarrera, int tieneFicha) {
				
				// Inicializa todos los atributos excepto ficha2, inicialmente no hay
				// ninguna casilla del recorrido con dos fichas. 
				super(color, numero, esBarrera, esSeguro, tieneFicha);
		
				this.esConexionPasillo = esConexionPasillo;
		
			}
			public boolean isEsConexionPasillo() {
			return esConexionPasillo;
			}
		 
		
			public void setEsConexionPasillo(boolean esConexionPasillo) {
			this.esConexionPasillo = esConexionPasillo;
			}
		  
			//no implementa el metodo siguiente() porque lo hereda
			
//metodo main para pruebas
			
			public static void main(String[] args){
				/*
				 * doble bucle for para recorrer todos los valores de colores y 
				 * el numero casillas almacenado en el array 
				 */
				
				int[] casillasConexionPasillo = {17, 34, 51, 68};
				
				for(Colores color : Colores.values()){
					for (int i:  casillasConexionPasillo){
					Ficha ficha1 = new Ficha(color, i, 1, 5, 100, 108);
					/*
					 * (Colores color, int identificador, int casillaActual,
						int casillaSalida, int casillaCasa, int casillaFinal)
					 */
					
					CasillaConexionPasillo casilla = new CasillaConexionPasillo(color, i, false, false, false, 0, ficha1);
					/*	 
				     * (Colores color, int numero, /* boolean esCasa, boolean esUltima,
					boolean esSeguro, boolean esSalida, boolean esPasillo, *//*
					boolean esBarrera, int tieneFicha, Ficha ficha1)
					 */
					
					System.out.println("La casilla siguiente de la casilla "+ i +" para la ficha" + color + " es: " + casilla.siguiente2(ficha1));
					}	
				}
			
				
			}//fin del metodo main

}
