package pe.edu.tecsup.app;

import java.util.List;

import pe.edu.tecsup.app.entities.Categoria;
import pe.edu.tecsup.app.services.CategoriaService;

public class TestAppBuscar {

	public static void main(String[] args) {

		try {
		
			CategoriaService categoriaService = new CategoriaService();
			
			String nombre = "Procesador";
			
			List<Categoria> categorias = categoriaService.buscarPorNombre(nombre);
			
			System.out.println("Lista de categorias:");
			
			// foreach
			System.out.println("Using foreach() .....! ");
			for(Categoria categoria : categorias) System.out.println(categoria);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
