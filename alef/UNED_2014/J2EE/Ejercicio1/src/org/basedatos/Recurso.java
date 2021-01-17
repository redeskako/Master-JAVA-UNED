package org.basedatos;

import java.io.Serializable;


public class Recurso implements Comparable<Object>, Serializable{


	private static final long serialVersionUID = 1L;
	private int IdRecurso;
	private String Descripcion;
	
	//Declaración de los recursos y los id de recursos de la base de datos
	
	public Recurso(){
		this.IdRecurso=0;
		this.Descripcion=null;
	}
	
	public Recurso (int IdRecurso, String Descripcion){
		this.IdRecurso=IdRecurso;
		this.Descripcion=Descripcion;
	}

	//Declaración de los getter y setters de los componentes de la base de datos
	public int getIdRecurso() {
		return IdRecurso;
	}

	public void setIdRecurso(int idRecurso) {
		IdRecurso = idRecurso;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	public String toString(){
		return " (IdRecurso:" + this.IdRecurso + "- Descripcion:" + this.Descripcion +")";
	}
	
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/*public ArrayList<Recurso> listadoRecursos () throws ErrorException {
		ArrayList<Recurso> miLista;
		BBDD miBd = new BBDD();
		miBd.abrirConexion();
		miLista=miBd.listadoRecursos ("Select * from recursos");
		miBd.cerrarConexion();
		return miLista;
	}

	public Recurso addRecursos (Recurso recurso) throws ErrorException {
		// TODO Auto-generated method stub
		
		BBDD miBd =new BBDD();
		miBd.abrirConexion();
		recurso = miBd.ejecutarRecurso("insert * into recursos");
		miBd.cerrarConexion();
		return this;
	}*/

}


