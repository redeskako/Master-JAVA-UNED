
import java.sql.ResultSet;
import java.util.Random;
import es.uned.master.java.grajeGUI.capaDatos.ConexionBD;
import es.uned.master.java.grajeGUI.capaGestion.GestionMensajes;
import es.uned.master.java.grajeGUI.capaGestion.GestionFechas;
import es.uned.master.java.grajeGUI.capaVista.GarajeGUI;

public class Driver {

	/**
	 * @param args
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
		GestionMensajes mensaje = new GestionMensajes();
		GestionFechas fecha= new GestionFechas();
		GarajeGUI frame = new GarajeGUI();
		
		// ----------------------------------------------------------------------------------------
		ConexionBD cnx = null;
		ResultSet rs = null;
		String sql = "";
		Registro registro = null;

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
		// ----------------------------------------------------------------------------------------

		
			while (frame.isBarrera()){
				//Entrada de coches
				if (GarajeGUI.plazasOcupadas <=97){
					plantaPaza=frame.buscarPlaza();
					// La planta asignada es un String de la forma:
					//planta,plaza
					//obtenemos el valor de la plaza  y planta 
					//extrayendo los substrings y conviertiendolos a Integer
					planta= Integer.parseInt(plantaPaza.substring(0,plantaPaza.indexOf(",")));
					plaza =Integer.parseInt(plantaPaza.substring((plantaPaza.indexOf(",") + 1), plantaPaza.length()));				 
					
	
					
					// ----------------------------------------------------------------------------------------	
				    // Introduce el registro en la BD.
				    sql = "INSERT INTO registro (planta, plaza, fecha_entrada) " +
				    		"VALUES (" + planta + ", " + plaza + ", '" + fecha.getFechaHora() + "');";
				    cnx.actualiza(sql);				
					// ----------------------------------------------------------------------------------------				
	
				    
					mensaje.setMsg(planta, plaza,"ENTRADA",fecha.getFechaHora(),fecha.getFechaHora());
					frame.display(mensaje.getMsg());						
					
				}else {
					mensaje.setMsg("lleno");
					frame.display(mensaje.getMsg());				
				}
				
				//Salida de coches
				//liberamos plazas aleatoriamente
				//interesa que entren m�s coches de los que salen para mostrar el cartel lleno
				//Por ello generamos plazas que no existen y se liberan las que existen y est�n ocupadas
				planta=aleatorio(9);
				plaza= aleatorio(18);
				if ((planta < 7) && (plaza <14)){
					plantaPaza=frame.liberarPlaza(planta,plaza);
					if (!plantaPaza.equals("NULL")){
						planta= Integer.parseInt(plantaPaza.substring(0,plantaPaza.indexOf(",")));
						plaza =Integer.parseInt(plantaPaza.substring((plantaPaza.indexOf(",") + 1), plantaPaza.length()));

						
						// ----------------------------------------------------------------------------------------
						// Actualiza el registro.
					    sql = "UPDATE registro SET fecha_salida = '" + fecha.getFechaHora()+ "' WHERE planta = " + planta + " AND plaza = " + plaza + " AND fecha_salida IS NULL;"; 
					    cnx.actualiza(sql);
					    // ----------------------------------------------------------------------------------------
					    
					    
						mensaje.setMsg(planta, plaza,"SALIDA",fecha.getFechaHora(),fecha.getFechaHora());
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
		// ----------------------------------------------------------------------------------------
		
		
	}
}