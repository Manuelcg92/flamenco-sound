package es.manuelcastro.utils;

import lombok.NoArgsConstructor;

/**
 * Clase predeterminada que recoge todas las constantes de los mensajes de validaciones de as entidades.
 * 
 * @author Manuel Castro
 * @version 1.1
 * @since 2025-06-10
 *
 */

@NoArgsConstructor
public final class Constantes {
	
	//Validaciones comunes
	public static final String CAMPO_OBLIGATORIO = "El campo es obligatorio";
	public static final String RANGO = "El campo debe tener entre 2 y 45 caracteres";
	public static final String ERROR = "error";
	public static final String ENTRADAS_JSP = "entradas/entradas";
	public static final String EDIT_USER_JSP = "usuarios/editarUsuario";
	public static final String ERROR_INESPERADO = "Ha ocurrido un error inesperado: ";
    
	//Validaciones Evento
	public static final String MIN_ENTRADAS = "No puede haber menos de 0 entradas";
	public static final String MAX_ENTRADAS = "No puede haber más de 100.000 entradas";
	
	//Validaciones Grupo
	public static final String LIMITE_CREACION_ANTERIOR = "No puede ser anterior al año 1900";
	public static final String LIMITE_CREACION_POSTERIOR = "No puede ser posterior al año actual";
	
	//Validaciones Usuario
	public static final String FORMATO_DNI = "El formato del DNI debe ser 8 dígitos seguidos de una letra";
	public static final String LETRA_DNI = "La letra del DNI no coincide con el número";
	public static final String FORMATO_EMAIL = "Formato de email inválido";
	public static final String RANGO_MAX_EMAIL = "El email no puede exceder los 200 caracteres";
	public static final String FORMATO_TELEFONO = "Formato de teléfono inválido (debe tener 9 dígitos)";
	public static final String RANGO_MIN_PASSWORD = "La contraseña debe tener al menos 8 caracteres";
	public static final String REGEXP_DNI = "^[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKE]$";
	public static final String REGEXP_EMAIL = "^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
	public static final String DNI_LETRAS = "TRWAGMYFPDXBNJZSQVHLCKE";
	public static final String REGEXP_TLF = "^[0-9]{9}$";
	
	//Mensajes errores Controladores
	public static final String ERROR_CARGAR = "Error al cargar la información";
	public static final String ERROR_INSERTAR = "Error al insertar la información";
	public static final String ERROR_ACTUALIZAR = "Error al actualizar la información";
	public static final String ERROR_ELIMINAR = "Error al eliminar la información";
	public static final String ERROR_CAMPOS_LOGIN = "Debe completar los campos requeridos";
	public static final String ERROR_WRONG_USER = "Usuario o contraseña incorrectos";
	public static final String ERROR_LOGIN = "Ocurrió un error al intentar iniciar sesión";
	public static final String ERROR_CONEXION = "Ocurrió un error con la conexión a la base de datos";
	public static final String ERROR_NOT_FOUND = "Ocurrió un error desconocido";
	public static final String ERROR_EVENT_EXIST = "Este evento ya tiene entradas vendidas";
	public static final String ERROR_USER_EXIST = "Este usuario ya tiene entradas compradas";
    
    //Mensaje errores relacionado a las entradas
	public static final String COMPRA_MAX_ENTRADAS = "Ya no quedan entradas disponibles";
    public static final String COMPRA_MAX_USUARIO = "Has superado el máximo de entradas permitido";
    public static final String PRECIO_NO_NEGATIVO = "El precio no puede ser negativo";
    public static final String NO_EVENTO = "El evento no fue encontrado";
    public static final String NO_USUARIO = "Usuario no encontrado";
    public static final String NO_ASIENTOS = "No quedan asientos disponibles para este evento";
    public static final String SESION_EXPIRADA = "Su sesión ha expirado. Por favor, inicie sesión de nuevo";
    public static final String USUARIO_NO_BBDD = "Error de seguridad: Usuario no encontrado en la base de datos";
    public static final String USUARIO_NO_AUTORIZADO = "Error de seguridad: Usuario no autorizado para esta operación";
    public static final String COMPRA_REALIZADA = "Su compra ha sido realizada con éxito. Código de entrada: ";
    
    //Mensajes errores de logger
    public static final String NO_RESOURCE = "Error de recurso no encontrado: ";
    
}
