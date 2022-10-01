package dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entidad.Factura_Producto;
import interfaces.Factura_ProductoInterface;

public class Factura_ProductoDAO implements Factura_ProductoInterface{
	PreparedStatement statement;
	ResultSet result;
	@Override
	public boolean insert(Factura_Producto obj) throws SQLException {
		boolean resp = false;
		String sql = "INSERT INTO factura_producto (idFactura, idProducto) VALUES(?, ?, ?)";
		try {
			statement = Conexion.getConn().prepareStatement(sql);
			statement.setInt(1, obj.getIdFactura());
			statement.setInt(2, obj.getIdProducto());
			statement.setInt(3, obj.getCantidad());
			
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
	public List<Factura_Producto> list() {
		List<Factura_Producto> resp = new ArrayList<Factura_Producto>();
		
		try {
			
			String sql = "SELECT * FROM factura_producto";
			statement = Conexion.getConn().prepareStatement(sql);
			result = statement.executeQuery();
			
			while(result.next()) {
				int idFactura = result.getInt(1);
				int idProducto = result.getInt(2);
				int cantidad = result.getInt(2);
				
				resp.add(new Factura_Producto(idFactura,idProducto, cantidad));
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
	public Factura_Producto getOne(int idFactura, int idProducto) {
		Factura_Producto resp = null;
		try {
			String sql = "SELECT * FROM factura_producto p WHERE idFactura = ? AND idProducto = ?";
			statement = Conexion.getConn().prepareStatement(sql);
			statement.setInt(1, idFactura);
			statement.setInt(2, idProducto);
			result = statement.executeQuery();
			
			if(result.next()) {
				int idFact = result.getInt(1);
				int idProd = result.getInt(2);
				int contidad = result.getInt(3);
				resp = new Factura_Producto(idFact,idProd, contidad);
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
	public boolean update(Factura_Producto obj) {
	boolean resp = false;
		
		try {
			String sql = "UPDATE factura_producto SET cantidad = ? WHERE idFactura = ? AND idProducto = ?";
			statement = Conexion.getConn().prepareStatement(sql);
			statement.setInt(1, obj.getCantidad());
			
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
	public boolean delete(int idFactura, int idProducto) {
	boolean resp = false;
		
		try {
			String sql = "DELETE FROM factura_producto WHERE idFactura = ? AND idProducto = ?";
			statement = Conexion.getConn().prepareStatement(sql);
			statement.setInt(1, idFactura);
			statement.setInt(2, idProducto);
			
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
	
	public void getTopBilledProduct() {
		String sql = 
				"SELECT p.nombre, SUM(p.valor * fp.cantidad) AS recaudacion FROM cliente c " 
				+ "INNER JOIN factura f ON c.idCliente = f.idCliente " 
				+ "INNER JOIN factura_producto fp ON fp.idFactura = f.idFactura "
				+ "INNER JOIN producto p ON p.idProducto = fp.idProducto "
				+ "GROUP BY p.idProducto "
				+ "ORDER BY recaudacion "
				+ "DESC "
				+ "LIMIT 1";
		
		try {
			Connection conn = Conexion.getConn();
			statement = Conexion.getConn().prepareStatement(sql);
			result = statement.executeQuery();
			conn.commit();
			if (result.next()) {
				String name = result.getString(1);
				int billed = result.getInt(2);
				System.out.println("El producto que mas facturo es: " +name + " con un total de: " + billed);
			}
			
	}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}
	public void getTopBilledClient() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		String sql = 
				"SELECT c.nombre, SUM(p.valor * fp.cantidad) AS recaudacion FROM cliente c " 
				+ "INNER JOIN factura f ON c.idCliente = f.idCliente " 
				+ "INNER JOIN factura_producto fp ON fp.idFactura = f.idFactura "
				+ "INNER JOIN producto p ON p.idProducto = fp.idProducto "
				+ "GROUP BY c.idCliente "
				+ "ORDER BY recaudacion "
				+ "DESC";
		
		try {
			Connection conn = Conexion.getConn();
			statement = Conexion.getConn().prepareStatement(sql);
			result = statement.executeQuery();
			conn.commit();
			while(result.next()) {
				String name = result.getString(1);
				int billed = result.getInt(2);
				System.out.println(name + " facturo un total de: " + billed);
			}
			
	}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
	}
}
