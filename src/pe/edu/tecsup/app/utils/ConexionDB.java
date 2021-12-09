package pe.edu.tecsup.app.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

	public static String URL = "jdbc:mysql://192.168.64.2/almacen?useSSL=false";

	public static String USERNAME = "tecsup";

	public static String PASSWORD = "tecsup";

	public static Connection getConexion() throws ClassNotFoundException, SQLException {
		
		// Cargar el driver
		Class.forName("com.mysql.jdbc.Driver");

		// Conectarme a la BD
		Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);

		return con;
	}

}
