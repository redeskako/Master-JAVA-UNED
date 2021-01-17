//Probando errores:
import pruebas.*;

public class Driver {
	public static void main(String[] args){
	
	Prueba p1;
	Prueba p2;
	
	try{
		p1 = new Prueba('o', 2);
		p2 = new Prueba('z', 8);
		
		System.out.printf("Tamaño de p1: %d\nColor de p1: %c\n\n", p1.getTalla(), p1.getColor());
		System.out.printf("Tamaño de p2: %d\n\n", p2.getTalla());	
	} catch (ClaseError err){
		System.out.println(err.getMessage());
		System.out.println(err);
		System.out.println("Le cuento el error que se ha producido al usuario\n");
		
		System.out.println(err.getCause());
		System.out.println(err.getCause().getMessage());
	}// fin catch
		
		
			
	
	}// fin método main
}// fin clase Driver
