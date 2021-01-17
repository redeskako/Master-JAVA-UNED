package es.uned.master.java.worldhealthbank.cliente;
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
import java.util.Properties;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.jfree.data.xy.XYSeries;


public class AppletWHB extends JApplet implements ActionListener, MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel paises;
	private JLabel totalPaises;
	private JLabel totalNumPaises;
	private ModeloPaises modeloPaises;
	private JTable tablaPaises;
	private JButton botonAvanzar_pais;
	private JButton botonRetroceder_pais;
	private JLabel paginaPaises;
	
	private JLabel indicadores;
	private JLabel totalIndicadores;
	private JLabel totalNumIndicadores;
	private ModeloIndicadores modeloIndicadores;
	private JTable tablaIndicadores;
	private JButton botonAvanzar_ind;
	private JButton botonRetroceder_ind;
	private JLabel paginaIndicadores;
	
	private JLabel resultado;
	private ModeloEstadistica modeloEstadistica;
	private JTable tablaResultado;
	
	private JPanel izquierda;
	private ClienteBD cliente;
	private CrearGrafica2 grafica;
	
	private Properties configuracion;
	
	public void init(){
		super.init();
		cliente=new ClienteBD(this);
		inicializarInterfaz();
		inicializarListeners();
		
	}
	
	
	
	private void inicializarInterfaz(){
                
        /******************************JPanel izquierda******************************/
        //Contiene lo relativo a la tabla de datos
        izquierda = new JPanel();
        
        GridBagLayout g1 = new GridBagLayout();
        izquierda.setLayout(g1);
        GridBagConstraints constraintsDER = new GridBagConstraints();
        
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
        constraintsDER.gridx = 2;// El elemento esta en la columna 0
		constraintsDER.gridy = 0;// El elemento esta en la fila 0
		constraintsDER.gridwidth = 1;// Ocupa 1 celda horizontalmente
		constraintsDER.gridheight = 1;// Ocupa 1 celda verticalmente
		constraintsDER.anchor = GridBagConstraints.WEST;
		izquierda.add(totalPaises, constraintsDER);
		constraintsDER.anchor = GridBagConstraints.CENTER;	
		
		totalNumPaises = new JLabel();
		totalNumPaises.setOpaque(true);
		totalNumPaises.setBackground(Color.lightGray);
        constraintsDER.gridx = 3;// El elemento esta en la columna 0
		constraintsDER.gridy = 0;// El elemento esta en la fila 0
		constraintsDER.gridwidth = 1;// Ocupa 1 celda horizontalmente
		constraintsDER.gridheight = 1;// Ocupa 1 celda verticalmente
		constraintsDER.anchor = GridBagConstraints.EAST;
		izquierda.add(totalNumPaises, constraintsDER);
		constraintsDER.anchor = GridBagConstraints.CENTER;	
		
        
        modeloPaises = new ModeloPaises();
        tablaPaises = new JTable(modeloPaises);
        tablaPaises.setPreferredScrollableViewportSize(new Dimension(400, 100));
        //Creamos un contenedor para la Tabla
        JScrollPane scrollPanePaises = new JScrollPane(tablaPaises);
        constraintsDER.gridx = 0;// El elemento esta en la columna 0
		constraintsDER.gridy = 1;// El elemento esta en la fila 1
		constraintsDER.gridwidth = 5;// Ocupa 5 celda horizontalmente
		constraintsDER.gridheight = 1;// Ocupa 1 celda verticalmente
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
		constraintsDER.gridx = 2;// El elemento esta en la columna 2
		constraintsDER.gridy = 2;// El elemento esta en la fila 2
		constraintsDER.gridwidth = 1;// Ocupa 1 celda horizontalmente
		constraintsDER.gridheight = 1;// Ocupa 1 celda verticalmente
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
		
		indicadores = new JLabel("Indicadores: ");
        constraintsDER.gridx = 0;// El elemento esta en la columna 0
		constraintsDER.gridy = 3;// El elemento esta en la fila 0
		constraintsDER.gridwidth = 1;// Ocupa 1 celda horizontalmente
		constraintsDER.gridheight = 1;// Ocupa 1 celda verticalmente
		constraintsDER.anchor = GridBagConstraints.WEST;
		izquierda.add(indicadores, constraintsDER);
		constraintsDER.anchor = GridBagConstraints.CENTER;
        
		totalIndicadores = new JLabel("Total registros: ");
        constraintsDER.gridx = 2;// El elemento esta en la columna 0
		constraintsDER.gridy = 3;// El elemento esta en la fila 0
		constraintsDER.gridwidth = 1;// Ocupa 1 celda horizontalmente
		constraintsDER.gridheight = 1;// Ocupa 1 celda verticalmente
		constraintsDER.anchor = GridBagConstraints.WEST;
		izquierda.add(totalIndicadores, constraintsDER);
		constraintsDER.anchor = GridBagConstraints.CENTER;
		
		totalNumIndicadores = new JLabel();
		totalNumIndicadores.setOpaque(true);
		totalNumIndicadores.setBackground(Color.lightGray);
        constraintsDER.gridx = 3;// El elemento esta en la columna 0
		constraintsDER.gridy = 3;// El elemento esta en la fila 0
		constraintsDER.gridwidth = 1;// Ocupa 1 celda horizontalmente
		constraintsDER.gridheight = 1;// Ocupa 1 celda verticalmente
		constraintsDER.anchor = GridBagConstraints.EAST;
		izquierda.add(totalNumIndicadores, constraintsDER);
		constraintsDER.anchor = GridBagConstraints.CENTER;	
		
        modeloIndicadores = new ModeloIndicadores();
        tablaIndicadores = new JTable(modeloIndicadores);
        tablaIndicadores.setPreferredScrollableViewportSize(new Dimension(400, 100));
        JScrollPane scrollPaneIndicadores = new JScrollPane(tablaIndicadores);
        constraintsDER.gridx = 0;// El elemento esta en la columna 0
		constraintsDER.gridy = 4;// El elemento esta en la fila 1
		constraintsDER.gridwidth = 5;// Ocupa 5 celda horizontalmente
		constraintsDER.gridheight = 1;// Ocupa 1 celda verticalmente
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
		constraintsDER.gridx = 2;// El elemento esta en la columna 2
		constraintsDER.gridy = 5;// El elemento esta en la fila 2
		constraintsDER.gridwidth = 1;// Ocupa 1 celda horizontalmente
		constraintsDER.gridheight = 1;// Ocupa 1 celda verticalmente
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
		
		
		resultado = new JLabel("Resultado: ");
	    constraintsDER.gridx = 0;// El elemento esta en la columna 0
		constraintsDER.gridy = 6;// El elemento esta en la fila 0
		constraintsDER.gridwidth = 1;// Ocupa 1 celda horizontalmente
		constraintsDER.gridheight = 1;// Ocupa 1 celda verticalmente
		constraintsDER.anchor = GridBagConstraints.WEST;
		izquierda.add(resultado, constraintsDER);
		constraintsDER.anchor = GridBagConstraints.CENTER;
	        
	        
	    modeloEstadistica = new ModeloEstadistica();
	    tablaResultado = new JTable(modeloEstadistica);
	    tablaResultado.setPreferredScrollableViewportSize(new Dimension(400, 100));
	    JScrollPane scrollPaneResultado = new JScrollPane(tablaResultado);
	    constraintsDER.gridx = 0;// El elemento esta en la columna 0
		constraintsDER.gridy = 7;// El elemento esta en la fila 1
		constraintsDER.gridwidth = 5;// Ocupa 5 celda horizontalmente
		constraintsDER.gridheight = 1;// Ocupa 1 celda verticalmente
		constraintsDER.weightx = 1.0;
		constraintsDER.anchor = GridBagConstraints.WEST;
		constraintsDER.fill = GridBagConstraints.BOTH;
		izquierda.add(scrollPaneResultado, constraintsDER);
		constraintsDER.anchor = GridBagConstraints.CENTER;
		
		
		/******************************JPanel grafica******************************/
        
        
        grafica = new CrearGrafica2();
        
		/*************************A�adir paneles al Applet*************************/

        add(izquierda, BorderLayout.WEST);
		add(grafica, BorderLayout.EAST);
        
 		setSize(900,600 );
		this.setVisible(true);

	}
	public int getPuertoServidor(){
		
		return Integer.parseInt(configuracion.getProperty("puerto"));
		
	}
	public void inicializarListeners(){
		/*********************** A�adimos Listener a los botones **********************/
		botonRetroceder_pais.addActionListener(this);
		botonAvanzar_pais.addActionListener(this);
		botonRetroceder_ind.addActionListener(this);
		botonAvanzar_ind.addActionListener(this);
		

		tablaPaises.addMouseListener(this);
		tablaIndicadores.addMouseListener(this);
		
	}
	/********************************* Control de eventos *************************************/
	@Override	
	public void actionPerformed(ActionEvent ev) {
		System.out.println("Entra en el actionPerformed");
		Object eventoLlamado = ev.getSource();
		String botonPulsado = ((JButton) eventoLlamado).getName();
		System.out.println("Evento getName= "+botonPulsado);
		
		
		if (botonPulsado == "avanzar_pag_paises") {
			ArrayList<Pais> data = new ArrayList<Pais>();
			
			Pais dataa = new Pais("dadadsad","adsadsadsa");
			data.add(dataa);
			data.add(dataa);
			data.add(dataa);
			data.add(new Pais("sdASDFG","gfdgfd"));
			data.add(new Pais("rrrrrr","ggggggg"));
			data.add(new Pais("ttttttttt","eeeeeeeeeeee"));
			System.out.println("tama�o de data despues de crearlo, valor: "+data.size());
			//data.add(dataa);
			
			
			setPaises(data, null);
			
			//data.add("fsfsf","fsfdsfs");
			//modeloPaises.addRow(aux);
		}else if(botonPulsado == "retroceder_pag_paises"){
			modeloPaises.removeAllRows();
		}else if(botonPulsado == "retroceder_pag_indicadores"){
			XYSeries datos = new XYSeries("Estadisticas");
			datos.add(1985,534);
			datos.add(1986,654);
			datos.add(1987,343);
			datos.add(1988,345);
			datos.add(1989,73);
			datos.add(1990,37);
			datos.add(1991,645);
			cambiarDatosGrafica(datos);
			
			
		}else if(botonPulsado == "avanzar_pag_indicadores"){
			ArrayList<Estadistica> data = new ArrayList<Estadistica>();
			
			Estadistica dataa = new Estadistica("dadadsad","adsadsadsa",4234,4324324);
			data.add(dataa);
			data.add(dataa);
			data.add(dataa);
			data.add(new Estadistica("sdASDFG","gfdgfd",34242,4324));
			data.add(new Estadistica("rrrrrr","ggggggg",4324,324324));
			data.add(new Estadistica("ttttttttt","eeeeeeeeeeee",4324 ,4234324));
			System.out.println("tama�o de data despues de crearlo, valor: "+data.size());
			//data.add(dataa);
			setEstadisticas(data, "hola");
			setNumPaginasPaises(23, 43);
		}

}



	@Override
	public void mouseClicked(MouseEvent ev) {
		int filaPaises = tablaPaises.getSelectedRow();
		int filaIndicadores = tablaIndicadores.getSelectedRow();
		//Comprobamos si se ha seleccionado alguna columna de cada tabla, si el valor devuelto es -1 es porque aun no hay ninguna fila seleccionada
		if(filaPaises!=-1 && filaIndicadores!=-1){//Caso afirmativo, actualizamos el valor de la grafica
		System.out.println("Columna selecionada Indicadores: "+filaIndicadores);
		System.out.println("Columna selecionada Paises: "+filaPaises);
		}
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}
/*Metodo que cambia los datos de la grafica por los que se le pasen como argumento
 * 
 */
	public void cambiarDatosGrafica(XYSeries datos){
		grafica.modificarValores(datos);
	}
	public void setPaises(ArrayList<Pais> paises, String filtro){
		modeloPaises.setFiltro(filtro);
		modeloPaises.addAllRows(paises);
	}
	public String getFiltroPaises(){
		return modeloPaises.getFiltro();
	}
	public void setIndicadores(ArrayList<Indicador> indicadores, String filtro){
		modeloIndicadores.setFiltro(filtro);
		modeloIndicadores.addAllRows(indicadores);
	}
	public String getFiltroIndicadores(){
		return modeloIndicadores.getFiltro();
	}
	public void setEstadisticas(ArrayList<Estadistica> estadisticas, String filtro){
		modeloEstadistica.setFiltro(filtro);
		modeloEstadistica.addAllRows(estadisticas);
	}
	public String getFiltroEstadisticas(){
		return modeloEstadistica.getFiltro();
	}
	public void setNumPaises(int numPaises){
		totalNumPaises.setText(Integer.toString(numPaises));
	}
	public void setNumIndicadores(int numIndicadores){
		totalNumPaises.setText(Integer.toString(numIndicadores));
	}
	public void setNumPaginasPaises(int actual, int total){
		paginaPaises.setText(Integer.toString(actual)+" de "+Integer.toString(total));
	}
	public void setNumPagIndicadores(int actual, int total){
		paginaIndicadores.setText(Integer.toString(actual)+" de "+Integer.toString(total));
	}




	
}
