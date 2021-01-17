/*
 *   CARLOS LUIS SÁNCHEZ BOCANEGRA
                            DNI: 26017022C
             Expediente UNED: 55­04­00458
Domicilio: C/Cura Merino 2 2ºD 29011 Málaga

 */
package libreria.server;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

import libreria.lenguaje.*;

public class GServidorSocket extends JApplet implements ActionListener{
	private JScrollPane parea;
	private Config idioma;
	private ServidorSocket servidor;
	
		private void inicia(){
			this.setJMenuBar(this.estableceMenu());
			Container con = this.getContentPane();
			con.setLayout(new BorderLayout());
			try{
				String pu = super.getParameter("Puerto");
				this.idioma.puerto(Integer.parseInt(pu));
			}catch(ClassCastException e){this.idioma.puerto(Config.PUERTO);}
			this.servidor= new ServidorSocket(this.idioma.puerto(),this.idioma.idioma());
			this.servidor.start();
			this.servidor.log= new JTextArea();
			this.servidor.log.setEditable(false);
			this.parea= new JScrollPane(this.servidor.log);
			this.servidor.log.append(this.idioma.traduce("_SERVIDORINICIADO"));
			con.add(this.parea,BorderLayout.CENTER);
			super.setSize(800,600);
		}
	public GServidorSocket(){
		super();
		this.idioma = new Config("es");
	}
	public void init(){
		this.inicia();
	}

		private JMenuBar estableceMenu(){
			JMenuBar menuBar=new JMenuBar();
				JMenu menu = new JMenu(this.idioma.traduce("_SERVIDOR"));
				menu.getAccessibleContext().setAccessibleDescription(
			        this.idioma.traduce("_SERVIDOR"));

				//	a group of JMenuItems
					JMenuItem serverDOWN= new JMenuItem(this.idioma.traduce("_PARARSERVER"));
					serverDOWN.getAccessibleContext().setAccessibleDescription(
							this.idioma.traduce("_PARARSERVER"));
					serverDOWN.setActionCommand("PARAR");
					serverDOWN.addActionListener(this);
				menu.add(serverDOWN);
			menuBar.add(menu);
		
				JMenu menuSalir= new JMenu(this.idioma.traduce("_SALIR"));
				JMenuItem salir = new JMenuItem(this.idioma.traduce("_SALIR"));
				salir.getAccessibleContext().setAccessibleDescription(
						this.idioma.traduce("_CERRARAPLICACION"));
				salir.setActionCommand("SALIR");
				salir.addActionListener(this);
				menuSalir.add(salir);
			menuBar.add(menuSalir);
			return menuBar;
		}

		public void actionPerformed(ActionEvent e){
			String st= (String) e.getActionCommand();
			if(st.equalsIgnoreCase("PARAR")){
				this.servidor.pararServidor();
			}else if(st.equalsIgnoreCase("SALIR")){
				System.exit(0);
			}
		}	
}

