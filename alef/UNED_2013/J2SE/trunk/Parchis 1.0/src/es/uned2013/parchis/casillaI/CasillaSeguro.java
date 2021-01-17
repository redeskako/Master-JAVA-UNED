package es.uned2013.parchis.casillaI;

import es.uned2013.parchis.parchis0_1.Ficha;
import es.uned2013.parchis.parchis0_1.Colores;

public class CasillaSeguro extends CasillaI{
	
	//private boolean esSeguro; //Indica si es una casilla segura.
	
	
	
	// Constructor1	
		public CasillaSeguro(Colores color, int numero, boolean esSeguro,
				boolean esBarrera, int tieneFicha, Ficha ficha1) {
				// Inicializa todos los atributos excepto ficha2, inicialmente no hay
				// ninguna casilla del recorrido con dos fichas. 
				super(color, numero, esBarrera, tieneFicha, ficha1,false);
			
				this.esSeguro = esSeguro;
				
			}
			
	// Constructor2	sin ficha para cuando se crean casillas no se requiere ficha
	public CasillaSeguro(Colores color, int numero, boolean esSeguro,
			boolean esBarrera, int tieneFicha) {
				
			// Inicializa todos los atributos excepto ficha2, inicialmente no hay
			// ninguna casilla del recorrido con dos fichas. 
			super(color, numero, esBarrera, false, tieneFicha);
			this.esSeguro = esSeguro;
		
					
			}
			
			/*
			 public boolean isEsSeguro() {
				return esSeguro;
			}
			*/
			 
			
			public void setEsSeguro(boolean esSeguro) {
				this.esSeguro = esSeguro;
			}
			
			/*
			@Override//indica que este m�todo sobreescribe el m�todo de la superclase
			public int siguiente2(){
				switch (this.getNumero()){
				case 68:
					switch(this.getColor()){
					case AMARILLO: this.setSiguiente(101);
					break;
					default:
						this.setSiguiente(1);
						break;
					}
					break;
				case 63:this.setSiguiente(this.getNumero()+1);
				break;
				case 56:this.setSiguiente(this.getNumero()+1);
				break;
				case 46:this.setSiguiente(this.getNumero()+1);
				break;
				case 39:this.setSiguiente(this.getNumero()+1);
				break;
				case 29:this.setSiguiente(this.getNumero()+1);
				break;
				case 22:this.setSiguiente(this.getNumero()+1);
				break;
				case 12:this.setSiguiente(this.getNumero()+1);
				break;
				case 5:this.setSiguiente(this.getNumero()+1);
				break;
				case 17:
					switch(this.getColor()){
					case AZUL: this.setSiguiente(201);
					break;
					default:
						this.setSiguiente(this.getNumero()+1);
					break;
					}
					break;
				case 34:
					switch(this.getColor()){
					case ROJO: this.setSiguiente(301);
					break;
					default:
						this.setSiguiente(this.getNumero()+1);
					break;
					}
					break;
				case 51:
					switch(this.getColor()){
					case VERDE: this.setSiguiente(401);
					break;
					default:
						this.setSiguiente(this.getNumero()+1);
						break;
					}
					break;
				default:
					this.setSiguiente(this.getNumero()+1);
				   	break;
				}
				return this.getSiguiente();
				
			}//fin metodo siguiente()
			*/
			
			//metodo main para pruebas
			
			public static void main(String[] args){
				/*
				 * doble bucle for para recorrer todos los valores de colores y 
				 * el numero casillas almacenado en la variable num
				 */
				//int num = 100;
				int[] casillasSeguras = {5, 12, 17, 22, 29, 34, 39, 46, 51, 56, 63, 68};
				
				for(Colores color : Colores.values()){
					for (int i:  casillasSeguras){
					Ficha ficha1 = new Ficha(color, i, 1, 5, 100, 108);
					/*
					 * (Colores color, int identificador, int casillaActual,
						int casillaSalida, int casillaCasa, int casillaFinal)
					 */
					
					CasillaSeguro casilla = new CasillaSeguro(color, i, false, false, 0, ficha1);
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
