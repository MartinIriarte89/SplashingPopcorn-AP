package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Genero;
import modelo.objetoNulo.GeneroNulo;
import persistencia.commons.ProveedorDeConexion;

public class GeneroDAO {

	public ArrayList<Genero> cargar() {
		String sql = "SELECT * FROM generos"; //agregar esto cuando esté en la bd WHERE borrado_logico = 0
		ArrayList<Genero> generos = new ArrayList<Genero>();

		try {
			Connection conexion = ProveedorDeConexion.getConexion();
			PreparedStatement declaracion = conexion.prepareStatement(sql);
			ResultSet resultados = declaracion.executeQuery();

			while (resultados.next()) {
				generos.add(crearGenero(resultados));
			}
		} catch (Exception e) {
			throw new DatosPerdidosError(e);
		}
		return generos;
	}

	public boolean borrar(Genero genero) {
		String sql = "UPDATE generos SET borrado_logico = 1 WHERE genero = ?";
		try {
			Connection conexion = ProveedorDeConexion.getConexion();
			PreparedStatement declaracion = conexion.prepareStatement(sql);

			declaracion.setString(1, genero.getNombre());
			declaracion.executeUpdate();

			return true;
		} catch (SQLException e) {
			throw new DatosPerdidosError(e);
		}
	}
	
	public boolean insertar(Genero genero) {
		String sql = "INSERT INTO generos (genero) VALUES (?)"; 
		
		try {
			Connection conexion = ProveedorDeConexion.getConexion();
			PreparedStatement declaracion = conexion.prepareStatement(sql);

			declaracion.setString(1, genero.getNombre());
			
			declaracion.executeUpdate();
			return true;

		} catch (SQLException e) {
			throw new DatosPerdidosError(e);
		}
	}
	
	
	private Genero crearGenero(ResultSet resultados) throws SQLException {
		String nombre = resultados.getString("genero");
		
		return new Genero(nombre);
	}

	public Genero buscarPor(String nombre) {
		Genero genero;
		try {
			String sql = "SELECT * FROM genero WHERE nombre = ?";
			Connection conexion = ProveedorDeConexion.getConexion();
			PreparedStatement declaracion = conexion.prepareStatement(sql);
			declaracion.setString(1, nombre);

			ResultSet resultados = declaracion.executeQuery();

			genero = GeneroNulo.construir();

			if (resultados.next()) {
				genero = crearGenero(resultados);
			}

		} catch (Exception e) {
			throw new DatosPerdidosError(e);

		}
		return genero;
		
	}
}
