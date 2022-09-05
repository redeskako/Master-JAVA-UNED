/*
 *   CARLOS LUIS SÁNCHEZ BOCANEGRA
                            DNI: 26017022C
             Expediente UNED: 55­04­00458
Domicilio: C/Cura Merino 2 2ºD 29011 Málaga

 */
package libreria.error;

public class ErrorLibreriaException extends RuntimeException {
	public ErrorLibreriaException(){
		super("\tErrorLibreriaException\n");
	}
	public ErrorLibreriaException(String err){
		super("ErrorLibreriaException\n"+err);
	}
}
