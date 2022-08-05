import java.awt.event.*;
import guiVideoClub.*;

public class Controlador implements ActionListener {
		
	private GUIVideoClub gui;
		
	GUIPrestamo vprestamo;
	GUIDevolucion vdevolucion;
	GUINuevoCliente vnuevoCli;
	GUIEliminarCliente veliminarCli;
	GUINuevaPelicula vnuevaPelicula;
	GUIEliminarPelicula veliminarPelicula;
	GUIInformacionClientes vinfoCli;
	GUIInformacionPeliculas vinfoPeliculas;

	public Controlador(GUIVideoClub g) {
		gui = g;
		gui.controlador(this) ;
		gui.setVisible(true) ;
		
		
		vprestamo = new GUIPrestamo();
		vprestamo.controlador(this) ;

		vdevolucion = new GUIDevolucion();
		vdevolucion.controlador(this) ;
		
		vnuevoCli = new GUINuevoCliente();
		vnuevoCli.controlador(this) ;
		
		veliminarCli = new GUIEliminarCliente();
		veliminarCli.controlador(this) ; 
		
		vnuevaPelicula = new GUINuevaPelicula();
		vnuevaPelicula.controlador(this) ;
		
		veliminarPelicula = new GUIEliminarPelicula();
		veliminarPelicula.controlador(this) ;
		
		vinfoCli = new GUIInformacionClientes();
		vinfoCli.controlador(this) ;
		
		vinfoPeliculas = new GUIInformacionPeliculas();
		vinfoPeliculas.controlador(this) ;
	}
			
	public void actionPerformed(ActionEvent event) {
		String comando = event.getActionCommand();
			
		//Creamos las GUIs que vamos a utilizar.		
		if (comando.equals("PRESTAMO")) {
			 //Mostramos la ventana de prestamos;
			  vprestamo.setVisible(true);
			 //controlamos los eventos de GUIPrestamo
		}else if(comando.equals("ANADIRCLIENTE")){
			 //Accion para Añadir un Cliente de la tabla.
			//leer de los edits del panel
			//meterlo en base de datos 
			//y en la estructura dinamica
		}
		else if (comando.equals("ANADIRPELICULA")) {
				 //Accion para Añadir una Pelicula de la tabla
				
		}
		else if (comando.equals("ACEPTAR")) {
				 //Accion para Aceptar el prestamo
		}
		else if (comando.equals("CANCELAR")){
				 //se pulsa el boton cancelar
				 vprestamo.setVisible(false);
		}
		else if (comando.equals("DEVOLUCION")) {
			//Mostramos la ventana de devoluciones
			vdevolucion.setVisible(true);
		}
		else if(comando.equals("MOSTRARPELICULAS")){
				//Accion para mostrar las peliculas de un 
				//cliente seleccionado de la tabla.				
		}
		else if (comando.equals("DEVOLUCIONDEV")) {
				 //Accion para devolver las peliculas
				 //del cliente anteriormente seleccionado.
		}
		else if(comando.equals("CANCELARDEV")) {
				 //se pulsa el boton cancelar
				 vdevolucion.setVisible(false);
		}
		else if (comando.equals("NUEVOCLIENTE")) {
			vnuevoCli.setVisible(true);
		}
		else if(comando.equals("ANADIRCLIENTE")){
				//Accion para Añadir un cliente.
				//insertaremos su nombre, apellidos y dni
				
		}else if(comando.equals("CANCELARCLI")){
				 //se pulsa el boton cancelar
				 vnuevoCli.setVisible(false);
		} else if (comando.equals("ELIMINARCLIENTE")) {
			//Mostramos la ventana de eliminar cliente.
			veliminarCli.setVisible(true);
		}else if(comando.equals("ELIMINARCLIENTEELIM")){
				//Accion para eliminar un cliente seleccionado.
				//de la tabla.
				
		}else if(comando.equals("CANCELARELIM")){
				 //se pulsa el boton cancelar
				 veliminarCli.setVisible(false);
						
		} else if (comando.equals("NUEVAPELICULA")) {
			vnuevaPelicula.setVisible(true);
		}else if(comando.equals("ANADIRPELICULA")){
				//Accion para Añadir una pelicula.
				//insertaremos el titulo de la pelicula.
		}else if(comando.equals("CANCELARPNPELI")){
				 //se pulsa el boton cancelar
				 vnuevaPelicula.setVisible(false);		
		} else if (comando.equals("ELIMINARPELICULA")) {
			veliminarPelicula.setVisible(true);
		}else if(comando.equals("ELIMINARPELICULAEPELI")){
				//Accion para eliminar una pelicula.
				//Seleccionada de la tabla.
				
		}else if(comando.equals("CANCELAREPELI")){
				 //se pulsa el boton cancelar
				 veliminarPelicula.setVisible(false);			
		} else if (comando.equals("INFOCLIENTES")) {
			//muestra una tabla con todos los clientes
		}else	if(comando.equals("CERRARINFOCLI")){
				//Cerramos la ventana.
				vinfoCli.setVisible(false);				
		} else if (comando.equals("INFOPELICULAS")) {
			//muestra una tabla con todas las peliculas
		}else if(comando.equals("CERRARINFOPELI")){
				//Cerramos la ventana.
				vinfoPeliculas.setVisible(false);				
		}else {
			//Salimos de la aplicacion;
			gui.setVisible(false);
		}
	}
}
