package Servidor;
public class DriverServidor {	

	// Habría que desarrollar este método para que desde servidor te pase un String sobre conexiones: 
	// si se han conectado bien o mal, si hay errores... y tú los muestres en la ventana que crees.
	/**
	 * Mostrar un mensaje en JTextArea si se han conectado bien o mal, si hay errores
	 * 
	 * @param s Mensaje para mostrar
	 */
	public void infoConexiones(String s){		
		info.append(s);
		info.setCaretPosition(info.getText().length() - 1);
	}
	
	
	
}
