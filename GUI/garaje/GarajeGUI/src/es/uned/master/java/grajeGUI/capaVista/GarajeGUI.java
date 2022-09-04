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
	private JTable table;
	private JTextArea textArea;		
	private FlowLayout layout;
	private JButton boton;
	private boolean barrera;
	public static int plazasOcupadas;

	public GarajeGUI(){
		barrera=true;
		plazasOcupadas=0;		
		//Datos para la cabecera del JTable
		String[] columns = new String[15];
		for (int i=0;i<15;i++){
			if (i==0){
				columns[i]="";	
			}else {
			columns[i]= "P" + i;
			}
		}
        //Rellenamos el JTable: incialmente plazas vacias        
		Object[][] data = new Object[7][15];
        for (int i =0; i<7;i++ ){
        	for (int j=0;j<10;j++){
        		if ((i==0) && (j==0)){
        			data[i][j]="  SOTANO";
        		} else if ((i>0) && (j==0)){
        			data[i][j]= "  PLANTA " + (i - 1);
        		} else if((i>0) && (j>0)){
        			data[i][j]="";
        		}        		
        	}
        }		
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
		 //create table with data
        table = new JTable(data, columns);           
       TableColumn column = null;
       //Asignar altura de celda
       table.setRowHeight(50);       
       for (int i = 0; i < 15; i++) {       	
           column = table.getColumnModel().getColumn(i);
           if (i == 0){
           	column.setMaxWidth(70);
           }
           if (i > 0){
               column.setMaxWidth(35);
           }          
       }        
       MyCellRenderer mcr = new MyCellRenderer();     
       // Coloreamos la columna 0 
       table.getColumnModel().getColumn(0).setCellRenderer(mcr);
       //Asignar contenido a celdas
       for (int i = 0; i < 7; i++) {
       	 for (int j = 1; j < 15; j++) {
       		 table.setValueAt("",i,j);
       		 table.getColumnModel().getColumn(j).setCellRenderer(mcr);       		
       	 }
       }
       
       /* textArea = new JTextArea(8,12);
        textArea.setLineWrap(false);
		textArea.setEditable(false);		
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        textArea.setBorder(BorderFactory.createCompoundBorder(border, 
                   BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        
        table.setBorder(BorderFactory.createCompoundBorder(border, 
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));			
		  layout = new BorderLayout();	      
	      layout.setVgap(30);
	      panel.setLayout(layout); 	     
	      panel.setBorder(new EmptyBorder(40, 40, 40,43) ); 	       
	      panel.add(table.getTableHeader(), BorderLayout.PAGE_START);
	      panel.add(table, BorderLayout.CENTER);
		  panel.add(table, BorderLayout.WEST);  		 
		  panel.add(textArea,BorderLayout.PAGE_END);	
		  */
       
	      
	//}
	 textArea = new JTextArea(10,35);
     textArea.setLineWrap(false);
	 textArea.setEditable(false);		
     Border border = BorderFactory.createLineBorder(Color.BLACK);
     textArea.setBorder(BorderFactory.createCompoundBorder(border, 
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
     
     table.setBorder(BorderFactory.createCompoundBorder(border, 
             BorderFactory.createEmptyBorder(10, 10, 10, 10)));			
		 // BorderLayout layout = new BorderLayout();
    
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
	 panel.add(table.getTableHeader(),layout);
	 panel.add(table,layout);
	 panel.add(table,layout);  
	 panel.add(table,layout);  
	 panel.add(textArea);
	 boton.setPreferredSize(new Dimension(100, 100));
	 panel.add(boton);
	 
		 
	}
	public boolean isBarrera() {
		return barrera;
	}


	public void setBarrera(boolean barrera) {
		this.barrera = barrera;
	}

	
     public void asignarPlaza(){
    	 if (plazasOcupadas < 98){
    		 plazasOcupadas++; 
    	 }    	 
     } 
     public void liberarPlaza(){
    	 if (plazasOcupadas >0){
    		 plazasOcupadas--; 
    	 }    	 
     } 
     public static int plazasOcupadas() {
         return plazasOcupadas;
     }
 	 public void asignar (int i, int j , String txt){
	    	this.table.setValueAt(txt, i, j);	    	
    }
 	public String liberarPlaza (int planta, int plaza){
 		String plazaLibre="NULL";  	    
 		if( this.table.getValueAt(planta, plaza).equals("X")){
 	 		this.table.setValueAt("", planta, plaza);
 	 		plazaLibre=planta +","+ plaza;
 	 		liberarPlaza();
 	 	} 		
 		return plazaLibre;
    }
	 public String buscarPlaza(){
		 String plaza="";
		 int i=0;
		 int j=1;
		 boolean plazaAsignada = false;
		  while (!plazaAsignada ) {
			  if( this.table.getValueAt(i, j).equals("")){
					this.table.setValueAt("X", i, j);
					plaza=i + "," + j;
					plazaAsignada = true;
					asignarPlaza();
		     }else{
		    	 if (j==14){
		    		 j=1;
		    		 i++;
		    	 }else{
		    		 j++;
		    	 }
		     }			
		 }		 
		 return plaza;
	 }		
	 public void display (String msg) throws InterruptedException{		 
		 this.textArea.setText(msg);
		 Thread.sleep(1500);
		 this.textArea.setText("");
	    }
	 
}
