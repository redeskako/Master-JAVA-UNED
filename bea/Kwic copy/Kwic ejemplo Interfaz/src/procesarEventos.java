import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;


public class procesarEventos  implements ActionListener{
	
	public void actionPerformed(ActionEvent e) {
		Object eventoLlamado = e.getSource();
		
		if(eventoLlamado instanceof JButton){//Comprobamos si el evento se ha originado en un JButton(en este ejemplo solo puede ser de este tipo)
			String botonPulsado=((JButton) eventoLlamado).getText();//Leemos el contenido del boton para saber que accion tenemos que realizar
			if(botonPulsado=="KWIC"){
				JOptionPane.showConfirmDialog(null, "Has pulsado: "+botonPulsado,"Prueba",JOptionPane.PLAIN_MESSAGE);
			}
			else if(botonPulsado == "Aceptar"){
				JOptionPane.showConfirmDialog(null, "Has pulsado: "+botonPulsado,"Prueba",JOptionPane.PLAIN_MESSAGE);
			}
		}
		else{
			
		}
		
	}

}
