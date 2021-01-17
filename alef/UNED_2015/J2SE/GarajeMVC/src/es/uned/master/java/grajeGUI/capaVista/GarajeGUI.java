/**
 * Clase GarajeGUI
 * Capa
 * Version 1
 * @author jrequeijo2, Jose lopez
 */

package es.uned.master.java.grajeGUI.capaVista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;

public class GarajeGUI extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	private JPanel panel;	
	private JLabel label;
	private JTable parking;
	private JTextArea textArea;		
	private FlowLayout layout;
	private JButton boton;
	private boolean barrera;

	public GarajeGUI(Object[][] plazas,String[] cabecera){
		barrera=true;				
		setTitle("PARKING GUI");		
		setSize(690,700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);			
		panel = new JPanel();
		this.add(panel);		
		label =new JLabel();		
		label.setFont(new Font("Serif", Font.BOLD, 30));
		label.setText("   GESTION PLAZAS APARCAMIENTO");
		 //Creamos el JTable para visualizar la ocupación del parking
		parking = new JTable(plazas, cabecera);         
       TableColumn column = null;
       //Asignar altura de celda
       parking.setRowHeight(50);       
       for (int i = 0; i < 15; i++) {       	
           column = parking.getColumnModel().getColumn(i);
           if (i == 0){
           	column.setMaxWidth(70);
           }
           if (i > 0){
               column.setMaxWidth(35);
           }          
       }        
       MyCellRenderer mcr = new MyCellRenderer();     
       // Coloreamos la columna 0 
       parking.getColumnModel().getColumn(0).setCellRenderer(mcr);
       //Asignar contenido a celdas
       for (int i = 0; i < 7; i++) {
       	 for (int j = 1; j < 15; j++) {
       		parking.setValueAt("",i,j);
       		parking.getColumnModel().getColumn(j).setCellRenderer(mcr);       		
       	 }
       }
              
	 textArea = new JTextArea(10,35);
     textArea.setLineWrap(false);
	 textArea.setEditable(false);		
     Border border = BorderFactory.createLineBorder(Color.BLACK);
     textArea.setBorder(BorderFactory.createCompoundBorder(border, 
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
     
     parking.setBorder(BorderFactory.createCompoundBorder(border, 
             BorderFactory.createEmptyBorder(10, 10, 10, 10)));			
		     
     boton = new JButton(); 
     
     boton.setText("CERRAR");  

     boton.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent e){  
     	  barrera=false;
     	  dispose();
       }
     });  
     
     layout = new FlowLayout();
	 layout.setHgap(55);
	 layout.setVgap(10);
	 panel.setLayout(layout);   
	 panel.setBorder(new EmptyBorder(20, 20, 40,40) ); 	 
     panel.add(label,layout);
	 panel.add(parking.getTableHeader(),layout);
	 panel.add(parking,layout);	   
	 panel.add(textArea);
	 boton.setPreferredSize(new Dimension(100, 100));
	 panel.add(boton);
	 
		 
	}
	public void asignarPlaza (String txt,int i, int j){
    	this.parking.setValueAt(txt,i, j);	    	
}
	public boolean isBarrera() {
		return barrera;
	}


	public void setBarrera(boolean barrera) {
		this.barrera = barrera;
	}
    
	 public void display (String msg) throws InterruptedException{		 
		 this.textArea.setText(msg);
		 Thread.sleep(1500);
		 this.textArea.setText("");
	    }
	 
}
