package pe.edu.tecsup.app;

import java.util.List;

import pe.edu.tecsup.app.entities.Categoria;
import pe.edu.tecsup.app.services.CategoriaService;

public class TestAppListar { 

	public static void main(String[] args) {

		try {
		
			CategoriaService categoriaService = new CategoriaService();
			
			List<Categoria> categorias = categoriaService.listar();
			
			System.out.println("Lista de categorias:");
			
			// foreach
			System.out.println("Using foreach() .....! ");
			for(Categoria categoria : categorias) System.out.println(categoria);
			
			// stream
			System.out.println("Using stream() .....! ");
			categorias.stream().forEach(item -> System.out.println(item));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
