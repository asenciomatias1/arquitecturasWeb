package edu.isistan;

import java.util.List;
//import java.util.function.Consumer;
//import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import edu.isistan.entity.Carrera;
import edu.isistan.entity.Estudiante;
import edu.isistan.entity.Matricula;

public class Select {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Example");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		// Persona j = em.find(Persona.class, 1);
		// System.out.println(j);
		@SuppressWarnings({ "unchecked", "unused" })
		List<Estudiante> estudiantes = em.createQuery("SELECT e FROM Estudiante e").getResultList();

		for (Estudiante estudiante : estudiantes) {
			System.out.println(estudiante);
		}

		@SuppressWarnings({ "unchecked", "unused" })
		List<Carrera> carreras = em.createQuery("SELECT c FROM Carrera c").getResultList();

		for (Carrera carrera : carreras) {
			System.out.println(carrera);
		}

		@SuppressWarnings({ "unchecked", "unused" })
		List<Matricula> matriculas = em.createQuery("SELECT m FROM Matricula m").getResultList();

		for (Matricula matricula : matriculas) {
			System.out.println(matricula);
		}

		/*
		 * 2.a System.out.println("\n Personas asignadas a un turno \n");
		 * 
		 * @SuppressWarnings("unchecked") List<Persona> personasCTurnos =
		 * em.createQuery(
		 * "SELECT p FROM Turno_Persona tp INNER JOIN Persona p on p.id = tp.jugadores_id WHERE tp.turnos_id = :idTurno GROUP BY p.id"
		 * ) .getResultList(); personasCTurnos.forEach(p -> System.out.println(p));
		 */

		/*
		 * 2.b
		 * System.out.println("\n Personas asignadas a un turno y siendo socios \n");
		 * 
		 * @SuppressWarnings("unchecked") List<Persona> personasCTurnosYSocios =
		 * em.createQuery(
		 * "SELECT p FROM turno_persona tp INNER JOIN persona p on p.id = tp.jugadores_id INNER JOIN socio s on p.id = s.persona_id WHERE tp.turnos_id = :idTurno GROUP BY p.id"
		 * ) .getResultList(); personasCTurnosYSocios.forEach(p ->
		 * System.out.println(p));
		 */

		em.getTransaction().commit();
		em.close();
		emf.close();
	}

}
