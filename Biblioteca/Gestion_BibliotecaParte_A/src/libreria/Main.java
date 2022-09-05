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
import java.util.*;
import libreria.lenguaje.Config;

public class Main extends JFrame implements ActionListener{
		public static Config idioma;
		private final int GLIBROS=0;
		private final int GSOCIOS=1;
		private final int GPRESTAMOS=2;
		private final int CLIBROS=3;
		private final int CSOCIOS=4;
		private final int CPRESTAMOS=5;
		private JDesktopPane mdi;
		private JMenuBar menuBar;
						
		public static void idioma(String local){
			idioma= new Config(local);
		}
		public static String idioma(){
			return idioma.locale();
		}
		public static Main prestamos(String local){
			idioma=new Config(local);
			return new Main(idioma.traduce("_PRESTAMOS"));
		}
			private JMenuBar estableceMenu(){
				JMenuBar menuBar=new JMenuBar();
					JMenu menu = new JMenu(idioma.traduce("_GESTION"));
					menu.getAccessibleContext().setAccessibleDescription(
				        "Gestión de Libros");	

					//	a group of JMenuItems
						JMenuItem book = new JMenuItem(idioma.traduce("_LIBROS"));
						book.getAccessibleContext().setAccessibleDescription(
						        idioma.traduce("_GESTIONLIBROS"));
						book.setActionCommand("GL");
						book.addActionListener(this);
						JMenuItem client= new JMenuItem(idioma.traduce("_SOCIOS"));
						client.getAccessibleContext().setAccessibleDescription(
						        idioma.traduce("_GESTIONSOCIOS"));
						client.setActionCommand("GS");
						client.addActionListener(this);
						JMenuItem rent= new JMenuItem(idioma.traduce("_PRESTAMOS"));
						rent.getAccessibleContext().setAccessibleDescription(
						        idioma.traduce("_GESTIONPRESTAMOS"));
						rent.setActionCommand("GP");
						rent.addActionListener(this);
					menu.add(book);
					menu.add(client);
					menu.add(rent);
				menuBar.add(menu);

				menu = new JMenu(idioma.traduce("_CONSULTAS"));
				menu.getAccessibleContext().setAccessibleDescription(
			        "Consultas");
//					a group of JMenuItems
					book = new JMenuItem(idioma.traduce("_LIBROS"));
					book.getAccessibleContext().setAccessibleDescription(
					        idioma.traduce("_CONSULTASLIBROS"));
					book.setActionCommand("CL");
					book.addActionListener(this);
					client= new JMenuItem(idioma.traduce("_SOCIOS"));
					client.getAccessibleContext().setAccessibleDescription(
					        idioma.traduce("_CONSULTASSOCIOS"));
					client.setActionCommand("CS");
					client.addActionListener(this);
					rent= new JMenuItem(idioma.traduce("_PRESTAMOS"));
					rent.getAccessibleContext().setAccessibleDescription(
					        idioma.traduce("_CONSULTASPRESTAMOS"));
					rent.setActionCommand("CP");
					rent.addActionListener(this);
				menu.add(book);
				menu.add(client);
				menu.add(rent);
			menuBar.add(menu);				

				JMenu menuIdioma= new JMenu(idioma.traduce("_IDIOMA"));
						ButtonGroup grupocheck= new ButtonGroup();
						JCheckBoxMenuItem es= new JCheckBoxMenuItem(idioma.traduce("_ES"));
						if (idioma.locale()=="español"){
							es.setSelected(true);
						}
						es.setActionCommand("IE");
						es.addActionListener(this);
						JCheckBoxMenuItem en= new JCheckBoxMenuItem(idioma.traduce("_EN"));
						if (idioma.locale()=="inglés"){
							en.setSelected(true);
						}
						en.setActionCommand("IN");
						en.addActionListener(this);
						grupocheck.add(es);
						grupocheck.add(en);
					menuIdioma.add(en);
					menuIdioma.add(es);
				menuBar.add(menuIdioma);					
					JMenu menuSalir= new JMenu(idioma.traduce("_SALIR"));
					JMenuItem acercaDe= new JMenuItem(idioma.traduce("_ACERCADE"));
					acercaDe.getAccessibleContext().setAccessibleDescription(
							idioma.traduce("_ACERCADE"));
					acercaDe.setActionCommand("ACERCADE");
					acercaDe.addActionListener(this);
					JMenuItem salir = new JMenuItem(idioma.traduce("_SALIR"));
					salir.getAccessibleContext().setAccessibleDescription(
							idioma.traduce("_CERRARAPLICACION"));
					salir.setActionCommand("SALIR");
					salir.addActionListener(this);
					menuSalir.add(acercaDe);
					menuSalir.add(salir);
				menuBar.add(menuSalir);
				return menuBar;
			}
		private Main(String local){
			super(idioma.traduce("_PRESTAMOS"));
			this.mdi= new JDesktopPane();
			super.setContentPane(this.mdi);
			this.mdi.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.menuBar= estableceMenu();
			this.createFrame();
			super.setJMenuBar(this.menuBar);
			super.setSize(new Dimension(1024,765));
		}
		private void createFrame(){
			CGuiLibros libros= new CGuiLibros(idioma.traduce("_LIBROS"));
			this.mdi.add(libros, this.GLIBROS);
			CGuiSocios socios= new CGuiSocios(idioma.traduce("_SOCIOS"));
			this.mdi.add(socios, this.GSOCIOS);
			CGuiPrestamos prestamos= new CGuiPrestamos(idioma.traduce("_ALQUILER"));
			this.mdi.add(prestamos);
			BGuiLibros blibros= new BGuiLibros(idioma.traduce("_BUSQUEDALIBROS"));
			this.mdi.add(blibros);
			BGuiSocios bsocios= new BGuiSocios(idioma.traduce("_BUSQUEDASOCIOS"));
			this.mdi.add(bsocios);
			BGuiPrestamos bprestamos= new BGuiPrestamos(idioma.traduce("_BUSQUEDAPRESTAMOS"));
			this.mdi.add(bprestamos);
			Acercade sobremi= new Acercade(idioma.traduce("_ACERCADE"));
			this.mdi.add(sobremi);
		}
					private CGuiLibros buscaLibros(){
						JInternalFrame[] lib= this.mdi.getAllFrames();
						CGuiLibros en=null;
						int i=0;
						boolean encontrado=false;
						while ((!encontrado)&& (i<lib.length)){
							try{
								en= (CGuiLibros) lib[i];
								encontrado=true;
							}catch(ClassCastException err){}
							i++;
						}
						return en;
					}				
				private void gestionLibros(){
					CGuiLibros en= buscaLibros();
					if (en==null){
						this.createFrame();
						en=buscaLibros();
					}
					en.setVisible(true);
					this.mdi.moveToFront(en);					
					try{
						en.setMaximum(true);
					}catch(Exception err){}
				}
					private CGuiSocios buscaSocios(){
						JInternalFrame[] lib= this.mdi.getAllFrames();
						CGuiSocios en=null;
						int i=0;
						boolean encontrado=false;
						while ((!encontrado)&& (i<lib.length)){
							try{
								en= (CGuiSocios) lib[i];
								encontrado=true;
							}catch(ClassCastException err){}
							i++;
						}
						return en;
					}				
				private void gestionSocios(){
					CGuiSocios en= buscaSocios();
					if (en==null){
						this.createFrame();
						en= buscaSocios();
					}
					en.setVisible(true);
					this.mdi.moveToFront(en);					
					try{
						en.setMaximum(true);
					}catch(Exception err){}					
				}
					private CGuiPrestamos buscaPrestamos(){
						JInternalFrame[] lib= this.mdi.getAllFrames();
						CGuiPrestamos en=null;
						int i=0;
						boolean encontrado=false;
						while ((!encontrado)&& (i<lib.length)){
							try{
								en= (CGuiPrestamos) lib[i];
								encontrado=true;
							}catch(ClassCastException err){}
							i++;
						}
						return en;
					}
				private void gestionPrestamos(){
					CGuiPrestamos en= buscaPrestamos();
					if (en==null){
						this.createFrame();
						en= buscaPrestamos();
					}
					en.setVisible(true);
					this.mdi.moveToFront(en);
					try{
						en.setMaximum(true);
					}catch(Exception err){}
				}
					private BGuiLibros buscaBLibros(){
						JInternalFrame[] lib= this.mdi.getAllFrames();
						BGuiLibros en=null;
						int i=0;
						boolean encontrado=false;
						while ((!encontrado)&& (i<lib.length)){
							try{
								en= (BGuiLibros) lib[i];
								encontrado=true;
							}catch(ClassCastException err){}
							i++;
						}
						return en;						
					}
				private void consultaLibros(){
					BGuiLibros en= buscaBLibros();
					if (en==null){
						this.createFrame();
						en=buscaBLibros();
					}
					en.setVisible(true);
					this.mdi.moveToFront(en);
					try{
						en.setMaximum(true);
					}catch(Exception err){}
				}
					private BGuiSocios buscaBSocios(){
						JInternalFrame[] lib= this.mdi.getAllFrames();
						BGuiSocios en=null;
						int i=0;
						boolean encontrado=false;
						while ((!encontrado)&& (i<lib.length)){
							try{
								en= (BGuiSocios) lib[i];
								encontrado=true;
							}catch(ClassCastException err){}
							i++;
						}
						return en;	
					}				
				private void consultaSocios(){
					BGuiSocios en= buscaBSocios();
					if (en==null){
						this.createFrame();
						en=buscaBSocios();
					}
					en.setVisible(true);
					this.mdi.moveToFront(en);
					try{
						en.setMaximum(true);
					}catch(Exception err){}
				}	
				private BGuiPrestamos buscaBPrestamos(){
					JInternalFrame[] lib=this.mdi.getAllFrames();
					BGuiPrestamos en=null;
					int i=0;
					boolean encontrado=false;
					while((!encontrado) && (i<lib.length)){
						try{
							en=(BGuiPrestamos) lib[i];
							encontrado=true;
						}catch(ClassCastException err){}
						i++;
					}
					return en;
				}
				private void consultaPrestamos(){
					BGuiPrestamos en= buscaBPrestamos();
					if (en==null){
						this.createFrame();
						en=buscaBPrestamos();
					}
					en.setVisible(true);
					this.mdi.moveToFront(en);
					try{
						en.setMaximum(true);
					}catch(Exception err){}
				}
				private void cambiaIdioma(){
					this.setTitle(idioma.traduce("_PRESTAMOS"));
					this.menuBar= this.estableceMenu();
					this.setJMenuBar(this.menuBar);
				}
				private void cambiaIdioma(String local){
					JInternalFrame[] lib;
					lib= this.mdi.getAllFrames();
					idioma.cambiaIdioma(local);
					this.cambiaIdioma();
					
					int i=0;
					while (i<lib.length){
						if (lib[i] instanceof CGuiLibros){
								CGuiLibros libro=null;
								libro= (CGuiLibros) lib[i];
								libro.actualizaIdioma();
						}else if (lib[i] instanceof CGuiSocios){
								CGuiSocios socio= null;
								socio=(CGuiSocios) lib[i];
								socio.actualizaIdioma();
						}else if (lib[i] instanceof CGuiPrestamos){
								CGuiPrestamos prestamo=null;
								prestamo=(CGuiPrestamos) lib[i];
								prestamo.actualizaIdioma();
						}else if(lib[i] instanceof BGuiLibros){
								BGuiLibros blibro=null;
								blibro=(BGuiLibros) lib[i];
								blibro.actualizaIdioma();
						}else if(lib[i] instanceof BGuiSocios){
								BGuiSocios bsocio=null;
								bsocio=(BGuiSocios) lib[i];
								bsocio.actualizaIdioma();
						}else if(lib[i] instanceof BGuiPrestamos){
								BGuiPrestamos bprestamo= null;
								bprestamo=(BGuiPrestamos) lib[i];
								bprestamo.actualizaIdioma();
						}else if(lib[i] instanceof Acercade){
								Acercade acercade= null;
								acercade=(Acercade) lib[i];
								acercade.actualizaIdioma();
						}else{
								this.createFrame();
						}
						i++;
					}
				}
				private Acercade buscaAcercaDe(){
					JInternalFrame[] lib= this.mdi.getAllFrames();
					Acercade en= null;
					int i=0;
					boolean encontrado=false;
					while((!encontrado) && (i<lib.length)){
						try{
							en=(Acercade) lib[i];
							encontrado=true;
						}catch(ClassCastException err){}
						i++;
					}
					return en;
				}
				private void acercaDe(){
					Acercade en=this.buscaAcercaDe();
					if (en==null){
						this.createFrame();
						en=this.buscaAcercaDe();
					}
					en.setVisible(true);
					this.mdi.moveToFront(en);
				}
				public void actionPerformed(ActionEvent e){
			String st= (String) e.getActionCommand();
			if (st.equalsIgnoreCase("GL")){
				//Gestion de libros
				this.gestionLibros();
				this.cambiaIdioma();
			}else if(st.equalsIgnoreCase("GS")){
				//Gestion de socios
				this.gestionSocios();
				this.cambiaIdioma();				
			}else if(st.equalsIgnoreCase("GP")){
				//Gestion de préstamos
				this.gestionPrestamos();
				this.cambiaIdioma();
			}else if (st.equalsIgnoreCase("CL")){
				//Consulta de Libros
				this.consultaLibros();
			}else if (st.equalsIgnoreCase("CS")){
				//Consulta de Socios
				this.consultaSocios();
			}else if(st.equalsIgnoreCase("CP")){
				//Consulta de Préstamos
				this.consultaPrestamos();
			}else if(st.equalsIgnoreCase("IE")){
				//Cambio idioma Español
				this.cambiaIdioma("es");
			}else if (st.equalsIgnoreCase("IN")){
				//Cambio idioma Ingles
				this.cambiaIdioma("en");
			}else if (st.equalsIgnoreCase("ACERCADE")){
				//Cuadro de acercade
				this.acercaDe();
			}else if(st.equalsIgnoreCase("SALIR")){
				System.exit(0);
			}
			
		}
}
