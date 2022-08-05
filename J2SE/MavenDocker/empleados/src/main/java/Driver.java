import es.uned.master.java.empleados.*;

public class Driver {

	public static void main(String[] args) {

		Empleados empleados = new Empleados();
		
		for(int i = 0; i<=10; i++) {
			Empleado empleado = new Empleado("C"+i);
			empleado.setNombre("Nombre-"+i);;
			empleado.setApellidos("Apellidos-"+i);
			empleados.addEmpleado(empleado);
		}
		System.out.println(empleados);
	}
}
