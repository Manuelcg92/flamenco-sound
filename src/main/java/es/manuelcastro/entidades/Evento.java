package es.manuelcastro.entidades;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import es.manuelcastro.utils.Constantes;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase Evento. Representa un evento con su informacion basica y el
 * grupoasociado.
 * 
 * @author Manuel Castro
 * @version 1.0
 * @since 2025-06-02
 */

@Entity
@Table(name = "eventos")
@Data
@NoArgsConstructor
public class Evento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "eventoId")
	private int eventoId;

	@NotBlank(message = Constantes.CAMPO_OBLIGATORIO)
	@Size(min = 2, max = 45, message = Constantes.RANGO)
	@Column(name = "nombre")
	private String nombre;

	@NotBlank(message = Constantes.CAMPO_OBLIGATORIO)
	@Size(min = 2, max = 45, message = Constantes.RANGO)
	@Column(name = "descripcion")
	private String descripcion;

	@NotBlank(message = Constantes.CAMPO_OBLIGATORIO)
	@Size(min = 2, max = 45, message = Constantes.RANGO)
	@Column(name = "lugar")
	private String lugar;

	@NotBlank(message = Constantes.CAMPO_OBLIGATORIO)
	@Size(min = 2, max = 45, message = Constantes.RANGO)
	@Column(name = "duracion")
	private String duracion;

	@NotBlank(message = Constantes.CAMPO_OBLIGATORIO)
	@Size(min = 2, max = 45, message = Constantes.RANGO)
	@Column(name = "tipoEvento")
	private String tipoEvento;

	@NotNull(message = Constantes.CAMPO_OBLIGATORIO)
	@Min(value = 0, message = Constantes.MIN_ENTRADAS)
	@Max(value = 100000, message = Constantes.MAX_ENTRADAS)
	@Column(name = "asientosDisp")
	private Integer asientosDisponibles;

	@NotNull(message = Constantes.CAMPO_OBLIGATORIO)
	@Column(name = "grupoId")
	private Integer grupoId;

}
