package pe.edu.tecsup.app.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pe.edu.tecsup.app.entities.Categoria;
import pe.edu.tecsup.app.utils.ConexionDB;

public class CategoriaDAOImpl implements CategoriaDAO {

	@Override
	public  List<Categoria> listar() throws Exception{
		List<Categoria> lista =  new ArrayList<Categoria>();
		
		// Conexion
		Connection con = ConexionDB.getConexion();
		
		// Preparamos la sentencia
		String sql = "select * from categorias";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		// Recuperar los registros del result set
		while(rs.next()) {
			Categoria categoria = new Categoria();
			categoria.setId(rs.getInt("id"));
			categoria.setNombre(rs.getString("nombre"));
			categoria.setDescripcion(rs.getString("descripcion"));
			lista.add(categoria);
		}
		
		rs.close();
		stmt.close();
		con.close();
		
		return lista;
	}
	
	@Override
	public  List<Categoria> buscarPorNombre(String nombre) throws Exception{
		List<Categoria> lista =  new ArrayList<Categoria>();
		
		// Conexion
		Connection con = ConexionDB.getConexion();
		
		// Preparamos la sentencia
		String sql = "SELECT id, nombre, descripcion FROM categorias WHERE nombre LIKE ? ";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, "%"+ nombre +"%");
		ResultSet rs = stmt.executeQuery();
		
		// Recuperar los registros del result set
		while(rs.next()) {
			Categoria categoria = new Categoria();
			categoria.setId(rs.getInt("id"));
			categoria.setNombre(rs.getString("nombre"));
			categoria.setDescripcion(rs.getString("descripcion"));
			lista.add(categoria);
		}
		
		rs.close();
		stmt.close();
		con.close();
		
		return lista;
	}
	
	@Override
	public  void registrar(Categoria categoria) throws Exception {
		
		Connection con = ConexionDB.getConexion();
		
		// Preparar la sentencia SQL
		String sql = "insert into categorias (nombre, descripcion) values (?, ?)";

		// Configura los datos
		PreparedStatement stmt = con.prepareStatement(sql);
		int i = 1;
		stmt.setString(i++, categoria.getNombre());
		stmt.setString(i++, categoria.getDescripcion());

		// Ejecuta la insercion
		stmt.executeUpdate();
		
		stmt.close();
		con.close();
		
	}
	
	@Override
	public  void actualizar(Categoria categoria) throws Exception {
		
		Connection con = ConexionDB.getConexion();
		
		// Preparar la sentencia SQL
		String sql = "UPDATE categorias SET nombre=? , descripcion=? WHERE id=?"; 
		
		PreparedStatement stmt = con.prepareStatement(sql);
		
		// Configura el PK del registro a eliminar
		int i = 1;
		stmt.setString(i++, categoria.getNombre()); 
		stmt.setString(i++, categoria.getDescripcion()); 
		stmt.setInt(i++, categoria.getId()); 
		
		// Ejecutar la actualizacion
		int estado = stmt.executeUpdate(); 
		
		// Verificar el cambio
		if (estado != 1)
			throw new SQLException("No se pudo actualizar correctamente"); 
		
		stmt.close();
		con.close();
		
	}

	@Override
	public void eliminar(Integer id) throws Exception {
		
		Connection con = ConexionDB.getConexion();
		
		// Preparar la sentencia SQL
		String sql = "DELETE FROM categorias WHERE id = ?"; 

		// Configura los datos		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, id);
		
		// Ejecutar el borrado
		int estado = stmt.executeUpdate(); 
		
		if (estado != 1)
			throw new SQLException("No se pudo eliminar id = "+ id );
		
		stmt.close();
		con.close();
	}

	@Override
	public int obtenerNroProductosPorCategoria(Integer categoria_id) 
				throws Exception {

		// Conexion
		Connection con = ConexionDB.getConexion();
		
		// Preparamos la sentencia
		String sql ="{CALL contar_productos (?,?)} ";
		CallableStatement cstatement = con.prepareCall(sql);
	
		// Entradas
		cstatement.setInt(1, categoria_id);//Tipo entero
		
		// Salidas
		//cstatement.registerOutParameter(2,Types.BINARY);
		
		// Ejecucion
		cstatement.execute();
		
		// Obtencion de los valores de salida
		int  nroProds = cstatement.getInt(2); // segundo parametro del sp
		
		// Cerrarmos conexion
		cstatement.close();
				
		return nroProds;
	}
	
}
