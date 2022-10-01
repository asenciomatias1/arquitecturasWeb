package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entidad.Factura;
import interfaces.FacturaInterface;

public class FacturaDAO implements FacturaInterface{

	PreparedStatement statement;
	ResultSet result;
	
	@Override
	public boolean insert(Factura obj) throws SQLException {
		boolean resp = false;
		String sql = "INSERT INTO factura (idFactura, idCliente) VALUES(?, ?)";
		try {
			statement = Conexion.getConn().prepareStatement(sql);
			statement.setInt(1, obj.getIdFactura());
			statement.setInt(2, obj.getIdCliente());
			
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
	public List<Factura> list() {
		List<Factura> resp = new ArrayList<Factura>();
		
		try {
			
			String sql = "SELECT * FROM factura";
			statement = Conexion.getConn().prepareStatement(sql);
			result = statement.executeQuery();
			
			while(result.next()) {
				int idFactura = result.getInt(1);
				int idCliente = result.getInt(2);
				
				resp.add(new Factura(idFactura,idCliente));
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
	public Factura getOne(int idToSearch) {
		Factura resp = null;
		try {
			String sql = "SELECT * FROM factura p WHERE idFactura = ?";
			statement = Conexion.getConn().prepareStatement(sql);
			statement.setInt(1, idToSearch);
			result = statement.executeQuery();
			
			if(result.next()) {
				int idFactura = result.getInt(1);
				int idCliente = result.getInt(2);
				resp = new Factura(idFactura,idCliente);
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
	public boolean update(Factura obj) {
	boolean resp = false;
		
		try {
			String sql = "UPDATE factura SET nombre = ?, edad = ? WHERE id = ?";
			statement = Conexion.getConn().prepareStatement(sql);
			statement.setInt(1, obj.getIdFactura());
			statement.setInt(2, obj.getIdCliente());
			
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
	public boolean delete(int idFactura) {
	boolean resp = false;
		
		try {
			String sql = "DELETE FROM factura WHERE id = ?";
			statement = Conexion.getConn().prepareStatement(sql);
			statement.setInt(1, idFactura);
			
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
