package es.manuelcastro.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import es.manuelcastro.utils.Constantes;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * Clase que representa un producto del sistema.
 * 
 * @author Manuel
 * @version 1.0
 * @since 2025-06-25
 */

@Entity
@Table(name = "producto")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "grupo") // Excluyo grupo para evitar recursividad

public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "productoId")
	private int productoId;

	@NotBlank(message = Constantes.CAMPO_OBLIGATORIO)
	@Size(min = 2, max = 45, message = Constantes.RANGO)
	@Column(name = "nombre")
	private String nombre;

	@NotBlank(message = Constantes.CAMPO_OBLIGATORIO)
	@Size(min = 2, max = 45, message = Constantes.RANGO)
	@Column(name = "descripcion")
	private String descripcion;

	@NotNull(message = Constantes.CAMPO_OBLIGATORIO)
	@PositiveOrZero(message = Constantes.PRECIO_NO_NEGATIVO)
	@Column(name = "precio")
	private Double precio;

	@ManyToOne
	@JoinColumn(name = "grupoId")
	private Grupo grupo;

	/**
	 * Constructor que asigna un grupo al producto a partir de su ID.
	 * 
	 * @param grupoId - ID del grupo al que se asociara este componente
	 */
	public Producto(int grupoId) {
		this.grupo = new Grupo();
		this.grupo.setGrupoId(grupoId);
	}
}
