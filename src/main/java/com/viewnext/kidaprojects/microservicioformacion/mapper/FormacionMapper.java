package com.viewnext.kidaprojects.microservicioformacion.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.viewnext.kidaprojects.microservicioformacion.model.Curso;
import com.viewnext.kidaprojects.microservicioformacion.model.Formacion;

/**
 * La clase {@code FormacionMapper} proporciona métodos para mapear entre objetos de las clases
 * {@code Formacion} y {@code Curso}.
 *
 * <p>El autor de esta clase es Víctor Colorado "Kid A".</p>
 *
 * @version 1.0
 * @since 3 de octubre de 2023
 */
@Component
public class FormacionMapper {

    /**
     * Convierte un objeto de la clase {@code Curso} en un objeto de la clase {@code Formacion}.
     *
     * @param curso El objeto de la clase {@code Curso} a convertir.
     * @return Un objeto de la clase {@code Formacion} convertido.
     */
    public Formacion toFormacion(Curso curso) {
        Formacion formacion = new Formacion();

        formacion.setAsignaturas(curso.getDuracion() >= 50 ? 10 : 5);
        formacion.setCurso(curso.getCodigo());
        formacion.setPrecio(curso.getPrecio());

        return formacion;
    }

    /**
     * Convierte una lista de objetos de la clase {@code Curso} en una lista de objetos de la clase {@code Formacion}.
     *
     * @param listaCursos La lista de objetos de la clase {@code Curso} a convertir.
     * @return Una lista de objetos de la clase {@code Formacion} convertidos.
     */
    public List<Formacion> toFormacionList(List<Curso> listaCursos) {
        return listaCursos.stream()
                .map(this::toFormacion)
                .toList();
    }

    /**
     * Convierte un objeto de la clase {@code Formacion} en un objeto de la clase {@code Curso}.
     *
     * @param formacion El objeto de la clase {@code Formacion} a convertir.
     * @return Un objeto de la clase {@code Curso} convertido.
     */
    public Curso toCurso(Formacion formacion) {
        Curso curso = new Curso();

        curso.setCodigo(formacion.getCurso());
        curso.setNombre(null);
        curso.setDuracion(formacion.getAsignaturas() * 10);
        curso.setPrecio(formacion.getPrecio());

        return curso;
    }

    /**
     * Convierte una lista de objetos de la clase {@code Formacion} en una lista de objetos de la clase {@code Curso}.
     *
     * @param listaFormacion La lista de objetos de la clase {@code Formacion} a convertir.
     * @return Una lista de objetos de la clase {@code Curso} convertidos.
     */
    public List<Curso> toCursoList(List<Formacion> listaFormacion) {
        return listaFormacion.stream()
                .map(this::toCurso)
                .toList();
    }
}
