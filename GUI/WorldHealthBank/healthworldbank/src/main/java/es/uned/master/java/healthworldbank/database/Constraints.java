package es.uned.master.java.healthworldbank.database;
import java.lang.annotation.*;

/**
 * Anotaciones para restricciones de campos
 * 
 * @author grupo alef (Juan S�nchez)
 * @date 5/4/2012
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Constraints {
	boolean primaryKey() default false;			// clave primaria
	boolean allowNull() default true;			// admite nulos o no
	boolean unique() default false;				// clave �nica
	boolean autoIncrement() default false;		// auto incremento
}
