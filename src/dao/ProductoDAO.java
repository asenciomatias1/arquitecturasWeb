package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entidad.Producto;
import interfaces.ProductoInterface;

public class ProductoDAO implements ProductoInterface{

	PreparedStatement statement;
	ResultSet result;
	@Override
	public boolean insert(Producto obj) throws SQLException {
		boolean resp = false;
		String sql = "INSERT INTO producto (idProducto, nombre, valor) VALUES(?, ?, ?)";
		try {
			statement = Conexion.getConn().prepareStatement(sql);
			statement.setInt(1, obj.getIdProducto());
			statement.setString(2, obj.getNombre());
			statement.setInt(3, obj.getValor());
			
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

	@Override
	public List<Producto> list() {
		List<Producto> resp = new ArrayList<Producto>();
		
		try {
			
			String sql = "SELECT * FROM Producto";
			statement = Conexion.getConn().prepareStatement(sql);
			result = statement.executeQuery();
			
			while(result.next()) {
				int idProducto = result.getInt(1);
				String nombre = result.getString(2);
				int valor = result.getInt(3);
				
				resp.add(new Producto(idProducto,nombre, valor));
			}
			
			statement.close();
			result.close();
			Conexion.disconn();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resp;
	}

	@Override
	public Producto getOne(int idProducto) {
		Producto resp = null;
		try {
			String sql = "SELECT * FROM producto p WHERE idProducto = ?";
			statement = Conexion.getConn().prepareStatement(sql);
			statement.setInt(1, idProducto);
			result = statement.executeQuery();
			
			if(result.next()) {
				int idProd = result.getInt(1);
				String nombre = result.getString(2);
				int valor = result.getInt(3);
				resp = new Producto(idProd,nombre, valor);
			}
			
			statement.close();
			result.close();
			Conexion.disconn();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resp;
	}

	@Override
	public boolean update(Producto obj) {
boolean resp = false;
		
		try {
			String sql = "UPDATE producto SET nombre = ?, valor = ? WHERE idProducto = ?";
			statement = Conexion.getConn().prepareStatement(sql);
			statement.setInt(1, obj.getIdProducto());
			statement.setString(2, obj.getNombre());
			statement.setInt(3, obj.getValor());
			
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

	@Override
	public boolean delete(int idProducto) {
	boolean resp = false;
		
		try {
			String sql = "DELETE FROM producto WHERE idProducto = ?";
			statement = Conexion.getConn().prepareStatement(sql);
			statement.setInt(1, idProducto);
			
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