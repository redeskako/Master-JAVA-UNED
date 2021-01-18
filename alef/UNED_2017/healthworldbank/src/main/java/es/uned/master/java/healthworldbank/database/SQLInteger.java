package es.uned.master.java.healthworldbank.database;
import java.lang.annotation.*;

/**
 * Anotaciones para definición de campos Integer
 * 
 * @author grupo alef (Juan Sánchez)
 * @date 5/4/2012
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SQLInteger {
	String name() default "";
	Constraints constraints() default @Constraints;
}
