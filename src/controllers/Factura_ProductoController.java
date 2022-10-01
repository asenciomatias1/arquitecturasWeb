package controllers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import dao.Conexion;
import dao.Factura_ProductoDAO;
import entidad.Factura_Producto;

public class Factura_ProductoController {
	static Factura_ProductoDAO data = new Factura_ProductoDAO();
	Factura_Producto factura_producto;
	
	
	@SuppressWarnings("deprecation")
	public static void addBillsProducts() throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		CSVParser parser = null;
		try {
			parser = CSVFormat.DEFAULT.withHeader().parse(new
			FileReader("src/data-sets/facturas-productos.csv"));
			for(CSVRecord row: parser) {
				Connection conn = Conexion.getConn();
				String insert = "INSERT INTO factura_producto (idFactura, idProducto, cantidad) VALUES(?, ?, ?)";
				PreparedStatement ps = conn.prepareStatement(insert);
				ps.setInt(1,  Integer.parseInt(row.get("idFactura")));
				ps.setInt(2, Integer.parseInt(row.get("idProducto")));
				ps.setInt(3, Integer.parseInt(row.get("cantidad")));
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
		List<Factura_Producto> resp = new ArrayList<Factura_Producto>();
		
		resp.addAll(data.list());
		
		for(Factura_Producto fp: resp) {
			String row = fp.getIdFactura() + " " + fp.getIdProducto() + " " + fp.getCantidad();
			System.out.println(row);
		}

	}

	public void delete(int i, int j) {
		try {
			data.delete(i, j);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void update(int idFactura, int idProducto, int valor) {
		try {
			Factura_Producto f = new Factura_Producto(idFactura, idProducto, valor);
			data.update(f);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getOne(int i, int j) {
		try {
			Factura_Producto fp = data.getOne(i,j);
			System.out.print(fp.getIdFactura() + " " + fp.getIdProducto() + " " + fp.getCantidad());
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void createTable() {
		String table = "CREATE TABLE factura_producto(" 
				+ "idFactura INT, " 
				+ "idProducto INT, "
				+ "cantidad INT,"
				+ "PRIMARY KEY(idFactura, idProducto))";
		Connection conn = Conexion.getConn();
		try {
			conn.prepareStatement(table).execute();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void getTopBilledProduct() {
		try {
			data.getTopBilledProduct();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void getTopBilledClient() {
		try {
			data.getTopBilledClient();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
