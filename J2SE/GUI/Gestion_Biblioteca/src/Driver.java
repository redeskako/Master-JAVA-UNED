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
			//Introduc el par�metro por la entrada
			// es espa�ol en ingl�s
			prestamos = Main.prestamos(args[0]);
		}else{
			// Por defecto espa�ol
			prestamos = Main.prestamos("es");
		}
		prestamos.setVisible(true);
	}
}