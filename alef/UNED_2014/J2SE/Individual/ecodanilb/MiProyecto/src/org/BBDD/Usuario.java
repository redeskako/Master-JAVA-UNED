package org.BBDD;

//CLASE USUARIO
//Define cada usuario de la base de datos


import java.util.*;
import java.sql.*;

public class Usuario{


	private String nombre;
	private String clave;
	private Boolean  tipo;


	//constructor
	public Usuario(String nombre, String clave){
		this.nombre = nombre;
		this.clave = clave;
	}
	
	public Usuario(String nombre, String clave, Boolean tipo){
		this.nombre = nombre;
		this.clave = clave;
		this.tipo = tipo;
	}



	public String clave(){
		return clave;
	}

	public void clave(String clave){
		this.clave = clave;
	}


	public String nombre(){
		return this.nombre;
	}

	public void nombre(String usu){
		this.nombre = usu;
	}


	public boolean  validarUsuario(BBDD bd){
		
		// Ver si dejamos el parametro bd o lo quitamos....
		
		
		String sqlsts;
		ResultSet rs = null;
        Boolean conectado;
        Boolean usuarioExiste;
		sqlsts = "Select * from usuarios where nombre='" + this.nombre +"' and clave='" + this.clave + "'";
		bd.abrirConexion(); 
		bd.getUsuario(sqlsts);
		if(bd.getUsuario(sqlsts)==null){ 
			 usuarioExiste = false;
		} else{
			usuarioExiste = true;
		}
	  return usuarioExiste;
	}
}// fin usuario