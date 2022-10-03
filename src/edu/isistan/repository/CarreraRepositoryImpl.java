package edu.isistan.repository;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import edu.isistan.dto.ReporteDTO;
import edu.isistan.entity.Carrera;
import edu.isistan.entity.Estudiante;
import edu.isistan.entity.Matricula;

public class CarreraRepositoryImpl implements CarreraRepository {
	private EntityManager em;

	public CarreraRepositoryImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	public List<ReporteDTO> generarReporte() {
		String sql = "SELECT c.nombre, m.anio_cursada, c.cantEstudiantes as inscriptos, 0 as graduados "
				+ "FROM Carrera c INNER JOIN Matricula m ON c.id = m.fk_carrera " + "GROUP BY c.id, m.anio_cursada "
				+ " HAVING m.anio_cursada != 0" + " UNION "
				+ "SELECT c.nombre, m.anio_cursada, 0 as inscriptos, count(m.graduado) as graduados "
				+ "FROM Carrera c INNER JOIN Matricula m ON c.id = m.fk_carrera " + "GROUP BY c.id, m.graduado "
				+ " HAVING m.graduado != 0 " + "ORDER BY nombre, anio_cursada ASC";

		Query query = em.createNativeQuery(sql);
		@SuppressWarnings("unchecked")
		List<Object[]> reportes = query.getResultList();
		List<ReporteDTO> reporte = reportes.stream()
				.map(o -> new ReporteDTO((String) o[0], (int) o[1], (int) o[2], (BigInteger) o[3]))
				.collect(Collectors.toList());
		return reporte;
	}

	@Override
	public List<Carrera> getCarrerasWithEstudiantes() {
		TypedQuery<Carrera> q = em.createQuery(
				"SELECT c FROM Carrera c WHERE c.cantEstudiantes > 0 ORDER BY c.cantEstudiantes", Carrera.class);
		return q.getResultList();
	}

	@Override
	public List<Estudiante> getEstudiantesByCiudad(Carrera c, String ciudadOrigen) {
		long idCarrera = c.getId();
		TypedQuery<Estudiante> q = em.createQuery(
				"SELECT e FROM Matricula m JOIN Estudiante e on (e.libreta_universitaria = m.libreta_uni_estudiante) JOIN Carrera c on (m.id_carrera = c.id) WHERE c.id = :idCarrera AND e.ciudad = :ciudadOrigen",
				Estudiante.class);
		q.setParameter("idCarrera", idCarrera);
		q.setParameter("ciudadOrigen", ciudadOrigen);

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
			// em.close();
		} else {
			em.getTransaction().begin();
			c = em.merge(c);
			em.getTransaction().commit();
			// em.close();
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
		// em.close();
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
