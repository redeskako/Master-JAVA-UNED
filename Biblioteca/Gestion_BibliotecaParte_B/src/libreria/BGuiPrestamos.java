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

public class BGuiPrestamos extends JInternalFrame implements ActionListener{
	private JComboBox propiedad;
	private JTextField buscarTxt;
	private JTable resSql;
	private JScrollPane presSql;
	private CPrestamos libreria;
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
	private JCheckBox alquiladas;
	private ButtonGroup fecha;	
	private JCheckBox fechaInicio;
	private JCheckBox fechaFin;
	private ButtonGroup completoFecha;
	private Config idioma;
	
	public BGuiPrestamos(String titulo,String local){
		super(titulo,
		          true, //resizable
		          true, //closable
		          true, //maximizable
		          false);//iconifiable	txtBoton[3]
		this.idioma= new Config(local);
		this.propiedad=new JComboBox();
		this.propiedad.addItem(this.idioma.traduce("_CUALQUIER"));
		this.propiedad.addItem(this.idioma.traduce("_LIBRO"));
		this.propiedad.addItem(this.idioma.traduce("_SOCIO"));
		this.dia= new JCheckBox(this.idioma.traduce("_DIA"));
		this.mes= new JCheckBox(this.idioma.traduce("_MES"));
		this.anyo= new JCheckBox(this.idioma.traduce("_ANYO"));
		this.completo= new JCheckBox(this.idioma.traduce("_COMPLETO"));
		this.fecha= new ButtonGroup();
		this.fecha.add(this.dia);
		this.fecha.add(this.mes);
		this.fecha.add(this.anyo);
		this.fecha.add(this.completo);
		this.completo.setSelected(true);
		this.buscarFechaTxt= new JTextField(10);
		this.buscarFechaTxt.setText(this.idioma.traduce("_dd-MM-yyyy"));
		this.buscarFechaBtn= new JButton(this.idioma.traduce("_BUSQUEDAFECHA"));
		this.buscarFechaBtn.setActionCommand("FECHABUSCAR");
		this.buscarFechaBtn.addActionListener(this);
		this.fechaInicio= new JCheckBox(this.idioma.traduce("_FECHAINICIO"));
		this.fechaFin=new JCheckBox(this.idioma.traduce("_FECHAFIN"));
		this.alquiladas= new JCheckBox(this.idioma.traduce("_PENDIENTES"));
		this.fechaInicio.setSelected(true);
		this.completoFecha= new ButtonGroup();
		this.completoFecha.add(this.fechaInicio);
		this.completoFecha.add(this.fechaFin);
		this.completoFecha.add(this.alquiladas);
		this.completo.add(this.alquiladas);
		this.buscarTxt= new JTextField(25);
		this.buscarTxt.setText(this.idioma.traduce("_INTRODUZCACADENABUSQUEDA"));
		this.libreria= new CPrestamos(this.idioma);
		this.resSql= new JTable(this.libreria);
		this.resSql.setEnabled(false);
		this.presSql= new JScrollPane(this.resSql);
		this.res= new JTextArea(15,800);
		this.pres= new JScrollPane(this.res);
		this.buscarBtn= new JButton (this.idioma.traduce("_BUSCAR"));
		this.buscarBtn.setActionCommand("BUSCAR");
		this.buscarBtn.addActionListener(this);
		this.logBtn=new JButton (this.idioma.traduce("_LIMPIARLOG"));
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
		p3.setLayout(new GridLayout(3,1));
		p3.add(this.fechaInicio);
		p3.add(this.fechaFin);
		p3.add(this.alquiladas);
		
		JPanel p4=new JPanel();
		p4.setLayout(new FlowLayout());
		p4.add(this.buscarFechaTxt);
		p4.add(this.buscarFechaBtn);

		JPanel p= new JPanel();
		p.setLayout(new GridLayout(1,2));
		p.add(p1);
		JPanel pp= new JPanel();
		pp.setLayout(new GridLayout(1,3));
		pp.add(p4);
		pp.add(p2);
		pp.add(p3);
		p.add(pp);
		con.add(p,BorderLayout.NORTH);
		con.add(this.presSql,BorderLayout.CENTER);
		con.add(this.pres,BorderLayout.SOUTH);
		
		super.setSize(new Dimension(800,600));
		//super.pack();
		super.setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		super.setVisible(false);
		this.res.append(this.libreria.mensajeSocket);
	}
	public void actualizaIdioma(String local){
		this.idioma= new Config(local);
		this.setTitle(this.idioma.traduce("_BUSQUEDALIBROS"));
		this.buscarTxt.setText(this.idioma.traduce("_INTRODUZCACADENABUSQUEDA"));
		this.buscarBtn.setText(this.idioma.traduce("_BUSCAR"));
		this.logBtn.setText(this.idioma.traduce("_LIMPIARLOG"));
		this.propiedad.removeAllItems();
		this.propiedad.addItem(this.idioma.traduce("_CUALQUIER"));
		this.propiedad.addItem(this.idioma.traduce("_LIBRO"));
		this.propiedad.addItem(this.idioma.traduce("_SOCIO"));
		this.buscarFechaBtn.setText(this.idioma.traduce("_BUSQUEDAFECHA"));
		this.dia.setText(this.idioma.traduce("_DIA"));
		this.mes.setText(this.idioma.traduce("_MES"));
		this.anyo.setText(this.idioma.traduce("_ANYO"));
		this.completo.setText(this.idioma.traduce("_COMPLETO"));
		this.buscarFechaTxt.setText(this.idioma.traduce("_dd-MM-yyyy"));
		this.fechaInicio.setText(this.idioma.traduce("_FECHAINICIO"));
		this.fechaFin.setText(this.idioma.traduce("_FECHAFIN"));
		this.alquiladas.setText(this.idioma.traduce("_PENDIENTES"));
    	//this.cambialeng.setText(this.idioma.traduce("_CAMBIAIDIOMA"));
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
						if (campo.equalsIgnoreCase(this.idioma.traduce("_LIBRO"))){
							razonbusqueda=CPrestamos.BUSQUEDALIBRO;
						}
						if (campo.equalsIgnoreCase(this.idioma.traduce("_SOCIO"))){
							razonbusqueda=CPrestamos.BUSQUEDACLIENTE;
						}
						valores=this.libreria.buscar(this.buscarTxt.getText().trim().toUpperCase(),razonbusqueda);
						this.res.append("--------------------------------------------\n");
						this.res.append(this.idioma.traduce("_REGISTROSENCONTRADOS")+": "+valores+"\n");
						this.res.append("--------------------------------------------\n");
					break;
				case 'F':
					String mifecha=this.buscarFechaTxt.getText().toUpperCase();
					int envio= CSocios.COMPLETO;
					String campoprop="FechaInicio";
					if (this.dia.isSelected()){
						envio=CPrestamos.DIA;
					}
					if (this.mes.isSelected()){
						envio=CPrestamos.MES;
					}
					if (this.anyo.isSelected()){
						envio=CPrestamos.ANYO;
					}
					if (this.fechaInicio.isSelected()){
						campoprop="FechaInicio";
					}
					if (this.fechaFin.isSelected()){
						campoprop="FechaFin";
					}
					if (this.alquiladas.isSelected()){
						envio=CPrestamos.COMPLETO;
						campoprop="FechaFin";
						mifecha="";
					}
					valores=this.libreria.buscarFecha(mifecha,envio,campoprop);
					if (valores==-1){
						this.res.append(this.idioma.traduce("_FECHAINCORRECTA"));
					}else{
						this.res.append(this.idioma.traduce("_REGISTROSENCONTRADOS")+": "+valores+"\n");
					}
					break;						
				case 'L':
					this.res.setText(this.idioma.traduce("_LIMPIADO")+"\n");
					break;
				default:
			}
		}catch(ErrorLibreriaException err){
			this.res.append("--------------------------------------------\n");
			this.res.append(this.idioma.traduce("_ERROR")+": "+err+"\n");
			this.res.append("--------------------------------------------\n");
		}
	}	
}
