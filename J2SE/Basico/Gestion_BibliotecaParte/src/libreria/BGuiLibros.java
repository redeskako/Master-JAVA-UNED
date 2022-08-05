/*
 *   CARLOS LUIS SÁNCHEZ BOCANEGRA
                            DNI: 26017022C
             Expediente UNED: 55­04­00458
Domicilio: C/Cura Merino 2 2ºD 29011 Málaga

 */
package libreria;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

import libreria.error.ErrorLibreriaException;
import libreria.lenguaje.*;

public class BGuiLibros extends JInternalFrame implements ActionListener{
	private JComboBox propiedad;
	private JTextField buscarTxt;
	private JTable resSql;
	private JScrollPane presSql;
	private CLibros libreria;
	private JTextArea res;
	private JScrollPane pres;
	private JButton buscarBtn;
	private JButton logBtn;
	
	
	public BGuiLibros(String titulo){
		super(titulo,
		          true, //resizable
		          true, //closable
		          true, //maximizable
		          false);//iconifiable	txtBoton[3]
		this.propiedad=new JComboBox();
		this.propiedad.addItem(Main.idioma.traduce("_CUALQUIER"));
		this.propiedad.addItem(Main.idioma.traduce("_NOMBRE"));
		this.propiedad.addItem(Main.idioma.traduce("_AUTOR"));
		this.propiedad.addItem(Main.idioma.traduce("_TEMA"));
		
		this.buscarTxt= new JTextField(50);
		this.buscarTxt.setText(Main.idioma.traduce("_INTRODUZCACADENABUSQUEDA"));
		this.libreria= new CLibros();
		this.resSql= new JTable(this.libreria);
		this.resSql.setEnabled(false);
		this.presSql= new JScrollPane(this.resSql);
		this.res= new JTextArea(15,800);
		this.pres= new JScrollPane(this.res);
		this.buscarBtn= new JButton (Main.idioma.traduce("_BUSCAR"));
		this.buscarBtn.setActionCommand("BUSCAR");
		this.buscarBtn.addActionListener(this);
		this.logBtn=new JButton (Main.idioma.traduce("_LIMPIARLOG"));
		this.logBtn.setActionCommand("LIMPIAR");
		this.logBtn.addActionListener(this);
		
		Container con= super.getContentPane();
		con.setLayout(new BorderLayout());
		JPanel p1= new JPanel();
		p1.setLayout(new FlowLayout());
		p1.add(this.buscarTxt);
		p1.add(this.propiedad);
		p1.add(this.buscarBtn);		
		p1.add(this.logBtn);
		
		con.add(p1,BorderLayout.NORTH);
		con.add(this.presSql,BorderLayout.CENTER);
		con.add(this.pres,BorderLayout.SOUTH);
		
		super.setSize(new Dimension(800,600));
		//super.pack();
		super.setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		super.setVisible(false);				
	}
	public void actualizaIdioma(){
		this.setTitle(Main.idioma.traduce("_BUSQUEDALIBROS"));
		this.buscarTxt.setText(Main.idioma.traduce("_INTRODUZCACADENABUSQUEDA"));
		this.buscarBtn.setText(Main.idioma.traduce("_BUSCAR"));
		this.logBtn.setText(Main.idioma.traduce("_LIMPIARLOG"));
		this.propiedad.removeAllItems();
		this.propiedad.addItem(Main.idioma.traduce("_CUALQUIER"));
		this.propiedad.addItem(Main.idioma.traduce("_NOMBRE"));
		this.propiedad.addItem(Main.idioma.traduce("_AUTOR"));
		this.propiedad.addItem(Main.idioma.traduce("_TEMA"));
		
    	//this.cambialeng.setText(Main.idioma.traduce("_CAMBIAIDIOMA"));
    	this.libreria.actualizaIdioma();
	}
	public void actionPerformed(ActionEvent e){
		char c=((String) e.getActionCommand()).charAt(0);
		try{
			switch (c){
				case 'B':
						String  campo=(String) this.propiedad.getSelectedItem();
						int razonbusqueda=CLibros.BUSQUEDATOTAL;
						if (campo.equalsIgnoreCase(Main.idioma.traduce("_NOMBRE"))){
							razonbusqueda=CLibros.BUSQUEDANOMBRE;
						}
						if (campo.equalsIgnoreCase(Main.idioma.traduce("_AUTOR"))){
							razonbusqueda=CLibros.BUSQUEDAAUTOR;
						}
						if (campo.equalsIgnoreCase(Main.idioma.traduce("_TEMA"))){
							razonbusqueda=CLibros.BUSQUEDATEMA;
						}
						int valores=this.libreria.buscar(this.buscarTxt.getText().trim().toUpperCase(),razonbusqueda);
						this.res.append("--------------------------------------------\n");
						this.res.append(Main.idioma.traduce("_REGISTROSENCONTRADOS")+": "+valores+"\n");
						this.res.append("--------------------------------------------\n");
					break;
				case 'L':
					this.res.setText(Main.idioma.traduce("_LIMPIADO")+"\n");
					break;
				default:
			}
		}catch(ErrorLibreriaException err){
			this.res.append("--------------------------------------------\n");
			this.res.append(Main.idioma.traduce("_ERROR")+": "+err+"\n");
			this.res.append("--------------------------------------------\n");
		}
	}
}
