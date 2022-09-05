package es.uned.master.java.healthworldbank.database;
import java.lang.annotation.*;

/**
 * Anotaciones para definici�n de campos Float
 * 
 * @author grupo alef (Juan S�nchez)
 * @date 5/4/2012
 *
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SQLDouble {
	String name() default "";
	Constraints constraints() default @Constraints;
}
