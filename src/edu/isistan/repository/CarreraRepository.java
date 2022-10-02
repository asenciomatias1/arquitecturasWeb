package edu.isistan.repository;

import java.util.List;

import edu.isistan.entity.Carrera;
import edu.isistan.entity.Estudiante;
import edu.isistan.entity.Matricula;

public interface CarreraRepository {

	void getReporteCarreras();

	List<Carrera> getCarrerasWithEstudiantes();

	List<Estudiante> getEstudiantes(Carrera c);

	List<Estudiante> getEstudiantesByCiudad(Carrera c, String ciudad);

	Carrera getCarreraById(long id);

	Carrera saveCarrera(Carrera c);

	void addEstudiante(Estudiante e, Carrera c);

}
