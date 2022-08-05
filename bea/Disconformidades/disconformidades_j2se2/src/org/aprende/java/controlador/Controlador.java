package org.aprende.java.controlador;
import java.awt.event.*;
import java.util.*;


import javax.swing.*;
import java.awt.*;
import org.aprende.java.bbdd.*;
import org.aprende.java.vista.*;

public class Controlador implements ActionListener{
	public  static final int CONSULTA=0;
	public  static final int EDICION=1;

	public  static final int NUEVO=0;
	public  static final int MODIFICAR=1;
	public  static final int ELIMINAR=2;

	
	private int Estado;
	private int Accion;
	private int cont;
	private Usuarios usu;
	private Disconformidades dis;
	private Servicios ser; 
	private Disconformidad d;
	private VistaDisconformidad ventana;
	private ArrayList<Disconformidad> arr;
	//private Iterator<Disconformidad> lit;
	
	//public Controlador(VistaDisconformidad ventana){
	public Controlador(){
		this.Estado=this.CONSULTA;
		cont=0;
		
		usu=new Usuarios();
		ser=new Servicios();
		dis= new Disconformidades();
		d = new Disconformidad();
		ventana=new VistaDisconformidad();
		ventana.setVisible(true);
		ventana.asocia(this);
		ventana.estableceComponentesActivados(this.CONSULTA);
		this.ventana.llenarcombo(ser.AllServicios());
		this.ventana.llenarcomboUsuarios(usu.AllUsuarios());
		this.dis= this.dis.listadoDisconformidades();
		this.arr =new ArrayList<Disconformidad>();
		//lit= this.arr.listIterator();
		arr.addAll(dis);
		primero();
	}
	
	private void primero(){
		d=dis.first();
		this.ventana.setvalores (d,ser.getServicio(d.servicio()).nombre(),usu.getUsuario(d.usuario()).nombre());
    	cont=0;
    	this.ventana.anteriordisponible(false);
    	System.out.println("Primero");
	}
	
	public void actionPerformed(ActionEvent evt){
		
		if (evt.getActionCommand().equalsIgnoreCase("Anterior")) {
/*			if (this.lit.hasPrevious()){
				Disconformidad d= this.lit.previous();
				this.ventana.setvalores (d);
			}*/
			if (cont > 0) {
				d=this.arr.get(--cont);
				this.ventana.setvalores (d,ser.getServicio(d.servicio()).nombre(),usu.getUsuario(d.usuario()).nombre());
				System.out.println("cont="+cont + "size="+ this.arr.size());
				System.out.println("Anterior");
			}else{
				System.out.println("Ya es el primero");
			}	
		
		}else if (evt.getActionCommand().equalsIgnoreCase("Siguiente")) {	
			/*if (this.lit.hasNext()){				
				Disconformidad d= this.lit.next();
				this.ventana.setvalores (d);
			}*/			
			if (cont < this.arr.size()-1) {
				d=this.arr.get(++cont);
				this.ventana.setvalores (d,ser.getServicio(d.servicio()).nombre(),usu.getUsuario(d.usuario()).nombre());
				System.out.println("cont="+cont + "size="+ this.arr.size());
				System.out.println("Siguiente");

			}else{
				System.out.println("Ya es el ultimo");
			}
			
		}else if (evt.getActionCommand().equals("Ultimo")) {
			//this.ventana.setvalores (this.arr.get(this.arr.size()-1),ser,usu);
			d=this.arr.get(this.arr.size()-1);
			this.ventana.setvalores (d,ser.getServicio(d.servicio()).nombre(),usu.getUsuario(d.usuario()).nombre());
			cont=this.arr.size()-1;
			
		}else if (evt.getActionCommand().equals("Inicio")) {
			primero();
			
		}else if (evt.getActionCommand().equals("Aceptar")) {
			System.out.println("acepto");

			this.Estado=this.CONSULTA;

			Disconformidad d =new Disconformidad();
			d=this.ventana.getvalores(d,ser,usu);
			
			if (this.Accion==this.NUEVO) {  //voy a a�adir un registro nuevo
				if (d == null) {
					System.out.println("no tengo disconformidad");
				}else{
					
					dis=dis.addDisconformidades(d);
					arr.clear();
					arr.addAll(dis);
				//	this.lit= this.arr.listIterator();
					
					/*arr=null;
					arr.addAll(dis);
					 */
					//listaDisconformidades.addDisconformidades(dis);
					//System.out.println(dis.toString());
				}	
			}else if (this.Accion==this.MODIFICAR){ // voy a modificar el registro actual
			//	this.lit= this.arr.listIterator();
			}
			ventana.estableceComponentesActivados(this.Estado);
			ventana.estableceBotonesVisibles(this.Estado);
	
		}else if (evt.getActionCommand().equals("Cancelar")) {
			System.out.println("cancelo");
			this.Estado=this.CONSULTA;
			ventana.estableceComponentesActivados(this.CONSULTA);
     		//ventana.rellenaVistaDisconformidad(regActual);
		}else if (evt.getActionCommand().equals("Editar")) {
			this.Estado=this.EDICION;  //establezco el estado a EDICION
			this.Accion=this.MODIFICAR;
			//regActual= vista.recogerDisconformidad();  // para tenerlo despues por si cancelo la edici�n
			ventana.estableceComponentesActivados(this.EDICION);
			
			Disconformidad d =new Disconformidad();
	
			//this.ventana.setvalores(d,ser,usu);
			System.out.println("Editar");

		}else if (evt.getActionCommand().equals("Nuevo")) {

			this.Estado=this.EDICION;  //establezco el estado a EDICION
			this.Accion=this.NUEVO;
			ventana.estableceComponentesActivados(this.EDICION);
			System.out.println("Nuevo");
	
		}else if (evt.getActionCommand().equals("Eliminar")) {
			dis=dis.deleteDisconformidades(d);
			arr.clear();
			arr.addAll(dis);
			primero();
			
			System.out.println("Eliminar");
			//this.lit= this.arr.listIterator();
		}else if (evt.getActionCommand().equals("Salir")) {
			System.out.println("Salir");
			System.exit(0);  //salgo del sistema

		}
	}
}
