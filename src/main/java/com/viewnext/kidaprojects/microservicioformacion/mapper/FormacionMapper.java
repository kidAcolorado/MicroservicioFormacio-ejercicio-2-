package com.viewnext.kidaprojects.microservicioformacion.mapper;


import java.util.List;

import org.springframework.stereotype.Component;

import com.viewnext.kidaprojects.microservicioformacion.model.Curso;
import com.viewnext.kidaprojects.microservicioformacion.model.Formacion;

@Component
public class FormacionMapper {

	public Formacion toFormacion(Curso curso) {
		Formacion formacion = new Formacion();

		if (curso.getDuracion() >= 50) {
			formacion.setAsignaturas(10);
		} else {
			formacion.setAsignaturas(5);
		}

		formacion.setCurso(curso.getCodigoCurso());
		formacion.setPrecio(curso.getPrecio());

		return formacion;

	}

	public List<Formacion> toFormacionList(List<Curso> listaCursos) {

		return listaCursos.stream()
				.map(this::toFormacion)
				.toList();

	}
	
	public Curso toCurso(Formacion formacion) {
		Curso curso = new Curso();
		
		curso.setCodigoCurso(formacion.getCurso());
		curso.setNombre("default");
		curso.setDuracion(formacion.getAsignaturas() * 10);
		curso.setPrecio(formacion.getPrecio());
		
		return curso;
	}
	
	public List<Curso> toCursoList(List<Formacion> listaFormacion){
		return listaFormacion.stream()
				.map(this::toCurso)
				.toList();
	}
}
