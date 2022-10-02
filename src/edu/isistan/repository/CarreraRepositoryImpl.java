package edu.isistan.repository;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import edu.isistan.entity.Carrera;
import edu.isistan.entity.Estudiante;
import edu.isistan.entity.Matricula;

public class CarreraRepositoryImpl implements CarreraRepository {
	private EntityManager em;

	public CarreraRepositoryImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	public void getReporteCarreras() {
		TypedQuery<Carrera> q = em.createQuery("SELECT c FROM Carrera c ORDER BY c.nombre", Carrera.class);
		List<Carrera> carreras = q.getResultList();
		System.out.println("\n Reporte Carreras: \n");
		for (Carrera c : carreras) {
			System.out.println("  Carrera : " + c.getNombre());
			TypedQuery<Matricula> q2 = em.createQuery(
					"SELECT m FROM Matricula m JOIN Carrera c WHERE c.id = :idCarrera ORDER BY m.anio_cursada",
					Matricula.class);
			q.setParameter("idCarrera", c.getId());

			List<Matricula> matriculas = q2.getResultList();
			for (Matricula m : matriculas) {
				TypedQuery<Estudiante> q3 = em.createQuery("SELECT e FROM Estudiante e WHERE e.id = :idEstudiante",
						Estudiante.class);
				q.setParameter("idEstudiante", m.getLibreta_uni_estudiante());
				Estudiante e = q3.getSingleResult();
				System.out.println("nombre: " + e.getNombre() + " apellido: " + e.getApellido() + " edad: "
						+ e.getEdad() + "documento: " + e.getDocumento() + " ciudad: " + e.getCiudad()
						+ " libreta estudiante: " + m.getLibreta_uni_estudiante() + " antiguedad: " + m.getAntiguedad()
						+ " graduado: " + m.isGraduado() + " anio_cursada: " + m.getAnio_cursada() + "\n\n\n");
			}
		}

	}

	@Override
	public List<Carrera> getCarrerasWithEstudiantes() {
		TypedQuery<Carrera> q = em.createQuery(
				"SELECT c FROM Carrera c WHERE c.cantEstudiantes > 0 ORDER BY c.cantEstudiantes", Carrera.class);
		return q.getResultList();
	}

	@Override
	public List<Estudiante> getEstudiantesByCiudad(Carrera c, String ciudadOrigen) {
		// AND e.ciudad = :ciudadOrigen
		// long idCarrera = c.getId();
		// SELECT DISTINCT p FROM Department d JOIN d.employees e JOIN e.projects p
		long idCarrera = 1;
		TypedQuery<Estudiante> q = em.createQuery(
				"SELECT e FROM Estudiante e,Matricula m WHERE e.libreta_universitaria = m.fk_estudiante",
				Estudiante.class);
		//q.setParameter("idCarrera", idCarrera);
		//q.setParameter("ciudadOrigen", ciudadOrigen);
		return q.getResultList();
	}

	@Override
	public Carrera getCarreraById(long id) {
		return em.find(Carrera.class, id);
	}

	@Override
	public Carrera saveCarrera(Carrera c) {
		if (this.getCarreraById(c.getId()) == null) {
			em.getTransaction().begin();
			em.persist(c);
			em.getTransaction().commit();
			//em.close();
		} else {
			em.getTransaction().begin();
			c = em.merge(c);
			em.getTransaction().commit();
			//em.close();
		}
		return c;
	}

	@Override
	public void addEstudiante(Estudiante e, Carrera c) {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		Matricula m = new Matricula(e, c, 0, false, year);
		int cantEstudiantes = c.getCantEstudiantes();
		cantEstudiantes++;
		c.setCantEstudiantes(cantEstudiantes);
		em.getTransaction().begin();
		em.persist(m);
		em.getTransaction().commit();
		//em.close();
	}

	
	
	@Override
	public List<Estudiante> getEstudiantes(Carrera c) {
		long idCarrera = c.getId();
		TypedQuery<Estudiante> q = em.createQuery(
				"SELECT e FROM Estudiante e JOIN Carrera c JOIN Matricula m WHERE c.id = :idCarrera", Estudiante.class);
		q.setParameter("idCarrera", idCarrera);
		return q.getResultList();
	}

}
