/*
 *   CARLOS LUIS SÁNCHEZ BOCANEGRA
                            DNI: 26017022C
             Expediente UNED: 55­04­00458
Domicilio: C/Cura Merino 2 2ºD 29011 Málaga

 */
package libreria;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.Date;
import java.text.*;

import libreria.error.ErrorLibreriaException;

public class Prestamo implements Comparable<Prestamo>{
	private int idPrestamo;
	private String idSocio;
	private String idLibro;
	private String fechaInicio;
	private String fechaFin;
	private Boolean aEliminar;
	private int estado;
	private String []NOMCOL;
	public static final int COLUMNAS=6;

			public String[] NOMCOL(){
				return this.NOMCOL;
			}
			private String [] iniciaNOMCOL(){
					this.NOMCOL= new String [COLUMNAS];
					this.NOMCOL[0]= Main.idioma.traduce("_ID");
					this.NOMCOL[1]= Main.idioma.traduce("_SOCIO");
					this.NOMCOL[2]= Main.idioma.traduce("_LIBRO");
					this.NOMCOL[3]= Main.idioma.traduce("_FECHAINICIO");
					this.NOMCOL[4]= Main.idioma.traduce("_FECHAFIN");
					this.NOMCOL[5]= Main.idioma.traduce("_ELIMINAR");
					return this.NOMCOL;
			}	
	public Prestamo(int idPrestamo, String idSocio, String idLibro, java.sql.Date fechaInicio,java.sql.Date fechaFin){
		this.iniciaNOMCOL();		
		this.idPrestamo = idPrestamo;
		this.idSocio = idSocio;
		this.idLibro = idLibro;
		SimpleDateFormat df= new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date fecha = Socio.calculaFecha(fechaInicio.toString(),"yyyy-MM-dd");
		this.fechaInicio = df.format(fecha);
		if (fechaFin != null){
			fecha = Socio.calculaFecha(fechaFin.toString(), "yyyy-MM-dd");
			this.fechaFin = df.format(fecha);
		}else{
			this.fechaFin = "";
		}
		this.aEliminar = new Boolean(false);
	}
	public int idPrestamo(){
		return this.idPrestamo;
	}
	public String idSocio(){
		return this.idSocio.substring(0,9);
	}
	public String idLibro(){
		return this.idLibro;
	}
	public void idPrestamo(int idPrestamo){
		this.idPrestamo = idPrestamo;
	}
	public void idSocio(String idSocio){
		this.idSocio = idSocio;
	}
	public void idLibro(String idLibro){
		this.idLibro = idLibro;
	}
	public String fechaInicio(){
		return this.fechaInicio;
	}
	public void fechaInicio(String fechaInicio){
		try{
			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			this.fechaInicio = df.format(Socio.calculaFecha(fechaInicio,"dd-MM-yyyy"));
		}catch(Exception err){
			throw new ErrorLibreriaException ("No es fecha"+err);
		}
	}
	public void fechaFin(String fechaFin){
		try{
			SimpleDateFormat df= new SimpleDateFormat("dd-MM-yyyy");
			this.fechaFin = df.format(Socio.calculaFecha(fechaFin,"dd-MM-yyyy"));
		}catch(Exception err){
			throw new ErrorLibreriaException ("No es fecha"+err);
		}
	}	
	public String fechaInicioSQL(){
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date fecha = df.parse(this.fechaInicio, new ParsePosition(0));
		df.setLenient(false);
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
		return df1.format(fecha);
	}
	public String fechaFin(){
		return this.fechaFin;
	}
	public String fechaFinSQL(){
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date fecha = df.parse(this.fechaFin, new ParsePosition(0));
		df.setLenient(false);
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
		if (fecha == null) return "";
		else return df1.format(fecha);
	}
	public int estado(){
		return this.estado;
	}
	public void estado(int estado){
		this.estado = estado;
	}	
	public boolean aEliminar(){
		return this.aEliminar;
	}
	public void aEliminar(boolean b){
		this.aEliminar = new Boolean(b);
	}
	public boolean equals(Object o){
		try{
			Prestamo p = (Prestamo) o;
			return ((p.idLibro() == this.idLibro) && (p.idSocio() == this.idSocio) && (p.fechaInicio() == this.fechaInicio));
		}catch(ClassCastException e){
			throw new ErrorLibreriaException (Main.idioma.traduce("_NOPRESTAMO")+e);
		}
	}
	public int compareTo(Prestamo p){
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date fechaThis = df.parse(this.fechaInicio,new ParsePosition(0));
		java.util.Date fechap = df.parse(p.fechaInicio(),new ParsePosition(0));
		return (fechaThis.compareTo(fechap));
	}
	public String estadoString(int i){
		switch (i){
			case Libro.NADA:
				return "";
			case Libro.ACTUALIZAR:
				return Main.idioma.traduce("_ACTUALIZAR");
			case Libro.BORRAR:
				return Main.idioma.traduce("_BORRAR");
			case Libro.NUEVO:
				return Main.idioma.traduce("_NUEVO");
			default:
				return "";
		}
	}
	public Object getColumn(int column)throws ErrorLibreriaException{
		switch (column){
			case 0:
					return new Integer(this.idPrestamo);
			case 1:
					return this.idSocio;
			case 2:
					return this.idLibro;
			case 3:
					return this.fechaInicio;
			case 4:
					return this.fechaFin;
			case 5:
					return this.aEliminar;
			default:
				throw new ErrorLibreriaException(Main.idioma.traduce("_NOHAYCOLUMNA"));
		}
	}
}