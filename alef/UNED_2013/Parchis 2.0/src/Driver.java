import java.awt.FlowLayout;

import javax.swing.WindowConstants;

import es.uned2013.parchis.server.gui.FormularioInicial;

public class Driver {
	
	public static void main (String [] args) {
		
		FormularioInicial formulario1=new FormularioInicial();
		formulario1.getContentPane().setLayout(new FlowLayout());
		formulario1.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		formulario1.setLocationRelativeTo(null);
		formulario1.setTitle("Parchis 2.0 Server");
		formulario1.pack();
		formulario1.setVisible(true);
	}
}
