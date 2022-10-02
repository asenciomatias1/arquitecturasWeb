package edu.isistan;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dto.ReporteDTO;
import edu.isistan.controller.CarreraController;
import edu.isistan.controller.EstudianteController;
import edu.isistan.entity.Carrera;
import edu.isistan.entity.Estudiante;
import edu.isistan.repository.CarreraRepositoryImpl;
import edu.isistan.repository.EstudianteRepositoryImpl;

public class Main {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Example");
		EntityManager em = emf.createEntityManager();

		EstudianteController eController = new EstudianteController(new EstudianteRepositoryImpl(em));
		
//		Estudiante e1 = new Estudiante("Robertossssss", "Zansaaaaaa", 20,
//		"Masculino", 24800200, "Tandil");
//		 
//		eController.addEstudiante(e1);
//		System.out.println("Estudiantes ordenados por apellido");
//		for (Estudiante e : eController.getEstudiantesOrderByApellido()) {
//			System.out.println(e);
//		}
		
//		System.out.println("Estudiantes por genero");
//		String gender = "Femenino";
//		for (Estudiante e : eController.getEstudiantesByGenero(gender)) {
//			System.out.println(e);
//		}
//		System.out.println("---");
		
		Estudiante e2 = eController.getEstudianteById(1);
		//System.out.println("Estudiante: " + e2);

		CarreraController cController = new CarreraController(new CarreraRepositoryImpl(em));

		Carrera c = new Carrera("Tudai", 2);

		//cController.addCarrera(c);

		Carrera d = cController.getCarreraById(1);

		//cController.addEstudiante(e2, d);
		
		System.out.println("Carreras con inscriptos ordenados por inscriptos");
		
		for (Carrera carr : cController.getCarrerasWithEstudiantes()) {
			System.out.println(carr);
		};
		
		System.out.println("Estudiantes de la carrera Tudai que viven en Tandil");
		
		String aux = "Tandil";
		for (Estudiante est : cController.getEstudiantesByCiudad(d, "Tandil")) {
			System.out.println(est);
		}

		for (ReporteDTO rp : cController.getReporteCarreras()) {
			System.out.println(rp);
		}
		
	}

}
