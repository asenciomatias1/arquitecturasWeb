package edu.isistan.repository;

import java.util.List;

import edu.isistan.entity.Carrera;
import edu.isistan.entity.Estudiante;
import edu.isistan.entity.Matricula;

public interface MatriculaRepository {

	List<Estudiante> getEstudiantesEgresados(Carrera c);

	void saveMatricula(Matricula m);
}
