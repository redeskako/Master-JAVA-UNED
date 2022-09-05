/*
 *   CARLOS
 */
import libreria.*;
import java.util.*;

public class Driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*CLibros libreria= new CLibros();
		libreria.conectaSQL("SELECT * FROM Libros");
		System.out.println(libreria.toString());
		*/
		Main prestamos;
		if (args.length>1){
			//Introduc el parámetro por la entrada
			// es español en inglés
			prestamos = Main.prestamos(args[0]);
		}else{
			// Por defecto español
			prestamos = Main.prestamos("es");
		}
		prestamos.setVisible(true);
	}
}