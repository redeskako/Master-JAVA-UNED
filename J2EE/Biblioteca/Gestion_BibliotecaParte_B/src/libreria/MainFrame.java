/*
 *   CARLOS LUIS SÁNCHEZ BOCANEGRA
                            DNI: 26017022C
             Expediente UNED: 55­04­00458
Domicilio: C/Cura Merino 2 2ºD 29011 Málaga

 */
package libreria;

import javax.swing.*;
import libreria.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.*;

import libreria.lenguaje.*;
import libreria.error.*;

public class MainFrame extends JApplet implements ActionListener{
	private Main principal;
	public Config idioma;
	private JMenuBar menuBar;
	public int puerto=8000;
	
	private void inicia(String local){
		this.principal= new Main(local);
		this.idioma= new Config(local);
		Container c= super.getContentPane();
		super.setLayout(new BorderLayout());
		c.add(this.principal,BorderLayout.CENTER);
		//super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setJMenuBar(this.estableceMenu());
		this.setSize(new Dimension(1024,765));
	}
	public  MainFrame(){
		super();
		this.inicia("es");
	}
	public void init(){
		this.idioma= new Config("es");
		try{
			this.idioma.puerto(Integer.parseInt(this.getParameter("Puerto")));
		}catch(ClassCastException e){this.idioma.puerto(Config.PUERTO);}
		this.setSize(new Dimension(1024,765));
	}

	private JMenuBar estableceMenu(){
		JMenuBar menuBar=new JMenuBar();

		JMenu menu = new JMenu(this.idioma.traduce("_CONSULTAS"));
		menu.getAccessibleContext().setAccessibleDescription(
	        "Consultas");
//			a group of JMenuItems
			JMenuItem book = new JMenuItem(this.idioma.traduce("_LIBROS"));
			book.getAccessibleContext().setAccessibleDescription(
					this.idioma.traduce("_CONSULTASLIBROS"));
			book.setActionCommand("CL");
			book.addActionListener(this);
			JMenuItem client= new JMenuItem(this.idioma.traduce("_SOCIOS"));
			client.getAccessibleContext().setAccessibleDescription(
					this.idioma.traduce("_CONSULTASSOCIOS"));
			client.setActionCommand("CS");
			client.addActionListener(this);
			JMenuItem rent= new JMenuItem(this.idioma.traduce("_PRESTAMOS"));
			rent.getAccessibleContext().setAccessibleDescription(
					this.idioma.traduce("_CONSULTASPRESTAMOS"));
			rent.setActionCommand("CP");
			rent.addActionListener(this);
		menu.add(book);
		menu.add(client);
		menu.add(rent);
	menuBar.add(menu);				

		JMenu menuIdioma= new JMenu(this.idioma.traduce("_IDIOMA"));
				ButtonGroup grupocheck= new ButtonGroup();
				JCheckBoxMenuItem es= new JCheckBoxMenuItem(this.idioma.traduce("_ES"));
				if (this.idioma.locale()=="español"){
					es.setSelected(true);
				}
				es.setActionCommand("IE");
				es.addActionListener(this);
				JCheckBoxMenuItem en= new JCheckBoxMenuItem(this.idioma.traduce("_EN"));
				if (this.idioma.locale()=="inglés"){
					en.setSelected(true);
				}
				en.setActionCommand("IN");
				en.addActionListener(this);
				grupocheck.add(es);
				grupocheck.add(en);
			menuIdioma.add(en);
			menuIdioma.add(es);
		menuBar.add(menuIdioma);					
			JMenu menuSalir= new JMenu(this.idioma.traduce("_SALIR"));
			JMenuItem acercaDe= new JMenuItem(this.idioma.traduce("_ACERCADE"));
			acercaDe.getAccessibleContext().setAccessibleDescription(
					this.idioma.traduce("_ACERCADE"));
			acercaDe.setActionCommand("ACERCADE");
			acercaDe.addActionListener(this);
			JMenuItem salir = new JMenuItem(this.idioma.traduce("_SALIR"));
			salir.getAccessibleContext().setAccessibleDescription(
					this.idioma.traduce("_CERRARAPLICACION"));
			salir.setActionCommand("SALIR");
			salir.addActionListener(this);
			menuSalir.add(acercaDe);
			menuSalir.add(salir);
		menuBar.add(menuSalir);
		return menuBar;
	}
	
	public void idioma(String local){
		this.idioma= new Config(local);
	}
	public String idioma(){
		return this.idioma.locale();
	}
	public void update(){
		this.inicia(this.idioma.idioma());
	}
	private void cambiaIdioma(String local){
		//this.setTitle(this.idioma.traduce("_PRESTAMOS"));
		this.idioma=new Config(local);
		this.setJMenuBar(this.estableceMenu());
		this.principal.cambiaIdioma(local);
	}
	public void actionPerformed(ActionEvent e){
		String st= (String) e.getActionCommand();
		if (st.equalsIgnoreCase("CL")){
			//Consulta de Libros
			this.principal.consultaLibros();
		}else if (st.equalsIgnoreCase("CS")){
			//Consulta de Socios
			this.principal.consultaSocios();
		}else if(st.equalsIgnoreCase("CP")){
			//Consulta de Préstamos
			this.principal.consultaPrestamos();
		}else if(st.equalsIgnoreCase("IE")){
			//Cambio idioma Español
			this.cambiaIdioma("es");
		}else if (st.equalsIgnoreCase("IN")){
			//Cambio idioma Ingles
			this.cambiaIdioma("en");
		}else if (st.equalsIgnoreCase("ACERCADE")){
			//Cuadro de acercade
			this.principal.acercaDe();
		}else if(st.equalsIgnoreCase("SALIR")){
			System.exit(0);
		}
	}	
}
