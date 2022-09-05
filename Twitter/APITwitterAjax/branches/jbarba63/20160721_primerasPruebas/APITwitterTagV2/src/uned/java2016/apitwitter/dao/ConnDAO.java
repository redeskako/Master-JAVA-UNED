package uned.java2016.apitwitter.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public interface ConnDAO {
	
	public boolean abrirConexion();

	public boolean estaConectado();
	
	public void cerrarConexion();
	
}
