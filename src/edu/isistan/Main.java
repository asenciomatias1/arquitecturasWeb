package edu.isistan;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import edu.isistan.controller.CarreraController;
import edu.isistan.controller.EstudianteController;
import edu.isistan.dto.ReporteDTO;
import edu.isistan.entity.Carrera;
import edu.isistan.entity.Estudiante;
import edu.isistan.repository.CarreraRepositoryImpl;
import edu.isistan.repository.EstudianteRepositoryImpl;

public class Main {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Example");
		EntityManager em = emf.createEntityManager();

		EstudianteController eController = new EstudianteController(new EstudianteRepositoryImpl(em));

		// Ejecutar clase Vacio para crear la base de datos, luego ya se puede usar esta
		// clase Main para los insert y select.

		Estudiante e1 = new Estudiante(260851, "Roberto", "Zansa", 20, "Masculino", 24800200, "Tandil");

		Estudiante e2 = new Estudiante(567892, "Maria", "Perez", 30, "Femenino", 25126780, "Mar del plata");

		Estudiante e3 = new Estudiante(127976, "Luis", "Luani", 22, "Masculino", 21098127, "Tandil");

		Estudiante e4 = new Estudiante(268908, "Juana", "Lorez", 28, "Femenino", 25780124, "Rauch");

		Estudiante e5 = new Estudiante(621671, "Raul", "Arren", 35, "Masculino", 22643098, "Mar del plata");

		// 2.a
		eController.addEstudiante(e1);
		eController.addEstudiante(e2);
		eController.addEstudiante(e3);
		eController.addEstudiante(e4);
		eController.addEstudiante(e5);

		// 2.c
		System.out.println("------------------------------------");
		System.out.println("Estudiantes ordenados por apellido");
		for (Estudiante e : eController.getEstudiantesOrderByApellido()) {
			System.out.println(e);
		}

		// 2.e
		System.out.println("------------------------------------");
		System.out.println("Estudiantes por genero");
		String gender = "Femenino";
		for (Estudiante e : eController.getEstudiantesByGenero(gender)) {
			System.out.println(e);
		}

		// 2.d (El id es la libreta universitaria)
		Estudiante e11 = eController.getEstudianteById(260851);

		Estudiante e22 = eController.getEstudianteById(567892);

		Estudiante e33 = eController.getEstudianteById(127976);

		Estudiante e44 = eController.getEstudianteById(268908);

		Estudiante e55 = eController.getEstudianteById(621671);

		CarreraController cController = new CarreraController(new CarreraRepositoryImpl(em));

		Carrera c = new Carrera("Tudai", 2);

		Carrera c2 = new Carrera("Tupar", 3);

		cController.addCarrera(c);

		cController.addCarrera(c2);

		Carrera d = cController.getCarreraById(1);

		// 2.b
		cController.addEstudiante(e11, d);

		cController.addEstudiante(e22, d);

		Carrera d2 = cController.getCarreraById(2);

		cController.addEstudiante(e33, d2);

		cController.addEstudiante(e44, d2);

		cController.addEstudiante(e55, d2);

		System.out.println("------------------------------------");
		System.out.println("Carreras con inscriptos ordenados por inscriptos");
		// 2.f
		for (Carrera carr : cController.getCarrerasWithEstudiantes()) {
			System.out.println(carr);
		}
		;

		System.out.println("------------------------------------");
		System.out.println("Estudiantes de la carrera Tudai que viven en Tandil");

		// 2.g
		for (Estudiante est : cController.getEstudiantesByCiudad(d, "Tandil")) {
			System.out.println(est);
		}

		// 3
		System.out.println("------------------------------------");
		System.out.println("Reportes de las carreras");
		for (ReporteDTO rp : cController.getReporteCarreras()) {
			System.out.println(rp);

		}

	}
}
