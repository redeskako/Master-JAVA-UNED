/*
 *   CARLOS LUIS SÁNCHEZ BOCANEGRA
                            DNI: 26017022C
             Expediente UNED: 55­04­00458
Domicilio: C/Cura Merino 2 2ºD 29011 Málaga

 */
package libreria;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import libreria.error.*;
import javax.swing.table.*;

public class CGuiPrestamos extends JInternalFrame implements ActionListener{
	private CPrestamos libreria;
	private JTable tabla;
	private TableColumn libro;
	private TableColumn socio;
	private String txtBoton[];
	public final static String [] accion={"ACEPTAR","CANCELAR","NUEVO","LIMPIAR"};
	private JButton [] botonera;
	private JScrollPane stabla;
	private JTextArea area;
	private JScrollPane sarea;
	//private JButton cambialeng;

		private void actualizaCombo(){
//////////////////////////////////Establecer un combo para actualizar
			this.libro= this.tabla.getColumnModel().getColumn(2);
			JComboBox mislibros= CPrestamos.carga("SELECT Nombre as Nombre from Libros ORDER BY Nombre");
			this.libro.setCellEditor(new DefaultCellEditor(mislibros));
			
			this.socio= this.tabla.getColumnModel().getColumn(1);
			JComboBox missocios= CPrestamos.carga("SELECT CONCAT(DNI,' - ',Apellidos,' ',Nombre) as Nombre from Socios ORDER BY Apellidos,Nombre");
			this.socio.setCellEditor(new DefaultCellEditor(missocios));			
		}
	public CGuiPrestamos(String titulo){
		super(titulo,
			          true, //resizable
			          true, //closable
			          true, //maximizable
			          false);//iconifiable		
		this.txtBoton=new String [4];
		this.txtBoton[0]=Main.idioma.traduce("_ACEPTAR");
		this.txtBoton[1]=Main.idioma.traduce("_CANCELAR");
		this.txtBoton[2]=Main.idioma.traduce("_NUEVO");
		this.txtBoton[3]=Main.idioma.traduce("_LIMPIARLOG");
	/*
	 * Creacion de los elementos básicos de comunicacion con bbdd
	 */
		// Creacion del map que conecta con JTable
		this.libreria= new CPrestamos();
	/*
	 * Creacion de componentes gráficos que veremos
	 */
		// Creacion de la botonera
		this.botonera = new JButton[txtBoton.length];
		for (int i= 0; i<txtBoton.length;i++){
			this.botonera[i]= new JButton(txtBoton[i]);
//System.out.println("Accion"+CGuiLibros.accion[i]);
			this.botonera[i].setActionCommand(CGuiLibros.accion[i]);
//System.out.println("Botonera: "+i+"- "+this.botonera[i].getActionCommand());			
			this.botonera[i].addActionListener(this);
		}
		//CHECK para idioma
//		this.cambialeng= new JButton(Main.idioma.traduce("_CAMBIAIDIOMA"));
//		this.cambialeng.setActionCommand("Idioma");
//		this.cambialeng.addActionListener(this);
		//JTable
		this.tabla= new JTable(this.libreria);
		this.tabla.getModel().addTableModelListener(this.libreria);
		this.stabla= new JScrollPane(this.tabla);
//////////////////////////////////Establecer un combo para actualizar
		this.actualizaCombo();
		//Contendores
		
		Container panel= this.getContentPane();
		panel.setLayout(new BorderLayout());
		JPanel p1= new JPanel(new FlowLayout(this.botonera.length));
		//p1.add(this.cambialeng);
		for (int i=0;i<this.botonera.length;i++){
			p1.add(this.botonera[i]);
		}
		panel.add(p1,BorderLayout.NORTH);
		panel.add(stabla,BorderLayout.CENTER);
		
		//Area
		this.area=new JTextArea(15,800);
		this.area.setFont(new Font("Serif", Font.ITALIC, 9));
		this.area.setLineWrap(true);
		this.area.setEditable(false);
		this.sarea=new JScrollPane(this.area);
		panel.add(sarea,BorderLayout.SOUTH );
		
		super.setSize(new Dimension(800,600));
		//super.pack();
		super.setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		super.setVisible(false);
	}
	public String toString(){
		String str=Main.idioma.traduce("_LIBRERIA")+":\n";
		str+=this.libreria.toString()+ "\n";
		str+= this.libreria.toString();
		return str;
	}
	public void actionPerformed(ActionEvent e){
		char c=((String) e.getActionCommand()).charAt(0);
		int [] i;
		try{
			switch (c){
				case 'A':
						i=this.libreria.aceptar();
						this.area.append("--------------------------------------------\n");
						this.area.append(Main.idioma.traduce("_REGISTROSNUEVOS")+":"+i[0]+"\n");
						this.area.append(Main.idioma.traduce("_REGISTROSBORRADOS")+":"+i[1]+"\n");
						this.area.append(Main.idioma.traduce("_REGISTROSACTUALIZADOS")+": "+i[2]+"\n");
						this.area.append("--------------------------------------------\n");
						break;					
				case 'C':
					i= this.libreria.cancela();
					this.area.append("--------------------------------------------\n");
					this.area.append(Main.idioma.traduce("_NUEVOSCANCELADOS")+": "+i[0]+"\n");
					this.area.append(Main.idioma.traduce("_ELIMINACIONESCANCELADAS")+": "+i[1]+"\n");
					this.area.append(Main.idioma.traduce("_ACTUALIZACIONESCANCELADAS")+":"+i[2]+"\n");
					this.area.append("--------------------------------------------\n");
					break;
				case 'N':
					this.libreria.nuevo();
					this.area.append("--------------------------------------------\n");
					this.area.append(Main.idioma.traduce("_PERMITIDOINSERTAR")+"\n");
					this.area.append("--------------------------------------------\n");
					break;
				case 'L':
					this.area.setText(Main.idioma.traduce("_LIMPIADO")+"\n");
					break;
				case 'I':
					this.cambiaIdioma();
					this.actualizaIdioma();
					this.area.setText(Main.idioma.traduce("_IDIOMACAMBIADO"));
					break;
				default:
			}
		}catch(ErrorLibreriaException err){
			this.area.append("--------------------------------------------\n");
			this.area.append(Main.idioma.traduce("_ERROR")+": "+err+"\n");
			this.area.append("--------------------------------------------\n");
		}
	}
	public void actualizaIdioma(){
		this.setTitle(Main.idioma.traduce("_ALQUILER"));
		this.txtBoton=new String [4];
		this.txtBoton[0]=Main.idioma.traduce("_ACEPTAR");
		this.txtBoton[1]=Main.idioma.traduce("_CANCELAR");
		this.txtBoton[2]=Main.idioma.traduce("_NUEVO");
		this.txtBoton[3]=Main.idioma.traduce("_LIMPIARLOG");    	
    	for (int i=0;i<this.botonera.length;i++){
    		this.botonera[i].setText(this.txtBoton[i]);
    	}
    	//this.cambialeng.setText(Main.idioma.traduce("_CAMBIAIDIOMA"));
    	this.libreria.actualizaIdioma();
    	this.actualizaCombo();
	}
	private void cambiaIdioma(){
	//System.out.println("Locale:"+Main.idioma.locale());
		if(Main.idioma.locale().equals("español")){
			Main.idioma.cambiaIdioma("en");
		}else{
			Main.idioma.cambiaIdioma("es");
		}
	}
}
