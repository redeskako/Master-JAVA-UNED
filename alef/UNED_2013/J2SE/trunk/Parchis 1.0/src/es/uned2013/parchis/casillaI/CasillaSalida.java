package es.uned2013.parchis.casillaI;

import es.uned2013.parchis.parchis0_1.Ficha;
import es.uned2013.parchis.parchis0_1.Colores;

public class CasillaSalida extends CasillaSeguro{
	
private boolean esSalida; //Indica si es una casilla segura.
	
	
	// Constructor1	
		public CasillaSalida(Colores color, int numero, boolean esSeguro, boolean esSalida,
				boolean esBarrera, int tieneFicha, Ficha ficha1) {
				// Inicializa todos los atributos excepto ficha2, inicialmente no hay
				// ninguna casilla del recorrido con dos fichas. 
				super(color, numero, esBarrera, esSeguro, tieneFicha, ficha1);
				this.esSalida = esSalida;
			
				
			}
			
			// Constructor2	sin ficha para cuando se crean casillas no se requiere ficha
			public CasillaSalida(Colores color, int numero, boolean esSeguro, boolean esSalida,
				boolean esBarrera, int tieneFicha) {
				
				// Inicializa todos los atributos excepto ficha2, inicialmente no hay
				// ninguna casilla del recorrido con dos fichas. 
				super(color, numero, esBarrera, esSeguro, tieneFicha);

				this.esSalida = esSalida;
				
			}
			public boolean isEsSalida() {
				return esSalida;
			}
			 
			 public void setEsSalida(boolean esSalida) {
				this.esSalida = esSalida;
			}
			 
			//no le hace falta el metodo siguiente() porque lo herada
			//de la clase CasillaSeguro
			 
			
			//metodo main para pruebas
				
			public static void main(String[] args){
					/*
					 * doble bucle for para recorrer todos los valores de colores y 
					 * el numero casillas almacenado en la variable num
					 */
					
					int[] casillasSalida = {5, 22,39, 56, 68};
					
					for(Colores color : Colores.values()){
						for (int i:  casillasSalida){
						Ficha ficha1 = new Ficha(color, i, 1, 5, 100, 108);
						/*
						 * (Colores color, int identificador, int casillaActual,
							int casillaSalida, int casillaCasa, int casillaFinal)
						 */
						
						CasillaSalida casilla = new CasillaSalida(color, i, false, false, false, 0, ficha1);
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
