package pe.edu.tecsup.app.dao;

import java.util.List;

import pe.edu.tecsup.app.entities.Categoria;

public interface CategoriaDAO {

	List<Categoria> listar() throws Exception;

	List<Categoria> buscarPorNombre(String nombre) throws Exception;

	void registrar(Categoria categoria) throws Exception;

	void actualizar(Categoria categoria) throws Exception;

	void eliminar(Integer id) throws Exception;

	int obtenerNroProductosPorCategoria(Integer categoria_id) throws Exception;

}