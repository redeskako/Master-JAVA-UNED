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

public class Main extends JDesktopPane{
		private final int GLIBROS=0;
		private final int GSOCIOS=1;
		private final int GPRESTAMOS=2;
		private final int CLIBROS=3;
		private final int CSOCIOS=4;
		private final int CPRESTAMOS=5;
		private Config idioma;
		//private JDesktopPane mdi;

		public Main(String locale){
			//super(idioma.traduce("_PRESTAMOS"));
			super();//this.mdi= new JDesktopPane();
			idioma=new Config(locale);
			//super.setContentPane(this.mdi);
			//this.mdi.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
			super.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
			//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.createFrame();
		//	super.setJMenuBar(this.menuBar);
		 	super.setSize(new Dimension(1024,765));
		}
		private void createFrame(){
			CGuiLibros libros= new CGuiLibros(this.idioma.traduce("_LIBROS"),this.idioma.idioma());
			this.add(libros, this.GLIBROS);
			CGuiSocios socios= new CGuiSocios(this.idioma.traduce("_SOCIOS"),this.idioma.idioma());
			this.add(socios, this.GSOCIOS);
			CGuiPrestamos prestamos= new CGuiPrestamos(this.idioma.traduce("_ALQUILER"),this.idioma.idioma());
			this.add(prestamos);
			BGuiLibros blibros= new BGuiLibros(this.idioma.traduce("_BUSQUEDALIBROS"),this.idioma.idioma());
			this.add(blibros);
			BGuiSocios bsocios= new BGuiSocios(this.idioma.traduce("_BUSQUEDASOCIOS"),this.idioma.idioma());
			this.add(bsocios);
			BGuiPrestamos bprestamos= new BGuiPrestamos(this.idioma.traduce("_BUSQUEDAPRESTAMOS"),this.idioma.idioma());
			this.add(bprestamos);
			Acercade sobremi= new Acercade(this.idioma.traduce("_ACERCADE"),this.idioma.idioma());
			this.add(sobremi);
		}
					private CGuiLibros buscaLibros(){
						JInternalFrame[] lib= this.getAllFrames();
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
				public void gestionLibros(){
					CGuiLibros en= buscaLibros();
					if (en==null){
						this.createFrame();
						en=buscaLibros();
					}
					en.setVisible(true);
					this.moveToFront(en);					
					try{
						en.setMaximum(true);
					}catch(Exception err){}
				}
					private CGuiSocios buscaSocios(){
						JInternalFrame[] lib= this.getAllFrames();
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
				public void gestionSocios(){
					CGuiSocios en= buscaSocios();
					if (en==null){
						this.createFrame();
						en= buscaSocios();
					}
					en.setVisible(true);
					this.moveToFront(en);					
					try{
						en.setMaximum(true);
					}catch(Exception err){}					
				}
					private CGuiPrestamos buscaPrestamos(){
						JInternalFrame[] lib= this.getAllFrames();
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
				public  void gestionPrestamos(){
					CGuiPrestamos en= buscaPrestamos();
					if (en==null){
						this.createFrame();
						en= buscaPrestamos();
					}
					en.setVisible(true);
					this.moveToFront(en);
					try{
						en.setMaximum(true);
					}catch(Exception err){}
				}
					private BGuiLibros buscaBLibros(){
						JInternalFrame[] lib= this.getAllFrames();
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
				public  void consultaLibros(){
					BGuiLibros en= buscaBLibros();
					if (en==null){
						this.createFrame();
						en=buscaBLibros();
					}
					en.setVisible(true);
					this.moveToFront(en);
					try{
						en.setMaximum(true);
					}catch(Exception err){}
				}
					private BGuiSocios buscaBSocios(){
						JInternalFrame[] lib= this.getAllFrames();
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
				public void consultaSocios(){
					BGuiSocios en= buscaBSocios();
					if (en==null){
						this.createFrame();
						en=buscaBSocios();
					}
					en.setVisible(true);
					this.moveToFront(en);
					try{
						en.setMaximum(true);
					}catch(Exception err){}
				}	
				private BGuiPrestamos buscaBPrestamos(){
					JInternalFrame[] lib=this.getAllFrames();
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
				public void consultaPrestamos(){
					BGuiPrestamos en= buscaBPrestamos();
					if (en==null){
						this.createFrame();
						en=buscaBPrestamos();
					}
					en.setVisible(true);
					this.moveToFront(en);
					try{
						en.setMaximum(true);
					}catch(Exception err){}
				}
				public void cambiaIdioma(String local){
					JInternalFrame[] lib;
					lib= this.getAllFrames();
					this.idioma.cambiaIdioma(local);
//					this.cambiaIdioma();
					
					int i=0;
					while (i<lib.length){
						if (lib[i] instanceof CGuiLibros){
								CGuiLibros libro=null;
								libro= (CGuiLibros) lib[i];
								libro.actualizaIdioma(this.idioma.idioma());
						}else if (lib[i] instanceof CGuiSocios){
								CGuiSocios socio= null;
								socio=(CGuiSocios) lib[i];
								socio.actualizaIdioma(this.idioma.idioma());
						}else if (lib[i] instanceof CGuiPrestamos){
								CGuiPrestamos prestamo=null;
								prestamo=(CGuiPrestamos) lib[i];
								prestamo.actualizaIdioma(this.idioma.idioma());
						}else if(lib[i] instanceof BGuiLibros){
								BGuiLibros blibro=null;
								blibro=(BGuiLibros) lib[i];
								blibro.actualizaIdioma(this.idioma.idioma());
						}else if(lib[i] instanceof BGuiSocios){
								BGuiSocios bsocio=null;
								bsocio=(BGuiSocios) lib[i];
								bsocio.actualizaIdioma(this.idioma.idioma());
						}else if(lib[i] instanceof BGuiPrestamos){
								BGuiPrestamos bprestamo= null;
								bprestamo=(BGuiPrestamos) lib[i];
								bprestamo.actualizaIdioma(this.idioma.idioma());
						}else if(lib[i] instanceof Acercade){
								Acercade acercade= null;
								acercade=(Acercade) lib[i];
								acercade.actualizaIdioma(this.idioma.idioma());
						}else{
								this.createFrame();
						}
						i++;
					}
				}
				private Acercade buscaAcercaDe(){
					JInternalFrame[] lib= this.getAllFrames();
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
				public void acercaDe(){
					Acercade en=this.buscaAcercaDe();
					if (en==null){
						this.createFrame();
						en=this.buscaAcercaDe();
					}
					en.setVisible(true);
					this.moveToFront(en);
				}
}
