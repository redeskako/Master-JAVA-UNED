package es.uned2013.parchis.casillaI;

import es.uned2013.parchis.parchis0_1.Ficha;
import es.uned2013.parchis.parchis0_1.Colores;
 public class CasillaCasa  extends CasillaI{
	
private boolean esCasa; //Indica si es una casilla segura.
	
	
	
	// Constructor1	
		public CasillaCasa(Colores color, int numero, boolean esCasa,
				boolean esBarrera, int tieneFicha, Ficha ficha1) {
				// Inicializa todos los atributos excepto ficha2, inicialmente no hay
				// ninguna casilla del recorrido con dos fichas. 
				super(color, numero, esBarrera, tieneFicha, ficha1,false);
				//this.color = color;
				//this.numero = numero;
				this.esCasa = esCasa;
				//this.esUltima = esUltima;
				//this.esSeguro = esSeguro;
				//this.esSalida = esSalida;
				//this.esPasillo = esPasillo;
				//this.esBarrera = esBarrera;
				//this.tieneFicha = tieneFicha;
				//this.ficha1 = ficha1;
				
			}
			
			// Constructor2	sin ficha para cuando se crean casillas no se requiere ficha
			public CasillaCasa(Colores color, int numero, boolean esCasa,
				boolean esBarrera, int tieneFicha) {
				
				// Inicializa todos los atributos excepto ficha2, inicialmente no hay
				// ninguna casilla del recorrido con dos fichas. 
				super(color, numero, esBarrera, false, tieneFicha);
				//this.color = color;
				//this.numero = numero;
				this.esCasa = esCasa;
				//this.esUltima = esUltima;
				//this.esSeguro = esSeguro;
				//this.esSalida = esSalida;
				//this.esPasillo = esPasillo;
				//this.esBarrera = esBarrera;
				//this.tieneFicha = tieneFicha;
					
			}
			
			public boolean isEsCasa() {
				return esCasa;
			}
			
			public void setEsCasa(boolean esCasa) {
				this.esCasa = esCasa;
			}
			
			
			
			@Override//indica que este m�todo sobreescribe el m�todo de la superclase
			public int siguiente2(Ficha ficha){
				switch (this.getNumero()){
				case 100:
					switch(this.getColor()){
					case AMARILLO: this.setSiguiente(5);
					break;
					default:
						//this.setSiguiente(1);
						break;
					}
					break;
				case 200:
					switch(this.getColor()){
					case AZUL: this.setSiguiente(22);
					break;
					default:
						//this.setSiguiente(1);
						break;
					}
					break;
				case 300:
					switch(this.getColor()){
					case ROJO: this.setSiguiente(39);
					break;
					default:
						//this.setSiguiente(1);
						break;
					}
					break;
				case 400:
					switch(this.getColor()){
					case VERDE: this.setSiguiente(56);
					break;
					default:
						//this.setSiguiente(1);
						break;
					}
					break;
				default:
					//this.setSiguiente(this.getNumero()+1);
				   	break;
				}
				return this.getSiguiente();
				
			}//fin metodo siguiente()
			
			
			//metodo main para pruebas
			
			public static void main(String[] args){
				/*
				 * doble bucle for para recorrer todos los valores de colores y 
				 * el numero casillas almacenado en la variable num
				 */
				//int num = 100;
				int[] casillasCasa = {100, 200, 300, 400};
				
				for(Colores color : Colores.values()){
					for (int i:  casillasCasa){
					Ficha ficha1 = new Ficha(color, i, 1, 5, 100, 108);
					/*
					 * (Colores color, int identificador, int casillaActual,
						int casillaSalida, int casillaCasa, int casillaFinal)
					 */
					
					CasillaCasa casilla = new CasillaCasa(color, i, false, false, 0, ficha1);
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
