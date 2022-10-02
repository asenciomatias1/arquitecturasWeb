package edu.isistan;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import edu.isistan.entity.Carrera;
import edu.isistan.entity.Estudiante;

public class Insert {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Example");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		/*
		Carrera d = new Carrera("Tudai", 2);
		em.persist(d);
		Estudiante e1 = new Estudiante("Roberto", "Zans", 20, "Masculino", 24800200, "Tandil");
		em.persist(e1);*/
		em.getTransaction().commit();
		em.close();
		emf.close();
	}

}
