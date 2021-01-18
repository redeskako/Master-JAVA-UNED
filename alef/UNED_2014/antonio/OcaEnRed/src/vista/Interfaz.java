/**
* 2014, Antonio Jesús Arquillos Álvarez
* Máster en Diseño y Desarrollo de Aplicaciones Web
* Universidad Nacional de Educación a Distancia
*/

package vista;

import modelo.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 * Clase que representa la interfaz del juego.
 * Extiende JFrame y se organiza como una serie de paneles para las diferentes áreas: 
 * 	- Eventos del sistema
 *  - Lista de jugadores activos
 *  - Mesajes de error
 *  - Botones: iniciar, salir y tirar dados
 *  - Tablero
 */

public class Interfaz extends JFrame implements Observer {

	private static final long serialVersionUID = 1L;
		
	private JTextArea areaEventos;				// Eventos del sistema	
	private JTextArea areaErrores;				// Errores	
	private JList listaJugadores;				// Lista de jugadores
	private DefaultListModel modeloListaJugadores;
	private JButton botonInicio;				// Iniciar juego	
	private JButton botonDados;					// Lanzar dados	
	private JButton botonSalir;					// Salir	
	private TableroOcaGUI tablero;				// Tablero	
	private DadoGUI[] dados = new DadoGUI[ 2 ];	// Dados

	// indicadores
	private volatile boolean botonInicioPulsado = false;
	private volatile boolean botonDadosPulsado = false;
	
	/**
	 * Metodo Constructor.
	 * Inicializa la interfaz
	 * Construye las áreas que forman la interfaz del juego.
	 * Establece dimensiones de la ventana y la visualiza.
	 */
	public Interfaz() {		
        // Título ventana
		super( "Juego de la Oca en Red" ); 
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setLayout( new BorderLayout() );
		
		add( panelAutor(), BorderLayout.NORTH );
		add( panelEstado(), BorderLayout.EAST );
		add( panelTablero(), BorderLayout.CENTER );
		add( panelControl(), BorderLayout.SOUTH );
		
		// Listener para la accion de cerrar la ventana
		addWindowListener(
			new WindowAdapter() {
				public void windowClosing( WindowEvent e ) {
	            	OcaEnRed.getPartida().setCancelada( true );
				}
			}
		);
		
		// Establece el tamaño de la ventana
		setSize( 775, 525 );
		// Muestra la ventana		
		pack();		
		setLocationRelativeTo( null );
		setVisible( true );
	}
	
	/**
	 * Área de información de sistema.
	 * Inicializa un panel que incluye:
	 * 	 Información sobre eventos de sistema
	 *   Lista de jugadores activos
	 *   Mensajes de error generados
	 * @return Panel panel de estado de juego
	 */
	private JPanel panelEstado( ) {		
		// Nuevo panel con borderlayout 
		JPanel panel = new JPanel( new BorderLayout() );
		// Añade el panel de eventos del sistema
		panel.add( panelEventos(), BorderLayout.NORTH );
		// Añade el panel de jugadores conectados
		panel.add( panelJugadores(), BorderLayout.CENTER );
		// Añade el panel de errores
		panel.add( panelErrores(), BorderLayout.SOUTH );
		// Devuelve el panel
		return panel;
	}

	/**
	 * Área de jugadores activos.
	 * Lista de jugadores que hay conectados actualmente.
	 * @return Panel panel con la lista de jugadores
	 */
	private JPanel panelJugadores( ) {
		// Nuevo panel con borderlayout
		JPanel panel = new JPanel( new BorderLayout() );
		// Añade la etiqueta de título del panel
		panel.add( new JLabel( " Jugadores participantes", JLabel.LEFT ), BorderLayout.NORTH );
		// Añade una lista contenedora de los jugadores activos		
		modeloListaJugadores = new DefaultListModel();
		listaJugadores = new JList( modeloListaJugadores );
		listaJugadores.setBackground( new Color( 100, 100, 100 ) );
		listaJugadores.setForeground( new Color( 220, 220, 220 ) );
		listaJugadores.setFont( new Font("Verdana", Font.BOLD, 10) );	
		panel.add( listaJugadores, BorderLayout.CENTER );
		// Devuelve el panel
		return panel;
	}

	/**
	 * Área de eventos de sistema.
	 * área de texto donde se mostrarán los eventos generados 
	 * @return Panel panel con el área de texto para eventos
	 */
	private JPanel panelEventos( ) {
		// Inicializa un panel
		JPanel panel = new JPanel( new BorderLayout() );
		// título
		panel.add( new JLabel( " Eventos de sistema", JLabel.LEFT ), BorderLayout.NORTH );
		// área de texto donde se muestran los eventos
		areaEventos = new JTextArea( 20, 22 );
		areaEventos.setEnabled( true );
		areaEventos.setEditable( false );
		areaEventos.setBackground( new Color( 100, 100, 100 ) );
		areaEventos.setForeground( new Color( 220, 220, 220 ) );
		areaEventos.setFont( new Font("Verdana", Font.PLAIN, 10) );		
		JScrollPane sp = new JScrollPane( areaEventos );		
		panel.add( sp, BorderLayout.CENTER );
		// devuelve el panel creado
		return panel;
	}

	/**
	 * Área de identificación.
	 * Muestra información de la aplicación
	 * @return JPanel panel con información del juego.
	 */
	private JPanel panelAutor( ) {
		// Nuevo panel con borderlayout
		JPanel panel = new JPanel( new BorderLayout() );
		// Añade una etiqueta con información del juego
		JLabel titulo = new JLabel( " Oca - Antonio Jesús Arquillos Álvarez - U.N.E.D.", SwingConstants.LEFT );
		titulo.setForeground( new Color( 0, 0, 0 ) );
		titulo.setFont( new Font( "Verdana", Font.ITALIC, 14 ) );
		panel.add( titulo, BorderLayout.CENTER );
		// Retorna el panel creado
		return panel;
	}
	
	/**
	 * Área juego
	 * Muestra tablero de juego
	 * @return JPanel panel con el tablero  
	 */
	private JPanel panelTablero( ) {
		// Nuevo panel con borderlayout
		JPanel panel = new JPanel( new BorderLayout() );
		panel.add( tablero = new TableroOcaGUI( "img/tablero.png" ), BorderLayout.CENTER );
		tablero.inicializar();
		// Devuelve el panel creado
		return panel;
	}

	/**
	 * Área de Mensajes de error.
	 * Área para la visualización de mensajes de error
	 * @return Panel panel con el área de texto de mensajes de error (vacía).
	 */
	private JPanel panelErrores( ) {
		// Nuevo panel con borderlayout
		JPanel panel = new JPanel( new BorderLayout() );
		// Añade una etiqueta con información del juego
		panel.add( new JLabel( " Errores", JLabel.LEFT ), BorderLayout.NORTH );
		// Añade un área de texto donde se escribirán los mensajes de error
		areaErrores = new JTextArea( 5, 22 );
		areaErrores.setEnabled( true );
		areaErrores.setEditable( false );
		areaErrores.setBackground( new Color( 100, 100, 100 ) );
		areaErrores.setForeground( new Color( 220, 220, 220 ) );
		areaErrores.setFont( new Font( "Verdana", Font.PLAIN, 10 ) );		
		JScrollPane sp = new JScrollPane( areaErrores );
		panel.add( sp, BorderLayout.CENTER );
		// Devuelve el panel creado
		return panel;
	}

	/**
	 * Área de Control.
	 * Botones para iniciar la partida, tirar los dados o salir
	 * @return Panel el panel de botones para control de juego
	 */
	private JPanel panelControl( ) {
		// Nuevo panel con borderlayout
		JPanel panel = new JPanel( new FlowLayout( FlowLayout.LEFT, 25, 5 ) );
		// Comenzar partida
        botonInicio = new JButton( "Empezar" );
		botonInicio.setEnabled( false );
        botonInicio.addActionListener( new ButtonHandler() );
        panel.add( botonInicio );
		// Lanzar dados
        botonDados = new JButton( "Tirar" );
        botonDados.setEnabled( false );
        botonDados.addActionListener( new ButtonHandler() );
        panel.add( botonDados );
		// Imágenes dados
		panel.add( dados[ 0 ] = new DadoGUI() );
		panel.add( dados[ 1 ] = new DadoGUI() );
		// Salir
        botonSalir = new JButton( "Salir" );
        botonSalir.addActionListener( new ButtonHandler() );
        panel.add( botonSalir );
		// Devuelve el panel creado
		return panel;
	}

    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed( ActionEvent e ) {
        	String cmd = e.getActionCommand();
            if ( "Empezar".equals( cmd ) ) {
            	// Comenzar partida
            	OcaEnRed.getPartida().setIniciada( true );
            }        	
            if ( "Tirar".equals( cmd ) ) {
				// Tirar dados            	
            	botonDadosPulsado = true;
            }        	
            if ( "Salir".equals( cmd ) ) {
            	OcaEnRed.getPartida().setCancelada( true );            	
            }
        }
    }
	
	/**
	 * Actualiza lista de jugadores
	 * @param Jugador[] lista de jugadores en la partida
	 */
	public void mostrarJugadores( ) {
		// manipula GUI en el thread event-dispatch
		SwingUtilities.invokeLater (
		    new Runnable() {
		    	public void run() {
		    		// borrar el contenido actual
		    		modeloListaJugadores.clear();
		    		// añade los datos de los jugadores que se han pasado		
		    		for( Jugador jugador : OcaEnRed.getPartida().getJugadores() ) {			
		    			modeloListaJugadores.addElement( jugador.toString() );
		    			if( jugador instanceof JugadorLocal ) {
		    				listaJugadores.setSelectedIndex( modeloListaJugadores.getSize() - 1 );
		    			}
		    		}	
				} // fin método run
			} // fin clase anónima interna
		); // fin de llamada al método SwingUtilities.invokeLater		
	}

	/**
	 * Muestra mensaje de error en el área correspondiente
	 * @param String mensaje de error
	 */
	public void mostrarError( final String error ) {
		// manipula GUI en el thread event-dispatch
		SwingUtilities.invokeLater (
		    new Runnable() {
		    	public void run() {
		    		areaErrores.append( error + "\n" );
					areaErrores.setCaretPosition( areaErrores.getDocument().getLength() );
				} // fin método run
			} // fin clase anónima interna
		); // fin de llamada al método SwingUtilities.invokeLater
	}		
		
	/**
	 * Muestra evento del sistema en el área correspondiente
	 * @param String Información de evento de sistema
	 */
	public void mostrarEvento( final String evento ) {
		// manipula GUI en el thread event-dispatch
		SwingUtilities.invokeLater (
		    new Runnable() {
		    	public void run() {
		    		areaEventos.append( evento + "\n" );
		    		areaEventos.setCaretPosition( areaEventos.getDocument().getLength() );		
				} // fin método run
			} // fin clase anónima interna
		); // fin de llamada al método SwingUtilities.invokeLater
	}

	/**
	 * Habilita o deshabilita el botónn para iniciar partida.
	 * @params estado
	 */
	public void enableBotonInicio( final boolean estado ) { 		
		// manipula GUI en el thread event-dispatch
		SwingUtilities.invokeLater (
		    new Runnable() {
		    	public void run() {
		    		botonInicio.setEnabled( estado );		
				} // fin método run
			} // fin clase anónima interna
		); // fin de llamada al método SwingUtilities.invokeLater		
	}

	/**
	 * Habilita o deshabilita el botón para lanzar dados.
	 * @params estado
	 */
	public void enableBotonDados( final boolean estado ) {
		// manipula GUI en el thread event-dispatch
		SwingUtilities.invokeLater (
		    new Runnable() {
		    	public void run() {
		    		botonDados.setEnabled( estado );		
				} // fin método run
			} // fin clase anónima interna
		); // fin de llamada al método SwingUtilities.invokeLater		
	}

	/**
	 * obtiene tablero
	 * @return TableroOcaGUI el tablero del juego
	 */
	public TableroOcaGUI getTablero() {
		return this.tablero;
	}

	/**
	 * Obtiene dado gráfico
	 * @return DadoGUI el dado del juego
	 */
	public DadoGUI getDado( int indice ) {
		return this.dados[ indice ];
	}

	public void lanzarDados( int[] puntos ) {
		ArrayList< Thread > threads = new ArrayList< Thread >();
		int i = 0;
		try {
			for ( final DadoGUI dado: dados ) {			
				dado.setPuntuacion( puntos[ i ] );
				threads.add( new Thread( new Runnable() {
					public void run() {          
						// ejecutar en nuevo thread 
						dado.lanzar();			   
					}
				} ) );  
				threads.get( i++ ).start();
			}		
			for ( Thread t : threads ) {
				t.join();
			}
		} catch ( InterruptedException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Devuelve estado botón de inicio
	 * @return boolean true si el botón se ha pulsado, false en caso contrario
	 */
	public boolean isBotonInicioPulsado() {
		return botonInicioPulsado;
	}

	/**
	 * Establece estado botón inicio
	 */
	public void setBotonInicioPulsado( boolean pulsado ) {
		botonInicioPulsado = pulsado;
	}

	/**
	 * Devuelve estado botón de dados
	 * @return boolean true si el botón se ha pulsado, false en caso contrario
	 */
	public boolean isBotonDadosPulsado() {
		return botonDadosPulsado;
	}

	/**
	 * Establece estado botón de dados
	 */
	public void setBotonDadosPulsado( boolean estado ) {
		botonDadosPulsado = estado;
	}

	public void moverFicha( int turno, int destino, int pausa ) {
		try {
			tablero.mueveFicha( turno, destino, pausa );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
}
