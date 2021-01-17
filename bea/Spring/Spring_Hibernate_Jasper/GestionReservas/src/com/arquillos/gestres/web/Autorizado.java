package com.arquillos.gestres.web;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;

@Retention( RetentionPolicy.RUNTIME )
@Target( {ElementType.TYPE} )
@Documented
@Inherited
public @interface Autorizado {
}