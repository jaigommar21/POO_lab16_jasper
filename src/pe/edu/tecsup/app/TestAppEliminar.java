package pe.edu.tecsup.app;

import pe.edu.tecsup.app.services.CategoriaService;

public class TestAppEliminar {

	public static void main(String[] args) {
		try {
			
			CategoriaService categoriaService = new CategoriaService();
			
			Integer id = 10;
			
			categoriaService.eliminar(id);
			
			System.out.printf("Se elimino la categoria con id = %d \n", id);
			
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

}
