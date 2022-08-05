package es.uned.master.java.kwic.red.servidor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Clase que representa las estadisticas entre cliente y servidor
 * Al ser un Bean,dispone de getter y setter para sus atributos
 */
public class Estadistica  implements Serializable {
	private static final long serialVersionUID = 8661825401053802031L;
	protected Collection<DatosConexion> datosConexiones;
    protected int conexionesAtendidas=0;


	//Constructor
	public Estadistica(){
		 datosConexiones=new ArrayList<DatosConexion>();
	}
	
	/* Funcion getConexionesAtendidas: Devuelve el numero de conexiones atendidas
	 * 
	 * @return conexionesAtendidas  
	 */
	public int getConexionesAtendidas() {
		return conexionesAtendidas;
	}

	/* Funcion getDatosConexiones: Devuelve una coleccion con los datos de cliente (fecha incio de conexion,
	 * fecha fin de conexion, ip, puerto)
	 * 
	 * @return datosConexiones  
	 */
	public Collection<DatosConexion> getDatosConexiones() {
		return datosConexiones;
	}

	@Override
	public String toString() {
		//cadena cuyo tamaño puede variar
		StringBuffer sb=new StringBuffer();
		sb.append("\n****  KWICNET Estadisticas  ****\n");
		sb.append("Conexiones Atendidas :"+conexionesAtendidas+"\n");
		sb.append("IP:Puerto\n");
		//Iterator para recorrer colecciones
		Iterator<DatosConexion> itera=datosConexiones.iterator();
		while(itera.hasNext()){
			DatosConexion dc=itera.next();
			sb.append(dc.dirIp+":"+dc.puerto+"\n");
		}
		sb.append("Tiempo medio de conexion: "+getTiempoMedioConexion()+"\n");
		sb.append("Tiempo total de conexion: "+getTiempoTotalConexion()+"\n");
		return sb.toString();
	}

	/**
	 * Funcion conexionesAtendidas: Devuelve el tiempo medio de conexion que tiene el servidor
	 * @return tiempoMedioConexion
	 */
	public float getTiempoMedioConexion() {
		if(getTiempoTotalConexion()!=0 && conexionesAtendidas!=0){
			return getTiempoTotalConexion()/conexionesAtendidas;
		}else
			return 0; 
	}

	/**
	 * Funcion getTiempoTotalConexion: Devuelve el tiempo total de conexion que tiene el servidor
	 * @return sumaTotal
	 */
	public long getTiempoTotalConexion(){
		Iterator<DatosConexion>it=this.getDatosConexiones().iterator();
		long sumaTotal=0;
		while(it.hasNext()){
			DatosConexion dc=it.next();
			sumaTotal=sumaTotal+(dc.getFecha_Fin_Conexion().getTime()-dc.getFecha_Inicio_Conexion().getTime());
		}
		return sumaTotal;
	}

}
