import javax.swing.JFrame;
import es.uned.socket.*;

import es.uned.socket.Servidor;

public class DriverServidor{
   public static void main( String args[] ){
      Servidor aplicacion = new Servidor();
      aplicacion.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      aplicacion.ejecutarServidor();
   }
}