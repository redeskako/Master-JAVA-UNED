package org.BBDD;

//CLASE USUARIO
//Define cada usuario de la base de datos


import java.util.*;
import java.sql.*;

public class Usuario{


	private String usuario;
	private String clave;



	//constructor
	public Usuario(String nombre, String clave){
		this.usuario = nombre;
		this.clave = clave;
	}


	public String clave(){
		return clave;
	}

	public void clave(String clave){
		this.clave = clave;
	}


	public String nombre(){
		return this.usuario;
	}

	public void nombre(String usuario){
		this.usuario = usuario;
	}

	
	//VERIFICAMOS USUARIO MANDANDO SETENCIA SQL A METODO GETUSUARIO

	public Usuario  validarUsuario(){
		Usuario  usuario ;
		BBDD bd = new BBDD();
		String sql;
	    bd.abrirConexion();
		sql = "Select * from usuarios where NombreUsuario='" + this.usuario +"' and Contrasena='" + this.clave + "'";
	 	usuario=bd.getUsuario(sql); 
	 	bd.cerrarConexion();
		 return usuario;
	}
	
}// fin usuario