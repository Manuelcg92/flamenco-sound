package es.manuelcastro.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import es.manuelcastro.utils.Constantes;
import es.manuelcastro.utils.ValidDni;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Clase que representa a un usuario en el sistema, almacena la informacion de
 * un usuario (representado por el objeto Usuario)
 * 
 * @author Manuel Castro
 * @version 1.0
 * @since 2025-06-03
 */

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usuarioId")
	private int usuarioId;

	@Column(name = "nombre")
	@NotBlank(message = Constantes.CAMPO_OBLIGATORIO)
	@Size(min = 2, max = 45, message = Constantes.RANGO)
	private String nombre;

	@Column(name = "apellido")
	@NotBlank(message = Constantes.CAMPO_OBLIGATORIO)
	@Size(min = 2, max = 45, message = Constantes.RANGO)
	private String apellido;

	@Column(name = "dni", unique = true)
	@NotBlank(message = Constantes.CAMPO_OBLIGATORIO)
	@Pattern(regexp = Constantes.REGEXP_DNI, message = Constantes.FORMATO_DNI)
	@ValidDni(message = Constantes.LETRA_DNI)
	private String dni;

	@Column(name = "email", unique = true)
	@NotBlank(message = Constantes.CAMPO_OBLIGATORIO)
	@Pattern(regexp = Constantes.REGEXP_EMAIL, message = Constantes.FORMATO_EMAIL)
	@Size(max = 200, message = Constantes.RANGO_MAX_EMAIL)
	private String email;

	@Column(name = "telefono")
	@NotBlank(message = Constantes.CAMPO_OBLIGATORIO)
	@Pattern(regexp = Constantes.REGEXP_TLF, message = Constantes.FORMATO_TELEFONO)
	private String telefono;

	@Column(name = "direccion")
	@NotBlank(message = Constantes.CAMPO_OBLIGATORIO)
	@Size(min = 2, max = 45, message = Constantes.RANGO)
	private String direccion;

	@Column(name = "usuario", unique = true)
	@NotBlank(message = Constantes.CAMPO_OBLIGATORIO)
	@Size(min = 2, max = 45, message = Constantes.RANGO)
	private String usuario;

	@Column(name = "password")
	@NotBlank(message = Constantes.CAMPO_OBLIGATORIO)
	@Size(min = 8, message = Constantes.RANGO_MIN_PASSWORD)
	private String password;

	@Column(name = "admin")
	private boolean admin;

	/**
	 * Constructor con parametros sin incluir el ID del usuario. Se utiliza para
	 * crear un objeto Usuario antes de que el ID sea asignado (por ejemplo, antes
	 * de persistirlo).
	 *
	 * @param nombre    - Nombre del usuario
	 * @param apellido  - Apellido del usuario
	 * @param dni       - DNI unico del usuario
	 * @param email     - Correo electronico del usuario
	 * @param telefono  - Telefono de contacto del usuario
	 * @param direccion - Direccion del usuario
	 * @param usuario   - Nombre de usuario para login
	 * @param password  - Password del usuario
	 * @param admin     - Indica si el usuario tiene permisos de administrador
	 */
	public Usuario(String nombre, String apellido, String dni, String email, String telefono, String direccion,
			String usuario, String password, boolean admin) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.email = email;
		this.telefono = telefono;
		this.direccion = direccion;
		this.usuario = usuario;
		this.password = password;
		this.admin = admin;
	}

}
