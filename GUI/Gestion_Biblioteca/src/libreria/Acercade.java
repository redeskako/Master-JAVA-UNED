/*
 *   CARLOS LUIS SÁNCHEZ BOCANEGRA
                            DNI: 26017022C
             Expediente UNED: 55­04­00458
Domicilio: C/Cura Merino 2 2ºD 29011 Málaga

 */
package libreria;

import javax.swing.*;
import java.awt.*;

public class Acercade extends JInternalFrame {
	private JLabel asignatura;
	private JLabel curso;
	private JLabel autor;
	
	public Acercade(String titulo){
		super(titulo,
		          false, //resizable
		          true, //closable
		          false, //maximizable
		          false);//iconifiable	txtBoton[3]
		this.asignatura=new JLabel(Main.idioma.traduce("_ASIGNATURA")+ ": Sistemas Informaticos I");
		this.curso=new JLabel("2006");
		this.autor=new JLabel(Main.idioma.traduce("_AUTOR")+": Carlos Luis Sánchez Bocanegra - 26017022C");
		Container con= super.getContentPane();
		con.setLayout(new GridLayout(3,1));
		con.add(asignatura);
		con.add(curso);
		con.add(autor);
		try{
			super.setMaximum(false);
			super.setResizable(false);
			super.setClosable(true);
		}catch(Exception err){}
		super.pack();
		super.setVisible(false);
	}
	public void actualizaIdioma(){
		this.setTitle(Main.idioma.traduce("_ACERCADE"));
		this.asignatura.setText(Main.idioma.traduce("_ASIGNATURA")+ ": Sistemas Informaticos I");
		this.autor.setText(Main.idioma.traduce("_AUTOR")+": Carlos Luis Sánchez Bocanegra - 26017022C");
	}
}
