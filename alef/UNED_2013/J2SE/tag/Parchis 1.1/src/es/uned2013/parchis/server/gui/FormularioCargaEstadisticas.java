package es.uned2013.parchis.server.gui;

import javax.swing.*;

import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import es.uned2013.parchis.server.databaseDriver.extraeDatos;


/**
 * despliega los botones mostrando los datos a elegir de la base de datos SQLite,
 * en caso de no existir partidas anteriores cierra el automaticamente el formulario y vuelve al anterior
 * @author Alef
 *
 */
public class FormularioCargaEstadisticas extends JFrame implements ActionListener{
	
    private JComboBox combo1;
    private JButton boton1;
    
    private void LanzaCargaEstadisticas() throws ClassNotFoundException, SQLException {
    	Class.forName("org.sqlite.JDBC"); 
		Connection conexion=null;
		Statement comando = null;
		ResultSet resultados= null;
		int i = 0;
		JLabel label1 = new JLabel("Seleccione la partida a cargar:");
		boton1 = new JButton("Aceptar");
		//label1.setBounds(10,10,100,30);
		add(label1);
        setLayout(null);
        combo1=new JComboBox();
        //combo1.setBounds(10,10,150,20);
     // CONSULTA DATOS    
        try {
        	conexion = DriverManager.getConnection("jdbc:sqlite:dbparchis.db");
			comando = conexion.createStatement();
			//obtenemos el id de la partida
			resultados = comando.executeQuery("select * from partida order by id_partida;");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "No existen partidas guardadas. Por favor, juegue una partida primero","error al mostrar", JOptionPane.ERROR_MESSAGE );
			e.printStackTrace();
		}    
        
        add(combo1); 
        add(boton1);
        // PROCESAR EL RESULTADO
        while (resultados.next()) {
        	//combo1.addItem(resultados.getString(2));
        	combo1.addItem(resultados.getObject("fecha_inicio"));
        	i++;
        }
      
        // CERRAR
        resultados.close();
        comando.close();
		conexion.close();
		combo1.setSelectedIndex(i-1);
		boton1.addActionListener (this);
    }

    public void actionPerformed(ActionEvent e) {
		if (e.getSource()==boton1) {
            //String seleccionado= String.valueOf(combo1.getSelectedIndex() + 1);
            //setTitle(seleccionado);
	    	marcoEstadistico marcoPanel;
	    	this.setVisible(false);
			try {
				marcoPanel = new marcoEstadistico(String.valueOf(combo1.getSelectedIndex() + 1));
				marcoPanel.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		    	Toolkit tk = Toolkit.getDefaultToolkit();  
		    	int xSize = ((int) tk.getScreenSize().getWidth());  
		    	int ySize = ((int) tk.getScreenSize().getHeight());  
		    	marcoPanel.setSize(xSize,ySize); 
		    	marcoPanel.setVisible( true ); // muestra el marco   
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}            
        }
    }
    
    public void FormularioCargaEstadisticas() throws ClassNotFoundException, SQLException {
        this.LanzaCargaEstadisticas();
        this.getContentPane().setLayout(new FlowLayout());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //formulario1.setBounds(700,700,800,750);
        this.setLocationRelativeTo(null);
        this.setTitle("Cargar estadisticas");
        this.pack();
        this.setVisible(true);
    }    
}