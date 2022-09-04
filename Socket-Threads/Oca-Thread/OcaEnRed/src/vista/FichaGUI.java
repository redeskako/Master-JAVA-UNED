/**
* 2014, Antonio Jes�s Arquillos �lvarez
* M�ster en Dise�o y Desarrollo de Aplicaciones Web
* Universidad Nacional de Educaci�n a Distancia
*/

package vista;

import java.awt.*;
import util.*;

/**
 * Clase para la visualizaci�n de ficha de jugador.
 * Se implementa como una extensi�n de la clase Canvas redefiniendo algunos m�todos
 * para que la ficha pueda dibujarse en pantalla.
 */
public class FichaGUI {
	
	private Image ficha;		// Imagen para la ficha
	private int indiceCasilla;	// �ndice de la casilla en la que se sit�a
	private int turno;			// Turno asociado 
	private int coordenadaX;	// Coordenada X posici�n tablero	
	private int coordenadaY;	// Coordenada Y posici�n tablero

	/**
	 * Constructor.
	 * Carga la imagen indicada y establece las coordenadas de la ficha.
	 * @param turno turno asociado
	 */
	public FichaGUI( int turno ) { 
		// Cargamos gr�fico
		Toolkit tk = Toolkit.getDefaultToolkit();
		ficha = tk.getImage( "img/ficha_" + turno + ".png"  );
		this.turno = turno;
	}

	/**
	 * Obtiene la imagen de la ficha como objeto Image
	 * @return Image gr�fico ficha
	 */
	public Image getImagen() {
		return ficha;
	}

	/**
	 * Obtiene el turno al que se asocia la ficha.
	 * @return int turno asociado a la ficha
	 */
	public int getTurno() {
		return turno;
	}
	
	public int getX() {
		return this.coordenadaX;		
	}

	public void setX( int x ) {
		this.coordenadaX = x;		
	}

	public int getY() {
		return this.coordenadaY;		
	}

	public void setY( int y ) {
		this.coordenadaY = y;		
	}
	
	public int getIndice() {
		return this.indiceCasilla;	
	}
	
	/**
	 * establece la ficha en la casilla que se pasa por par�metro 
	 * @param casilla casilla destino
	 */
	public void setCasilla( CasillaInfo casilla ) {
		this.indiceCasilla = casilla.getIndice();
		this.coordenadaX = casilla.getCoordenadaX();
		this.coordenadaY = casilla.getCoordenadaY();
	}
}