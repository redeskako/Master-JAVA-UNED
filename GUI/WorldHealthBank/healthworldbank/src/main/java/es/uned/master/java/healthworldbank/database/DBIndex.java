package es.uned.master.java.healthworldbank.database;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Da instrucciones al procesador de anotaciones para crear un �ndice en una tabla
 * 
 * @author grupo alef (Juan S�nchez)
 *
 */
@Target(ElementType.TYPE)						// solamente se aplica a clases
@Retention(RetentionPolicy.RUNTIME)				// retener hasta tiempo de ejecuci�n
public @interface DBIndex {
	public String name() default "";			// nombre del �ndice
	public String[] index() default {};			// campos que configuran el �ndice
}
