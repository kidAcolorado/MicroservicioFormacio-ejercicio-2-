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

/**
 * La clase {@code FormacionServiceImpl} implementa la interfaz {@link FormacionService} y proporciona
 * la lógica para realizar operaciones relacionadas con formaciones y cursos.
 *
 * <p>El autor de esta clase es Víctor Colorado "Kid A".</p>
 *
 * @version 1.0
 * @since 3 de octubre de 2023
 */
@Service
public class FormacionServiceImpl implements FormacionService {

	private WebClient cursoWebClient;
	private FormacionMapper mapper;

	/**
     * Crea una instancia de {@code FormacionServiceImpl} con el cliente WebClient y el mapeador FormacionMapper proporcionados.
     *
     * @param cursoWebClient El cliente WebClient utilizado para realizar llamadas a servicios web.
     * @param mapper          El mapeador FormacionMapper utilizado para convertir entre objetos de formación y cursos.
     */
	public FormacionServiceImpl(WebClient cursoWebClient, FormacionMapper mapper) {
		this.cursoWebClient = cursoWebClient;
		this.mapper = mapper;
	}

	/**
     * Devuelve una lista de cursos disponibles en formato JSON.
     *
     * @return Una ResponseEntity con la lista de formaciones en formato JSON.
     */
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

	/**
     * Realiza el alta de un curso y devuelve una respuesta adecuada.
     *
     * @param formacion La información de la formación a dar de alta.
     * @return Una ResponseEntity con la respuesta apropiada.
     */
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
