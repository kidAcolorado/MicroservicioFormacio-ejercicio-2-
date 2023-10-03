package com.viewnext.kidaprojects.microservicioformacion.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.viewnext.kidaprojects.microservicioformacion.mapper.FormacionMapper;
import com.viewnext.kidaprojects.microservicioformacion.model.Curso;
import com.viewnext.kidaprojects.microservicioformacion.model.Formacion;

@Service
public class FormacionServiceImpl implements FormacionService {

	private WebClient cursoWebClient;
	private FormacionMapper mapper;

	public FormacionServiceImpl(WebClient cursoWebClient, FormacionMapper mapper) {
		this.cursoWebClient = cursoWebClient;
		this.mapper = mapper;
	}

	@Override
	public ResponseEntity<?> devolverListaCursos() {
		
		ResponseEntity<List<Curso>> listCursosResponse = cursoWebClient.get()
				.uri("/curso")
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.toEntityList(Curso.class)
				.block();
		
		if (listCursosResponse != null && listCursosResponse.getStatusCode() == HttpStatus.OK) {
			List<Curso> listaCursos = listCursosResponse.getBody();
			List<Formacion> listaFormaciones = mapper.toFormacionList(listaCursos);

			return ResponseEntity.ok(listaFormaciones);

		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT_FOUND");
		}

	}

	@Override
	public ResponseEntity<?> darAltaCurso(Formacion formacion) {
		ResponseEntity<List<Curso>> listaCursoResponse = cursoWebClient.get()
				.uri("/curso")
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.toEntityList(Curso.class)
				.block();

		if (listaCursoResponse != null && listaCursoResponse.getStatusCode() == HttpStatus.OK) {
			List<Curso> listaCursos = listaCursoResponse.getBody();

			for (Curso c : listaCursos) {
				if (c.getCodigo().equalsIgnoreCase(formacion.getCurso())) {
					return ResponseEntity.status(HttpStatus.CONFLICT)
							.body("Ya existe un curso con el código proporcionado.");
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
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("No se pudo obtener la lista de cursos.");
		}
	}

}
