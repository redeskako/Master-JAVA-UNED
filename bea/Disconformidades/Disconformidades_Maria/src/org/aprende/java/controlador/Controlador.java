package org.aprende.java.controlador;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.plaf.OptionPaneUI;
import java.awt.*;
import org.aprende.java.bbdd.*;
import org.aprende.java.vista.*;

public class Controlador implements ActionListener{
	public  static final int CONSULTA=0;
	public  static final int EDICION=1;

	public  static final int NUEVO=0;
	public  static final int MODIFICAR=1;
	public  static final int ELIMINAR=2;

	private Usuarios listaUsuarios;
	private Disconformidades listaDisconformidades;
	private Servicios listaServicios;
	private VistaDisconformidad vista;
	private Disconformidad regActual=null;
	private ArrayList<Disconformidad> arr;
	private int Estado;
	private int Accion;
	
	
	public Controlador(){
		try{
			
			//establezco el estado al valor de CONSULTA (me puedo mover por los registros, pero no puedo modificar directamente los datos
			this.Estado=this.CONSULTA;
			vista=new VistaDisconformidad();//creo una instancia a la ventana
			vista.asocia(this);  //le establezco el listener a los componentes de la ventana
			vista.estableceComponentesActivados(this.CONSULTA); 
		
			//relleno el formulario con los datos de la primera disconformidad
			listaDisconformidades=new Disconformidades().listadoDisconformidades();
		
					
			if (!listaDisconformidades.isEmpty()) { //si no esta vacia la lista //en la bd hay elementos 
				this.arr =new ArrayList<Disconformidad>();
				arr.addAll(listaDisconformidades);
				vista.rellenaVistaDisconformidad((Disconformidad)listaDisconformidades.first());
				
			}else { //no tengo disconformidades
				vista.sinDisconformidades();  //pongo los botones editar y eliminar desactivados al igual que los de desplazamiento
			}	
		}catch(DisconformidadException e){
			System.out.println(e);
		}
	}

	
	public void actionPerformed(ActionEvent evt){
		//boton nuevo
		if (evt.getActionCommand().equals("Nuevo")) {
			System.out.println("nuevo");
			this.Estado=this.EDICION;  //establezco el estado a EDICION
			this.Accion=this.NUEVO;
			
			vista.estableceComponentesActivados(this.EDICION);
			regActual= vista.recogerDisconformidad();
			vista.establecerNuevaDisconformidad();
		}
		
		//boton editar
		if (evt.getActionCommand().equals("Editar")) {
			System.out.println("Editar");
			this.Estado=this.EDICION;  //establezco el estado a EDICION
			this.Accion=this.MODIFICAR;
			regActual= vista.recogerDisconformidad();  // para tenerlo despues por si cancelo la edici�n
			vista.estableceComponentesActivados(this.EDICION);
		}
		//boton eliminar
		if (evt.getActionCommand().equals("Eliminar")) {
			System.out.println("Eliminar");
			this.Accion=this.ELIMINAR;
		}
		//boton aceptar
		if (evt.getActionCommand().equals("Aceptar")) {
			System.out.println("Aceptar");
			Disconformidad dis=vista.recogerDisconformidad();
			this.Estado=this.CONSULTA;
			
			if (this.Accion==this.NUEVO) {  //voy a a�adir un registro nuevo
				if (dis==null) {
					System.out.println("no tengo disconformidad");
				}else{
					listaDisconformidades.addDisconformidades(dis);
					System.out.println(dis.toString());
				}	
			}else if (this.Accion==this.MODIFICAR){ // voy a modificar el registro actual
				
			}
			vista.estableceComponentesActivados(this.Estado);
			vista.estableceBotonesVisibles(this.Estado);
		}								
		//boton cancelar
		if (evt.getActionCommand().equals("Cancelar")) {
			System.out.println("Cancelar");
			this.Estado=this.CONSULTA;
			vista.estableceComponentesActivados(this.CONSULTA);
			vista.rellenaVistaDisconformidad(regActual);
		}
		
		
		//boton ultimo
		if (evt.getActionCommand().equals("Ultimo")) {
			System.out.println("Ultimo");
			vista.rellenaVistaDisconformidad((Disconformidad)listaDisconformidades.last());


		}
		//boton siguiente
		if (evt.getActionCommand().equals("Siguiente")) {
			System.out.println("Siguiente");
			if (this.arr.listIterator().hasNext()){
				Disconformidad midis=(Disconformidad)this.arr.listIterator().next();
				midis=(Disconformidad)this.arr.listIterator().next();
				vista.rellenaVistaDisconformidad(midis);
			}else{//no hay mas elementos
				JOptionPane.showMessageDialog(null, "No hay mas elementos");
			}
					}
		//boton inicio
		if (evt.getActionCommand().equals("Inicio")) {
			System.out.println("Inicio");
			vista.rellenaVistaDisconformidad((Disconformidad)listaDisconformidades.first());

		}
		//boton anterior
		if (evt.getActionCommand().equals("Anterior")) {
			System.out.println("Anterior");
			if (this.arr.listIterator().hasPrevious()){
				vista.rellenaVistaDisconformidad((Disconformidad)this.arr.listIterator().previous());
			}else{//no hay mas elementos
				JOptionPane.showMessageDialog(null, "No hay mas elementos");
			}

		}
		//boton salir
		if (evt.getActionCommand().equals("Salir")) {
			System.out.println("salir");
			System.exit(0);  //salgo del sistema
			
		}
		
	}// fin actionPerformed
}
