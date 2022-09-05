import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class interfaz {
	
	procesarEventos evento = new procesarEventos();
	
	
	public void crearInterfaz(){
		

		JTabbedPane tabbedPane = new JTabbedPane();//Interfaz gráfica con pestañas
			
		
/******************************** Pestaña KWIC *****************************/
		JPanel panel1 = new JPanel();//Creamos un nuevo panel para alojar el contenido de la pestaña KWIC
		
		GridBagLayout gl = new GridBagLayout();//Creamos un nuevo diseño de tipo "GridBagLayout" para el panel
		panel1.setLayout(gl);//Asignamos el diseño al panel
		GridBagConstraints constraints = new GridBagConstraints();//Creamos una variable del tipo GridBagConstraints para definir los parámetros de cada celda
		
		
		JLabel introLabel = new JLabel("Introduzca texto");
		constraints.gridx = 0;//El elemento está en la columna 0
		constraints.gridy = 0;//El elemento está en la fila 0
		constraints.gridwidth = 1;//Ocupa 1 celda horizontalmente
		constraints.gridheight = 1;//Ocupa 1 celda verticalmente
		constraints.anchor = GridBagConstraints.WEST;//Aparecerá pegado a la parte izquierda
		constraints.insets = new Insets(10,10,10,10);//Hacemos un padding en todos los componentes (al no resetearlo se crearán todos los elementos con este padding)
		panel1.add(introLabel, constraints);
		//constraints.weightx = 0.0;
		constraints.anchor = GridBagConstraints.CENTER;//Volvemos a poner la posición por defecto
		
		JTextArea introArea = new JTextArea();
		JScrollPane scrollIntroArea = new JScrollPane(introArea);//Creamos un panel deslizante para que pueda mostrar más texto del que cabe en el textArea original
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weighty = 1.0;//La fila se tiene que ampliar verticalmente
		constraints.weightx = 1.0;
		constraints.fill = GridBagConstraints.BOTH;//Para que rellene todas las celdas que ocupa
		panel1.add(scrollIntroArea, constraints);//Añadimos al panel el panel deslizante junto con las restricciones de la celda
		constraints.weighty = 0.0;//Reseteamos los valores para que no afecten a las demás celdas
		constraints.weightx = 0.0;
		constraints.fill = GridBagConstraints.NONE;
		
		JButton kwicBoton = new JButton("KWIC");
		kwicBoton.addActionListener(evento);
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 1.0;
		constraints.anchor = GridBagConstraints.CENTER;//Así aparecerá centrado (es la opción por defecto de "anchor")
		panel1.add(kwicBoton, constraints);
		constraints.weightx = 0.0;
		//constraints.fill = GridBagConstraints.NONE;
		
		JLabel resultLabel = new JLabel("Resultado: ");
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 1.0;
		constraints.anchor = GridBagConstraints.WEST;
		panel1.add(resultLabel, constraints);
		constraints.weightx = 0.0;
		constraints.anchor = GridBagConstraints.CENTER;
		
		JTextArea resultArea= new JTextArea();
		resultArea.setEditable(false);//Para que no se pueda introducir texto por pantalla
		JScrollPane scrollResultArea = new JScrollPane(resultArea);//Creamos un panel deslizante para que pueda mostrar más texto del que cabe en el textArea original
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weighty = 1.0;//La fila tiene que ampliarse verticalmente
		constraints.weightx = 1.0;
		constraints.fill = GridBagConstraints.BOTH;//Para que ocupe todas las celdas que abarca
		panel1.add(scrollResultArea, constraints);
		constraints.weighty = 0.0;//Reseteamos los valores para que no afecten a las demás celdas
		constraints.weightx = 0.0;
		constraints.fill = GridBagConstraints.NONE;
		
		tabbedPane.addTab("KWIC",  null, panel1, "KWIC");
/***************************************************************************/		
		
/******************************** Pestaña No Claves ************************/
		JPanel panel2 = new JPanel();//Crea un nuevo panel para alojar los elementos de la pestaña No Claves
		
		GridBagLayout g2 = new GridBagLayout();//Creamos un nuevo diseño de tipo "GridBagLayout" para el panel
		panel2.setLayout(g2);//Asignamos el diseño al panel
		GridBagConstraints constraints2 = new GridBagConstraints();
		
		JLabel noClaves = new JLabel("Tabla no claves");
		constraints2.insets = new Insets(10,10,10,10);//Hcemos un pading en todos los componentes
		constraints2.gridx = 0;//El elemento está en la columna 0
		constraints2.gridy = 0;//El elemento está en la fila 0
		constraints2.gridwidth = 1;//Ocupa 1 celda horizontalmente
		constraints2.gridheight = 1;//Ocupa 1 celda verticalmente
		constraints2.anchor = GridBagConstraints.WEST;
		
		panel2.add(noClaves,constraints2);
		constraints2.anchor = GridBagConstraints.CENTER;

		
		JTextArea textoNoClaves = new JTextArea();
		textoNoClaves.setEditable(false);//Para que no se pueda introducir texto por pantalla
		JScrollPane scrollTextoNoClaves = new JScrollPane(textoNoClaves);//Creamos un panel deslizante para que pueda mostrar más texto del que cabe en el textArea original
		constraints2.gridx = 0;
		constraints2.gridy = 1;
		constraints2.gridwidth = 2;
		constraints2.gridheight = 1;
		constraints2.weighty = 1.0;//La fila se tiene que ampliar verticalmente
		constraints2.weightx = 1.0;
		constraints2.fill = GridBagConstraints.BOTH;//Para que ocupe todas las celdas que abarca
		
		panel2.add(scrollTextoNoClaves,constraints2);
		constraints2.weighty = 0.0;
		constraints2.weighty = 0.0;
		
		constraints2.fill = GridBagConstraints.NONE;

		
		JLabel introNoClave = new JLabel("Introduzca No claves");
		constraints2.gridx = 0;
		constraints2.gridy = 2;
		constraints2.gridwidth = 1;
		constraints2.gridheight = 1;
		constraints2.anchor = GridBagConstraints.WEST;
		
		panel2.add(introNoClave,constraints2);
		constraints2.anchor = GridBagConstraints.CENTER;
		
		JTextField texto2 = new JTextField(15);
		constraints2.gridx = 0;
		constraints2.gridy = 3;
		constraints2.gridwidth = 1;
		constraints2.gridheight = 1;
		constraints2.weighty = 1.0;//La celda se tiene que ampliar verticalmente
		constraints2.weightx = 1.0;//La celda se amplia horizontalmente
		constraints2.anchor = GridBagConstraints.NORTHWEST;
		
		panel2.add(texto2,constraints2);
		constraints2.fill = GridBagConstraints.NONE;
		constraints2.anchor = GridBagConstraints.CENTER;
		constraints2.weighty = 0.0;
		constraints2.weightx = 0.0;
		
		JButton botonAceptar = new JButton("Aceptar");
		botonAceptar.addActionListener(evento);
		constraints2.insets = new Insets(7,10,10,10);
		constraints2.gridx = 1;
		constraints2.gridy = 3;
		constraints2.gridwidth = 1;
		constraints2.gridheight = 1;
		constraints2.weighty = 1.0;//La celda se tiene que ampliar verticalmente
		constraints2.weightx = 1.0;//La celda se amplia horizontalmente
		constraints2.anchor = GridBagConstraints.NORTHWEST;
		
		panel2.add(botonAceptar,constraints2);	
		constraints2.weighty = 0.0;
		constraints2.weightx = 0.0;
		constraints2.anchor = GridBagConstraints.CENTER;
		

		tabbedPane.addTab("No claves",  null, panel2, "No Claves");
/***************************************************************************/		

		

		

		
		JFrame frame = new JFrame("Prueba KWIC");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.setContentPane(tabbedPane);
        frame.setSize(500,600);
        frame.setVisible(true);
		//resultArea.append("Hola mundo, esto es una prueba a ver si escribe \n o noooo!!!");
	}

}
