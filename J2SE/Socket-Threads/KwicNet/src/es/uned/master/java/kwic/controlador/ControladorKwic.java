package es.uned.master.java.kwic.controlador;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

import es.uned.master.java.kwic.logica.coleccion.KwicColeccion;
import es.uned.master.java.kwic.red.*;
import es.uned.master.java.kwic.red.servidor.DatosCliente;
import es.uned.master.java.kwic.red.servidor.ServidorKWIC;
import es.uned.master.java.kwic.logica.modelo.KwicException;
import es.uned.master.java.kwic.files.KwicFicheros;

public class ControladorKwic implements ActionListener {

	// Areas y campo de texto
	//area de texto de frases
	private KwicFormArea parentIn;
	//area de texto de resultado kwic
	private KwicFormArea parentOut;
	//area de texto de no claves
	private KwicFormArea parentOutNoClaves;
	//campo de texto para las no claves
	private KwicFormField parentField;
	//campo de texto, ruta del fichero frases
	private KwicFormField campoFicheroFrases;
	//campo de texto, ruta del fichero resultante kwic
	private KwicFormField campoFicheroResultado;
	//campo de texto, ruta de fichero no claves
	private KwicFormField campoFicheroNoClaves;
	//area de texto estadisticas
	private KwicFormArea campoEstadisticas;
	//area de texto datos cliente
	private KwicFormArea campoResultadosClientes;
	// Ficheros de entrada y salida
	private FileInputStream ficheroFrases;
	private FileOutputStream ficheroResultado;
	private FileInputStream ficheroNoClaves;
	// Cadenas de caracteres
	private String display;
	private String display2;
	private String AreaNoClaves;

	// Constructor
	public ControladorKwic(KwicFormArea parent) {

		// En el constructor asignamos el valor del display al parent;
		this.parentIn = parent;
	}

	// Constructor
	public ControladorKwic(KwicFormArea areaIn, KwicFormArea areaOut,
			KwicFormArea areaOutNoClaves, KwicFormField fieldIn,
			KwicFormField ficheroFrases, KwicFormField ficheroResultado,
			KwicFormField ficheroNoClaves, KwicFormArea estadisticas, KwicFormArea resultadoClientes) {
		
		this.parentIn = areaIn;
		this.parentOut = areaOut;
		this.parentField = fieldIn;
		this.parentOutNoClaves = areaOutNoClaves;
		// campo que contiene la ruta del fichero frases
		this.campoFicheroFrases = ficheroFrases;
		// campo que contiene la ruta del fichero donde guardamos el rdo Kwic
		this.campoFicheroResultado = ficheroResultado;
		// campo que contiene la ruta del fichero noClaves
		this.campoFicheroNoClaves = ficheroNoClaves;
		//campo que contiene las estadisticas
		this.campoEstadisticas = estadisticas;
		//campo que contiene los resultados de las conexiones de los clientes
		this.campoResultadosClientes = resultadoClientes;
		
	}

	/***************************************************************************************************
	 * Funcion actionPerformed:
	 * 
	 * @param ev: objeto de tipo ActionEvent. Proporciona informacion acerca
	 *            del suceso que se ha producido, se distingue el boton pulsado
	 */

	public void actionPerformed(ActionEvent ev) {
		
		//componente de la GUI que interactu� el usuario
		Object eventoLlamado = ev.getSource();
		KwicColeccion kwic = new KwicColeccion();

		if (eventoLlamado instanceof JButton) {
			// Comprobamos si el evento se ha originado en un JButton
			String botonPulsado = ((JButton) eventoLlamado).getName();
			// Leemos el contenido del boton para saber que accion tenemos que
			// realizar
			System.out.println("BOTON PULSADO: " + botonPulsado);

			/***************************************************************************/
			/*************************** Control del bton KWIC *************************/
			if (botonPulsado == "KWIC") {
				
				System.out.println("******************************");
				System.out.println("BOTON:" + botonPulsado);

				try {

					if (parentIn.getText().isEmpty()) {
						throw new KwicException(
								"No se puede dejar la entrada de titulos vacio");
						
					} else {
						
						// Obtenemos el valor del display
						display = parentIn.getText();
						int i = 0;
						StringTokenizer strk = new StringTokenizer(display,"\n");
						String frases[] = new String[strk.countTokens()];
						// se almacenan las frases introducidas en un array de
						// strings frases
						while (strk.hasMoreTokens()) {
							frases[i] = strk.nextToken();
							i++;
						}
						// Comprobamos que ha cogido la frases y las ha
						// introducido en un array de Strings
						System.out.println("No CLAVES introducidas por teclado son: " + AreaNoClaves);
						System.out.println("Las FRASES introducidas por teclado son: ");

						for (int j = 0; j < frases.length; j++) {
							System.out.println(frases[j]);
						}

						// Procesamos las no claves
						kwic.computaNoClaves(parentOutNoClaves.getText());

						// Procesamos las frases
						for (int x = 0; x < frases.length; x++) {
							kwic.computaIndice(frases[x]);
						}
						//asignamos el valor del resultado kwic al campo de texto resultante
						parentOut.setDisplayValue(kwic.toString());
						
					}
					
					arrancarServidor(kwic.toString(),(JButton)eventoLlamado);
					

				} catch (Exception e) {
					System.out.println("ERROR BOTÓN KWIC: " + e);
					e.printStackTrace();
				}// cierre try-catch

			/***************************************************************************/
			/*************************** Control del boton Aceptar *********************/
			} else if (botonPulsado == "adjuntarFicheroNoClaves") {
				
				System.out.println("******************************");
				System.out.println("BOTON: " + botonPulsado);
				//instancia de la clase KwicFicheros
				KwicFicheros fichero = new KwicFicheros();
				//volcamos el contenido del fichero de las no claves en area de texto de noClaves(parentOutNoClaves)
				fichero.obtenerFichero(ficheroNoClaves, campoFicheroNoClaves, parentOutNoClaves, eventoLlamado);
				//inicializamos a vacio
				parentField.setDisplayValue("");
				//pintamos en el log
				System.out.println("Las palabras no clave obtenidadas del fichero: "+ parentOutNoClaves.getText());

			/***************************************************************************/
			/**************** Control del boton adjuntar fichero Frases *****************/
			} else if (botonPulsado == "adjuntarFichero") {
				
				System.out.println("Se ha pulsado el boton para Adjuntar un archivo con las frases");
				//instancia de la clase KwicFicheros
				KwicFicheros fichero = new KwicFicheros();
				//volcamos el contenido del fichero de las frases en el area de texto de frases(parentIn)
				fichero.obtenerFichero(ficheroFrases, campoFicheroFrases, parentIn, eventoLlamado);
				//pintamos en el log
				System.out.println("Las frases obtenidadas del fichero: "+ parentIn.getText());
				
			/***************************************************************************/
			/****************** Control del boton grabar fichero resultante ************/
			} else if (botonPulsado.equals("grabarFichero")) {
				//instancia de la clase KwicFicheros
				KwicFicheros fichero = new KwicFicheros();
				//graba el resultado kwic en un fichero de salida 
				fichero.grabarFichero(campoFicheroResultado, ficheroResultado, parentOut, eventoLlamado);
				System.out.println("Kwic resultado guardado enfichero: "+ parentOut.getText());
				
			}// cierre else if
		}// cierro metodo actionPerformed
	}
		
	/***************************************************************************************************
	 * Funcion arrancarServidor: Esta funcion arranca el servidor. Se crea una instancia del objeto servidorKWIC
	 *  y se llama al m�todo ejecutarServidor() de la clase ServidorKwic
	 * 
	 * @param kwic: cadena que contiene el resultado de kwic 
	 * @param boton: objeto boton
	 * 
	 * 
	 */
	private void arrancarServidor(String kwic, JButton boton){
		
		boton.setEnabled(true);
		
		System.out.println("Arrancamos el servidor pasandole los datos del kwic");
		//instancia de la clase DatosClientes, que usaremos para pasarselo al servidor
		DatosCliente datosCli = new DatosCliente(parentOutNoClaves.getText(), parentIn.getText(), kwic);
		//instancia de la clase servidor KWIC					
		ServidorKWIC servidor =new ServidorKWIC(datosCli, campoEstadisticas, campoResultadosClientes);
		servidor.ejecutarServidor();
		
	}
}
