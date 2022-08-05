package es.uned.master.java.healthworldbank.cliente;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import org.jfree.data.xy.XYSeries;

import es.uned.master.java.healthworldbank.datos.Estadistica;
import es.uned.master.java.healthworldbank.datos.Indicador;
import es.uned.master.java.healthworldbank.datos.Pais;
import es.uned.master.java.healthworldbank.modelosTabla.ModeloEstadistica;
import es.uned.master.java.healthworldbank.modelosTabla.ModeloIndicadores;
import es.uned.master.java.healthworldbank.modelosTabla.ModeloPaises;

// TODO: Auto-generated Javadoc
/**
 * Applet que se encarga de la parte visual e implementa los escuchadores de eventos.
 *
 * @author grupo alef (Hector Hernandez)
 * @author grupo alef (Marcos Bello)
 * @date 16/4/2012
 */

public class AppletWHB extends JApplet implements ActionListener, MouseListener{

	/** Constante version. */
	private static final long serialVersionUID = -1772762671161228750L;
	
	/** Etiqueta paises. */
	private JLabel paises;
	
	/** Etiqueta total paises. */
	private JLabel totalPaises;
	
	/** Etiqueta total de número de paises. */
	private JLabel totalNumPaises;
	
	/**Modelo de tabla paises. */
	private ModeloPaises modeloPaises;
	
	/** Tabla paises. */
	private JTable tablaPaises;
	
	/** Botón avanzar_pais. */
	private JButton botonAvanzar_pais;
	
	/** Botón retroceder_pais. */
	private JButton botonRetroceder_pais;
	
	/** Etiqueta pagina paises. */
	private JLabel paginaPaises;
	
	/** Etiqueta indicadores. */
	private JLabel indicadores;
	
	/** Etiqueta total indicadores. */
	private JLabel totalIndicadores;
	
	/** Etiqueta total numero indicadores. */
	private JLabel totalNumIndicadores;
	
	/** Modelo tabla indicadores. */
	private ModeloIndicadores modeloIndicadores;
	
	/** Tabla indicadores. */
	private JTable tablaIndicadores;
	
	/** Botón avanzar_ind. */
	private JButton botonAvanzar_ind;
	
	/** Botón retroceder_ind. */
	private JButton botonRetroceder_ind;
	
	/** Etiqueta pagina indicadores. */
	private JLabel paginaIndicadores;
	
	/** Etiqueta resultado. */
	private JLabel resultado;
	
	/** Etiqueta total resultados. */
	private JLabel totalResultados;
	
	/** Etiqueta total num resultados. */
	private JLabel totalNumResultados;
	
	/** Modelo tabla estadistica. */
	private ModeloEstadistica modeloEstadistica;
	
	/** Tabla resultado. */
	private JTable tablaResultado;
	
	/** Botón avanzar_res. */
	private JButton botonAvanzar_res;
	
	/** Botón retroceder_res. */
	private JButton botonRetroceder_res;
	
	/** Etiqueta pagina resultados. */
	private JLabel paginaResultados;
	
	/** The izquierda. */
	private JPanel izquierda;
	
	/** Instancia clase ClienteBD cliente. */
	private ClienteBD cliente;
	
	/** Instancia de grafica. */
	private CrearGrafica2 grafica;
	
	/** Entero registro pais actual. */
	private int registroPaisActual=0;
	
	/** Entero registro indicadores actual. */
	private int registroIndicadoresActual=0;
	
	/** Entero registro resultados actual. */
	private int registroResultadosActual=0;
	
	/** Entero total registros paises. */
	private int totalRegistrosPaises=0;
	
	/** Entero total registros indicadores. */
	private int totalRegistrosIndicadores=0;
	
	/** Entero total registros resultados. */
	private int totalRegistrosResultados=0;
	
	/** Entero total pag paises. */
	private int totalPagPaises=0;
	
	/** Entero total pag indicadores. */
	private int totalPagIndicadores=0;
	
	/** Entero total pag resultados. */
	private int totalPagResultados=0;
	
	/** Entero pag actual paises. */
	private int pagActualPaises=0;
	
	/** Entero pag actual indicadores. */
	private int pagActualIndicadores=0;
	
	/** Entero pag actual resultados. */
	private int pagActualResultados=0;
	
	/** Entero RPP límite de registros para la paginación consultas. */
	private static int RPP=20; //Numero de registros que pediremos y mostraremos en cada consulta
	
	/** Codigo de pais para la consulta de las estadisticas. */
	private String codPais="";
	
	/** Codigo de indicador para la consulta de las estadisticas. */
	private String codIndicador="";
	
	/* (non-Javadoc)
	 * @see java.applet.Applet#init()
	 */
	public void init(){
		super.init();
		cliente=new ClienteBD(this);
		inicializarInterfaz();
		inicializarListeners();
		
	}
	
	/**
	 * Iniciliza el Applet creando primero un panel "izquierda" con lo referente al tratamiento
	 * de las tablas de datos y despues crea otro elemento JPanel que contiene la grafica y lo
	 * situa en la parte derecha.
	 */	
	
	
	private void inicializarInterfaz(){
                
        /******************************JPanel izquierda******************************/
        //Contiene lo relativo a la tabla de datos
        izquierda = new JPanel();
        
        GridBagLayout g1 = new GridBagLayout();
        izquierda.setLayout(g1);
        GridBagConstraints constraintsDER = new GridBagConstraints();
        
        /***********PAISES**************/
        
        paises = new JLabel("Paises: ");
        constraintsDER.gridx = 0;// El elemento esta en la columna 0
		constraintsDER.gridy = 0;// El elemento esta en la fila 0
		constraintsDER.gridwidth = 1;// Ocupa 1 celda horizontalmente
		constraintsDER.gridheight = 1;// Ocupa 1 celda verticalmente
		// Aparecece pegado a la parte izquierda
		constraintsDER.anchor = GridBagConstraints.WEST;
		/* Hacemos un padding en todos los componentes (al no resetearlo se
	 	   creaian todos los elementos con este padding)*/
		constraintsDER.insets = new Insets(5, 5, 5, 5);
		izquierda.add(paises, constraintsDER);
		// Posicion por defecto
		constraintsDER.anchor = GridBagConstraints.CENTER;	
        
        totalPaises = new JLabel("Total registros: ");
        constraintsDER.gridx = 2;
		constraintsDER.gridy = 0;
		constraintsDER.gridwidth = 1;
		constraintsDER.gridheight = 1;
		constraintsDER.anchor = GridBagConstraints.WEST;
		izquierda.add(totalPaises, constraintsDER);
		constraintsDER.anchor = GridBagConstraints.CENTER;	
		
		totalNumPaises = new JLabel();
		totalNumPaises.setOpaque(true);
		totalNumPaises.setBackground(Color.lightGray);
        constraintsDER.gridx = 3;
		constraintsDER.gridy = 0;
		constraintsDER.gridwidth = 1;
		constraintsDER.gridheight = 1;
		constraintsDER.anchor = GridBagConstraints.EAST;
		izquierda.add(totalNumPaises, constraintsDER);
		constraintsDER.anchor = GridBagConstraints.CENTER;	
		
        
        modeloPaises = new ModeloPaises();
        tablaPaises = new JTable(modeloPaises);
        tablaPaises.setPreferredScrollableViewportSize(new Dimension(400, 100));
        //Creamos un contenedor para la Tabla
        JScrollPane scrollPanePaises = new JScrollPane(tablaPaises);
        constraintsDER.gridx = 0;
		constraintsDER.gridy = 1;
		constraintsDER.gridwidth = 5;
		constraintsDER.gridheight = 1;
		constraintsDER.weightx = 1.0;
		constraintsDER.anchor = GridBagConstraints.WEST;
		constraintsDER.fill = GridBagConstraints.BOTH;
		izquierda.add(scrollPanePaises, constraintsDER);
		constraintsDER.anchor = GridBagConstraints.CENTER;
		
		botonRetroceder_pais = new JButton("Retroceder");
		botonRetroceder_pais.setName("retroceder_pag_paises");
		constraintsDER.gridx = 1;
		constraintsDER.gridy = 2;
		constraintsDER.gridwidth = 1;
		constraintsDER.gridheight = 1;
		constraintsDER.fill = GridBagConstraints.BOTH;
		constraintsDER.anchor = GridBagConstraints.CENTER;
		izquierda.add(botonRetroceder_pais, constraintsDER);
		constraintsDER.fill = GridBagConstraints.NONE;
		
		paginaPaises = new JLabel();
		constraintsDER.gridx = 2;
		constraintsDER.gridy = 2;
		constraintsDER.gridwidth = 1;
		constraintsDER.gridheight = 1;
		constraintsDER.anchor = GridBagConstraints.CENTER;
		izquierda.add(paginaPaises, constraintsDER);
		constraintsDER.anchor = GridBagConstraints.CENTER;
		
		botonAvanzar_pais = new JButton("Avanzar");
		botonAvanzar_pais.setName("avanzar_pag_paises");
		constraintsDER.gridx = 3;
		constraintsDER.gridy = 2;
		constraintsDER.gridwidth = 1;
		constraintsDER.gridheight = 1;
		constraintsDER.fill = GridBagConstraints.BOTH;
		constraintsDER.anchor = GridBagConstraints.CENTER;
		izquierda.add(botonAvanzar_pais, constraintsDER);
		constraintsDER.fill = GridBagConstraints.NONE;
		
		/***************INDICADORES***************/
		
		indicadores = new JLabel("Indicadores: ");
        constraintsDER.gridx = 0;
		constraintsDER.gridy = 3;
		constraintsDER.gridwidth = 1;
		constraintsDER.gridheight = 1;
		constraintsDER.anchor = GridBagConstraints.WEST;
		izquierda.add(indicadores, constraintsDER);
		constraintsDER.anchor = GridBagConstraints.CENTER;
        
		totalIndicadores = new JLabel("Total registros: ");
        constraintsDER.gridx = 2;
		constraintsDER.gridy = 3;
		constraintsDER.gridwidth = 1;
		constraintsDER.gridheight = 1;
		constraintsDER.anchor = GridBagConstraints.WEST;
		izquierda.add(totalIndicadores, constraintsDER);
		constraintsDER.anchor = GridBagConstraints.CENTER;
		
		totalNumIndicadores = new JLabel();
		totalNumIndicadores.setOpaque(true);
		totalNumIndicadores.setBackground(Color.lightGray);
        constraintsDER.gridx = 3;
		constraintsDER.gridy = 3;
		constraintsDER.gridwidth = 1;
		constraintsDER.gridheight = 1;
		constraintsDER.anchor = GridBagConstraints.EAST;
		izquierda.add(totalNumIndicadores, constraintsDER);
		constraintsDER.anchor = GridBagConstraints.CENTER;	
		
        modeloIndicadores = new ModeloIndicadores();
        tablaIndicadores = new JTable(modeloIndicadores);
        tablaIndicadores.setPreferredScrollableViewportSize(new Dimension(400, 100));
        JScrollPane scrollPaneIndicadores = new JScrollPane(tablaIndicadores);
        constraintsDER.gridx = 0;
		constraintsDER.gridy = 4;
		constraintsDER.gridwidth = 5;
		constraintsDER.gridheight = 1;
		constraintsDER.weightx = 1.0;
		constraintsDER.anchor = GridBagConstraints.WEST;
		constraintsDER.fill = GridBagConstraints.BOTH;
		izquierda.add(scrollPaneIndicadores, constraintsDER);
		constraintsDER.anchor = GridBagConstraints.CENTER;
		
		botonRetroceder_ind = new JButton("Retroceder");
		botonRetroceder_ind.setName("retroceder_pag_indicadores");
		constraintsDER.gridx = 1;
		constraintsDER.gridy = 5;
		constraintsDER.gridwidth = 1;
		constraintsDER.gridheight = 1;
		constraintsDER.fill = GridBagConstraints.BOTH;
		constraintsDER.anchor = GridBagConstraints.CENTER;
		izquierda.add(botonRetroceder_ind, constraintsDER);
		constraintsDER.fill = GridBagConstraints.NONE;
		
		paginaIndicadores = new JLabel();
		constraintsDER.gridx = 2;
		constraintsDER.gridy = 5;
		constraintsDER.gridwidth = 1;
		constraintsDER.gridheight = 1;
		constraintsDER.anchor = GridBagConstraints.CENTER;
		izquierda.add(paginaIndicadores, constraintsDER);
		constraintsDER.anchor = GridBagConstraints.CENTER;
		
		botonAvanzar_ind = new JButton("Avanzar");
		botonAvanzar_ind.setName("avanzar_pag_indicadores");
		constraintsDER.gridx = 3;
		constraintsDER.gridy = 5;
		constraintsDER.gridwidth = 1;
		constraintsDER.gridheight = 1;
		constraintsDER.fill = GridBagConstraints.BOTH;
		constraintsDER.anchor = GridBagConstraints.CENTER;
		izquierda.add(botonAvanzar_ind, constraintsDER);
		constraintsDER.fill = GridBagConstraints.NONE;
		
		/**********************RESULTADO*************************/
		
		resultado = new JLabel("Resultado: ");
	    constraintsDER.gridx = 0;
		constraintsDER.gridy = 6;
		constraintsDER.gridwidth = 1;
		constraintsDER.gridheight = 1;
		constraintsDER.anchor = GridBagConstraints.WEST;
		izquierda.add(resultado, constraintsDER);
		constraintsDER.anchor = GridBagConstraints.CENTER;
		
		totalResultados = new JLabel("Total registros: ");
        constraintsDER.gridx = 2;
		constraintsDER.gridy = 6;
		constraintsDER.gridwidth = 1;
		constraintsDER.gridheight = 1;
		constraintsDER.anchor = GridBagConstraints.WEST;
		izquierda.add(totalResultados, constraintsDER);
		constraintsDER.anchor = GridBagConstraints.CENTER;
		
		totalNumResultados = new JLabel();
		totalNumResultados.setOpaque(true);
		totalNumResultados.setBackground(Color.lightGray);
        constraintsDER.gridx = 3;
		constraintsDER.gridy = 6;
		constraintsDER.gridwidth = 1;
		constraintsDER.gridheight = 1;
		constraintsDER.anchor = GridBagConstraints.EAST;
		izquierda.add(totalNumResultados, constraintsDER);
		constraintsDER.anchor = GridBagConstraints.CENTER;	        
	        
	    modeloEstadistica = new ModeloEstadistica();
	    tablaResultado = new JTable(modeloEstadistica);
	    tablaResultado.setPreferredScrollableViewportSize(new Dimension(400, 100));
	    JScrollPane scrollPaneResultado = new JScrollPane(tablaResultado);
	    constraintsDER.gridx = 0;
		constraintsDER.gridy = 7;
		constraintsDER.gridwidth = 5;
		constraintsDER.gridheight = 1;
		constraintsDER.weightx = 1.0;
		constraintsDER.anchor = GridBagConstraints.WEST;
		constraintsDER.fill = GridBagConstraints.BOTH;
		izquierda.add(scrollPaneResultado, constraintsDER);
		constraintsDER.anchor = GridBagConstraints.CENTER;
		
		botonRetroceder_res = new JButton("Retroceder");
		botonRetroceder_res.setName("retroceder_pag_resultados");
		constraintsDER.gridx = 1;
		constraintsDER.gridy = 8;
		constraintsDER.gridwidth = 1;
		constraintsDER.gridheight = 1;
		constraintsDER.fill = GridBagConstraints.BOTH;
		constraintsDER.anchor = GridBagConstraints.CENTER;
		izquierda.add(botonRetroceder_res, constraintsDER);
		constraintsDER.fill = GridBagConstraints.NONE;
		
		paginaResultados = new JLabel();
		constraintsDER.gridx = 2;
		constraintsDER.gridy = 8;
		constraintsDER.gridwidth = 1;
		constraintsDER.gridheight = 1;
		constraintsDER.anchor = GridBagConstraints.CENTER;
		izquierda.add(paginaResultados, constraintsDER);
		constraintsDER.anchor = GridBagConstraints.CENTER;
		
		botonAvanzar_res = new JButton("Avanzar");
		botonAvanzar_res.setName("avanzar_pag_resultados");
		constraintsDER.gridx = 3;
		constraintsDER.gridy = 8;
		constraintsDER.gridwidth = 1;
		constraintsDER.gridheight = 1;
		constraintsDER.fill = GridBagConstraints.BOTH;
		constraintsDER.anchor = GridBagConstraints.CENTER;
		izquierda.add(botonAvanzar_res, constraintsDER);
		constraintsDER.fill = GridBagConstraints.NONE;
		
		
		/******************************JPanel grafica******************************/
        
        
        grafica = new CrearGrafica2();
        
		/*************************A�adir paneles al Applet*************************/

        add(izquierda, BorderLayout.WEST);
		add(grafica, BorderLayout.EAST);
        
 		setSize(900,600 );
		this.setVisible(true);
		
		/************************Inicializar total registros**********************/
		cliente.obtenerNumPaises();
		cliente.obtenerNumIndicadores();
		//cliente.obtenerNumResultados();
		
		totalRegistrosPaises=Integer.valueOf(totalNumPaises.getText());
		totalRegistrosIndicadores=Integer.valueOf(totalNumIndicadores.getText());
		//totalRegistrosResultados=Integer.valueOf(totalNumResultados.getText());
		
		initPagPaises();
		initPagIndicadores();
		//initPagResultados();

	}
	
	/**
	 * A�adimos escuchadores de eventos a los botones y a las dos tablas.
	 */	
	
	public void inicializarListeners(){
		/*********************** Anadimos Listener a los botones **********************/
		botonRetroceder_pais.addActionListener(this);
		botonAvanzar_pais.addActionListener(this);
		botonRetroceder_ind.addActionListener(this);
		botonAvanzar_ind.addActionListener(this);
		botonRetroceder_res.addActionListener(this);
		botonAvanzar_res.addActionListener(this);
		
		/*********************** Anadimos Listener a las tablas ***********************/
		tablaPaises.addMouseListener(this);
		tablaIndicadores.addMouseListener(this);
		
	}
	
	/**
	 * ************************** Control de eventos *********************************.
	 *
	 * @param ev the ev
	 */
	@Override	
	public void actionPerformed(ActionEvent ev) {
		System.out.println("Entra en el actionPerformed");
		Object eventoLlamado = ev.getSource();
		String botonPulsado = ((JButton) eventoLlamado).getName();
		System.out.println("Evento getName= "+botonPulsado);
		
		if (botonPulsado == "avanzar_pag_paises") {
			//Si no estamos al final y el boton estaba deshabilitado, lo habilitamos
			if((!botonRetroceder_pais.isEnabled())){
				botonRetroceder_pais.setEnabled(true);
				}
			if(registroPaisActual<=totalRegistrosPaises-RPP){
			modeloPaises.removeAllRows();
			System.out.println("registroPaisActual+RPP: "+(registroPaisActual+RPP)+" registroPaisActual: "+registroPaisActual);
			cliente.obtenerPaises(registroPaisActual+RPP, registroPaisActual);
			registroPaisActual+=RPP;
			setNumPagPaises(++pagActualPaises,totalPagPaises);
			}
			else if(registroPaisActual<totalRegistrosPaises){//Caso en el que falten menos de RPP registros por mostrar
				modeloPaises.removeAllRows();
				System.out.println("totalRegistrosPaises: "+totalRegistrosPaises+" registroPaisActual: "+registroPaisActual);
				cliente.obtenerPaises(totalRegistrosPaises, registroPaisActual);
				registroPaisActual=totalRegistrosPaises;
				setNumPagPaises(++pagActualPaises,totalPagPaises);
			}
			//Si llegamos al final se desactiva el boton
			if(pagActualPaises==totalPagPaises){
				botonAvanzar_pais.setEnabled(false);
			}
		}else if(botonPulsado == "retroceder_pag_paises"){
			//Si no estamos al final y el boton estaba deshabilitado, lo habilitamos
			if((!botonAvanzar_pais.isEnabled())){
				botonAvanzar_pais.setEnabled(true);
				}
			if(pagActualPaises>1){
				if(registroPaisActual>=RPP){
					modeloPaises.removeAllRows();
					int aux1=registroPaisActual-RPP;
					int aux2=registroPaisActual-2*RPP;
					System.out.println("registroPaisActual-RPP: "+aux1+" registroPaisActual-2*RPP: "+aux2);
					cliente.obtenerPaises(registroPaisActual-RPP, registroPaisActual-2*RPP);
					registroPaisActual-=RPP;
					setNumPagPaises(--pagActualPaises,totalPagPaises);
				}else if(registroPaisActual<=RPP&&registroPaisActual>0){//PAra registros menores que RPP
					modeloPaises.removeAllRows();
					System.out.println("registroPaisActual: "+registroPaisActual+" primerRegistro: "+0);
					cliente.obtenerPaises(registroPaisActual, 0);
					registroPaisActual=0;
					setNumPagPaises(--pagActualPaises,totalPagPaises);
				}
			}
			//Si estamos al principio deshabilitamos el boton
			if(pagActualPaises==1){
				botonRetroceder_pais.setEnabled(false);
			}
		}else if(botonPulsado == "avanzar_pag_indicadores"){
			//Si no estamos al final y el boton estaba deshabilitado, lo habilitamos
			if((!botonRetroceder_ind.isEnabled())){
				botonRetroceder_ind.setEnabled(true);
				}
			if(registroIndicadoresActual<=totalRegistrosIndicadores-RPP){
				modeloIndicadores.removeAllRows();
				System.out.println("registroIndicadoresActual+RPP: "+(registroIndicadoresActual+RPP)+" registroIndicadoresActual: "+registroIndicadoresActual);
				cliente.obtenerIndicadores(registroIndicadoresActual+RPP, registroIndicadoresActual);
				registroIndicadoresActual+=RPP;
				setNumPagIndicadores(++pagActualIndicadores,totalPagIndicadores);
				}
				else if(registroIndicadoresActual<totalRegistrosIndicadores){//Caso en el que falten menos de RPP registros por mostrar
					modeloIndicadores.removeAllRows();
					System.out.println("totalRegistrosIndicadores: "+totalRegistrosIndicadores+" registroPaisIndicadores: "+registroIndicadoresActual);
					cliente.obtenerIndicadores(totalRegistrosIndicadores, registroIndicadoresActual);
					registroIndicadoresActual=totalRegistrosIndicadores;
					setNumPagIndicadores(++pagActualIndicadores,totalPagIndicadores);
				}
			//Si llegamos al final se desactiva el boton
			if(pagActualIndicadores==totalPagIndicadores){
				botonAvanzar_ind.setEnabled(false);
			}
		}else if(botonPulsado == "retroceder_pag_indicadores"){
			//Si no estamos al final y el boton estaba deshabilitado, lo habilitamos
			if((!botonAvanzar_ind.isEnabled())){
				botonAvanzar_ind.setEnabled(true);
				}
			if(pagActualIndicadores>1){
				if(registroIndicadoresActual>=RPP){
					modeloIndicadores.removeAllRows();
					int aux1=registroIndicadoresActual-RPP;
					int aux2=registroIndicadoresActual-2*RPP;
					System.out.println("registroIndicadoresActual-RPP: "+aux1+" registroIndicadoresActual-2*RPP: "+aux2);
					cliente.obtenerIndicadores(registroIndicadoresActual-RPP, registroIndicadoresActual-2*RPP);
					registroIndicadoresActual-=RPP;
					setNumPagIndicadores(--pagActualIndicadores,totalPagIndicadores);
				}else if(registroIndicadoresActual<=RPP&&registroIndicadoresActual>0){//PAra registros menores que RPP
					modeloIndicadores.removeAllRows();
					System.out.println("registroPaisIndicadores: "+registroIndicadoresActual+" primerRegistro: "+0);
					cliente.obtenerIndicadores(registroIndicadoresActual, 0);
					registroIndicadoresActual=0;
					setNumPagIndicadores(--pagActualIndicadores,totalPagIndicadores);
				}			
			}
			//Si estamos al principio deshabilitamos el boton
			if(pagActualIndicadores==1){
				botonRetroceder_ind.setEnabled(false);
			}
		}else if(botonPulsado == "avanzar_pag_resultados"){
			//Si no estamos al final y el boton estaba deshabilitado, lo habilitamos
			if((!botonRetroceder_res.isEnabled())){
				botonRetroceder_res.setEnabled(true);
				}
			if(registroResultadosActual<=totalRegistrosResultados-RPP){
				modeloEstadistica.removeAllRows();
				System.out.println("registroResultadosActual+RPP: "+(registroResultadosActual+RPP)+" registroResultadosActual: "+registroResultadosActual);
				cliente.obtenerEstadisticas(codPais, codIndicador, registroResultadosActual+RPP, registroResultadosActual);
				registroResultadosActual+=RPP;
				setNumPagResultados(++pagActualResultados,totalPagResultados);
				}
				else if(registroResultadosActual<totalRegistrosResultados){//Caso en el que falten menos de RPP registros por mostrar
					modeloEstadistica.removeAllRows();
					System.out.println("totalRegistrosResultados: "+totalRegistrosResultados+" registroResultadosActual: "+registroResultadosActual);
					cliente.obtenerEstadisticas(codPais, codIndicador, registroResultadosActual+RPP, registroResultadosActual);
					registroResultadosActual=totalRegistrosResultados;
					setNumPagResultados(++pagActualResultados,totalPagResultados);
				}
			//Si llegamos al final se desactiva el boton
			if(pagActualResultados==totalPagResultados){
				botonAvanzar_res.setEnabled(false);
			}
		}else if(botonPulsado == "retroceder_pag_resultados"){
			//Si no estamos al final y el boton estaba deshabilitado, lo habilitamos
			if((!botonAvanzar_res.isEnabled())){
				botonAvanzar_res.setEnabled(true);
				}
			if(pagActualResultados>1){
				if(registroResultadosActual>=RPP){
					modeloEstadistica.removeAllRows();
					int aux1=registroResultadosActual-RPP;
					int aux2=registroResultadosActual-2*RPP;
					System.out.println("registroResultadosActual-RPP: "+aux1+" registroResultadosActual-2*RPP: "+aux2);
					cliente.obtenerEstadisticas(codPais, codIndicador, registroResultadosActual-RPP, registroResultadosActual-2*RPP);
					registroResultadosActual-=RPP;
					setNumPagResultados(--pagActualResultados,totalPagResultados);
				}else if(registroResultadosActual<=RPP&&registroResultadosActual>0){//PAra registros menores que RPP
					modeloEstadistica.removeAllRows();
					System.out.println("registroResultadosActual: "+registroResultadosActual+" primerRegistro: "+0);
					cliente.obtenerEstadisticas(codPais, codIndicador, registroResultadosActual, 0);
					registroResultadosActual=0;
					setNumPagResultados(--pagActualResultados,pagActualResultados);
				}			
			}
			//Si estamos al principio deshabilitamos el boton
			if(pagActualResultados==1){
				botonRetroceder_res.setEnabled(false);
			}
		}
}

	/**
	 * Sobreescribimos el metodo llamado llamado tras pulsar con el raton sobre una de las dos tablas
	 * Este metodo se encarga de comprobar si ya se ha pulsado sobre alguna celda de ambas columnas,
	 * en caso afirmativo procedemos a completar la tabla de las estadisticas y a dibujar la grafica.
	 *
	 * @param ev the ev
	 */	

	@Override
	public void mouseClicked(MouseEvent ev) {
		int filaPaises = tablaPaises.getSelectedRow();
		int filaIndicadores = tablaIndicadores.getSelectedRow();		
		//Comprobamos si se ha seleccionado alguna columna de cada tabla, si el valor devuelto es -1 es porque aun no hay ninguna fila seleccionada
		if(filaPaises!=-1 && filaIndicadores!=-1){
			//Caso afirmativo, actualizamos el valor de la grafica
			codPais=(String) tablaPaises.getValueAt(filaPaises, 0);
			codIndicador=(String) tablaIndicadores.getValueAt(filaIndicadores, 0);
			System.out.println("codPais: "+codPais);
			System.out.println("codPais: "+codIndicador);			
			cliente.obtenerNumResultados(codPais, codIndicador);
			//Una vez obtenida la consulta mostramos los resultados			
			//cliente.obtenerNumResultados();
			totalRegistrosResultados=Integer.valueOf(totalNumResultados.getText());
			//Si no hay registros ponemos a 0 el total de paginas y ademas vaciamos la grafica y la tabla
			if (totalRegistrosResultados==0){
				grafica.vaciarTabla();
				modeloEstadistica.removeAllRows();
				totalPagResultados=0;
			}
			pagActualResultados=0;
			registroResultadosActual=0;
			initPagResultados();			
			setNumPagResultados(pagActualResultados,totalPagResultados);
			//Hacemos avanzar una vez
			if(registroResultadosActual<=totalRegistrosResultados-RPP){
				modeloEstadistica.removeAllRows();
				System.out.println("registroResultadosActual+RPP: "+(registroResultadosActual+RPP)+" registroResultadosActual: "+registroResultadosActual);
				cliente.obtenerEstadisticas(codPais, codIndicador, registroResultadosActual+RPP, registroResultadosActual);
				registroResultadosActual+=RPP;
				setNumPagResultados(++pagActualResultados,totalPagResultados);
				}
				else if(registroResultadosActual<totalRegistrosResultados){//Caso en el que falten menos de RPP registros por mostrar
					modeloEstadistica.removeAllRows();
					System.out.println("totalRegistrosResultados: "+totalRegistrosResultados+" registroResultadosActual: "+registroResultadosActual);
					cliente.obtenerEstadisticas(codPais, codIndicador, registroResultadosActual+RPP, registroResultadosActual);
					registroResultadosActual=totalRegistrosResultados;
					setNumPagResultados(++pagActualResultados,totalPagResultados);
				}
				
			System.out.println("Columna selecionada Indicadores: "+filaIndicadores);
			System.out.println("Columna selecionada Paises: "+filaPaises);
		}
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}
	
	/**
	 * Metodo que dibuja en la grafica la sere XY de datos que se le pase como argumento.
	 *
	 * @param datos the datos
	 */	
	public void cambiarDatosGrafica(XYSeries datos){
		grafica.modificarValores(datos);
	}
	
	/**
	 * Metodo que a�ade un arrayList de Paises y su correspondiente filtro a su tabla correspondiente.
	 *
	 * @param paises the paises
	 * @param filtro the filtro
	 */	
	public void setPaises(ArrayList<Pais> paises, String filtro){
		modeloPaises.setFiltro(filtro);
		modeloPaises.addAllRows(paises);
	}
	
	/**
	 * Metodo que devuelve el valor del filtro de la tabla Paises.
	 *
	 * @return the filtro paises
	 */	
	public String getFiltroPaises(){
		return modeloPaises.getFiltro();
	}
	
	/**
	 * Metodo que a�ade un arrayList de Indicadores y su correspondiente filtro a su tabla correspondiente.
	 *
	 * @param indicadores the indicadores
	 * @param filtro the filtro
	 */	
	public void setIndicadores(ArrayList<Indicador> indicadores, String filtro){
		modeloIndicadores.setFiltro(filtro);
		modeloIndicadores.addAllRows(indicadores);
	}
	
	/**
	 * Metodo que devuelve el valor del filtro de la tabla Indicadores.
	 *
	 * @return the filtro indicadores
	 */	
	public String getFiltroIndicadores(){
		return modeloIndicadores.getFiltro();
	}
	
	/**
	 * Metodo que a�ade un arrayList de Estadisticas y su correspondiente filtro a su tabla correspondiente.
	 *
	 * @param estadisticas the estadisticas
	 * @param filtro the filtro
	 */	
	public void setEstadisticas(ArrayList<Estadistica> estadisticas, String filtro){
		modeloEstadistica.setFiltro(filtro);
		modeloEstadistica.addAllRows(estadisticas);
	}
	
	/**
	 * Metodo que devuelve el valor del filtro de la tabla Estadisticas.
	 *
	 * @return the filtro estadisticas
	 */	
	public String getFiltroEstadisticas(){
		return modeloEstadistica.getFiltro();
	}
	
	/**
	 * Metodo que cambia el valor mostrado en el JLabel "totalNumPaises" que contiene el total de registros Paises.
	 *
	 * @param numPaises the new num paises
	 */	
	public void setNumPaises(int numPaises){
		totalNumPaises.setText(Integer.toString(numPaises));
	}
	
	/**
	 * Metodo que cambia el valor mostrado en el JLabel "totalNumIntdicadores" que contiene el total de registros Indicadores.
	 *
	 * @param numIndicadores the new num indicadores
	 */	
	public void setNumIndicadores(int numIndicadores){
		totalNumIndicadores.setText(Integer.toString(numIndicadores));
	}
	
	/**
	 * Metodo que cambia el valor mostrado en el JLabel "totalNumResultados" que contiene el total de registros Resultados.
	 *
	 * @param numResultados the new num resultados
	 */	
	public void setNumResultados(int numResultados){
		System.out.print("Ponemos el número total de registros de resultados.");
		totalNumResultados.setText(Integer.toString(numResultados));
	}
	
	/**
	 * Metodo que cambia el valor de la paginacion de la tabla Paises.
	 *
	 * @param actual the actual
	 * @param total the total
	 */	
	public void setNumPagPaises(int actual, int total){
		paginaPaises.setText(Integer.toString(actual)+" de "+Integer.toString(total));
	}
	
	/**
	 * Metodo que cambia el valor de la paginacion de la tabla Indicadores.
	 *
	 * @param actual the actual
	 * @param total the total
	 */
	public void setNumPagIndicadores(int actual, int total){
		paginaIndicadores.setText(Integer.toString(actual)+" de "+Integer.toString(total));
	}
	
	/**
	 * Metodo que cambia el valor de la paginacion de la tabla Resultados.
	 *
	 * @param actual the actual
	 * @param total the total
	 */
	public void setNumPagResultados(int actual, int total){
		paginaResultados.setText(Integer.toString(actual)+" de "+Integer.toString(total));
	}
	
	/**
	 * Metodo que inicializa las paginas de la tabla Paises.
	 */
	public void initPagPaises(){
		totalPagPaises=totalRegistrosPaises/RPP;
		if(totalRegistrosPaises%RPP!=0){
			totalPagPaises++;
		}
		setNumPagPaises(pagActualPaises,totalPagPaises);
		//Avanzamos una vez para dibujar valores de la primera página
		if(registroPaisActual<=totalRegistrosPaises-RPP){
			modeloPaises.removeAllRows();
			System.out.println("registroPaisActual+RPP: "+(registroPaisActual+RPP)+" registroPaisActual: "+registroPaisActual);
			cliente.obtenerPaises(registroPaisActual+RPP, registroPaisActual);
			registroPaisActual+=RPP;
			setNumPagPaises(++pagActualPaises,totalPagPaises);
			}
			else if(registroPaisActual<totalRegistrosPaises){//Caso en el que falten menos de RPP registros por mostrar
				modeloPaises.removeAllRows();
				System.out.println("totalRegistrosPaises: "+totalRegistrosPaises+" registroPaisActual: "+registroPaisActual);
				cliente.obtenerPaises(totalRegistrosPaises, registroPaisActual);
				registroPaisActual=totalRegistrosPaises;
				setNumPagPaises(++pagActualPaises,totalPagPaises);
			}
	}
	
	/**
	 * Metodo que inicializa las paginas de la tabla Indicadores.
	 */
	public void initPagIndicadores(){
		totalPagIndicadores=totalRegistrosIndicadores/RPP;
		if(totalRegistrosIndicadores%RPP!=0){
			totalPagIndicadores++;
		}
		setNumPagIndicadores(pagActualIndicadores,totalPagIndicadores);
		//Avanzamos una vez para dibujar valores de la primera página
		if(registroIndicadoresActual<=totalRegistrosIndicadores-RPP){
			modeloIndicadores.removeAllRows();
			System.out.println("registroIndicadoresActual+RPP: "+(registroIndicadoresActual+RPP)+" registroIndicadoresActual: "+registroIndicadoresActual);
			cliente.obtenerIndicadores(registroIndicadoresActual+RPP, registroIndicadoresActual);
			registroIndicadoresActual+=RPP;
			setNumPagIndicadores(++pagActualIndicadores,totalPagIndicadores);
			}
			else if(registroIndicadoresActual<totalRegistrosIndicadores){//Caso en el que falten menos de RPP registros por mostrar
				modeloIndicadores.removeAllRows();
				System.out.println("totalRegistrosIndicadores: "+totalRegistrosIndicadores+" registroPaisIndicadores: "+registroIndicadoresActual);
				cliente.obtenerIndicadores(totalRegistrosIndicadores, registroIndicadoresActual);
				registroIndicadoresActual=totalRegistrosIndicadores;
				setNumPagIndicadores(++pagActualIndicadores,totalPagIndicadores);
			}
	}
	
	/**
	 * Metodo que inicializa las paginas de la tabla Resultados.
	 */
	public void initPagResultados(){
		if (totalRegistrosResultados>RPP){
			totalPagResultados=totalRegistrosResultados/RPP;
		}else if (totalRegistrosResultados==0){
			totalPagResultados=0;
		}else{
			totalPagResultados=1;
		}
		if((totalRegistrosResultados>RPP)&&(totalRegistrosResultados%RPP!=0)){
			totalPagResultados++;
		}
		setNumPagResultados(pagActualResultados,totalPagResultados);
	}
	
	/**
	 * Mostrar error.
	 *
	 * @param error the error
	 * @param mensaje the mensaje
	 */
	public void mostrarError(String error, String mensaje){
		JOptionPane.showMessageDialog(null,  mensaje+"\n"+error, "Alerta",JOptionPane.ERROR_MESSAGE);  	  
	}
}
