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

public class BGuiSocios extends JInternalFrame implements ActionListener{
	private JComboBox propiedad;
	private JTextField buscarTxt;
	private JTable resSql;
	private JScrollPane presSql;
	private CSocios libreria;
	private JTextArea res;
	private JScrollPane pres;
	private JButton buscarBtn;
	private JButton logBtn;
	private JButton buscarFechaBtn;
	private JTextField buscarFechaTxt;
	private JCheckBox anyo;
	private JCheckBox mes;
	private JCheckBox dia;
	private JCheckBox completo;
	private ButtonGroup fecha;
	
	
	public BGuiSocios(String titulo){
		super(titulo,
		          true, //resizable
		          true, //closable
		          true, //maximizable
		          false);//iconifiable	txtBoton[3]
		this.propiedad=new JComboBox();
		this.propiedad.addItem(Main.idioma.traduce("_CUALQUIER"));
		this.propiedad.addItem(Main.idioma.traduce("_DNI"));
		this.propiedad.addItem(Main.idioma.traduce("_NOMBRE"));
		this.propiedad.addItem(Main.idioma.traduce("_APELLIDOS"));
		this.propiedad.addItem(Main.idioma.traduce("_DIRECCION"));
		this.dia= new JCheckBox(Main.idioma.traduce("_DIA"));
		this.mes= new JCheckBox(Main.idioma.traduce("_MES"));
		this.anyo= new JCheckBox(Main.idioma.traduce("_ANYO"));
		this.completo= new JCheckBox(Main.idioma.traduce("_COMPLETO"));
		this.fecha= new ButtonGroup();
		this.fecha.add(this.dia);
		this.fecha.add(this.mes);
		this.fecha.add(this.anyo);
		this.fecha.add(this.completo);
		this.completo.setSelected(true);
		this.buscarFechaTxt= new JTextField(10);
		this.buscarFechaTxt.setText(Main.idioma.traduce("_dd-MM-yyyy"));
		this.buscarFechaBtn= new JButton(Main.idioma.traduce("_BUSQUEDAFECHA"));
		this.buscarFechaBtn.setActionCommand("FECHABUSCAR");
		this.buscarFechaBtn.addActionListener(this);
		
		this.buscarTxt= new JTextField(25);
		this.buscarTxt.setText(Main.idioma.traduce("_INTRODUZCACADENABUSQUEDA"));
		this.libreria= new CSocios();
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
		JPanel p2= new JPanel();
		p2.setLayout(new GridLayout(4,1));
		p2.add(this.dia);
		p2.add(this.mes);
		p2.add(this.anyo);
		p2.add(this.completo);
		JPanel p3= new JPanel();
		p3.setLayout(new FlowLayout());
		p3.add(p2);
		p3.add(this.buscarFechaTxt);
		p3.add(this.buscarFechaBtn);
		JPanel p= new JPanel();
		p.setLayout(new GridLayout(1,2));
		p.add(p1);
		p.add(p3);
		con.add(p,BorderLayout.NORTH);
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
		this.propiedad.addItem(Main.idioma.traduce("_DNI"));
		this.propiedad.addItem(Main.idioma.traduce("_NOMBRE"));
		this.propiedad.addItem(Main.idioma.traduce("_APELLIDOS"));
		this.propiedad.addItem(Main.idioma.traduce("_DIRECCION"));
		this.propiedad.addItem(Main.idioma.traduce("_FECHAALTA"));
		this.buscarFechaBtn.setText(Main.idioma.traduce("_BUSQUEDAFECHA"));
		this.dia.setText(Main.idioma.traduce("_DIA"));
		this.mes.setText(Main.idioma.traduce("_MES"));
		this.anyo.setText(Main.idioma.traduce("_ANYO"));
		this.completo.setText(Main.idioma.traduce("_COMPLETO"));
		this.buscarFechaTxt.setText(Main.idioma.traduce("_dd-MM-yyyy"));
    	//this.cambialeng.setText(Main.idioma.traduce("_CAMBIAIDIOMA"));
    	this.libreria.actualizaIdioma();
	}
	public void actionPerformed(ActionEvent e){
		char c=((String) e.getActionCommand()).charAt(0);
		int valores=0;
		try{
			switch (c){
				case 'B':
						String  campo=(String) this.propiedad.getSelectedItem();
						int razonbusqueda=CSocios.BUSQUEDATOTAL;
						if (campo.equalsIgnoreCase(Main.idioma.traduce("_DNI"))){
							razonbusqueda=CSocios.BUSQUEDADNI;
						}
						if (campo.equalsIgnoreCase(Main.idioma.traduce("_NOMBRE"))){
							razonbusqueda=CSocios.BUSQUEDANOMBRE;
						}
						if (campo.equalsIgnoreCase(Main.idioma.traduce("_APELLIDOS"))){
							razonbusqueda=CSocios.BUSQUEDAAPELLIDOS;
						}
						if (campo.equalsIgnoreCase(Main.idioma.traduce("_DIRECCION"))){
							razonbusqueda=CSocios.BUSQUEDADIRECCION;
						}
						valores=this.libreria.buscar(this.buscarTxt.getText().trim().toUpperCase(),razonbusqueda);
						this.res.append("--------------------------------------------\n");
						if (valores==-1){
							this.res.append(Main.idioma.traduce("_FECHAINCORRECTA"));
						}else{
							this.res.append(Main.idioma.traduce("_REGISTROSENCONTRADOS")+": "+valores+"\n");
						}
						this.res.append("--------------------------------------------\n");
					break;
				case 'F':
						String mifecha=this.buscarFechaTxt.getText().toUpperCase();
						int envio= CSocios.COMPLETO;
						if (this.dia.isSelected()){
							envio=CSocios.DIA;
						}
						if (this.mes.isSelected()){
							envio=CSocios.MES;
						}
						if (this.anyo.isSelected()){
							envio=CSocios.ANYO;
						}
						valores=this.libreria.buscarFecha(mifecha,envio);
						if (valores==-1){
							this.res.append(Main.idioma.traduce("_FECHAINCORRECTA"));
						}else{
							this.res.append(Main.idioma.traduce("_REGISTROSENCONTRADOS")+": "+valores+"\n");
						}
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
