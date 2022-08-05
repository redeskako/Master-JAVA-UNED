package es.uned.master.java.kwic.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

import javax.swing.JButton;
import es.uned.master.java.kwic.logica.coleccion.KwicColeccion;
import es.uned.master.java.kwic.logica.modelo.KwicException;
import java.io.*;
import java.nio.*;



public class ControladorKwic implements ActionListener {

	private KwicFormArea parentIn;
	private KwicFormArea parentOut;
	private KwicFormArea parentOutNoClaves;
	private KwicFormField parentField;
	private String display;
	private String display2;
	private String AreaNoClaves;
	
	//Constructor
	public ControladorKwic(KwicFormArea parent) {

		// En el constructor asignamos el valor del display al parent;
		this.parentIn = parent;
	}
	
	//Constructor
	public ControladorKwic(KwicFormArea areaIn, KwicFormArea areaOut, KwicFormArea areaOutNoClaves, KwicFormField fieldIn){
		this.parentIn = areaIn;
		this.parentOut = areaOut;
		this.parentField = fieldIn;		
		this.parentOutNoClaves = areaOutNoClaves;	
	}
	
	

	/***************************************************************************************************
	 *Funcion actionPerformed:
	 *
	 * @param ev: objeto de tipo ActionEvent
	 * Proporciona informacion acerca del suceso que se ha producido, se distingue el boton pulsado
	 */

	public void actionPerformed(ActionEvent ev) {

		Object eventoLlamado = ev.getSource();
		KwicColeccion kwic = new KwicColeccion();
		
		if (eventoLlamado instanceof JButton) {
			// Comprobamos si el evento se ha originado en un JButton
			String botonPulsado = ((JButton) eventoLlamado).getText();
			// Leemos el contenido del boton para saber que accion tenemos que realizar
			
			if (botonPulsado == "KWIC") {
				System.out.println("******************************");
				System.out.println("BOTON:" + botonPulsado);
				
				try {

					if (parentIn.getText().isEmpty()) {
						throw new KwicException(
								"No se puede dejar la entrada de títulos vacía");
					} else {
						// Obtenemos el valor del display
						display = parentIn.getText();
						int i = 0;
						StringTokenizer strk = new StringTokenizer(display,"\n");
						String frases[] = new String[strk.countTokens()];
				
						//se almacenan las frases introducidas en un array de strings frases
						while (strk.hasMoreTokens()){					
							frases[i] = strk.nextToken();					
							i++;
						}
						//Comprobamos que ha cogido la frases y las ha introducido en un array de Strings
						System.out.println("No CLAVES introducidas por teclado son: "+AreaNoClaves);
						System.out.println("Las FRASES introducidas por teclado son: ");
				
						for (int j=0;j < frases.length;j++){
							System.out.println(frases[j]);
						}
				
				
						//Procesamos las no claves
						kwic.computaNoClaves(AreaNoClaves);
				
						//Procesamos las frases
						for (int x = 0; x < frases.length; x++) {
							kwic.computaIndice(frases[x]);
						}
						parentOut.setDisplayValue(kwic.toString());
						
						//Inicializar fichero
						File ficheroKwic = new File("kwicText.txt");
						if (ficheroKwic.exists()){
							ficheroKwic.delete();
						}
						FileOutputStream kwicText = null;
						Writer salida =null;
						try{
							//Almacenamos el valor de las palabras no clave en un fichero de texto
							kwicText = new FileOutputStream(ficheroKwic);
							salida = new BufferedWriter(new OutputStreamWriter(kwicText, "UTF8"));
							salida.append(kwic.toString());
						}catch(IOException e){
							System.out.println("No se puede escribr en el archivo");
						}finally{
							//Cierre del flujo
							if (kwicText != null){
								try{
									salida.close();
									kwicText.close();
								}catch (Exception e1){
									e1.printStackTrace();
								}
							}
						}
					}

				} catch (Exception e) {
					System.out.println("ERROR BOTÓN KWIC: " + e);
				}//cierre try-catch
				
			} else if (botonPulsado == "Aceptar") {
				System.out.println("******************************");
				System.out.println("BOTON: " + botonPulsado);
				try {

					if (parentField.getText().isEmpty()) {
						throw new KwicException(
								"No se puede dejar la entrada de no claves vacía");
					} else {
						// Obtenemos el valor del display
						display2 = parentField.getText();
						//Leer archivo de texto noClaves
						StringBuffer buffer = new StringBuffer();
						try{
							FileInputStream ficheroNoClaves = new FileInputStream(display2);
							InputStreamReader entradaNoClaves = new InputStreamReader(ficheroNoClaves, "UTF8");
							Reader reader = new BufferedReader(entradaNoClaves);
							int ch; // el codigo de un caracter
							while ((ch = reader.read()) > -1){
								buffer.append((char)ch);
							}
							buffer.toString();
							System.out.println("El valor del buffer es..." + buffer.toString());
							parentOutNoClaves.setDisplayValue(buffer.toString());
						}catch(IOException e){
								e.printStackTrace();
							
						}
						
						parentField.setDisplayValue("");
						
						
						
						//Version 1:concatenamos las no claves separadas por un espacio en blanco				
						//parentOutNoClaves.setDisplayValue(parentOutNoClaves.getText() +" "+ display2);		
						AreaNoClaves = parentOutNoClaves.getText();
						System.out.println("Las palabras no clave insertadas: " + AreaNoClaves);								
					}//cierre else
				} catch (Exception e) {
					System.out.println("ERROR BOTÓN ACEPTAR: " + e);
				}//cierre try-catch
			}//else if botonPulsado
		}//if
	}//metodo actionPerformed

}

