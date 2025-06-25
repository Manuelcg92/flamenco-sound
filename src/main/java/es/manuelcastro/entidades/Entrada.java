package es.manuelcastro.entidades;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import es.manuelcastro.utils.Constantes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase Entrada. Representa una entrada con su informacion basica y el evento
 * asociado.
 * 
 * @author Manuel Castro
 * @version 1.1
 * @since 2025-06-16
 */

@Entity
@Table(name = "ventaentrada")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Entrada {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "entradaId")
	private Integer entradaId;

	@Size(min = 2, max = 45, message = Constantes.RANGO)
	@Column(name = "codigo", unique = true)
	private String codigo;

	@PositiveOrZero(message = Constantes.PRECIO_NO_NEGATIVO)
	@Column(name = "precio")
	private Double precio;

	@ManyToOne
	@JoinColumn(name = "eventoId", referencedColumnName = "eventoId", nullable = false)
	@NotNull(message = Constantes.CAMPO_OBLIGATORIO)
	private Evento evento;

	@ManyToOne
	@JoinColumn(name = "usuarioId", referencedColumnName = "usuarioId", nullable = false)
	@NotNull(message = Constantes.CAMPO_OBLIGATORIO)
	private Usuario usuario;

	@Column(name = "fecha")
	private LocalDateTime fecha;

}