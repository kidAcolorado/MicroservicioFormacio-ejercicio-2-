package com.viewnext.kidaprojects.microservicioformacion.model;

import java.util.Objects;


/**
 * La clase {@code Curso} representa una clase auxiliar para el tratamiento de datos provenientes de la Api ApiCrudCursos. 
 * Contiene información sobre el código del curso, nombre, duración y precio.
 *
 * <p>El autor de esta clase es Víctor Colorado "Kid A".</p>
 *
 * @version 1.0
 * @since 3 de octubre de 2023
 */
public class Curso {

	private String codigo;
	private String nombre;
	private int duracion; 
	private int precio; 
	
	public Curso(String codigo, String nombre, int duracion, int precio) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.duracion = duracion;
		this.precio = precio;
	}

	public Curso() {
		super();
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigoCurso) {
		this.codigo = codigoCurso;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Curso other = (Curso) obj;
		return Objects.equals(codigo, other.codigo);
	}

	@Override
	public String toString() {
		return "Curso [codigoCurso=" + codigo + ", nombre=" + nombre + ", duracion=" + duracion + ", precio="
				+ precio + "]";
	}
}
