/*
 *   CARLOS LUIS SÁNCHEZ BOCANEGRA
                            DNI: 26017022C
             Expediente UNED: 55­04­00458
Domicilio: C/Cura Merino 2 2ºD 29011 Málaga

 */
package libreria;

import java.io.*;
import java.util.*;
import java.sql.Date;
import java.text.*;
import libreria.error.*;
import libreria.lenguaje.*;

public class Socio implements Comparable<Socio>,Serializable{
	private int idSocio;
	private String dNI;
	private String nombre;
	private String apellidos;
	private String direccion;
	private String fechaAlta; //Fecha Alta parametrizada a dd/MM/yyyy
	private Boolean aEliminar;
	private int estado;
	private String []NOMCOL;
	public static final int COLUMNAS=7;
	private Config idioma;
	
	public String[] NOMCOL(){
		return this.NOMCOL;
	}
	private String [] iniciaNOMCOL(){
			this.NOMCOL= new String [COLUMNAS];
			this.NOMCOL[0]= this.idioma.traduce("_ID");
			this.NOMCOL[1]= this.idioma.traduce("_DNI");
			this.NOMCOL[2]= this.idioma.traduce("_APELLIDOS");
			this.NOMCOL[3]= this.idioma.traduce("_NOMBRE");
			this.NOMCOL[4]= this.idioma.traduce("_DIRECCION");
			this.NOMCOL[5]= this.idioma.traduce("_FECHAALTA");
			this.NOMCOL[6]= this.idioma.traduce("_ELIMINAR");
			return this.NOMCOL;
	}
		public static java.util.Date calculaFecha(String fecha,String formato){
			java.util.Date fec=null;
			SimpleDateFormat df= new SimpleDateFormat(formato);
			df.setLenient(false);
			fec= df.parse(fecha, new ParsePosition(0));
			if (fec==null) {
				fec=df.parse(new java.util.Date(System.currentTimeMillis()).toString(),new ParsePosition(0));
			}
			return fec;
		}
		public Socio (int idSocio, String dni,String apellidos, String nombre, String direccion, String fechaAlta,String local){
			this(dni,apellidos,nombre,direccion,local);
			this.idSocio=idSocio;
			SimpleDateFormat df= new SimpleDateFormat("dd-MM-yyyy");
			this.fechaAlta= df.format(Socio.calculaFecha(fechaAlta,"yyyy-MM-dd"));
		}
	public Socio (int idSocio, String dNI,String apellidos, String nombre, String direccion, java.sql.Date fechaAlta,String local){
		this(dNI,apellidos,nombre,direccion,local);
		this.idSocio=idSocio;
		SimpleDateFormat df= new SimpleDateFormat("dd-MM-yyyy");
		this.fechaAlta=df.format(Socio.calculaFecha(fechaAlta.toString(),"yyyy-MM-dd"));
	}
	public Socio(String dNI, String apellidos, String nombre, String direccion,String local){
			this.idioma= new Config(local);
			this.iniciaNOMCOL();
			this.dNI= dNI;
			this.nombre= nombre;
			this.apellidos= apellidos;
			this.direccion= direccion;
			SimpleDateFormat df= new SimpleDateFormat("dd/MM/yyyy");
			this.fechaAlta= df.format(new Date(System.currentTimeMillis()));
			this.aEliminar= new Boolean(false);
			this.estado=Libro.NADA;
	}
	public int idSocio(){
		return this.idSocio;
	}
	public String nombre(){
		return this.nombre;
	}
	public String apellidos(){
		return this.apellidos;
	}
	public String direccion(){
		return this.direccion;
	}
	public String dni(){
		return this.dNI;
	}
	public java.util.Date fecha(){
		java.util.Date fec=null;
		SimpleDateFormat df= new SimpleDateFormat("dd-MM-yyyy");
		try{
			fec=df.parse(this.fechaAlta);
		}catch(ParseException err){
			throw new ErrorLibreriaException ("Fecha incorrecta."+err);
		}
		return fec;			
	}
	public String fechaAlta(){
		return this.fechaAlta;
	}
	public String fechaAltaSQL(){
		SimpleDateFormat df= new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date fecha= df.parse(this.fechaAlta, new ParsePosition(0));
		df.setLenient(false);
		SimpleDateFormat df1= new SimpleDateFormat("yyyy-MM-dd");
		return df1.format(fecha);
	}
	public static String bfechaAltaSQL(String fechaAlta){
		SimpleDateFormat df= new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date fecha= df.parse(fechaAlta, new ParsePosition(0));
		df.setLenient(false);
		SimpleDateFormat df1= new SimpleDateFormat("yyyy-MM-dd");
		return df1.format(fecha);
	}
	public void nombre(String nombre){
		this.nombre= nombre;
	}
	public void dni(String dni){
		this.dNI=dni;
	}
	public void apellidos(String apellidos){
		this.apellidos= apellidos;
	}
	public void direccion(String direccion){
		this.direccion=direccion;
	}
	public void fechaAlta(String fechaAlta){
		try{
			SimpleDateFormat df= new SimpleDateFormat("dd-MM-yyyy");
			this.fechaAlta=df.format(Socio.calculaFecha(fechaAlta,"dd-MM-yyyy"));
		}catch(Exception err){
			throw new ErrorLibreriaException ("No es fecha"+err);
		}
	}
	public static String validaFecha(String fecha){
		String res=null;
		try{
			SimpleDateFormat df= new SimpleDateFormat("dd-MM-yyyy");
			res= df.format(Socio.calculaFecha(fecha,"dd-MM-yyyy"));
		}catch(Exception err){}
		return res;
	}
	public int estado(){
		return this.estado;
	}
	public void estado(int estado){
		this.estado=estado;
	}	
	public boolean aEliminar(){
		return this.aEliminar;
	}
	public void aEliminar(boolean b){
		this.aEliminar= new Boolean(b);
	}
	public boolean equals(Object o){
		try{
			Socio s= (Socio) o;
			return this.dNI.toUpperCase().equals(s.dni().toUpperCase());
		}catch(ClassCastException e){
			throw new ErrorLibreriaException (this.idioma.traduce("_NOAUTOR")+e);
		}
	}
	public int compareTo(Socio s){
		return this.nombre.toUpperCase().compareTo(s.nombre().toUpperCase());
	}
	public String estadoString(int i){
		switch (i){
			case Libro.NADA:
				return "";
			case Libro.ACTUALIZAR:
				return this.idioma.traduce("_ACTUALIZAR");
			case Libro.BORRAR:
				return this.idioma.traduce("_BORRAR");
			case Libro.NUEVO:
				return this.idioma.traduce("_NUEVO");
			default:
				return "";
		}
	}
	public String toString(){
		return new String(this.idioma.traduce("_ID")+this.idSocio+" -> "+this.apellidos+" "+this.nombre+" - "+this.direccion+ "-  Alta =>"+this.fechaAlta.toString()+" - Estado =>"+estadoString(this.estado));
	}
	public Object getColumn(int column)throws ErrorLibreriaException{
		switch (column){
			case 0:
					return new Integer(this.idSocio);
			case 1:
					return this.dNI;
			case 2:
					return this.apellidos;
			case 3:
					return this.nombre;
			case 4:
					return this.direccion;
			case 5:
					return this.fechaAlta;
			case 6:
					return this.aEliminar;
			default:
				throw new ErrorLibreriaException(this.idioma.traduce("_NOHAYCOLUMNA"));
		}
	}
}