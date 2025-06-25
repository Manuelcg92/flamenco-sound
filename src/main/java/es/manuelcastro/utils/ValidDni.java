package es.manuelcastro.utils;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Anotacion de validacion personalizada para el DNI espanol. Asegura que un
 * campo String que representa un DNI espanol tenga el formato valido y que la
 * letra coincide con el algoritmo.
 *
 * @author Manuel Castro
 * @version 1.0
 * @since 2025-06-09
 */

@Documented
@Constraint(validatedBy = DniValidator.class)
@Retention(RUNTIME)
@Target(FIELD)
public @interface ValidDni {

	/*
	 * Mensaje de error por defecto proporcionado por el framework si no salta
	 * ninguno de los mensajes personalizados ni las otras anotaciones
	 */
	String message() default "";

	// Grupos de validacion
	Class<?>[] groups() default {};

	// Carga util de metadatos
	Class<? extends Payload>[] payload() default {};

}
