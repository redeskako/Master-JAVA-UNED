package es.uned2013.parchis.server.databaseDriver;

import org.junit.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseTest {

	/**
	 * metodo para probar la creacion y funcionamiento de la base de datos, no se utilizará posteriormente en la partida
	 */
	public static void main(String[] args) {
		insertaDatos insertar = new insertaDatos();
		String numPartida;
		
		String valorDado = String.valueOf(Math.floor(Math.random()*4+1)) ;
		
		try {
			insertar.creaTablas();
			numPartida = insertar.insertaPartidaNueva(4);
			for (int i=1; i<100 ; i++){
				insertar.insertaTirada(numPartida, String.valueOf(Math.floor(Math.random()*4+1)), String.valueOf(i) , String.valueOf(Math.floor(Math.random()*16+1)), String.valueOf(Math.floor(Math.random()*68+1)), String.valueOf(Math.floor(Math.random()*68+1)), String.valueOf(Math.floor(Math.random()*6+1)));
			}
			
			for (int i=1; i<100 ; i++){
				insertar.insertaFichaComida(numPartida,String.valueOf(Math.floor(Math.random()*4+1)), String.valueOf(i) , String.valueOf(Math.floor(Math.random()*16+1)));
			}
			insertar.finalizaPartida(numPartida);
			System.out.println("finaliza correctamente");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
