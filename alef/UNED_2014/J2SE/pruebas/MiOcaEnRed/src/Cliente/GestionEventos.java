package Cliente;

import java.awt.event.*;





public class GestionEventos implements ActionListener{
	
	       private MiClienteGui gui;
	       private Cliente cliente;
	   
	       public GestionEventos (MiClienteGui gui){
	    	   this.gui = gui; 
	       }
	
	    
	       
	public void actionPerformed (ActionEvent e){
		
		// Se accede al objeto origen del evento por medio del método getSource.
		Object o =  e.getSource();
		
		if (o == gui.getComenzar()){
		 
		   cliente= new Cliente(gui);
		   gui.actulizarEstadoComenzar(false);
		   gui.actualizarEstadoTerminar(true);
		   gui.actualizarEstadoTirarDado(true);
		   cliente.iniciarComunicacion();
	       
		 }   
	  
		if (o ==gui.getTirarDado()){
		   gui.jlDado.setText("");
		 gui.jlDado.setText("Has sacado un : " + String.valueOf(Dado.tirarDado()));
		 gui.actualizarEstadoLanzamiento(false);
		// Enviar el resultado de la tirada hacia servidor
	   
	   }
		  
		   
	  
		   
	   if (o==gui.getTerminar()){
		   
		   /*
		    * Cierra la comunicación con el servidor
		    */
	   }
	 
	   
	}
}
