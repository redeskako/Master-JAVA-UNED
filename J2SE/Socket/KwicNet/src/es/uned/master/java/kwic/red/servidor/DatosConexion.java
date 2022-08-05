package es.uned.master.java.kwic.red.servidor;


	import java.io.Serializable;
	import java.util.Date;

	/**
	 * Clase que representa los datos relevantes de una conexion entre cliente y servidor
	 * Al ser un Bean,dispone de getter y setter para sus atributos	 
	 */
	public class DatosConexion implements Serializable{
		//datos que se utilizaran para mostrar en la pestana de las estadisticas
		protected Date fecha_Inicio_Conexion;
		protected Date fecha_Fin_Conexion;
		protected String dirIp;
		protected int puerto;	    


		//Constructor
		public DatosConexion(){}

		/* Funcion getFecha_Inicio_Conexion: Devuelve la fecha de inicio de conexion del cliente
		 * 
		 * @return fecha_Inicio_Conexion: fecha  
		 */
		public Date getFecha_Inicio_Conexion() {
			return fecha_Inicio_Conexion;
		}
		/* Funcion setFecha_Inicio_Conexion: Asignamos el valor de la fecha de inicio de conexion
		 * 
		 */
		public void setFecha_Inicio_Conexion(Date fecha_Inicio_Conexion) {
			this.fecha_Inicio_Conexion = fecha_Inicio_Conexion;
		}
		/* Funcion getFecha_Fin_Conexion: Devuelve la fecha de fin de conexion del cliente
		 * 
		 * @return fecha_Fin_Conexion: fecha  
		 */
		public Date getFecha_Fin_Conexion() {
			return fecha_Fin_Conexion;
		}
		/* Funcion setFecha_Fin_Conexion: Asignamos el valor de la fecha de fin de conexion
		 * 
		 */
		public void setFecha_Fin_Conexion(Date fecha_Fin_Conexion) {
			this.fecha_Fin_Conexion = fecha_Fin_Conexion;
		}
		/* Funcion getDirIp: Devuelve la direccion IP del cliente
		 * 
		 * @return dipIp: direccion IP  
		 */
		public String getDirIp() {
			return dirIp;
		}
		/* Funcion setDirIp: Asignamos el valor de la direccion IP
		 * 
		 */
		public void setDirIp(String dirIp) {
			this.dirIp = dirIp;
		}
		/* Funcion getPuerto: Devuelve el número de puerto que recibe los datos del servidor.
		 * 
		 * @return puerto: puerto de conexion  
		 */
		public int getPuerto() {
			return puerto;
		}
		/* Funcion setPuerto: Asignamos el valor del puerto
		 * 
		 */
		public void setPuerto(int puerto) {
			this.puerto = puerto;
		}
		
}
