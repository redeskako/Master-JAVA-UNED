package org.aprende.java.controlador;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import org.aprende.java.bbdd.*;
import org.aprende.java.vista.*;

public class Controlador implements ActionListener{
	private Usuarios usu;
	//private Servicios serv;
	private Disconformidades dis;
	
	
	public Controlador(VistaDisconformidad ventana){
		usu=new Usuarios();
		//serv=new Servicios();
		dis= new Disconformidades();
	}
	
	public void actionPerformed(ActionEvent evt){
		if (evt.getActionCommand().equals("Aceptar")) {
			System.out.println("acepto");
	
		}
		if (evt.getActionCommand().equals("Cancelar")) {
			System.out.println("cancelo");

		}
		if (evt.getActionCommand().equals("Editar")) {
			System.out.println("Editar");

		}
		if (evt.getActionCommand().equals("Eliminar")) {
			System.out.println("Eliminar");

		}
	}
}
