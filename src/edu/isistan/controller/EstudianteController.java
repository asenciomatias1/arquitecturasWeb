package edu.isistan.controller;

import java.util.List;

import edu.isistan.entity.Estudiante;
import edu.isistan.repository.EstudianteRepository;

public class EstudianteController {
	EstudianteRepository repo;

	public EstudianteController(EstudianteRepository repo) {
		super();
		this.repo = repo;
	}

	public void addEstudiante(Estudiante e2) {
		try {
			repo.saveEstudiante(e2);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Estudiante getEstudianteById(int id) {
		try {
			return repo.getEstudianteById(id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Estudiante> getEstudiantesOrderByApellido() {
		try {
			return repo.getEstudiantesOrderByApellido();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Estudiante> getEstudiantesByGenero(String genero) {
		try {
			return repo.getEstudiantesByGenero(genero);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
