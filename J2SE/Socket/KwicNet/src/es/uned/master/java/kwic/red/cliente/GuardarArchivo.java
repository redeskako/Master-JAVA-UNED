package es.uned.master.java.kwic.red.cliente;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;


import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import es.uned.master.java.kwic.InterfazCliente;


public class GuardarArchivo extends Thread {
	
	private InterfazCliente interfaz;
	private FileOutputStream ficheroFrases;
	private FileOutputStream ficheroNoClaves;
	private FileOutputStream ficheroKwic;
	private ActionEvent ev;
	
public GuardarArchivo(ActionEvent aux, InterfazCliente aux2){
	ev=aux;
	interfaz=aux2;
}
	public void run() {
	System.out.println("Se ha pulsado el boton para guardar");
	Object eventoLlamado = ev.getSource();
	
	final JFileChooser fc = new JFileChooser();
	FileNameExtensionFilter filtroExtension = new FileNameExtensionFilter("Texto(*.txt)", "txt");
	fc.setFileFilter(filtroExtension);
	int returnVal = fc.showSaveDialog((Component) eventoLlamado);
	if (returnVal == JFileChooser.APPROVE_OPTION) {//Se pulsa el boton Guardar
		//Escribimos la ruta donde se va a guardar
		interfaz.setDatosArchivoCargar(fc.getSelectedFile().getAbsolutePath());
		try {
			if (fc.getSelectedFile().exists()) {
				fc.getSelectedFile().delete();
			}
			ficheroFrases = new FileOutputStream(fc.getSelectedFile()+"Frases.txt");
			ficheroKwic = new FileOutputStream(fc.getSelectedFile()+"Kwic.txt");
			ficheroNoClaves = new FileOutputStream(fc.getSelectedFile()+"NoClaves.txt");
			System.out.println("Guardando: "+ fc.getSelectedFile().getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}

		Writer salidaKwic = null;
		Writer salidaNoClaves = null;
		Writer salidaFrases = null;
		try {		

			//Flujo de salida de las no claves
			salidaNoClaves = new BufferedWriter(new OutputStreamWriter(ficheroNoClaves, "UTF8"));
			salidaNoClaves.append("Lista de No Claves:"+"\n\t");
			salidaNoClaves.append(interfaz.getAreaNoClaves());
			//Flujo de salida de las frases
			salidaFrases = new BufferedWriter(new OutputStreamWriter(ficheroFrases, "UTF8"));
			salidaFrases.append("\n"+"Lista de Frases:"+"\n\t");
			salidaFrases.append(interfaz.getAreaFrases());
			//Flujo de salida del resultado kwic
			salidaKwic = new BufferedWriter(new OutputStreamWriter(ficheroKwic, "UTF8"));
			salidaKwic.append("\n"+"Resultado Kwic:"+"\n\t");
			salidaKwic.append(interfaz.getAreaKwic());
		} catch (IOException e) {
			System.out.println("ERROR Almacenando archivo: "+ e.getMessage());
		} finally {
			// Cerramos flujo
			if (ficheroFrases != null) {
				try {
					salidaNoClaves.close();
					salidaFrases.close();
					salidaKwic.close();
					ficheroFrases.close();
					ficheroKwic.close();
					ficheroNoClaves.close();

				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
        
        
        
    } else {										//Se cancela la operacion
        System.out.println("Operacion cancelada por el usuario");
        }

	}
}
