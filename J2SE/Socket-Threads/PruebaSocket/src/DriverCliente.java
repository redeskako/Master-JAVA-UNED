import javax.swing.JFrame;
import es.uned.socket.*;

public class DriverCliente{
	 public static void main( String args[] ){
	      Cliente aplicacion;

	      if ( args.length == 0 )
	         aplicacion = new Cliente( "127.0.0.1" );
	      else
	         aplicacion = new Cliente( args[ 0 ] );

	      aplicacion.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	      aplicacion.ejecutarCliente();
	   }
}