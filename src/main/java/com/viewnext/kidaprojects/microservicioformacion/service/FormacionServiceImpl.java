package com.viewnext.kidaprojects.microservicioformacion.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

import com.viewnext.kidaprojects.microservicioformacion.mapper.FormacionMapper;
import com.viewnext.kidaprojects.microservicioformacion.model.Curso;
import com.viewnext.kidaprojects.microservicioformacion.model.Formacion;

public class FormacionServiceImpl implements FormacionService {

	private WebClient cursoWebClient;
	private FormacionMapper mapper;

	public FormacionServiceImpl(WebClient cursoWebClient, FormacionMapper mapper) {
		this.cursoWebClient = cursoWebClient;
		this.mapper = mapper;
	}

	@Override
	public ResponseEntity<?> devolverListaCursos() {
		ResponseEntity<List<Curso>> response = cursoWebClient.get()
				.uri("curso")
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.toEntityList(Curso.class)
				.block();
		if (response != null && response.getStatusCode() == HttpStatus.OK) {
			List<Curso> listaCursos = response.getBody();
			List<Formacion> listaFormaciones = mapper.toFormacionList(listaCursos);

			return ResponseEntity.ok(listaFormaciones);

		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR");
		}

	}

	@Override
	public ResponseEntity<?> darAltaCurso(Formacion formacion) {
		ResponseEntity<List<Curso>> response = cursoWebClient.get()
				.uri("curso")
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.toEntityList(Curso.class)
				.block();

		if (response != null && response.getStatusCode() == HttpStatus.OK) {
			List<Curso> listaCursos = response.getBody();

			for (Curso c : listaCursos) {
				if (c.getCodigoCurso().equalsIgnoreCase(formacion.getCurso())) {
					return ResponseEntity.noContent().build();
				}
			}

			Curso curso = mapper.toCurso(formacion);

			cursoWebClient.post()
			.uri("curso")
			.bodyValue(curso)
			.retrieve()
			.toBodilessEntity()
			.block();

			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
