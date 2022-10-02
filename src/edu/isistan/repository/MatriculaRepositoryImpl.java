package edu.isistan.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import edu.isistan.entity.Carrera;
import edu.isistan.entity.Estudiante;
import edu.isistan.entity.Matricula;

public class MatriculaRepositoryImpl implements MatriculaRepository {
	private EntityManager em;

	public MatriculaRepositoryImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	public List<Estudiante> getEstudiantesEgresados(Carrera c) {
		long idCarrera = c.getId();
		TypedQuery<Estudiante> q = em.createQuery(
				"SELECT e FROM Estudiante e JOIN Carrera c JOIN Matricula m WHERE c.id = :idCarrera AND m.graduado = true",
				Estudiante.class);
		q.setParameter("idCarrera", idCarrera);
		return q.getResultList();
	}

	@Override
	public void saveMatricula(Matricula m) {
		em.getTransaction().begin();
		em.persist(m);
		em.getTransaction().commit();
		em.close();
	}

}
