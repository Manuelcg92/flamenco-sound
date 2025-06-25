package es.manuelcastro.entidades;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import es.manuelcastro.utils.Constantes;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Clase que representa a un grupo en el sistema, almacena la informacion de un
 * grupo (representado por el objeto Grupo)
 * 
 * @author Manuel Castro
 * @version 1.1
 * @since 2025-06-03
 */

@Entity
@Table(name = "grupos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Grupo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "grupoId")
	private int grupoId;

	@NotBlank(message = Constantes.CAMPO_OBLIGATORIO)
	@Size(min = 2, max = 45, message = Constantes.RANGO)
	@Column(name = "nombre")
	private String nombre;

	@NotNull(message = Constantes.CAMPO_OBLIGATORIO)
	@Min(value = 1900, message = Constantes.LIMITE_CREACION_ANTERIOR)
	@Max(value = 2025, message = Constantes.LIMITE_CREACION_POSTERIOR)
	@Column(name = "creacion")
	private Integer creacion;

	@Column(name = "origen")
	private String origen;

	@Column(name = "genero")
	private String genero;

	// Lista de componentes que forman parte del grupo.
	// Relacion uno-a-muchos con la entidad Componente.
	
	@OneToMany(mappedBy = "grupo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Componente> componentes;
	
	@OneToMany(mappedBy = "grupo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Producto> productos;


}
