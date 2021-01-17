package org.aprende.java.bbdd;
import java.util.*;
import java.io.Serializable;
import java.text.*;

public class Disconformidad implements Comparable, Serializable{

	private int numero ;
	private Date fecha;
	private String motivo;
	private String docs;
	private int servicio;
	private int usuario;
	private boolean devolucion;
	private String comentario;

	//constructores
	public Disconformidad(){
		this.numero=0;
		this.fecha=null;
		this.docs=null;
		this.servicio=0;
		this.usuario=0;
		this.devolucion=false;
		this.motivo=null;
		this.comentario=null;
	}

	public Disconformidad(int numero, int servicio, int usuario, boolean confirmacion){
		this.numero=numero;
		this.fecha= new Date();
		this.docs="";
		this.servicio=servicio;
		this.usuario=usuario;
		this.devolucion=false;
		this.motivo="";
		this.comentario="";
	}
	public Disconformidad(int numero, Date fecha, String doc, int servicio, int usuario, boolean devolucion,String motivo,String comentario){
		this.numero=numero;
		this.fecha=fecha;
		this.docs=doc;
		this.servicio=servicio;
		this.usuario=usuario;
		this.devolucion=devolucion;
		this.motivo=motivo;
		this.comentario=comentario;
	}

	//get y set de las variables de clase
	public boolean devolucion() {
		return devolucion;
	}
	public void devolucion(boolean confirmacion) {
		this.devolucion = confirmacion;
	}
	public String docs() {
		return docs;
	}
	public void docs(String doc) {
		this.docs = doc;
	}
	public Date fecha() {
		return fecha;
	}
	public void fecha(Date fecha) {
		this.fecha = fecha;
	}
	public int numero() {
		return numero;
	}
	public void numero(int numero) {
		this.numero = numero;
	}
	public int servicio() {
		return servicio;
	}
	public void servicio(int servicio) {
		this.servicio = servicio;
	}
	public int usuario() {
		return usuario;
	}
	public void usuario(int usuario) {
		this.usuario = usuario;
	}
	public String motivo() {
		return motivo;
	}
	public void motivo(String motivo) {
		this.motivo = motivo;
	}
	public String comentario() {
		return comentario;
	}
	public void comentario(String coment) {
		this.comentario = coment;
	}

	//metodos
	public String toString(){
		return " (Numero: " + this.numero + " - Fecha: " + this.fecha + " - Documentos afectados: " + this.docs + " - Servicio: " + this.servicio + " - usuario: " + this.usuario + " - Devolucion: " + this.devolucion + " - Motivo: " + this.motivo + " - Comentario: " + this.comentario +")";
	}
	public int compareTo(Object o){
		Disconformidad d= (Disconformidad) o;
		return this.fecha().compareTo(d.fecha()); //Ordenar por fecha
	}
	public boolean equals(Object o){
		Disconformidad d= (Disconformidad) o;
		//Dos disconformidades son iguales si son iguales todos sus propiedades, excepto el numero por ser clave autonumerica
		//no comparamos el atributo numero porque al ser autonumerico no pueden haber dos disconformidades con el mismo numero
		return ( (this.fecha().equals(d.fecha()) && (this.docs().equals(d.docs())) && (this.servicio()== d.servicio()) && (this.usuario()==d.usuario())));
	}



	//Metodo que devuelve una lista de disconformidades
	//Si nos llegan errores de los metodos abrirConexion y cerrarConexion se lanzan a la clase DisconformidadException
	public ArrayList<Disconformidad> listadoDisconformidades(int gestion,int idusu) throws DisconformidadException{
		ArrayList<Disconformidad> miLista;
		String sql="";

		if (gestion==1){ //administrador
			sql="Select * from Disconformidad";
		}else{
			sql="Select * from Disconformidad where idUsuario=" + idusu ;
		}

		BBDD miBd = new BBDD();
		miBd.abrirConexion(); //No hacemos tratamiento de errores porque ya se ha hecho en el m�todo abrirConexi�n.
		miLista=miBd.listadoDisconformidades(sql);
		miBd.cerrarConexion();//No hacemos tratamiento de errores porque ya se ha hecho en el m�todo abrirConexi�n.
		return miLista;
	}

	public ArrayList<Disconformidad> listadoDisconformidadesPorPaginas(int gestion,int idusu, int pag, int intervalo, String orden) throws DisconformidadException{
		ArrayList<Disconformidad> miLista;
		String sql="";
		int calpag=intervalo*(pag-1);
		sql = " ORDER BY " + orden +  " LIMIT " + String.valueOf(calpag) + "," + String.valueOf(intervalo);

		if (gestion==1){ //administrador
			sql="Select * from Disconformidad" + sql;
		}else{
			sql="Select * from Disconformidad where idUsuario=" + idusu + sql ;
		}

		BBDD miBd = new BBDD();
		miBd.abrirConexion(); //No hacemos tratamiento de errores porque ya se ha hecho en el m�todo abrirConexi�n.
		miLista=miBd.listadoDisconformidades(sql);
		miBd.cerrarConexion();//No hacemos tratamiento de errores porque ya se ha hecho en el m�todo abrirConexi�n.
		return miLista;
	}

	public int numeroRegistros() throws DisconformidadException{
		int cont;
		String sql="select count(*) from Disconformidad";

		BBDD miBd = new BBDD();
		miBd.abrirConexion(); //No hacemos tratamiento de errores porque ya se ha hecho en el m�todo abrirConexi�n.
		cont=miBd.numeroRegistros(sql);
		miBd.cerrarConexion();//No hacemos tratamiento de errores porque ya se ha hecho en el m�todo abrirConexi�n.
		return cont;
	}

	public Disconformidad getDisconformidad(int numero) throws DisconformidadException{
		Disconformidad  miDisconf=null;
		String sql="";

		sql="Select * from Disconformidad where numero=" + numero ;

		BBDD miBd = new BBDD();
		miBd.abrirConexion(); //No hacemos tratamiento de errores porque ya se ha hecho en el m�todo abrirConexi�n.
		miDisconf=miBd.getDisconformidad(sql);
		miBd.cerrarConexion();//No hacemos tratamiento de errores porque ya se ha hecho en el m�todo abrirConexi�n.
		return miDisconf;
	}

	public boolean insertDisconformidad(Disconformidad d) throws DisconformidadException{
		boolean valido=false;
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		BBDD miBd = new BBDD();
		miBd.abrirConexion(); //No hacemos tratamiento de errores porque ya se ha hecho en el m�todo abrirConexi�n.
		//miBd.ejecutarDisconformidad("insert into Disconformidad(fecha, docs, servicio,idusuario,devolucion,motivo,comentario) " + "values('2007-04-12','"   + d.docs() + "'," + d.servicio() + "," +d.usuario() + "," + d.devolucion() + ",'" +  d.motivo() + "','" +d.comentario() +"')");
		miBd.ejecutarDisconformidad("insert into Disconformidad(fecha, docs, servicio,idusuario,devolucion,motivo,comentario) " + "values('" + sdf.format(d.fecha())+ "','"   + d.docs() + "'," + d.servicio() + "," +d.usuario() + "," + d.devolucion() + ",'" +  d.motivo() + "','" +d.comentario() +"')");
		miBd.cerrarConexion();//No hacemos tratamiento de errores porque ya se ha hecho en el m�todo abrirConexi�n.
		valido=true;
		return valido;
	}

	public boolean updateDisconformidad(Disconformidad d) throws DisconformidadException{
		boolean valido=false;
		//SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		BBDD miBd = new BBDD();
		miBd.abrirConexion(); //No hacemos tratamiento de errores porque ya se ha hecho en el m�todo abrirConexi�n.
		String cadena="update Disconformidad set docs='"   + d.docs() +
		"', servicio=" + d.servicio() +
		",idusuario=" + d.usuario() +
		",devolucion=" + d.devolucion() +
		",motivo='" + d.motivo() +
		"', comentario='" + d.comentario() + "'" +
		" where numero= " + d.numero();
		
	/*	String cadena="update Disconformidad set fecha='" + sdf.format(d.fecha()) +
		"',docs='"   + d.docs() +
		"', servicio=" + d.servicio() +
		",idusuario=" + d.usuario() +
		",devolucion=" + d.devolucion() +
		",motivo='" + d.motivo() +
		"', comentario='" + d.comentario() + "'" +
		" where numero= " + d.numero(); */

		/* miBd.ejecutarDisconformidad("update Disconformidad set docs='"   + d.docs() +
															"', servicio=" + d.servicio() +
															",idusuario=" + d.usuario() +
															",devolucion=" + d.devolucion() +
															",motivo='" + d.motivo() +
															"', comentario='" + d.comentario() + "'" +
															" where numero= " + d.numero() );
		*/
		miBd.ejecutarDisconformidad(cadena);

		miBd.cerrarConexion();//No hacemos tratamiento de errores porque ya se ha hecho en el m�todo abrirConexi�n.
		valido=true;
		return valido;
	}

	public boolean deleteDisconformidad(Disconformidad d) throws DisconformidadException{
		boolean valido=false;
		BBDD miBd = new BBDD();
		miBd.abrirConexion(); //No hacemos tratamiento de errores porque ya se ha hecho en el m�todo abrirConexi�n.
		miBd.ejecutarDisconformidad("delete from Disconformidad where numero = " + d.numero());
		miBd.cerrarConexion();//No hacemos tratamiento de errores porque ya se ha hecho en el m�todo abrirConexi�n.
		valido=true;
		return valido;
	}
}
