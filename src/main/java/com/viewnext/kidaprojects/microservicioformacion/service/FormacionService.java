package com.viewnext.kidaprojects.microservicioformacion.service;

import org.springframework.http.ResponseEntity;

import com.viewnext.kidaprojects.microservicioformacion.model.Formacion;

public interface FormacionService {

	public ResponseEntity<?> devolverListaCursos();
	
	public ResponseEntity<?> darAltaCurso(Formacion formacion);
}
