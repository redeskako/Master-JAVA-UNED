package es.uned.master.java.kwic.red.cliente;

import java.io.Serializable;
import java.util.Date;

public class DatosServidor implements Serializable{
    private String IP;
    private int PUERTO;
    private Date INICIO_CONEXION;
    private Date FIN_CONEXION;
    private int intentos;
    private int realizadas;

    DatosServidor(String IP, int PUERTO, Date INICIO_CONEXION, Date FIN_CONEXION, int intentos,int realizadas){
        this.IP=IP;
        this.PUERTO=PUERTO;
        this.INICIO_CONEXION=INICIO_CONEXION;
        this.FIN_CONEXION=FIN_CONEXION;
        this.intentos=intentos;
        this.realizadas=realizadas;
    }
@Override
public String toString(){
	return ("IP: "+IP+" PUERTO: "+PUERTO+" INICIO SESION: "+INICIO_CONEXION.toString()+" FIN SESION: "+FIN_CONEXION);
}

} 
