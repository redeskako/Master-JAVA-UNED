package es.uned.master.java.healthworldbank.database;
import java.lang.annotation.*;

/**
 * Da instrucciones al procesador de anotaciones para la creación de una tabla 
 * 
 * @author grupo alef (Juan Sánchez)
 * @date 5/4/2012
 *
 */

@Target(ElementType.TYPE)						// solamente se aplica a clases
@Retention(RetentionPolicy.RUNTIME)				// retener hasta tiempo de ejecución
public @interface DBTable {
	public String name() default "";			// nombre de la tabla
	public String[] primaryKey() default {};	// clave primaria
}
