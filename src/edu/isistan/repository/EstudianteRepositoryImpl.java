package edu.isistan.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import edu.isistan.entity.Estudiante;

public class EstudianteRepositoryImpl implements EstudianteRepository {
	private EntityManager em;

	public EstudianteRepositoryImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	public List<Estudiante> getEstudiantesByGenero(String genero) {
		TypedQuery<Estudiante> q = em.createQuery("SELECT e FROM Estudiante e WHERE e.genero = :genero",
				Estudiante.class);
		q.setParameter("genero", genero);
		return q.getResultList();
	}

	@Override
	public List<Estudiante> getEstudiantesOrderByApellido() {
		TypedQuery<Estudiante> q = em.createQuery("SELECT e FROM Estudiante e order by e.apellido", Estudiante.class);
		return q.getResultList();
	}

	@Override
	public Estudiante getEstudianteById(long id) {
		return em.find(Estudiante.class, id);
	}

	@Override
	public Estudiante saveEstudiante(Estudiante e) {
		em.getTransaction().begin();
		em.persist(e);
		em.getTransaction().commit();
		//em.close();
		return e;
	}
}
