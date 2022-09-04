/**
* 2014, Antonio Jes�s Arquillos �lvarez
* M�ster en Dise�o y Desarrollo de Aplicaciones Web
* Universidad Nacional de Educaci�n a Distancia
*/

package vista;

import java.awt.*;


/**
 * Clase para la visualizaci�n del dado.
 * Se utilizan seis im�genes correspondientes a las caras del dado.
 * Se implementa como una extensi�n de la clase Canvas redefiniendo algunos m�todos
 * para que el dado pueda dibujarse en pantalla.
 */
public class DadoGUI extends Canvas {
	
	private static final long serialVersionUID = 1L;
	private static final int REPETICIONES_DADO = 20;

	// array de im�genes para las caras de un dado
	private Image caras[] = new Image[ 6 ];
	// utilizamos un mediatracker para a�adir las im�genes
	private MediaTracker m;
	// cara que debe cargarse en llamada a paint()
	private int caraActual;
	// puntos obtenidos en la tirada
	private int puntuacion;
	
	/**
	 * Constructor
	 * Carga las im�genes de las caras del dado en el mediatracker.
	 * Inicializa �ndice de las im�genes a 1 y el tama�o.
	 */
	public DadoGUI() {
		m = new MediaTracker( this );
		Toolkit tk = Toolkit.getDefaultToolkit();
		// A�adimos al mediatracker las caras del dado
		for( int i = 0; i < 6; i++ ) {
			caras[ i ] = tk.getImage( "img/dado_" + ( i + 1 ) + ".png" );
			// asociamos el 0 como indice de todas las im�genes
			m.addImage( caras[ i ], 0 );
		}
		// esperamos a que se hayan cargado todas las im�genes
		try {
			m.waitForID( 0 );
		} catch( Exception e ){}
		// primera imagen
		caraActual = 0;
		// tama�o canvas
		setSize( caras[ caraActual ].getWidth( null ), caras[ caraActual ].getHeight( null ) );
	}
	
	/**
	 * Actualiza la puntuaci�n obtenida por el dado
	 * @param puntos Puntos obtenidos
	 */
	public void setPuntuacion( int puntos ) {
		this.puntuacion = puntos;
	}

	/**
	 * Obtiene la puntuaci�n del dado
	 * @return Puntos obtenidos
	 */
	public int getPuntuacion() {
		return this.puntuacion;
	}
	
	/**
	 * Actualiza la cara del dado que debe mostrarse
	 * @param cara que se visualiza
	 */
	public void setCara( int caraActual ) {
		if( ( caraActual > 0 ) && ( caraActual <= 6 ) ) {
			this.caraActual = caraActual - 1;
		}
		else this.caraActual = 0;
	}
	
	/**
	 * Simulaci�n gr�fica de lanzamiento de dado
	 * @param int valor final
	 */
	public void lanzar() {
		// simulamos el movimiento del dado:
		for ( int repeticion = REPETICIONES_DADO; repeticion >= 0; repeticion-- ) {
			setCara( ( repeticion + getPuntuacion() - 1 ) % 6 + 1 );
			repaint();
			try {
				Thread.sleep( 10 * ( REPETICIONES_DADO - repeticion ) );
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}			    		
	}
	
	/**
	 * Este metodo se llama cada vez que se quiere redibujar la pantalla
	 * Para evitar el flickering no borramos la plantalla 
	 * @param Graphics objeto Graphics donde se muestra el dado
	 */
	@Override
	public void update( Graphics g ) {
		paint( g );
	}
	
	/**
	 * Redefinici�n del m�todo paint de la clase Canvas.
	 * Se utiliza para que el dado se pueda dibujar en pantalla.
	 * @param Graphics objeto Graphics donde se muestra el dado
	 */
	@Override
	public void paint( Graphics g )	{
		// dibujamos la imagen de la cara seleccionada en 0,0.
		g.drawImage( caras[ caraActual ], 0, 0, this );
	}
}	