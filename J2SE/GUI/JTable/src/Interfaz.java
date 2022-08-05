import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;



public class Interfaz extends JFrame implements ActionListener{
	private JLabel paises;
	private ModeloPaises modeloPaises;
	private JTable tablaPaises;
	private JButton botonAvanzar_pais;
	private JButton botonRetroceder_pais;
	private JLabel paginaPaises;
	
	private JLabel indicadores;
	private ModeloIndicadores modeloIndicadores;
	private JTable tablaIndicadores;
	private JButton botonAvanzar_ind;
	private JButton botonRetroceder_ind;
	private JLabel paginaIndicadores;
	
	private JLabel resultado;
	private ModeloResultado modeloResultado;
	private JTable tablaResultado;
	
	 public Interfaz() {
	        super("Cliente, mostrar JTable y Graficas");
	        
	        //Controlador controlador = new Controlador(this);
	        
	        /******************************JPanel izquierda******************************/
	        //Contiene lo relativo a la tabla de datos
	        JPanel izquierda = new JPanel();
	        
	        GridBagLayout g1 = new GridBagLayout();
	        izquierda.setLayout(g1);
	        GridBagConstraints constraints = new GridBagConstraints();
	        
	        paises = new JLabel("Paises: ");
	        constraints.gridx = 0;// El elemento esta en la columna 0
			constraints.gridy = 0;// El elemento esta en la fila 0
			constraints.gridwidth = 1;// Ocupa 1 celda horizontalmente
			constraints.gridheight = 1;// Ocupa 1 celda verticalmente
			// Aparecece pegado a la parte izquierda
			constraints.anchor = GridBagConstraints.WEST;
			/* Hacemos un padding en todos los componentes (al no resetearlo se
		 	   creaian todos los elementos con este padding)*/
			constraints.insets = new Insets(5, 5, 5, 5);
			izquierda.add(paises, constraints);
			// Posicion por defecto
			constraints.anchor = GridBagConstraints.CENTER;
	        
			String[] columnNames = {"indicator_code","indicator_name","source_note", "source_organization"};
	        modeloPaises = new ModeloPaises();
	        tablaPaises = new JTable(modeloPaises);
	        tablaPaises.setPreferredScrollableViewportSize(new Dimension(400, 100));
	        //Creatamos un contenedor para la Tabla
	        JScrollPane scrollPanePaises = new JScrollPane(tablaPaises);
	        constraints.gridx = 0;// El elemento esta en la columna 0
			constraints.gridy = 1;// El elemento esta en la fila 1
			constraints.gridwidth = 5;// Ocupa 5 celda horizontalmente
			constraints.gridheight = 1;// Ocupa 1 celda verticalmente
			//constraints.weighty = 1.0;// La fila se tiene que ampliar verticalmente
			constraints.weightx = 1.0;
			// Aparecece pegado a la parte izquierda
			constraints.anchor = GridBagConstraints.WEST;
			constraints.fill = GridBagConstraints.BOTH;
			izquierda.add(scrollPanePaises, constraints);
			// constraints.weightx = 0.0;
			// Posicion por defecto
			constraints.anchor = GridBagConstraints.CENTER;
			
			botonRetroceder_pais = new JButton("Retroceder");
			botonRetroceder_pais.setName("retroceder_pag_paises");
			constraints.gridx = 1;
			constraints.gridy = 2;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			//constraints.weightx = 1.0;
			//Centrado (es la opcion por defecto de anchor)
			constraints.fill = GridBagConstraints.BOTH;
			constraints.anchor = GridBagConstraints.CENTER;
			izquierda.add(botonRetroceder_pais, constraints);
			//constraints.weightx = 0.0;
			constraints.fill = GridBagConstraints.NONE;
			
			paginaPaises = new JLabel("0 de 100");
			constraints.gridx = 2;// El elemento esta en la columna 2
			constraints.gridy = 2;// El elemento esta en la fila 2
			constraints.gridwidth = 1;// Ocupa 1 celda horizontalmente
			constraints.gridheight = 1;// Ocupa 1 celda verticalmente
			// Aparecece pegado a la parte izquierda
			constraints.anchor = GridBagConstraints.WEST;
			izquierda.add(paginaPaises, constraints);
			// constraints.weightx = 0.0;
			// Posicion por defecto
			constraints.anchor = GridBagConstraints.CENTER;
			
			botonAvanzar_pais = new JButton("Avanzar");
			botonAvanzar_pais.setName("avanzar_pag_paises");
			constraints.gridx = 3;
			constraints.gridy = 2;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			//constraints.weightx = 1.0;
			//Centrado (es la opcion por defecto de anchor)
			constraints.fill = GridBagConstraints.BOTH;
			constraints.anchor = GridBagConstraints.CENTER;
			izquierda.add(botonAvanzar_pais, constraints);
			//constraints.weightx = 0.0;
			constraints.fill = GridBagConstraints.NONE;
			
			indicadores = new JLabel("Indicadores: ");
	        constraints.gridx = 0;// El elemento esta en la columna 0
			constraints.gridy = 3;// El elemento esta en la fila 0
			constraints.gridwidth = 1;// Ocupa 1 celda horizontalmente
			constraints.gridheight = 1;// Ocupa 1 celda verticalmente
			// Aparecece pegado a la parte izquierda
			constraints.anchor = GridBagConstraints.WEST;
			izquierda.add(indicadores, constraints);
			// Posicion por defecto
			constraints.anchor = GridBagConstraints.CENTER;
	        
	        
	        modeloIndicadores = new ModeloIndicadores();
	        tablaIndicadores = new JTable(modeloIndicadores);
	        tablaIndicadores.setPreferredScrollableViewportSize(new Dimension(400, 100));
	        //Creatamos un contenedor para la Tabla
	        JScrollPane scrollPaneIndicadores = new JScrollPane(tablaIndicadores);
	        constraints.gridx = 0;// El elemento esta en la columna 0
			constraints.gridy = 4;// El elemento esta en la fila 1
			constraints.gridwidth = 4;// Ocupa 4 celda horizontalmente
			constraints.gridheight = 1;// Ocupa 1 celda verticalmente
			constraints.weighty = 1.0;// La fila se tiene que ampliar verticalmente
			constraints.weightx = 1.0;
			// Aparecece pegado a la parte izquierda
			constraints.anchor = GridBagConstraints.WEST;
			constraints.fill = GridBagConstraints.BOTH;
			izquierda.add(scrollPaneIndicadores, constraints);
			// constraints.weightx = 0.0;
			// Posicion por defecto
			constraints.anchor = GridBagConstraints.CENTER;
			
			botonRetroceder_ind = new JButton("Retroceder");
			botonRetroceder_ind.setName("retroceder_pag_indicadores");
			constraints.gridx = 1;
			constraints.gridy = 5;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			//constraints.weightx = 1.0;
			//Centrado (es la opcion por defecto de anchor)
			constraints.fill = GridBagConstraints.BOTH;
			constraints.anchor = GridBagConstraints.CENTER;
			izquierda.add(botonRetroceder_ind, constraints);
			//constraints.weightx = 0.0;
			constraints.fill = GridBagConstraints.NONE;
			
			paginaIndicadores = new JLabel("0 de 100");
			constraints.gridx = 2;// El elemento esta en la columna 2
			constraints.gridy = 5;// El elemento esta en la fila 2
			constraints.gridwidth = 1;// Ocupa 1 celda horizontalmente
			constraints.gridheight = 1;// Ocupa 1 celda verticalmente
			// Aparecece pegado a la parte izquierda
			constraints.anchor = GridBagConstraints.WEST;
			izquierda.add(paginaIndicadores, constraints);
			// constraints.weightx = 0.0;
			// Posicion por defecto
			constraints.anchor = GridBagConstraints.CENTER;
			
			botonAvanzar_ind = new JButton("Avanzar");
			botonAvanzar_ind.setName("avanzar_pag_indicadores");
			constraints.gridx = 3;
			constraints.gridy = 5;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			//constraints.weightx = 1.0;
			//Centrado (es la opcion por defecto de anchor)
			constraints.fill = GridBagConstraints.BOTH;
			constraints.anchor = GridBagConstraints.CENTER;
			izquierda.add(botonAvanzar_ind, constraints);
			//constraints.weightx = 0.0;
			constraints.fill = GridBagConstraints.NONE;
			
			
			resultado = new JLabel("Resultado: ");
		    constraints.gridx = 0;// El elemento esta en la columna 0
			constraints.gridy = 6;// El elemento esta en la fila 0
			constraints.gridwidth = 1;// Ocupa 1 celda horizontalmente
			constraints.gridheight = 1;// Ocupa 1 celda verticalmente
			// Aparecece pegado a la parte izquierda
			constraints.anchor = GridBagConstraints.WEST;
			izquierda.add(resultado, constraints);
			// Posicion por defecto
			constraints.anchor = GridBagConstraints.CENTER;
		        
		        
		    modeloResultado = new ModeloResultado();
		    tablaResultado = new JTable(modeloResultado);
		    tablaResultado.setPreferredScrollableViewportSize(new Dimension(400, 150));
		    //Creatamos un contenedor para la Tabla
		    JScrollPane scrollPaneResultado = new JScrollPane(tablaResultado);
		    constraints.gridx = 0;// El elemento esta en la columna 0
			constraints.gridy = 7;// El elemento esta en la fila 1
			constraints.gridwidth = 5;// Ocupa 5 celda horizontalmente
			constraints.gridheight = 1;// Ocupa 1 celda verticalmente
			constraints.weighty = 1.0;// La fila se tiene que ampliar verticalmente
			constraints.weightx = 1.0;
			// Aparecece pegado a la parte izquierda
			constraints.anchor = GridBagConstraints.WEST;
			constraints.fill = GridBagConstraints.BOTH;
			izquierda.add(scrollPaneResultado, constraints);
			// constraints.weightx = 0.0;
			// Posicion por defecto
			constraints.anchor = GridBagConstraints.CENTER;
			
			
						/******************************JPanel derecha******************************/
	        //Contiene lo relativo a la tabla de datos
	        JPanel derecha = new JPanel();
	        derecha.setLayout(new BorderLayout());
	        
	        CrearGrafica demo = new CrearGrafica("Representacion grafica", "datos eje X", "datos eje Y");
	        //demo.pack();
	        //RefineryUtilities.centerFrameOnScreen(demo);
	        //demo.setVisible(true);
	        derecha.add(demo, BorderLayout.CENTER);
	        
	  		
			
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			getContentPane().add(izquierda, BorderLayout.WEST);
			getContentPane().add(derecha, BorderLayout.EAST);
			//this.setContentPane(izquierda);
			//this.setSize(500, 600);
			this.setVisible(true);
			
			
			/************** Añadimos Listener a los botones *******************/
			botonRetroceder_pais.addActionListener(this);
			botonAvanzar_pais.addActionListener(this);
			botonRetroceder_ind.addActionListener(this);
			botonAvanzar_ind.addActionListener(this);

	 }	
	 		/************** Control de eventos *******************/
			@Override	
			public void actionPerformed(ActionEvent ev) {
				System.out.println("Entra en el actionPerformed");
				Object eventoLlamado = ev.getSource();
				String botonPulsado = ((JButton) eventoLlamado).getName();
				System.out.println("Evento getName= "+botonPulsado);
				
				if (botonPulsado == "avanzar_pag_paises") {
					String aux[] = {"Hola","caracola"};
					modeloPaises.addRow(aux);
				}else if(botonPulsado == "retroceder_pag_paises"){
					
				}else if(botonPulsado == "retroceder_pag_indicadores"){
					
				}else if(botonPulsado == "avanzar_pag_indicadores"){
					
				}
			
		
			
			
	    }
	 

	
	
	
	public static void main(String[] args) {
		Interfaz frame = new Interfaz();
        frame.pack();
        frame.setVisible(true);

	}




}
