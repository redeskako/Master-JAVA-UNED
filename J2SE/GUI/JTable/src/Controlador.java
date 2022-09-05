import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


public class Controlador implements ActionListener{
	
	
Interfaz interfaz;
	
	public Controlador(Interfaz aux){
		interfaz=aux;
	}
	

	@Override
	public void actionPerformed(ActionEvent ev) {
			System.out.println("Entra en el actionPerformed");
			Object eventoLlamado = ev.getSource();
			String botonPulsado = ((JButton) eventoLlamado).getName();
			System.out.println("Evento getName= "+botonPulsado);
			
			if (botonPulsado == "avanzar_pag_paises") {
			}else if(botonPulsado == "retroceder_pag_paises"){
				
			}else if(botonPulsado == "retroceder_pag_indicadores"){
				
			}else if(botonPulsado == "avanzar_pag_indicadores"){
				
			}
		
	}
	
	

}
