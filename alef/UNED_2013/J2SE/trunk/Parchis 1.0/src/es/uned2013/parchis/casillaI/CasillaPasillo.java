package es.uned2013.parchis.casillaI;

import es.uned2013.parchis.parchis0_1.Ficha;
import es.uned2013.parchis.parchis0_1.Colores;

public class CasillaPasillo extends CasillaI {

		private boolean esPasillo; //Indica si es una de las casillas que están entre la úlitima casilla seguro del recorrido y la casilla final
		
		// Constructor1	
		public CasillaPasillo(Colores color, int numero, boolean esPasillo,
				boolean esBarrera, boolean b, int i) {
				// Inicializa todos los atributos excepto ficha2, inicialmente no hay
				// ninguna casilla del recorrido con dos fichas. 
			super(color, numero, esBarrera, b, i);	
	
			this.esPasillo = esPasillo;
		
			}
			
			// Constructor2	sin ficha para cuando se crean casillas no se requiere ficha
			public CasillaPasillo(Colores color, int numero, boolean esPasillo, 
				boolean esBarrera, int tieneFicha) {
				// Inicializa todos los atributos excepto ficha2, inicialmente no hay
				// ninguna casilla del recorrido con dos fichas. 
				super(color, numero, esBarrera, tieneFicha);
				
				this.esPasillo = esPasillo;
		
			}
			
			// Setters and Getters 
						 
			public boolean isEsPasillo() {
				return esPasillo;
			}
			
			
			public void setEsPasillo(boolean esPasillo) {
				this.esPasillo = esPasillo;
			}
			
			
			/* metodo siguiente copiado de Casilla.siguiente2()
			 * nos da el valor de la casilla siguiente teniendo en cuenta los itinerarios
			 * que debe seguir cada jugador dependiendo del color de su ficha
			 */
			
			public int siguiente2(Ficha ficha){
				switch (this.getNumero()){
				case 101: 
				case 102:
				case 103:
				case 104:
				case 105:
				case 106:
				case 107:
					switch(this.getColor()){
					case AMARILLO: this.setSiguiente(this.getNumero()+1);
					break;
					default:
						//this.setSiguiente(1);
						break;
					}
					break;
				case 201:
				case 202:
				case 203:
				case 204:
				case 205:
				case 206:
				case 207:
					switch(this.getColor()){
					case AZUL: this.setSiguiente(this.getNumero()+1);
					break;
					default:
						//this.setSiguiente(this.getNumero()+1);
					break;
					}
					break;
				case 301:
				case 302:
				case 303:
				case 304:
				case 305:
				case 306:
				case 307:
					switch(this.getColor()){
					case ROJO: this.setSiguiente(this.getNumero()+1);
					break;
					default:
						//this.setSiguiente(this.getNumero()+1);
					break;
					}
					break;
				case 401:
				case 402:
				case 403:
				case 404:
				case 405:
				case 406:
				case 407:
					switch(this.getColor()){
					case VERDE: this.setSiguiente(this.getNumero()+1);
					break;
					default:
						//this.setSiguiente(this.getNumero()+1);
						break;
					}
					break;
				default:
					//this.setSiguiente(this.getNumero()+1);
				   	break;
				}
				return this.getSiguiente();
				
			}
			
		
			//metodo main para pruebas
			
			public static void main(String[] args){
				/*
				 * doble bucle for para recorrer todos los valores de colores y 
				 * el numero casillas almacenado en la variable num
				 */
				//int num = 100;
				int[] casillaPasillo = {101,102,103,104,105,106,107,201,202,203,204,205,205,207,
						301,302,303,304,305,306,307,401,401,403,404,405,406,407};
				
				for(Colores color : Colores.values()){
					for (int i: casillaPasillo){
					Ficha ficha1 = new Ficha(color, i, 1, 5, 100, 108);
					/*
					 * (Colores color, int identificador, int casillaActual,
						int casillaSalida, int casillaCasa, int casillaFinal)
					 */
					
					CasillaPasillo casilla = new CasillaPasillo(color, i, false, false, 0, ficha1);
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

