package pe.edu.tecsup.app;

import java.util.List;

import pe.edu.tecsup.app.entities.Categoria;
import pe.edu.tecsup.app.services.CategoriaService;

public class TestAppInsertar {

	public static void main(String[] args) {
		try {
			
			CategoriaService categoriaService = new CategoriaService();
			
			String nombre = "Tablets";
			
			String descripcion = "Lista de tablets";
			
			categoriaService.registrar(nombre, descripcion);
			
			System.out.println("Se ha insertado correctamente!");
			
			List<Categoria> categorias = categoriaService.listar();
			
			for (Categoria categoria : categorias) System.out.println(categoria);
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

}
