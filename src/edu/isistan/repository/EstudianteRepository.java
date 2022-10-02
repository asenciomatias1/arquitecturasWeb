package edu.isistan.repository;

import java.util.List;

import edu.isistan.entity.Estudiante;

public interface EstudianteRepository {

	List<Estudiante> getEstudiantesOrderByApellido();

	List<Estudiante> getEstudiantesByGenero(String genero);

	Estudiante getEstudianteById(long id);

	Estudiante saveEstudiante(Estudiante e);

}
