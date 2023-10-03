package com.viewnext.kidaprojects.microservicioformacion.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * La clase {@code GlobalExceptionHandler} proporciona un controlador de excepciones global para manejar
 * excepciones comunes en toda la aplicación. Gestiona errores relacionados con el formato de la solicitud JSON
 * y argumentos inválidos en las solicitudes.
 *
 * <p>El autor de esta clase es Víctor Colorado "Kid A".</p>
 *
 * @version 1.0
 * @since 3 de octubre de 2023
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	
    private static final String INVALID_FORMAT = "Error de formato en la solicitud JSON.";
    private static final String INVALID_ARGUMENT = "Argumento inválido para la solicitud.";
    
    /**
     * Maneja la excepción {@code HttpMessageNotReadableException} que se produce cuando la solicitud JSON
     * tiene un formato inválido.
     *
     * @param ex La excepción que se ha producido.
     * @return Una respuesta HTTP con un mensaje de error y estado BAD_REQUEST.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(INVALID_FORMAT);
    }
    
    /**
     * Maneja la excepción {@code NumberFormatException} que se produce cuando un argumento en la solicitud no es un número válido.
     *
     * @param ex La excepción que se ha producido.
     * @return Una respuesta HTTP con un mensaje de error y estado BAD_REQUEST.
     */
    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<String> handleNumberFormatException(NumberFormatException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(INVALID_ARGUMENT);
    }
}
