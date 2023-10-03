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

@RestController
public class FormacionRestController {

	
	private FormacionService service;
	
	public FormacionRestController(FormacionService service) {
		this.service = service;
	}

	@GetMapping(value = "formacion", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> mostrarFormaciones() {

		ResponseEntity<?> listaFormacionesResponse = service.devolverListaCursos();
		
		if (listaFormacionesResponse.getStatusCode() == HttpStatus.OK) {
			return ResponseEntity.ok(listaFormacionesResponse);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR");
		}

	}

	@PostMapping(value = "formacion", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> darAltaCurso(@RequestBody Formacion formacion){
		 // Llama al método del servicio
		ResponseEntity<?> respuesta = service.darAltaCurso(formacion);
		
       

        // Manejo de la respuesta:
        if (respuesta.getStatusCode() == HttpStatus.NO_CONTENT) {
            // En caso de éxito, puedes devolver una respuesta personalizada
            return ResponseEntity.ok("Curso dado de alta exitosamente.");
        } else if (respuesta.getStatusCode() == HttpStatus.CONFLICT) {
            // En caso de conflicto, puedes devolver una respuesta personalizada
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe un curso con el código proporcionado.");
        } else {
            // En caso de otros errores, puedes devolver una respuesta personalizada
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al dar de alta el curso.");
        }
    }

}
