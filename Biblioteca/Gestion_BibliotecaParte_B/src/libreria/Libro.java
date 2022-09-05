/*
 *   CARLOS LUIS SÁNCHEZ BOCANEGRA
                            DNI: 26017022C
             Expediente UNED: 55­04­00458
Domicilio: C/Cura Merino 2 2ºD 29011 Málaga

 */
package libreria;

import java.io.*;
import libreria.error.*;
import libreria.lenguaje.*;

public class Libro implements Comparable<Libro>,Serializable{
	public static final int NULO=0;
	public static final int NADA=0;
	public static final int NUEVO=1;
	public static final int ACTUALIZAR=2;
	public static final int BORRAR=3;
	public static final int COLUMNAS=5;

	private String NOMCOL[];
	private int idLibro;
	private String nombre;
	private String autor;
	private String tema;
	private Boolean aEliminar;
	private int estado;
	private Config idioma;
	
	public String[] NOMCOL(){
		return this.NOMCOL;
	}
	private void iniciaNOMCOL(){
		this.NOMCOL= new String [5];
		this.NOMCOL[0]= this.idioma.traduce("_ID");
		this.NOMCOL[1]= this.idioma.traduce("_NOMBRE");
		this.NOMCOL[2]= this.idioma.traduce("_AUTOR");
		this.NOMCOL[3]= this.idioma.traduce("_TEMA");
		this.NOMCOL[4]= this.idioma.traduce("_ELIMINAR");
	}
	public Libro(int idLibro, String nombre, String autor, String tema,String local){
		this.idioma= new Config(local);
		this.iniciaNOMCOL();
		this.idLibro= idLibro;
		this.nombre= nombre;
		this.autor= autor;
		this.tema= tema;
		this.aEliminar= new Boolean(false);
		this.estado=NADA;
	}
	public Libro(String nombre, String autor, String tema,String local){
		this.idioma= new Config(local);
		this.iniciaNOMCOL();
		this.idLibro=NULO;
		this.nombre= nombre;
		this.autor= autor;
		this.tema= tema;
		this.aEliminar=new Boolean(false);
	}
	public int idLibro(){
		return this.idLibro;
	}
	public String nombre(){
		return this.nombre;
	}
	public String autor(){
		return this.autor;
	}
	public String tema(){
		return this.tema;
	}
	public void nombre(String nombre){
		this.nombre= nombre;
	}
	public void tema(String tema){
		this.tema= tema;
	}
	public void autor(String autor){
		this.autor= autor;
	}
	public void estado(int estado){
		this.estado=estado;
	}
	public int estado(){
		return this.estado;
	}
	public boolean aEliminar(){
		return this.aEliminar;
	}
	public void aEliminar(boolean b){
		this.aEliminar= new Boolean(b);
	}
	public boolean equals(Object o){
		try{
			Libro l= (Libro) o;
			return this.nombre.toUpperCase().equals(l.nombre().toUpperCase());
		}catch(ClassCastException e){
			throw new ErrorLibreriaException ("No es un libro."+e);
		}
	}
	public int compareTo(Libro l){
		return this.nombre.toUpperCase().compareTo(l.nombre().toUpperCase());
	}
	public String estadoString(int i){
		switch (i){
			case NADA:
				return "";
			case ACTUALIZAR:
				return this.idioma.traduce("_ACTUALIZAR");
			case BORRAR:
				return this.idioma.traduce("_BORRAR");
			case NUEVO:
				return this.idioma.traduce("_NUEVO");
			default:
				return "";
		}
	}
	public String toString(){
		return new String(this.idioma.traduce("_AUTOR:")+this.idLibro+" -> "+this.nombre+" ("+this.autor+" - "+this.tema+") :  Estado =>"+this.estadoString(this.estado));
	}
	public Object getColumn(int column)throws ErrorLibreriaException{
		switch (column){
			case 0:
					return new Integer(this.idLibro);
			case 1:
					return this.nombre;
			case 2:
					return this.autor;
			case 3:
					return this.tema;
			case 4:
					return this.aEliminar;
			default:
				throw new ErrorLibreriaException(this.idioma.traduce("_NOHAYCOLUMNA"));
		}
	}
}
