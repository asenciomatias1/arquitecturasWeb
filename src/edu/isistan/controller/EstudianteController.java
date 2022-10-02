package edu.isistan.controller;

import java.util.*;

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

	/*
	 * public void addClients() throws SQLException, InstantiationException,
	 * IllegalAccessException, IllegalArgumentException, InvocationTargetException,
	 * NoSuchMethodException, SecurityException, ClassNotFoundException { //
	 * CSVParser parser = null; // try { // parser =
	 * CSVFormat.DEFAULT.withHeader().parse(new //
	 * FileReader("src/data-sets/clientes.csv")); // for (CSVRecord row : parser) {
	 * Connection conn = Conexion.getConn(); String insert =
	 * "INSERT INTO cliente (idCliente, nombre, email) VALUES(?, ?, ?)";
	 * PreparedStatement ps = conn.prepareStatement(insert); // ps.setInt(1,
	 * Integer.parseInt(row.get("idCliente"))); // ps.setString(2,
	 * row.get("nombre")); // ps.setString(3, row.get("email")); ps.executeUpdate();
	 * ps.close(); conn.commit(); // } // } catch (FileNotFoundException e) { //
	 * TODO Auto-generated catch block // e.printStackTrace(); // } catch
	 * (IOException e) { // TODO Auto-generated catch block // e.printStackTrace();
	 * // }
	 * 
	 * }
	 * 
	 * public void getAll() { List<Cliente> resp = new ArrayList<Cliente>();
	 * 
	 * resp.addAll(data.list());
	 * 
	 * for (Cliente c : resp) { String row = c.getId() + " " + c.getNombre() + " " +
	 * c.getEdad(); System.out.println(row); }
	 * 
	 * }
	 * 
	 * public void delete(int i) { try { data.delete(i);
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * }
	 * 
	 * public void update(int id, String name, int age) { try { Cliente c = new
	 * Cliente(id, age, name); data.update(c);
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } }
	 * 
	 * public void getOne(int id) { try { Cliente c = data.getOne(id);
	 * System.out.print(c.getNombre());
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * }
	 * 
	 * public void createTable() { String table = "CREATE TABLE Cliente(" +
	 * "idCliente INT, " + "nombre VARCHAR(500)," + "email VARCHAR(500)," +
	 * "PRIMARY KEY(idCliente))"; Connection conn = Conexion.getConn(); try {
	 * conn.prepareStatement(table).execute(); conn.commit(); } catch (SQLException
	 * e) { // TODO Auto-generated catch block e.printStackTrace(); }
	 * 
	 * }
	 */

}
