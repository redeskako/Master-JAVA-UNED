package es.uned2013.parchis.casillaI;

import es.uned2013.parchis.parchis0_1.Ficha;
import es.uned2013.parchis.parchis0_1.Colores;


public class CasillaMeta extends CasillaI{
private boolean esUltima; //Indica si es una casilla segura.
	
	
	
	// Constructor1	
		public CasillaMeta(Colores color, int numero, boolean esUltima,
				boolean esBarrera, int tieneFicha, Ficha ficha1) {
				// Inicializa todos los atributos excepto ficha2, inicialmente no hay
				// ninguna casilla del recorrido con dos fichas. 
				super(color, numero, esBarrera, tieneFicha, ficha1,false);
				
				this.esUltima = esUltima;
			
			}
			
			// Constructor2	sin ficha para cuando se crean casillas no se requiere ficha
			public CasillaMeta(Colores color, int numero, boolean esUltima,
				boolean esBarrera, int tieneFicha) {
				
				// Inicializa todos los atributos excepto ficha2, inicialmente no hay
				// ninguna casilla del recorrido con dos fichas. 
				super(color, numero, esBarrera, false,tieneFicha);
				
				this.esUltima = esUltima;
			
			}
			public boolean isEsUltima() {
				return esUltima;
			}
			 
			
			public void setEsUltima(boolean esUltima) {
				this.esUltima = esUltima;
			}
			

}
