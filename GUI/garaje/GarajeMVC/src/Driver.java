
/**
 * Clase Driver
 * Version 2
 * Autores: Jose Requeijo, Jose L�pez
 */

import java.util.Random;

import es.uned.master.java.grajeGUI.capaDatos.ConexionBD;
import es.uned.master.java.grajeGUI.capaGestion.ControladorPlazas;
import es.uned.master.java.grajeGUI.capaGestion.ErrorGaraje;
import es.uned.master.java.grajeGUI.capaGestion.GestionMensajes;
import es.uned.master.java.grajeGUI.capaGestion.GestionFechas;
import es.uned.master.java.grajeGUI.capaVista.GarajeGUI;

public class Driver {

	/**
	 * Clase que genera n�meros aleatorios
	 */
	static int aleatorio(int tope){
		 Random rand = new Random();   	    		 
		 int valor=rand.nextInt((tope - 0) + 1) + 0; 
		 return valor;
	}
	
	public static void main(String[] args) throws InterruptedException {		
		String plantaPaza ="";			
		int plaza=0;
		int planta=0;
		String fechaEntrada="";
		
		// Instanciaci�n de la clase Gestora de Mensajes
		GestionMensajes mensaje = new GestionMensajes();
		// Instanciaci�n de la clase Generadora de Fechas
		GestionFechas fecha= new GestionFechas();
		// Instanciaci�n de la clase CONTROLADOR que gestiona la asignacion de plazas
		ControladorPlazas controlador = new ControladorPlazas();
		// Instanciaci�n del entorno gr�fico
		GarajeGUI frame = new GarajeGUI(controlador.getPlazas(),controlador.getCabecera());
		
		ConexionBD cnx = null;
		String sql = "";

		// Crea la conexión y abre la BD garaje.
		cnx = new ConexionBD();
		cnx.abre("garaje");
		
		try
		{
			// Si la tabla tiene registros.
			sql = "SELECT COUNT(*) FROM registro";
			if (cnx.consulta(sql).getInt(1) != 0)
			{
				// Los borra.
				sql = "DELETE FROM registro;";
				cnx.actualiza(sql);
			}			

			// La barrera es contrloda en el entorno gr�fico mediante el boton CERRAR.
			while (frame.isBarrera()){
				
				//Entrada de coches
				if (ControladorPlazas.plazasOcupadas <=97){
					plantaPaza=controlador.buscarPlaza();
					/**
					 * La planta asignada es un String de la forma:
					   planta,plaza
					   obtenemos el valor de la plaza  y planta 
					   extrayendo los substrings separados por ','y conviertiendolos a Integer
				     */

					planta= Integer.parseInt(plantaPaza.substring(0,plantaPaza.indexOf(",")));
					plaza =Integer.parseInt(plantaPaza.substring((plantaPaza.indexOf(",") + 1), plantaPaza.length()));				 
					//mostramos la plaza ocupada en el JTable
					frame.asignarPlaza("X",planta, plaza);
	                
					// ----------------------------------------------------------------------------------------	
				    // Introduce el registro en la BD.
				    sql = "INSERT INTO registro (planta, plaza, fecha_entrada) " +
				    		"VALUES (" + planta + ", " + plaza + ", '" + fecha.getFechaHora() + "');";
				    cnx.actualiza(sql);				
					// ----------------------------------------------------------------------------------------				
	
				    //Construimos el Ticket de entrada y lo mostramos en el TextArea
					mensaje.setMsg(planta, plaza,"ENTRADA",fecha.getFechaHora(),fecha.getFechaHora());
					frame.display(mensaje.getMsg());						
					
				}else {
					/**
					 * Parking lleno construimos el mensaje de completo
					 * y lo mostramos en el TextArea				
					 */
					mensaje.setMsg("lleno");
					frame.display(mensaje.getMsg());				
				}
				
				/**
				 * Salida de coches
				 * liberamos plazas aleatoriamente
				 * interesa que entren m�s coches de los que salen para mostrar el cartel lleno
				 * Por ello generamos plazas que no existen 
				 * y se liberan las que existen y est�n ocupadas
				 */
				planta=aleatorio(9);
				plaza= aleatorio(18);
				if ((planta < 7) && (plaza <14)){
					plantaPaza=controlador.liberarPlaza(planta,plaza);
					if (!plantaPaza.equals("NULL")){
						planta= Integer.parseInt(plantaPaza.substring(0,plantaPaza.indexOf(",")));
						plaza =Integer.parseInt(plantaPaza.substring((plantaPaza.indexOf(",") + 1), plantaPaza.length()));
						//mostramos la plaza libre en el JTable
						frame.asignarPlaza("",planta, plaza);
						 
						// ----------------------------------------------------------------------------------------
						// Obtenemos la fecha/hora de entrada.
					    sql = "SELECT fecha_entrada FROM registro WHERE planta = " + planta + " AND plaza = " + plaza + " AND fecha_salida IS NULL;"; 
					    fechaEntrada = cnx.consulta(sql).getString(1);
						
						// Actualiza el registro.
					    sql = "UPDATE registro SET fecha_salida = '" + fecha.getFechaHora()+ "' WHERE planta = " + planta + " AND plaza = " + plaza + " AND fecha_salida IS NULL;"; 
					    cnx.actualiza(sql);
					    // ----------------------------------------------------------------------------------------
					    
					    //Construimos el Ticket de salida y lo mostramos en el TextArea					    
						mensaje.setMsg(planta, plaza,"SALIDA",fechaEntrada,fecha.getFechaHora());						
						frame.display(mensaje.getMsg());
					}	
				}			
				Thread.sleep(2000);
			}
			
			
		// ----------------------------------------------------------------------------------------
		}
		
		// Error con la BD.
		catch (Exception e)
		{
			throw new ErrorGaraje("Error trabajando con la BD.");
		}
		
		// Cierra la BD.
		finally
		{
			cnx.cierra();
		}
		// ----------------------------------------------------------------------------------------
		
	}
}