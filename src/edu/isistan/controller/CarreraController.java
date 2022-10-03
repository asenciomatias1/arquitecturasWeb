package edu.isistan.controller;

import java.util.List;

import edu.isistan.dto.ReporteDTO;
import edu.isistan.entity.Carrera;
import edu.isistan.entity.Estudiante;
import edu.isistan.repository.CarreraRepository;

public class CarreraController {
	CarreraRepository repo;

	public CarreraController(CarreraRepository repo) {
		super();
		this.repo = repo;
	}

	public void addCarrera(Carrera c) {
		try {
			repo.saveCarrera(c);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Carrera getCarreraById(int id) {
		return repo.getCarreraById(id);
	}

	public void addEstudiante(Estudiante e2, Carrera c) {
		try {
			repo.addEstudiante(e2, c);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Carrera> getCarrerasWithEstudiantes() {
		try {
			return repo.getCarrerasWithEstudiantes();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Estudiante> getEstudiantesByCiudad(Carrera carrera, String ciudad) {
		try {
			return repo.getEstudiantesByCiudad(carrera, ciudad);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<ReporteDTO> getReporteCarreras() {
		try {
			return repo.generarReporte();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
