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
import dao.ProductoDAO;
import entidad.Producto;

public class ProductoController {
	ProductoDAO data;
	Producto producto;
	
	public ProductoController(ProductoDAO data, Producto producto) {
		super();
		this.data = data;
		this.producto = producto;
	}

	public ProductoDAO getData() {
		return data;
	}

	public void setData(ProductoDAO data) {
		this.data = data;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	@SuppressWarnings("deprecation")
	public void addProducts() throws SQLException {
		CSVParser parser = null;
		try {
			parser = CSVFormat.DEFAULT.withHeader().parse(new
			FileReader("src/data-sets/productos.csv"));
			for(CSVRecord row: parser) {
				Connection conn = Conexion.getConn();
				String insert = "INSERT INTO producto (idProducto, nombre, valor) VALUES(?, ?, ?)";
				PreparedStatement ps = conn.prepareStatement(insert);
				ps.setInt(1,  Integer.parseInt(row.get("idProducto")));
				ps.setString(2, row.get("nombre"));
				ps.setInt(3, Integer.parseInt(row.get("valor")));
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
		List<Producto> resp = new ArrayList<Producto>();
		
		resp.addAll(data.list());
		
		for(Producto c: resp) {
			String row = c.getIdProducto() + " " + c.getNombre() + " " + c.getValor();
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

	public void update(int idProducto, String nombre, int valor) {
		try {
			Producto p = new Producto(idProducto, nombre, valor);
			data.update(p);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getOne(int id) {
		try {
			Producto p = data.getOne(id);
			System.out.print(p.getNombre());
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void createTable() {
		String table = "CREATE TABLE producto(" 
				+ "idProducto INT, " 
				+ "nombre VARCHAR(500),"
				+ "valor INT,"
				+ "PRIMARY KEY(idProducto))";
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
