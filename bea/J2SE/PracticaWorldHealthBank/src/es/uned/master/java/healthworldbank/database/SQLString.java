package es.uned.master.java.healthworldbank.database;
import java.lang.annotation.*;

/**
 * Anotaciones para definición de campos String
 * 
 * @author grupo alef (Juan Sánchez)
 * @date 5/4/2012
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SQLString {
	int value() default 0;	// longitud del campo, aunque en SQLite no es necesaria
	String name() default "";
	Constraints constraints() default @Constraints;
}
