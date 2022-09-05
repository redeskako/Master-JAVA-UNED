package es.uned.master.java.healthworldbank.database;
import java.lang.annotation.*;

/**
 * Da instrucciones al procesador de anotaciones para la creaci�n de una tabla 
 * 
 * @author grupo alef (Juan S�nchez)
 * @date 5/4/2012
 *
 */

@Target(ElementType.TYPE)						// solamente se aplica a clases
@Retention(RetentionPolicy.RUNTIME)				// retener hasta tiempo de ejecuci�n
public @interface DBTable {
	public String name() default "";			// nombre de la tabla
	public String[] primaryKey() default {};	// clave primaria
}
