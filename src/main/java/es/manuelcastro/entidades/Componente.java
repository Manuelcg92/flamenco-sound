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
import javax.validation.constraints.Size;

import es.manuelcastro.utils.Constantes;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Clase Componente. Representa un componente musical que pertenece a un grupo.
 * Cada componente tiene un ID, nombre, instrumento y una relacion con un grupo.
 * 
 * @author Manuel Castro
 * @version 1.1
 * @since 2025-06-16
 */

@Entity
@Table(name = "componentes")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "grupo") // Excluyo grupo para evitar recursividad
public class Componente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "componenteId")
	private int componenteId;

	@NotBlank(message = Constantes.CAMPO_OBLIGATORIO)
	@Size(min = 2, max = 45, message = Constantes.RANGO)
	@Column(name = "nombre")
	private String nombre;

	@NotBlank(message = Constantes.CAMPO_OBLIGATORIO)
	@Size(min = 2, max = 45, message = Constantes.RANGO)
	@Column(name = "instrumento")
	private String instrumento;

	@ManyToOne
	@JoinColumn(name = "grupoId")
	private Grupo grupo;

	/**
	 * Constructor que asigna un grupo al componente a partir de su ID.
	 * 
	 * @param grupoId - ID del grupo al que se asociara este componente
	 */
	public Componente(int grupoId) {
		this.grupo = new Grupo();
		this.grupo.setGrupoId(grupoId);
	}
}
