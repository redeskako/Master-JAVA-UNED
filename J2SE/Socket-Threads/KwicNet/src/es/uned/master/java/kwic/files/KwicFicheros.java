package es.uned.master.java.kwic.files;

import java.awt.Component;
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

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import es.uned.master.java.kwic.controlador.KwicFormArea;
import es.uned.master.java.kwic.controlador.KwicFormField;

public class KwicFicheros {
	
	//constructor
	public KwicFicheros(){
		System.out.println("Manejamos ficheros");
	}
	
	/*
	 * Funcion obtenerFichero: Obtine el contenido de un fichero indicado por el usuario 
	 * y volcamos el contenido en un area de texto que pasamos como parametro
	 * 
	 * @param ficheroEntrada: flujo de entrada del fichero
	 * @param campoFichero: ruta del directorio fichero de entrada
	 * @param campoSalida: area de texto donde volcamos el contenido del fichero 
	 * @param evento: componente de la GUI que interactuó el usuario
	 * 
	 * 
	 */
	public void obtenerFichero(FileInputStream ficheroEntrada, KwicFormField campoFichero, KwicFormArea campoSalida, Object evento) {
		
		try {
			// muestra directamente una ventana que permite navegar por los
			// directorios y elegir un fichero
			final JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog((Component) evento);
			//el usuario selecciono el botón Abrir el cuadro de dialogo
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				try {
					// Obtenemos el directorio entero del fichero
					ficheroEntrada = new FileInputStream(fc.getSelectedFile().getAbsolutePath());
					System.out.println("Abriendo: " + fc.getSelectedFile().getAbsolutePath());
				} catch (IOException e) {
					e.printStackTrace();
				}
				//volcamos en el campo de texto el directorio del archivo noClaves
				campoFichero.setDisplayValue(fc.getSelectedFile().getAbsolutePath());

				//recogemos los datos de un fichero de texto
				StringBuffer buffer = new StringBuffer();
				try {
					//archivo codificado en UTF8
					InputStreamReader entradaFichero = new InputStreamReader(ficheroEntrada, "UTF8");
					//se maneja el flujo en forma de buffer
					Reader reader = new BufferedReader(entradaFichero);
					int ch;
					while ((ch = reader.read()) > -1) {
						buffer.append((char) ch);
					}
					//volcamos en el area de texto el contenido del fichero 
					campoSalida.setDisplayValue(buffer.toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else { //se cancela la operacion, el usuario selecciono bton cancelar del cuadro de dialogo
				System.out.println("Operacion cancelada por el usuario");
			}
		} catch (Exception e) {
			System.out.println("ERROR BOTON ADJUNTAR FICHERO:" + e);
		}
	}
	
	/*
	 * Funcion grabarFichero:Graba el kwic resultado en un fichero de salida
	 * 
	 * @param campoFichero: campo de texto de la ruta del directorio del fichero
	 * @param ficheroSalida: flujo de salida del fichero
	 * @param campoResultado: area texto del kwic resultado
	 * @param evento:componente de la GUI que interactuó el usuario
	 * 
	 * 
	 */
	public void grabarFichero(KwicFormField campoFichero, FileOutputStream ficheroSalida, KwicFormArea campoResultado, Object evento){
		
		try {
			//la clase JFileChooser muestra directamente una ventana que permite navegar por los directorios y elegir un fichero
			final JFileChooser fc = new JFileChooser();
			//en el cuadro de dialogo solo se mostraran los archivos txt
			FileNameExtensionFilter filtroExtension = new FileNameExtensionFilter("Texto(*.txt)", "txt");
			//obtenemos el boton que se ha pulsado en el cuadro de dialogo
			int returnVal = fc.showSaveDialog((Component) evento);
			//el usuario selecciono bton Guardar del cuadro de dialogo
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				//si introducimos el nombre del fichero con extension se la sacamos para anadirla automaticamente
				String cadena = fc.getSelectedFile().getAbsolutePath();
				int j=0;
				for (int i=0; i<cadena.length();i++){
					if (cadena.charAt(i)=='.'){
						j=i;
					}
				}			
				cadena = cadena.substring(0, j);
				System.out.println("Nombre del fichero donde guardar rdo kwic: "+cadena);
					
				// Obtenemos el fichero de salida
				fc.setSelectedFile(new File(cadena+".txt"));
				//volcamos en el campo de texto la ruta del directorio del archivo
				campoFichero.setDisplayValue(fc.getSelectedFile().getAbsolutePath());
				try {
					if (fc.getSelectedFile().exists()) {
						fc.getSelectedFile().delete();
					}
					//abrimos un flujo de salida pasandole el fichero seleccionado
					ficheroSalida = new FileOutputStream(fc.getSelectedFile());				
					System.out.println("Abriendo: "+ fc.getSelectedFile().getAbsolutePath());
				} catch (IOException e) {
					e.printStackTrace();
				}

				Writer salida = null;
				try {
					//escritura de caracteres en un archivo codificado en UTF-8
					salida = new BufferedWriter(new OutputStreamWriter(ficheroSalida, "UTF8"));
					//anadimos al flujo de salida el contenido del area de texto del resultado
					salida.append(campoResultado.getText());
				} catch (IOException e) {
					System.out.println("ERROR Almacenando archivo: "+ e.getMessage());
				} finally {
					// Cerramos flujo
					if (ficheroSalida != null) {
						try {
							salida.close();
							ficheroSalida.close();

						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
			} else { //se cancela la operacion, el usuario selecciono bton cancelar del cuadro de dialogo
				System.out.println("Operacion cancelada por el usuario");
			}

		} catch (Exception e) {
			System.out.println("ERROR BOTON GRABAR FICHERO:" + e);
		}
	}

}
