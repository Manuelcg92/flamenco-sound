package es.manuelcastro.utils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Validador personalizado para el formato y la letra del DNI espanol.
 *
 * Este validador trabaja en conjunto con {@code @NotBlank} y {@code @Pattern}
 * en la entidad para gestionar mensajes de error especificos para cada tipo de
 * inconsistencia.
 *
 * @author Manuel Castro
 * @version 1.0
 * @since 2025-06-09
 */

public class DniValidator implements ConstraintValidator<ValidDni, String> {

	// Instancia del logger para esta clase
	private static final Logger log = LoggerFactory.getLogger(DniValidator.class);

	// Tabla de letras para el calculo del DNI
	private static final String LETRAS_DNI = Constantes.DNI_LETRAS;

	// Patron de expresion para validar que el DNI introducido tiene el formato
	// esperado
	private static final Pattern DNI_FORMAT_PATTERN = Pattern.compile(Constantes.REGEXP_DNI);

	/**
	 * Contructor vacio.
	 */
	@Override
	public void initialize(ValidDni constraintAnnotation) {

	}

	/**
	 * Valida si un DNI tiene un formato y letra correctos segun el algoritmo
	 * oficial.
	 * 
	 * @param dniField - DNI ingresado que se desea validar
	 * @param context  - Contexto de validación proporcionado por Bean Validation
	 * @return - {@code true} si el DNI es valido o se delega la validacion,
	 *         {@code false} si no es valido
	 */
	@Override
	public boolean isValid(String dniField, ConstraintValidatorContext context) {

		// Comprobamos que el DNI no este vacio ni sea distinta a una longitud de 9 (el
		// DNI son 8 numeros y 1 letra)
		// Devolvemos true para que se encarguen @NotBlank o @Pattern de mostrar el
		// mensaje, no ambos
		if (dniField == null || dniField.trim().length() != 9) {

			return true;
		}

		String dni = dniField.trim().toUpperCase();

		// Comprobamos que el formato de DNI sea el esperado, sino devuelve true y la
		// anotacion @Pattern mostrara el mensaje de error
		if (!DNI_FORMAT_PATTERN.matcher(dni).matches()) {

			return true;
		}

		// Separamos en dos atributos los numeros y la letra.
		String numeroStr = dni.substring(0, 8);

		char letraDni = dni.charAt(8);

		// Aqui validamos que el numero sea realmente un numero para evitar
		// inconsistencias
		int numero;

		try {

			numero = Integer.parseInt(numeroStr);

		} catch (NumberFormatException e) {

			/*
			 * Mensaje de log de advertencia por si llega a este punto, que no deberia ya
			 * que @Pattern deberia encargarse antes
			 */
			log.warn("La parte numérica del DNI no es un entero válido. DNI inválido. Excepción: {}", e.getMessage());

			return false;
		}

		// Aplicamos el algoritmo para saber si el resto de la division entre los
		// numeros por 23 coincide con alguna letra en la tabla
		int resto = numero % 23;

		char letraCalculada = LETRAS_DNI.charAt(resto);

		return letraDni == letraCalculada;
	}

}