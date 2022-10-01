package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import entidad.Cliente;
import interfaces.ClienteInterface;

public class ClienteDAO implements ClienteInterface{

	PreparedStatement statement;
	ResultSet result;
	
	public boolean insert(Cliente obj){
		boolean resp = false;
		String sql = "INSERT INTO persona (id, nombre, edad) VALUES(?, ?, ?)";
		try {
			statement = Conexion.getConn().prepareStatement(sql);
			statement.setInt(1, obj.getId());
			statement.setString(2, obj.getNombre());
			statement.setInt(3, obj.getEdad());
			
			if(statement.executeUpdate() > 0 ) {
				resp = true;
			}
			
			statement.close();
			Conexion.disconn();
			
			return resp;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return resp;
	}

	public List<Cliente> list() {
		List<Cliente> resp = new ArrayList<Cliente>();
		
		try {
			
			String sql = "SELECT * FROM cliente";
			statement = Conexion.getConn().prepareStatement(sql);
			result = statement.executeQuery();
			
			while(result.next()) {
				int id = result.getInt(1);
				String name = result.getString(2);
				int age = result.getInt(3);
				
				resp.add(new Cliente(id,age,name));
			}
			
			statement.close();
			result.close();
			Conexion.disconn();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resp;
	}

	public Cliente getOne(int idToSearch) {
		Cliente resp = null;
		try {
			String sql = "SELECT * FROM cliente p WHERE id = ?";
			statement = Conexion.getConn().prepareStatement(sql);
			statement.setInt(1, idToSearch);
			result = statement.executeQuery();
			
			if(result.next()) {
				int id = result.getInt(1);
				String name = result.getString(2);
				int age = result.getInt(3);
				resp = new Cliente(id,age,name);
			}
			
			statement.close();
			result.close();
			Conexion.disconn();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resp;
	}

	public boolean update(Cliente obj) {
		boolean resp = false;
		
		try {
			String sql = "UPDATE cliente SET nombre = ?, edad = ? WHERE id = ?";
			statement = Conexion.getConn().prepareStatement(sql);
			statement.setString(1, obj.getNombre());
			statement.setInt(2, obj.getEdad());
			statement.setInt(3, obj.getId());
			
			if(statement.executeUpdate() > 0 ) {
				resp = true;
			}
			
			statement.close();
			Conexion.disconn();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resp;
	}

	public boolean delete(int id) {
		boolean resp = false;
		
		try {
			String sql = "DELETE FROM cliente WHERE id = ?";
			statement = Conexion.getConn().prepareStatement(sql);
			statement.setInt(1, id);
			
			if(statement.executeUpdate() > 0 ) {
				resp = true;
			}
			
			statement.close();
			Conexion.disconn();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resp;
	}

}
