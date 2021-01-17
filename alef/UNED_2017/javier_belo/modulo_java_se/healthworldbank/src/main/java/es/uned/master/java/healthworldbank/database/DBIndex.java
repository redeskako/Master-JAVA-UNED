package es.uned.master.java.healthworldbank.database;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Da instrucciones al procesador de anotaciones para crear un índice en una tabla
 * 
 * @author grupo alef (Juan Sánchez)
 *
 */
@Target(ElementType.TYPE)						// solamente se aplica a clases
@Retention(RetentionPolicy.RUNTIME)				// retener hasta tiempo de ejecución
public @interface DBIndex {
	public String name() default "";			// nombre del índice
	public String[] index() default {};			// campos que configuran el índice
}
