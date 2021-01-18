/**
* 2014, Antonio Jesús Arquillos Álvarez
* Máster en Diseño y Desarrollo de Aplicaciones Web
* Universidad Nacional de Educación a Distancia
*/
package vista;

import util.*;
import java.awt.*;
import java.util.*;
import java.util.List;

import javax.swing.SwingUtilities;

/**
 * Clase para la visualización del tablero.
 * Se implementa como una extensión de la clase Canvas redefiniendo algunos métodos
 * para que el tablero pueda dibujarse en pantalla.
 */
@SuppressWarnings("serial")
public class TableroOcaGUI extends Canvas {	

	private CasillaInfo[] casillas = new CasillaInfo[ 63 ];
	private MediaTracker m;
	private Image tablero;
	private List< FichaGUI > fichas = new ArrayList< FichaGUI >();

	/**
	 * Metodo constructor.
	 * Inicializa tablero
	 */
	public TableroOcaGUI( String imagenTablero ) {
		// Inicializa el Toolkit para cargar imagenes
		Toolkit tk = Toolkit.getDefaultToolkit();
		// Carga la imagen a la propiedad tablero (Image)
		tablero = tk.getImage( imagenTablero );
		m = new MediaTracker( this );
		m.addImage( tablero, 0 );
		// espera a que se cargue la imagen
		try {
			m.waitForID( 0 );
		} catch( Exception e ){}
		// tamaño del Canvas se corresponde al tamaño imagen
		setSize( tablero.getWidth( null ), tablero.getHeight( null ) );
	}
		
	private CasillaInfo getCasilla ( int indice ) {
		return casillas[ indice ];
	}

	private List<FichaGUI> getFichas( ) {
		return fichas;
	}
	
	/**
	 * Añade las fichas
	 * @param FichaGUI[] array de objetos FichaGUI
	 */
	public void addFichas( FichaGUI[] fichas ) {
		for( FichaGUI ficha : fichas ) {
			this.fichas.add( ficha );
			m.addImage( ficha.getImagen(), 0 );
			// esperamos a que se haya cargado la imagen
			try {
				m.waitForID( 0 );
			} catch( Exception e ){}
		}
	}
			
	/**
	 * Añade ficha
	 * @param objeto FichaGUI
	 */
	public void addFicha( final int turno ) {
		// manipula GUI en el thread event-dispatch
		SwingUtilities.invokeLater (
		    new Runnable() {
		    	public void run() {
		    		FichaGUI ficha = new FichaGUI( turno );
		    		ficha.setCasilla( getCasilla( 0 ) );
		    		ficha.setX( ficha.getX() + ficha.getTurno() * 20 );
		    		getFichas().add( ficha ); 
		    		m.addImage( ficha.getImagen(), 0 );
		    		// esperamos a que se haya cargado la imagen
		    		try {
		    			m.waitForID( 0 );
		    		} catch( Exception e ){}
		    		// redibujamos canvas
		    		repaint();		
				} // fin método run
			} // fin clase anónima interna
		); // fin de llamada al método SwingUtilities.invokeLater		
		
	}
	
	/**
	 * Mueve una ficha identificada mediante el primer parametro, desde la casilla inicial, hasta la casilla final,
	 * esperando en cada movimiento de casilla "pausa" milisegundos.
	 * @param int identificador de la ficha que queremos mover
	 * @param int casilla hacia donde se ha de mover
	 * @param int tiempo de espera entre movimientos de casilla, en milisegundos
	 * @throws InterruptedException 
	 */
	public void mueveFicha( int turno, int destino, int pausa ) throws InterruptedException {
		// Obtiene la ficha con el identificador
		FichaGUI ficha = fichas.get( turno );
		// Recorre todas las casillas por las que se moverá la ficha
		int delta;
		int indiceOrigen, indiceDestino;
		for(  delta = Math.abs( destino - ficha.getIndice() ); delta > 0 ; delta-- ) {
			// nueva posición de la ficha
		    indiceOrigen = ficha.getIndice();
		    indiceDestino = indiceOrigen + ( ( destino > indiceOrigen ) ? 1 : -1 );
		    ficha.setCasilla( casillas[ indiceDestino - 1 ] );
		    // Redibuja la imagen del tablero
		    repaint();
			// pausa
			Thread.sleep( 4 * pausa );					
		 }		    				
	}

	/**
	 * redibujar pantalla
	 * para evitar el "flickering" no borramos
	 * @param Graphics Objeto graphics donde actualizamos.
	 */
	public void update( Graphics g ) {
		paint( g );
	}
	
	/**
	 * Metodo de dibujado. 
	 * @param Graphics pantalla donde se quiere dibujar
	 */
	public void paint( Graphics g ) {
		// Dibujar tablero
		g.drawImage( tablero, 0, 0, this );
		// Dibuja las fichas de los jugadores que hay en la partida
		for( FichaGUI ficha : fichas ) {
			// ficha en su posición actual
			g.drawImage( ficha.getImagen(), ficha.getX(), ficha.getY(), this );
		}
	}
	
	public void inicializar() {
		CasillaInfoReader reader = new CasillaInfoReader();
		List< CasillaInfo > tableroOcaCfg = reader.leerCasillas( "xsd/TableroOca.xml" );
		for ( CasillaInfo ci : tableroOcaCfg ) {
			casillas[ ci.getIndice() - 1 ] = ci;
		}
	}
} 		