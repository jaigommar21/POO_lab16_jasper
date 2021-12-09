package pe.edu.tecsup.app;

import pe.edu.tecsup.app.services.CategoriaService;

public class TestAppActualizar {

	public static void main(String[] args) {
		try {
			
			CategoriaService categoriaService = new CategoriaService();
			
			Integer id = 11;
			
			String nombre = "CAMBAIADO : Tablets";
			
			String descripcion = "CAMBAIADO : Lista de tablets";
			
			categoriaService.actualizar(id, nombre, descripcion);
			
			System.out.printf("Se actualiza la categoria con id = %d \n", id);
				
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

}
