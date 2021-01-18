/**
* 2014, Antonio Jesús Arquillos Álvarez
* Máster en Diseño y Desarrollo de Aplicaciones Web
* Universidad Nacional de Educación a Distancia
*/

package modelo;

import util.*;
import java.util.List;

/**
 * Clase TableroOca.
 * Implementación de clase abstracta Tablero 
 */
public class TableroOca extends Tablero {

	private static final int NUMERO_CASILLAS = 63;
	private static final int CASILLA_INICIO = 1;
	private static final int CASILLA_FIN = 63;

	/**
	 * Instancia un nuevo tablero para el juego de la oca.
	 */
	public TableroOca() {
		super( NUMERO_CASILLAS );
	}
	
	/**
	 * @see oca.Tablero#inicializarTablero()
	 */
	@Override
	public void inicializarTablero() {	
		Casilla[] casillas = new Casilla[ this.getTotalCasillas() ];
		CasillaInfoReader reader = new CasillaInfoReader();
		List< CasillaInfo > tableroOcaCfg = reader.leerCasillas( "xsd/TableroOca.xml" );
		for ( CasillaInfo ci : tableroOcaCfg ) {
			int i = ci.getIndice();
			switch( ci.getTipo() ) {
				case CasillaInfo.CASILLA_INICIO:
					casillas[ i - 1 ] = new CasillaInicio( this );
					break;
				case CasillaInfo.CASILLA_NORMAL:
					casillas[ i - 1 ] = new CasillaNormal( this, i );
					break;
				case CasillaInfo.CASILLA_OCA:
					casillas[ i - 1 ] = new CasillaOca( this, i, ci.getIndiceDestino() );
					break;
				case CasillaInfo.CASILLA_PUENTE:
					casillas[ i - 1 ] = new CasillaPuente( this, i, ci.getIndiceDestino() );
					break;
				case CasillaInfo.CASILLA_POSADA:
					casillas[ i - 1 ] = new CasillaPosada( this, i );
					break;
				case CasillaInfo.CASILLA_DADOS:
					casillas[ i - 1 ] = new CasillaDados( this, i, ci.getIndiceDestino() );
					break;
				case CasillaInfo.CASILLA_POZO:
					casillas[ i - 1 ] = new CasillaPozo( this, i );
					break;
				case CasillaInfo.CASILLA_LABERINTO:
					casillas[ i - 1 ] = new CasillaLaberinto( this, i, ci.getIndiceDestino() );
					break;
				case CasillaInfo.CASILLA_CARCEL:
					casillas[ i - 1 ] = new CasillaCarcel( this, i );
					break;
				case CasillaInfo.CASILLA_MUERTE:
					casillas[ i - 1 ] = new CasillaMuerte( this, i );
					break;
				case CasillaInfo.CASILLA_FIN:
					casillas[ i - 1 ] = new CasillaFinal( this, i );
					break;					
			}
		}		
		this.setCasillas( casillas );
	}


	/**
	 * Método Getter de la propiedad <tt>casillaInicio</tt>.
	 *
	 * @return Devuelve la casilla de inicio del juego.
	 * @uml.property name="casillaInicio"
	 */
	public CasillaInicio getCasillaInicio() {
		return ( CasillaInicio ) this.getCasillas()[ CASILLA_INICIO - 1 ];
	}

	public Casilla getCasillaFinal() {
		return this.getCasillas()[ CASILLA_FIN - 1 ];
	}
	
	public Casilla getCasilla( Casilla casillaOrigen, int tirada ) {
		int indiceDestino = casillaOrigen.getIndice() + tirada;		
		if( indiceDestino > this.getTotalCasillas() ) {
			int exceso = indiceDestino - this.getTotalCasillas();
			indiceDestino = this.getTotalCasillas() - exceso;
		}
		return this.getCasilla( indiceDestino );
	}	
}
