import videoClub.Admin_Prestamo;
import videoClub.Pelicula;
import videoClub.Prestamo;
import videoClub.Usuario;
import videoClub.VideoClubException;
import guiVideoClub.*;

//import java.util.*;



public class PrestamoTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Apéndice de método generado automáticamente
/*	Usuario u1 = new Usuario("Antonio", "Gonzalez", "34234", 234234, new Date()) ; ;
		Usuario u2 = new Usuario("Carlos", "Perez", "34234", 23342, new Date()) ; ;
		Usuario u3 = new Usuario("Pepe", "ramirez", "34234", 42342, new Date()) ; ;
=======
		
		Usuario u1 = new Usuario("Antonio", "Gonzalez",  "23423423") ; 
		Usuario u2 = new Usuario("Carlos", "Perez",  "23423424") ; 
		Usuario u3 = new Usuario("Pepe", "ramirez",  "23423425") ; 
>>>>>>> .r51
		
<<<<<<< .mine
		Pelicula p1 = new Pelicula("Rambo",1) ;
		Pelicula p2 = new Pelicula("Rambo 2 ",2) ;
		Pelicula p3 = new Pelicula("Rambo 3",3) ;
=======
		
		Pelicula p1 = new Pelicula("Rambo",1) ;
		Pelicula p2 = new Pelicula("Rambo 2 ",2) ;
		Pelicula p3 = new Pelicula("Rambo 3",3) ;
>>>>>>> .r51
		
		Prestamo prestamo1 = new Prestamo(u1,p1) ;
		Prestamo prestamo2 = new Prestamo(u2,p2) ;
		Prestamo prestamo3 = new Prestamo(u3,p3) ;
		
		//creamos el mapa
		Admin_Prestamo ad = new Admin_Prestamo() ;
		
		try{
			ad.insertarPrestamo(u1, prestamo1) ;
			ad.insertarPrestamo(u2, prestamo2) ;
			System.out.println(ad.toString());
			ad.insertarPrestamo(u3, prestamo3) ;
			System.out.println("\n\n\n" + ad.toString());
		
		}catch(VideoClubException e){
			System.out.println("Error en tester" +e);
		}*/
		
		try{
			GUIVideoClub gui = new GUIVideoClub() ;
			//GUIPrestamo prestamo= new GUIPrestamo();
			//GUINuevaPelicula n = new GUINuevaPelicula() ;
			//GUINuevoCliente nc = new GUINuevoCliente() ;
			//gui.setVisible(true) ;
			
			//prestamo.setVisible(true) ;
			Controlador controlador = new Controlador(gui) ;
		}catch (Exception e){
			System.out.println(e) ;
		}
	}

}
