package com.viewnext.kidaprojects.microservicioformacion.service;

import org.springframework.http.ResponseEntity;

import com.viewnext.kidaprojects.microservicioformacion.model.Formacion;

/**
 * La interfaz {@code FormacionService} define los métodos para realizar operaciones relacionadas con formaciones y cursos.
 *
 * <p>El autor de esta interfaz es Víctor Colorado "Kid A".</p>
 *
 * @version 1.0
 * @since 3 de octubre de 2023
 */
public interface FormacionService {

	public ResponseEntity<?> devolverListaCursos();
	
	public ResponseEntity<?> darAltaCurso(Formacion formacion);
}
