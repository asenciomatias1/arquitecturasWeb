package controllers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import dao.Conexion;
import dao.FacturaDAO;
import entidad.Factura;

public class FacturaController {
	FacturaDAO data;
	Factura factura;
	
	public FacturaController(FacturaDAO data, Factura factura) {
		super();
		this.data = data;
		this.factura = factura;
	}
	
	@SuppressWarnings("deprecation")
	public void addBills() throws SQLException {
		CSVParser parser = null;
		try {
			parser = CSVFormat.DEFAULT.withHeader().parse(new
			FileReader("src/data-sets/facturas.csv"));
			for(CSVRecord row: parser) {
				Connection conn = Conexion.getConn();
				String insert = "INSERT INTO factura (idFactura, idCliente) VALUES(?, ?)";
				PreparedStatement ps = conn.prepareStatement(insert);
				ps.setInt(1,  Integer.parseInt(row.get("idFactura")));
				ps.setInt(2, Integer.parseInt(row.get("idCliente")));
				ps.executeUpdate();
				ps.close();
				conn.commit();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void getAll() {
		List<Factura> resp = new ArrayList<Factura>();
		
		resp.addAll(data.list());
		
		for(Factura f: resp) {
			String row = f.getIdFactura() + " " + f.getIdCliente();
			System.out.println(row);
		}

	}

	public void delete(int i) {
		try {
			data.delete(i);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void update(int idFactura, int idCliente) {
		try {
			Factura f = new Factura(idFactura, idCliente);
			data.update(f);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getOne(int id) {
		try {
			Factura f = data.getOne(id);
			System.out.print(f.getIdFactura());
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void createTable() {
		String table = "CREATE TABLE factura(" 
				+ "idFactura INT, " 
				+ "idCliente INT,"
				+ "PRIMARY KEY(idFactura, idCliente))";
		Connection conn = Conexion.getConn();
		try {
			conn.prepareStatement(table).execute();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
