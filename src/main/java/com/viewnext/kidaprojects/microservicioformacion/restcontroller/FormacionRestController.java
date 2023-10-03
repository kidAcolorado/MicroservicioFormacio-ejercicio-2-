package com.viewnext.kidaprojects.microservicioformacion.restcontroller;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.viewnext.kidaprojects.microservicioformacion.model.Formacion;
import com.viewnext.kidaprojects.microservicioformacion.service.FormacionService;

/**
 * El controlador REST {@code FormacionRestController} proporciona endpoints para gestionar las operaciones relacionadas con formaciones y cursos.
 *
 * <p>El autor de esta clase es Víctor Colorado "Kid A".</p>
 *
 * @version 1.0
 * @since 3 de octubre de 2023
 */
@RestController
public class FormacionRestController {

	
	private FormacionService service;
	
	/**
     * Crea una instancia de {@code FormacionRestController} con el servicio FormacionService proporcionado.
     *
     * @param service El servicio FormacionService utilizado para realizar operaciones relacionadas con formaciones y cursos.
     */
	public FormacionRestController(FormacionService service) {
		this.service = service;
	}

	/**
     * Endpoint para mostrar la lista de formaciones disponibles.
     *
     * @return Una ResponseEntity con la lista de formaciones en formato JSON.
     */
	@GetMapping(value = "formacion", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> mostrarFormaciones() {

		ResponseEntity<?> listaFormacionesResponse = service.devolverListaCursos();
		
		if (listaFormacionesResponse.getStatusCode() == HttpStatus.OK) {
			return ResponseEntity.ok(listaFormacionesResponse);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR");
		}

	}

	/**
     * Endpoint para dar de alta un curso en la formación.
     *
     * @param formacion La información de la formación a dar de alta.
     * @return Una ResponseEntity con la respuesta adecuada.
     */
	@PostMapping(value = "formacion", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> darAltaCurso(@RequestBody Formacion formacion){
		 // Llama al método del servicio
		ResponseEntity<?> respuesta = service.darAltaCurso(formacion);
		
       

        // Manejo de la respuesta:
        if (respuesta.getStatusCode() == HttpStatus.NO_CONTENT) {
            // En caso de éxito, devuelve una respuesta personalizada
            return ResponseEntity.ok("Curso dado de alta exitosamente.");
        } else if (respuesta.getStatusCode() == HttpStatus.CONFLICT) {
            // En caso de conflicto, devuelve una respuesta personalizada
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe un curso con el código proporcionado.");
        } else {
            // En caso de otros errores, devuelve una respuesta personalizada
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al dar de alta el curso.");
        }
    }

}
