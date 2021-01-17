package org.Otros;
import org.BBDD.*;


import java.util.ArrayList;


public class Recursos {
	
	private int codigo;
	private String nombre;
	
	// CONSTRUCTOR

	
	public Recursos(){


	}
	
	public Recursos(int p1, String p2){
		codigo = p1;
		nombre = p2;

	}


	
		// FUNCION PARA OBTENER GETCODIGO
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	//ARRAYLIST DEL TIPO RECURSOS CON 2 COMPONENETES
	public ArrayList<Recursos> listadoRecursos(String sql) {
		ArrayList<Recursos> listaRecursos;
		BBDD miBd = new BBDD();
		miBd.abrirConexion(); //No hacemos tratamiento de errores porque ya se ha hecho en el m���todo abrirConexi���n.		
	

		
				
		listaRecursos =miBd.listadoRecursos(sql);
		miBd.cerrarConexion();//No hacemos tratamiento de errores porque ya se ha hecho en el m���todo abrirConexi���n.
		return listaRecursos;		
	}
	
	
}
