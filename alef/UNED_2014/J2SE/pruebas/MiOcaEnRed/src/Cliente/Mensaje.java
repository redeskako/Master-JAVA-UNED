package Cliente;
import java.io.*;
public class Mensaje implements Serializable {

	static final int TIRADA = 0, CONEXION = 1; 
    private int type;
    private String mensaje;
    
    Mensaje(int type, String mensaje) {
        this.type = type;
        this.mensaje = mensaje;
    }
	     
    // getters
    int getType() {
        return type;
    }
    
    String getMensaje() {
    	return mensaje;
    }
	
	
}
