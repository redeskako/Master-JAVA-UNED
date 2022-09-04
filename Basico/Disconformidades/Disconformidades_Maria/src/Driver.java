import org.aprende.java.*;
import org.aprende.java.bbdd.*;
import org.aprende.java.controlador.*;
public class Driver {

	
	public static void main(String[] args) {
		Controlador micontrol= null;
		try{
			micontrol=new Controlador();

		}catch(DisconformidadException e){
			System.out.println(e.getMessage());
		}
	}

}
