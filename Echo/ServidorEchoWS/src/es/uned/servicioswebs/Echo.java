package es.uned.servicioswebs;

public class Echo {
	public String saludar (String nombre){
		return "Hola " + nombre;
		// Aqu� se puede recurrir a una base de datos o tareas complejas.
	}
	
	public String despedir (String nombre){
		return "Adi�s " + nombre;
	}
}
