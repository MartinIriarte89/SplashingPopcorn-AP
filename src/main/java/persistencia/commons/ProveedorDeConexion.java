package persistencia.commons;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import persistencia.DatosPerdidosError;

public class ProveedorDeConexion {

	private static String url;
	private static Connection conexion;

	static {
		Properties properties = new Properties();
		try {
			properties.load(ProveedorDeConexion.class.getResourceAsStream("/env.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		url = properties.getProperty("datasource");
	}

	public static Properties readPropertiesFile(String fileName) {
		FileInputStream fis = null;
		Properties prop = null;
		try {
			fis = new FileInputStream(fileName);
			prop = new Properties();
			prop.load(fis);
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return prop;
	}

	public static Connection getConexion() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		}
		if (conexion == null || conexion.isClosed()) {
			conexion = DriverManager.getConnection(url,"root","");
		}
		return conexion;
	}
	
	public static Connection getConexion(String url) throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		}
		if (conexion == null || conexion.isClosed()) {
			conexion = DriverManager.getConnection(url,"root","");
		}
		return conexion;
	}
	
	public static boolean cerrarConexion() {
		if (conexion != null) {
			try {
				conexion.close();
			} catch (SQLException e) {
				throw new DatosPerdidosError(e);
			}
			return true;
		} else
			return false;
	}

	/*public static Connection getConexion(String url) throws SQLException {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		}
		if (conexion == null) {
			conexion = DriverManager.getConnection("jdbc:sqlite:" + url);
		}
		return conexion;
	}*/

}
