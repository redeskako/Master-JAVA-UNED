package es.uned.master.java.kwic;

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

import es.uned.master.java.kwic.controlador.ControladorKwic;
import es.uned.master.java.kwic.controlador.KwicFormArea;
import es.uned.master.java.kwic.controlador.KwicFormField;

public class Interfaz {

	public void crearInterfaz() {
		// Interfaz grafica con pesta�as
		JTabbedPane tabbedPane = new JTabbedPane(); 
										
		/***************************************************************************/
		/******************************** Pestana KWIC *****************************/
		//Panel para alojar el contenido de las pestanas KWIC
		JPanel panel1 = new JPanel();
		//Creamos un nuevo diseno de tipo GridBagLayout pare el panel
		GridBagLayout gl = new GridBagLayout();
		// Asignamos el diseno al panel
		panel1.setLayout(gl);
		//Creamos una variable de tipo GridBagConstraints para definir los parametros de cada celda
		GridBagConstraints constraints = new GridBagConstraints();

		JLabel introLabel = new JLabel("Introduzca texto");
		constraints.gridx = 0;// El elemento esta en la columna 0
		constraints.gridy = 0;// El elemento esta en la fila 0
		constraints.gridwidth = 1;// Ocupa 1 celda horizontalmente
		constraints.gridheight = 1;// Ocupa 1 celda verticalmente
		// Aparecece pegado a la parte izquierda
		constraints.anchor = GridBagConstraints.WEST;
		/* Hacemos un padding en todos los componentes (al no resetearlo se
	 	   creaian todos los elementos con este padding)*/
		constraints.insets = new Insets(10, 10, 10, 10);
		panel1.add(introLabel, constraints);
		// constraints.weightx = 0.0;
		// Posicion por defecto
		constraints.anchor = GridBagConstraints.CENTER;
														
		/***************************************************************************/
		/****************************** Area de frases *****************************/
		JTextArea introArea = new JTextArea();
		//Barra de scroll
		introArea.setEditable(false);
		JScrollPane scrollIntroArea = new JScrollPane(introArea);
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.weighty = 1.0;// La fila se tiene que ampliar verticalmente
		constraints.weightx = 1.0;
		// Para que rellene todas las celdas que ocupa
		constraints.fill = GridBagConstraints.BOTH;
		//a�adimos al panel la barra de scroll
		panel1.add(scrollIntroArea, constraints);
		// Reseteamos los valores para que no afecten a las demas celdas
		constraints.weighty = 0.0;
		constraints.weightx = 0.0;
		constraints.fill = GridBagConstraints.NONE;

		/***************************************************************************/
		/************************* Carga archivo frases ****************************/
		JTextField datosArchivoCargar = new JTextField();
		datosArchivoCargar.setEditable(false);
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		//constraints.weightx = 1.0;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.fill = GridBagConstraints.BOTH;
		panel1.add(datosArchivoCargar, constraints);
		constraints.weightx = 0.0;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.fill = GridBagConstraints.NONE;
		

		JButton adjuntarFichero = new JButton("Adjuntar fichero");
		adjuntarFichero.setName("adjuntarFichero");
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 1.0;
		//Centrado (es la opcion por defecto de anchor)
		constraints.anchor = GridBagConstraints.CENTER;
		panel1.add(adjuntarFichero, constraints);
		constraints.weightx = 0.0;
		
		/***************************************************************************/
		/************************* Campo resultado Kwic ****************************/
		JButton kwicBoton = new JButton("KWIC");
		kwicBoton.setName("KWIC");
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.weightx = 1.0;
		//Centrado (es la opcion por defecto de anchor)
		//constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.CENTER;
		panel1.add(kwicBoton, constraints);
		constraints.weightx = 0.0;
		constraints.fill = GridBagConstraints.NONE;
		

		JLabel resultLabel = new JLabel("Resultado: ");
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 1.0;
		constraints.anchor = GridBagConstraints.WEST;
		panel1.add(resultLabel, constraints);
		constraints.weightx = 0.0;
		constraints.anchor = GridBagConstraints.CENTER;

		JTextArea resultArea = new JTextArea();
		//El textArea no es editable
		resultArea.setEditable(false);
		//Barra de scroll
		JScrollPane scrollResultArea = new JScrollPane(resultArea);
		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.weighty = 1.0;// La fila tiene que ampliarse verticalmente
		constraints.weightx = 1.0;
		//Ocupa todas las celdas que abarca
		constraints.fill = GridBagConstraints.BOTH;
		panel1.add(scrollResultArea, constraints);
		//Reseteamos los valores para que no afecte a las demas celdas
		constraints.weighty = 0.0;
		constraints.weightx = 0.0;
		constraints.fill = GridBagConstraints.NONE;
		
		/***************************************************************************/
		/*********************** Campo graba resultado Kwic ************************/
		JTextField datosArchivoGrabar = new JTextField();
		datosArchivoGrabar.setEditable(false);
		constraints.gridx = 0;
		constraints.gridy = 6;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		//constraints.weightx = 1.0;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.fill = GridBagConstraints.BOTH;
		panel1.add(datosArchivoGrabar, constraints);
		constraints.weightx = 0.0;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.fill = GridBagConstraints.NONE;
		

		JButton grabarFichero = new JButton("Grabar fichero");
		grabarFichero.setName("grabarFichero");
		constraints.gridx = 1;
		constraints.gridy = 6;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 1.0;
		//Centrado (es la opcion por defecto de anchor)
		constraints.anchor = GridBagConstraints.CENTER;
		panel1.add(grabarFichero, constraints);
		constraints.weightx = 0.0;

		tabbedPane.addTab("KWIC", null, panel1, "KWIC");
		/***************************************************************************/
		/******************************** Pestana No Claves ************************/
		//Panel para alojar el contenido de las pestanas KWIC
		JPanel panel2 = new JPanel();
		//Creamos un nuevo diseno de tipo GridBagLayout pare el panel
		GridBagLayout g2 = new GridBagLayout();
		panel2.setLayout(g2);// Asignamos el diseno al panel
		GridBagConstraints constraints2 = new GridBagConstraints();

		JLabel noClaves = new JLabel("Tabla no claves");
		// Hacemos un pading en todos los componentes
		constraints2.insets = new Insets(10, 10, 10, 10);
		constraints2.gridx = 0;// El elemento esta en la columna 0
		constraints2.gridy = 0;// El elemento esta en la fila 0
		constraints2.gridwidth = 1;// Ocupa 1 celda horizontalmente
		constraints2.gridheight = 1;// Ocupa 1 celda verticalmente
		constraints2.anchor = GridBagConstraints.WEST;

		panel2.add(noClaves, constraints2);
		constraints2.anchor = GridBagConstraints.CENTER;

		JTextArea textoNoClaves = new JTextArea();
		//TextArea no editable
		textoNoClaves.setEditable(false);
		//Barrra de scroll
		JScrollPane scrollTextoNoClaves = new JScrollPane(textoNoClaves);
		constraints2.gridx = 0;
		constraints2.gridy = 1;
		constraints2.gridwidth = 2;
		constraints2.gridheight = 1;
		constraints2.weighty = 1.0;// La fila se tiene que ampliar verticalmente
		constraints2.weightx = 1.0;
		constraints2.fill = GridBagConstraints.BOTH;// Para que ocupe todas las
													// celdas que abarca

		panel2.add(scrollTextoNoClaves, constraints2);
		constraints2.weighty = 0.0;
		constraints2.weighty = 0.0;

		constraints2.fill = GridBagConstraints.NONE;

		JLabel introNoClave = new JLabel("Introduzca No claves");
		constraints2.gridx = 0;
		constraints2.gridy = 2;
		constraints2.gridwidth = 1;
		constraints2.gridheight = 1;
		constraints2.anchor = GridBagConstraints.WEST;

		panel2.add(introNoClave, constraints2);
		constraints2.anchor = GridBagConstraints.CENTER;

		/***************************************************************************/
		/******************* Campo introducir palabras noClave *********************/
		JTextField texto2 = new JTextField(15);	
		texto2.setVisible(false);
		constraints2.gridx = 0;
		constraints2.gridy = 3;
		constraints2.gridwidth = 1;
		constraints2.gridheight = 1;
		//La celda se tiene que ampliar verticalmente
		constraints2.weighty = 1.0;
		constraints2.weightx = 1.0;// La celda se amplia horizontalmente
		constraints2.anchor = GridBagConstraints.NORTHWEST;

		panel2.add(texto2, constraints2);
		constraints2.fill = GridBagConstraints.NONE;
		constraints2.anchor = GridBagConstraints.CENTER;
		constraints2.weighty = 0.0;
		constraints2.weightx = 0.0;

		JButton botonAceptar = new JButton("Aceptar");
		botonAceptar.setName("Aceptar");
		botonAceptar.setVisible(false);
		// botonAceptar.addActionListener(evento);
		constraints2.insets = new Insets(7, 10, 10, 10);
		constraints2.gridx = 1;
		constraints2.gridy = 3;
		constraints2.gridwidth = 1;
		constraints2.gridheight = 1;
		//La celda se tiene que ampliar verticalmente
		constraints2.weighty = 1.0;
		constraints2.weightx = 1.0;// La celda se amplia horizontalmente
		constraints2.anchor = GridBagConstraints.NORTHWEST;

		panel2.add(botonAceptar, constraints2);
		constraints2.weighty = 0.0;
		constraints2.weightx = 0.0;
		constraints2.anchor = GridBagConstraints.CENTER;
		
		/***************************************************************************/
		/********************* Campo cargar archivo noClave ************************/
		JTextField datosArchivoNoClaves = new JTextField();
		datosArchivoNoClaves.setEditable(false);
		constraints2.gridx = 0;
		constraints2.gridy = 4;
		constraints2.gridwidth = 1;
		constraints2.gridheight = 1;
		//constraints.weightx = 0.1;
		constraints2.anchor = GridBagConstraints.WEST;
		constraints2.fill = GridBagConstraints.BOTH;
		panel2.add(datosArchivoNoClaves, constraints2);
		constraints2.weightx = 0.0;
		constraints2.anchor = GridBagConstraints.CENTER;
		constraints2.fill = GridBagConstraints.NONE;
		

		JButton adjuntarFicheroNoClaves = new JButton("Adjuntar fichero");
		adjuntarFicheroNoClaves.setName("adjuntarFicheroNoClaves");
		constraints2.gridx = 1;
		constraints2.gridy = 4;
		constraints2.gridwidth = 1;
		constraints2.gridheight = 1;
		constraints2.weightx = 1.0;
		//Centrado (es la opcion por defecto de anchor)
		constraints2.anchor = GridBagConstraints.WEST;
		panel2.add(adjuntarFicheroNoClaves, constraints2);
		constraints2.weightx = 0.0;

		tabbedPane.addTab("No claves", null, panel2, "No Claves");
		
		/***************************************************************************/
		/******************************** Pestana Estadisticas ************************/
		//Panel para alojar el contenido de las pestana Estadisticas
		JPanel panel3 = new JPanel();
		//Creamos un nuevo diseno de tipo GridBagLayout pare el panel
		GridBagLayout g3 = new GridBagLayout();
		panel3.setLayout(g3);// Asignamos el diseno al panel
		GridBagConstraints constraints3 = new GridBagConstraints();

		JLabel resumen = new JLabel("Resumen");
		// Hacemos un pading en todos los componentes
		constraints3.insets = new Insets(10, 10, 10, 10);
		constraints3.gridx = 0;// El elemento esta en la columna 0
		constraints3.gridy = 0;// El elemento esta en la fila 0
		constraints3.gridwidth = 1;// Ocupa 1 celda horizontalmente
		constraints3.gridheight = 1;// Ocupa 1 celda verticalmente
		constraints3.anchor = GridBagConstraints.WEST;

		panel3.add(resumen, constraints3);
		constraints3.anchor = GridBagConstraints.CENTER;

		JTextArea datosEstadisticos = new JTextArea();
		//TextArea no editable
		datosEstadisticos.setEditable(false);
		//Barrra de scroll
		JScrollPane scrollDatosEstadisticos = new JScrollPane(datosEstadisticos);
		constraints3.gridx = 0;
		constraints3.gridy = 1;
		constraints3.gridwidth = 1;
		constraints3.gridheight = 1;
		constraints3.weighty = 1.0;// La fila se tiene que ampliar verticalmente
		constraints3.weightx = 1.0;
		constraints3.fill = GridBagConstraints.BOTH;// Para que ocupe todas las
													// celdas que abarca

		panel3.add(scrollDatosEstadisticos, constraints3);
		constraints3.weighty = 0.0;
		constraints3.weighty = 0.0;

		constraints3.fill = GridBagConstraints.NONE;

		
		JTextArea resultadosCliente = new JTextArea();
		//TextArea no editable
		resultadosCliente.setEditable(false);
		//Barrra de scroll
		JScrollPane scrollResultadosCliente = new JScrollPane(resultadosCliente);
		constraints3.gridx = 0;
		constraints3.gridy = 2;
		constraints3.gridwidth = 1;
		constraints3.gridheight = 1;
		constraints3.weighty = 1.0;// La fila se tiene que ampliar verticalmente
		constraints3.weightx = 1.0;
		constraints3.fill = GridBagConstraints.BOTH;// Para que ocupe todas las
													// celdas que abarca

		panel3.add(scrollResultadosCliente, constraints3);
		constraints3.weighty = 0.0;
		constraints3.weighty = 0.0;

		constraints3.fill = GridBagConstraints.NONE;



		tabbedPane.addTab("Estadisticas", null, panel3, "Estadisticas");
		
		/***************************************************************************/
		System.out.println("******************************");
		System.out.println("********PRACTICA KWIC*********");
		
		//Anadimos eventos a los botones
		ControladorKwic kwicEngineKWIC = new ControladorKwic(new KwicFormArea(introArea),
						 	 			 					 new KwicFormArea(resultArea), 
						 	 			 					 new KwicFormArea(textoNoClaves), 
						 	 			 					 new KwicFormField(texto2), 
						 	 			 					 new KwicFormField(datosArchivoCargar),
						 	 			 					 new KwicFormField(datosArchivoGrabar),
						 	 			 					 new KwicFormField(datosArchivoNoClaves),
															new KwicFormArea(datosEstadisticos),
															new KwicFormArea(resultadosCliente));
		kwicBoton.addActionListener(kwicEngineKWIC);
		botonAceptar.addActionListener(kwicEngineKWIC);
		adjuntarFichero.addActionListener(kwicEngineKWIC);
		grabarFichero.addActionListener(kwicEngineKWIC);
		adjuntarFicheroNoClaves.addActionListener(kwicEngineKWIC);
		
		
			

		JFrame frame = new JFrame("Prueba KWIC");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setContentPane(tabbedPane);
		frame.setSize(700, 800);
		frame.setVisible(true);
	}

}
