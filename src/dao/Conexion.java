package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	
	private static final String host = "localhost";
	private static final String puerto = "3306";
	private static final String db = "entregable1";
	
	private static String user = "root";
	private static String pass = "password";
	private static String uri;
	private static Connection conn;
	private static String driver = "com.mysql.cj.jdbc.Driver";
	
	
	public static Connection getConn(){
		try {
			Class.forName(driver).getDeclaredConstructor().newInstance();
			uri = "jdbc:mysql://"+host+":"+puerto+"/"+db;
			conn = DriverManager.getConnection(uri, user, pass);
			conn.setAutoCommit(false); 
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static void disconn() {
		try {
			conn.commit();
			conn.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
}
