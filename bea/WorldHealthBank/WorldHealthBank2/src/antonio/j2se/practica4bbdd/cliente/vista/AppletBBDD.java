package antonio.j2se.practica4bbdd.cliente.vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.AccessControlException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import antonio.j2se.practica4bbdd.cliente.modelo.ModeloTablaEstadisticas;
import antonio.j2se.practica4bbdd.cliente.modelo.ModeloTablaIndicadores;
import antonio.j2se.practica4bbdd.cliente.modelo.ModeloTablaPaises;
import antonio.j2se.practica4bbdd.cliente.red.ClienteBBDD;
import antonio.j2se.practica4bbdd.excepciones.ErrorComunicacionNegocioException;
import antonio.j2se.practica4bbdd.excepciones.ErrorConfiguracionNegocioException;
import antonio.j2se.practica4bbdd.servidor.modelo.Estadistica;
import antonio.j2se.practica4bbdd.servidor.modelo.Indicador;
import antonio.j2se.practica4bbdd.servidor.modelo.Pais;

/**
 * Clase que extiende javax.swing.JApplet
 * Representa el cliente utilizado en la aplicacion,es un Applet
 * @author  Antonio Sánchez Antón
 * @since  1.0
 * @see javax.swing.JApplet
 */
public class AppletBBDD extends JApplet {
   private static final long serialVersionUID = 7944631637977720613L;
   private JTabbedPane contenedorPaneles;
   private JPanel panelPpal;
   private ChartPanel panelGrafico;
   private JTable tablaPais;
   private JTable tablaIndicador;
   private JTable tablaEstadistica;
   private JScrollPane scrollTablaPais;
   private JScrollPane scrollTablaIndicador;
   private JScrollPane scrollTablaEstadistica;
   private JLabel etiquetaTablaPais;
   private JLabel etiquetaTablaIndicador;
   private JLabel etiquetaTablaEstadistica;
   private JLabel labelPaisSeleccionado;
   private JLabel labelIndicadorSeleccionado;
   private JLabel labelPaisRegistrosTotales;
   private JLabel labelPaisPaginasTotales;
   private JLabel labelIndicadorRegistrosTotales;
   private JLabel labelIndicadorPaginasTotales;
   private JLabel etiquetaDirIPConfigurada;
   private JLabel etiquetaPuertoConfigurado;
   private JLabel etiquetaTamPaginaConfigurada;
   private JButton siguientePaginaPais;
   private JButton anteriorPaginaPais;
   private JButton siguientePaginaIndicador;
   private JButton anteriorPaginaIndicador;
   private JButton botonReset;
   private JTextField paginaActualPais;
   private JTextField paginaActualIndicador;
   private JTextField seleccionPais;
   private JTextField seleccionIndicador;
   private JTextField textPaisPaginasTotales;
   private JTextField textPaisRegistrosTotales;
   private JTextField textIndicadorPaginasTotales;
   private JTextField textIndicadorRegistrosTotales;
   private JTextField estadisticaPaisSeleccionado;
   private JTextField estadisticaIndicadorSeleccionado;
   private JTextField textDirIPConfigurada;
   private JTextField textPuertoConfigurado;
   private JTextField textTamPaginaConfigurado;

   private ClienteBBDD cliente;
   //Contiene el pais seleccionado en cada momento
   private Pais paisSeleccionado;
   //Contiene el indicador seleccionado en cada momento
   private Indicador indicadorSeleccionado;
   //Contiene las propiedades de configuracion(Tamaño pagina,Direcion IP y Puerto del servidor)
   private Properties configuracion;
   

   private int TAMAÑOPAGINA=-1;

	@Override
	public void init() {
	  try{	
			super.init();
			cliente=new ClienteBBDD(this);
			cargarConfiguracion();
			inicializaGUI();
			TAMAÑOPAGINA=getTamanoPaginas();
			textDirIPConfigurada.setText(getDireccionIPServidor().toString());
			textPuertoConfigurado.setText(getPuertoServidor()+"");
			textTamPaginaConfigurado.setText(getTamanoPaginas()+"");
			//Inicializamos la tabla de Paises con la 1º pagina
			cliente.obtenerPaises(TAMAÑOPAGINA,0);
			cliente.obtenerPaisesCount();
			paginaActualPais.setText("1");
	
			//Listener para saber que Pais se ha seleccionado
			MouseListener listenerClickTablaPais= new MouseListener() {
				public void mouseReleased(MouseEvent arg0) {}
				public void mousePressed(MouseEvent arg0) {}
				public void mouseExited(MouseEvent arg0) {}
				public void mouseEntered(MouseEvent arg0) {}
				public void mouseClicked(MouseEvent arg0) {
					try{
						int filaSeleccionada=tablaPais.rowAtPoint(arg0.getPoint());
						System.out.println("Fila Seleccionada Pais "+filaSeleccionada);
						paginaActualIndicador.setText("1");
						if(filaSeleccionada>-1){
			                String codPaisSeleccion=(String)tablaPais.getModel().getValueAt(filaSeleccionada, 0);
			                String descPaisSeleccion=(String)tablaPais.getModel().getValueAt(filaSeleccionada, 1);
			               // si no hay pais seleccionado en la tabla o el seleccionado es diferente hay que habia antes
				                if((paisSeleccionado==null)|| (!codPaisSeleccion.equals(paisSeleccionado.getCodigo()))){
				                	 paisSeleccionado=new Pais(codPaisSeleccion,descPaisSeleccion); 
				                	 seleccionIndicador.setText("ALL BY PAIS "+paisSeleccionado.getCodigo());
				                	 cliente.obtenerIndicadoresByPais(TAMAÑOPAGINA,0,paisSeleccionado.getCodigo());
				                	 cliente.obtenerIndicadoresByPaisCount(paisSeleccionado.getCodigo());
				                	 seleccionarPaisEstadisticas(paisSeleccionado);
				                }else {//deseleccionamos la fila
									paisSeleccionado=null;
									indicadorSeleccionado=null;
									inicializarTablaIndicadores();
									tablaPais.clearSelection();
								    deSeleccionarPaisEstadisticas();	
								    deSeleccionarIndicadorEstadisticas();
				                }
			        	}
					}catch(ErrorComunicacionNegocioException ecne){
						ecne.printStackTrace();
						JOptionPane.showMessageDialog(null, ecne.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
					}catch (AccessControlException ace) {
						ace.printStackTrace();
    					JOptionPane.showMessageDialog(null, "Existe algun problema de permisos.Revise java.policy","Error",JOptionPane.ERROR_MESSAGE);
					}
				}
			};

			//Listener para saber que Indicador se ha seleccionado
			MouseListener listenerClickTablaIndicador= new MouseListener() {
				public void mouseReleased(MouseEvent arg0) {}
				public void mousePressed(MouseEvent arg0) {}
				public void mouseExited(MouseEvent arg0) {}
				public void mouseEntered(MouseEvent arg0) {}
				public void mouseClicked(MouseEvent arg0) {
					try{
						int filaSeleccionada=tablaIndicador.rowAtPoint(arg0.getPoint());
						System.out.println("Fila Seleccionada Indicador "+filaSeleccionada);
						paginaActualPais.setText("1");
						if(filaSeleccionada>-1){
			                String codIndicadorSeleccion=(String)tablaIndicador.getModel().getValueAt(filaSeleccionada, 0);
			                String desIndicadorSeleccion=(String)tablaIndicador.getModel().getValueAt(filaSeleccionada, 1);
			             // si no hay indicador seleccionado en la tabla o el seleccionado es diferente hay que habia antes
			                if((indicadorSeleccionado==null)|| (!codIndicadorSeleccion.equals(indicadorSeleccionado.getCodigo()))){
			                	indicadorSeleccionado=new Indicador(codIndicadorSeleccion,desIndicadorSeleccion);
			                	seleccionPais.setText("ALL BY INDIC "+indicadorSeleccionado.getCodigo());
			                	cliente.obtenerPaisesByIndicador(TAMAÑOPAGINA,0,indicadorSeleccionado.getCodigo());
			                	cliente.obtenerPaisesByIndicadorCount(indicadorSeleccionado.getCodigo());
			                	seleccionarIndicadorEstadisticas(indicadorSeleccionado);
			                }else {
			                	indicadorSeleccionado=null;
			                	paisSeleccionado=null;
			                	inicializarTablaPais();
								tablaIndicador.clearSelection();
								deSeleccionarIndicadorEstadisticas();
							    deSeleccionarPaisEstadisticas();
			                }          
						}
					}catch(ErrorComunicacionNegocioException ecne){
						ecne.printStackTrace();
						JOptionPane.showMessageDialog(null, ecne.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
					}catch (AccessControlException ace) {
						ace.printStackTrace();
    					JOptionPane.showMessageDialog(null, "Existe algun problema de permisos.Revise java.policy","Error",JOptionPane.ERROR_MESSAGE);
					}
				}
			};
			
			//Listener para solicitar anterior pagina de paises
			ActionListener listenerAnteriorPaginaPais=new ActionListener() {
				public void actionPerformed(ActionEvent arg0){
					try{
							if(!paginaActualPais.getText().equals("1")){
						    	  //decrementamos la pagina mostrada en tabla paises
								paginaActualPais.setText(Integer.valueOf(paginaActualPais.getText())-1+"");
								String filtro=((ModeloTablaPaises)(tablaPais.getModel())).getFiltro();
								//Si hay filtro la tabla se cargo con filtro por lo que la anterior pagina es sobre ese filtro
								if(filtro==null){
								     cliente.obtenerPaises(TAMAÑOPAGINA, TAMAÑOPAGINA* (Integer.parseInt(paginaActualPais.getText())-1));
								}
								else {
									 cliente.obtenerPaisesByIndicador(TAMAÑOPAGINA, TAMAÑOPAGINA* (Integer.parseInt(paginaActualPais.getText())-1),filtro);
								}
								//Si habia una seleccion en la tabla pais,inicializamos la tabla de indicadores
								if(!estadisticaPaisSeleccionado.getText().trim().equals("")){
									inicializarTablaIndicadores();
									tablaPais.clearSelection();
								}
							    deSeleccionarPaisEstadisticas();
								
							}else//Mensaje de error indicando que no hay paginas anteriores a la 1º
								JOptionPane.showMessageDialog(null, "No hay paginas de pais anteriores a la actual","Error",JOptionPane.ERROR_MESSAGE);
					}catch(ErrorComunicacionNegocioException ecne){
						ecne.printStackTrace();
						JOptionPane.showMessageDialog(null, ecne.getMessage(),"Error",JOptionPane.ERROR_MESSAGE); 			
					}catch (AccessControlException ace) {
						ace.printStackTrace();
    					JOptionPane.showMessageDialog(null, "Existe algun problema de permisos.Revise java.policy","Error",JOptionPane.ERROR_MESSAGE);
					}
					
				}
			};
			//Listener para solicitar siguiente pagina de paises
			ActionListener listenerSiguientePaginaPais=new ActionListener() {
				public void actionPerformed(ActionEvent arg0){
					try{
						//Compruebo que la pagina actual no tiene menos de TAMAÑOPAGINA registros,si es asi
						//es que no hay mas paginas
						if(tablaPais.getModel().getRowCount()!=TAMAÑOPAGINA)
							JOptionPane.showMessageDialog(null, "No hay paginas de pais posteriores a la actual","Error",JOptionPane.ERROR_MESSAGE);
						else{
							String filtro=((ModeloTablaPaises)(tablaPais.getModel())).getFiltro();
							//Si hay filtro la tabla se cargo con filtro por lo que la anterior pagina es sobre ese filtro
							if(filtro==null){
							     cliente.obtenerPaises(TAMAÑOPAGINA, TAMAÑOPAGINA* Integer.parseInt(paginaActualPais.getText()));
		    				}else{ 
								 cliente.obtenerPaisesByIndicador(TAMAÑOPAGINA, TAMAÑOPAGINA* Integer.parseInt(paginaActualPais.getText()),filtro);
							}	 
								 //incrementamos la pagina mostrada en tabla paises
							paginaActualPais.setText(Integer.valueOf(paginaActualPais.getText())+1+"");
							//Si habia una seleccion en la tabla pais,inicializamos la tabla de indicadores
							if(!estadisticaPaisSeleccionado.getText().trim().equals("")){
								inicializarTablaIndicadores();
								tablaPais.clearSelection();
							}
							deSeleccionarPaisEstadisticas();
						}
					}catch(ErrorComunicacionNegocioException ecne){
						ecne.printStackTrace();
						JOptionPane.showMessageDialog(null, ecne.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
					}catch (AccessControlException ace) {
						ace.printStackTrace();
    					JOptionPane.showMessageDialog(null, "Existe algun problema de permisos.Revise java.policy","Error",JOptionPane.ERROR_MESSAGE);
					}
				}
			};
			
			//Listener para solicitar anterior pagina de indicadores
			ActionListener listenerAnteriorPaginaIndicador=new ActionListener() {
				public void actionPerformed(ActionEvent arg0){
					try{
						if(!paginaActualIndicador.getText().equals("1")){
					    	  //decrementamos la pagina mostrada en tabla indicadores
							paginaActualIndicador.setText(Integer.valueOf(paginaActualIndicador.getText())-1+"");
							String filtro=((ModeloTablaIndicadores)(tablaIndicador.getModel())).getFiltro();
							//Si hay filtro la tabla se cargo con filtro por lo que la anterior pagina es sobre ese filtro
							if(filtro!=null){
								cliente.obtenerIndicadoresByPais(TAMAÑOPAGINA, TAMAÑOPAGINA* (Integer.parseInt(paginaActualIndicador.getText())-1), filtro);
							}else{//La tabla fue cargada sin filtro
								cliente.obtenerIndicadores(TAMAÑOPAGINA, TAMAÑOPAGINA* (Integer.parseInt(paginaActualIndicador.getText())-1));
							}
							//Si habia una seleccion en la tabla inidcador,inicializamos la tabla de paises
							if(!estadisticaIndicadorSeleccionado.getText().trim().equals("")){
								inicializarTablaPais();
								tablaIndicador.clearSelection();
							}
							deSeleccionarIndicadorEstadisticas();
						}else//Mensaje de error indicando que no hay paginas anteriores a la 1º
							JOptionPane.showMessageDialog(null, "No hay paginas de Indicador anteriores a la actual","Error",JOptionPane.ERROR_MESSAGE);
					  }catch(ErrorComunicacionNegocioException ecne){
						  ecne.printStackTrace();
						  JOptionPane.showMessageDialog(null, ecne.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
					  }catch (AccessControlException ace) {
							ace.printStackTrace();
	    					JOptionPane.showMessageDialog(null, "Existe algun problema de permisos.Revise java.policy","Error",JOptionPane.ERROR_MESSAGE);
						}
					}
			};
			
			
			//Listener para solicitar siguiente pagina de indicadores
			ActionListener listenerSiguientePaginaIndicador=new ActionListener() {
				public void actionPerformed(ActionEvent arg0){
					try{
						//Compruebo que la pagina actual no tiene menos de TAMAÑOPAGINA registros,si es asi
						//es que no hay mas paginas
						if(tablaIndicador.getModel().getRowCount()!=TAMAÑOPAGINA)
							JOptionPane.showMessageDialog(null, "No hay paginas de Indicador posteriores a la actual","Error",JOptionPane.ERROR_MESSAGE);
						else{////Si la tabla de datos fue cargada con filtro,se debe solicitar pagina con filtro
							String filtro=((ModeloTablaIndicadores)(tablaIndicador.getModel())).getFiltro();
							if( filtro!=null){
								cliente.obtenerIndicadoresByPais(TAMAÑOPAGINA, TAMAÑOPAGINA* (Integer.parseInt(paginaActualIndicador.getText())), filtro);
							}else{
								cliente.obtenerIndicadores(TAMAÑOPAGINA, TAMAÑOPAGINA* (Integer.parseInt(paginaActualIndicador.getText())));
							}
							//incrementamos la pagina mostrada en tabla indicador
							paginaActualIndicador.setText(Integer.valueOf(paginaActualIndicador.getText())+1+"");
							//Si habia una seleccion en la tabla inidcador,inicializamos la tabla de paises
							if(!estadisticaIndicadorSeleccionado.getText().trim().equals("")){
								inicializarTablaPais();
								tablaIndicador.clearSelection();
							}
							deSeleccionarIndicadorEstadisticas();
						}	
					}catch(ErrorComunicacionNegocioException ecne){
						ecne.printStackTrace();
						JOptionPane.showMessageDialog(null, ecne.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
					}catch (AccessControlException ace) {
						ace.printStackTrace();
    					JOptionPane.showMessageDialog(null, "Existe algun problema de permisos.Revise java.policy","Error",JOptionPane.ERROR_MESSAGE);
					}
				}
			};
			
			ActionListener listenerReset=new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try{
						//Inicializamos la tabla de Paises con la 1º pagina de carga TOTAL
						cliente.obtenerPaises(TAMAÑOPAGINA,0);
						cliente.obtenerPaisesCount();
						paginaActualPais.setText("1");
						seleccionPais.setText("ALL");
						//Inicializamos la tabla de Indicadores con la 1º pagina de carga TOTAL
						cliente.obtenerIndicadores(TAMAÑOPAGINA,0);
						cliente.obtenerIndicadoresCount();
						paginaActualIndicador.setText("1");
						seleccionIndicador.setText("ALL");
						inicializarTablaEstadisticas();
					}catch(ErrorComunicacionNegocioException ecne){
						ecne.printStackTrace();
						JOptionPane.showMessageDialog(null, ecne.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
					}catch (AccessControlException ace) {
						ace.printStackTrace();
    					JOptionPane.showMessageDialog(null, "Existe algun problema de permisos.Revise java.policy","Error",JOptionPane.ERROR_MESSAGE);
					}
				}
			};
			
			//Registro de Listeners
			anteriorPaginaPais.addActionListener(listenerAnteriorPaginaPais);
			siguientePaginaPais.addActionListener(listenerSiguientePaginaPais);
			tablaPais.addMouseListener(listenerClickTablaPais);
			anteriorPaginaIndicador.addActionListener(listenerAnteriorPaginaIndicador);
			siguientePaginaIndicador.addActionListener(listenerSiguientePaginaIndicador);
			tablaIndicador.addMouseListener(listenerClickTablaIndicador);
			botonReset.addActionListener(listenerReset);
			
	  }catch(ErrorComunicacionNegocioException ecne){
		  ecne.printStackTrace();
		  ecne.getCause().printStackTrace();
		  JOptionPane.showMessageDialog(null, ecne.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		  System.exit(-1);
	  }catch (ErrorConfiguracionNegocioException ecfne) {
		  ecfne.printStackTrace();
		  ecfne.getCause().printStackTrace();
		  JOptionPane.showMessageDialog(null, ecfne.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		  System.exit(-1);
       }catch(AccessControlException ace){
		  ace.printStackTrace();
		  JOptionPane.showMessageDialog(null, "Existe algun problema de permisos.Revise java.policy","Error",JOptionPane.ERROR_MESSAGE);
		  System.exit(-1);
	  }
	}
   
   
	
	/**
	 * Metodo que inicializa la interfaz Grafica
	 */
	private void inicializaGUI(){
		System.out.println("Inicializando applet.....");
		contenedorPaneles = new JTabbedPane();
		inicializarPanelPpal();
		contenedorPaneles.addTab("Principal", panelPpal);
		this.add(contenedorPaneles);
		this.setSize(1000, 600);
        System.out.println("Applet inicializado");
	}
    /**
     * Inicializa el panel principal(Pestaña Principal)	
     */
	private void inicializarPanelPpal(){
		panelPpal=new JPanel();
		etiquetaTablaPais=new JLabel();
		etiquetaTablaIndicador=new JLabel();
		etiquetaTablaEstadistica=new JLabel();
		etiquetaDirIPConfigurada=new JLabel();
		etiquetaPuertoConfigurado=new JLabel();
		etiquetaTamPaginaConfigurada=new JLabel();
		tablaPais=new JTable();
		tablaIndicador=new JTable();
		tablaEstadistica=new JTable();
		siguientePaginaPais=new JButton();
		anteriorPaginaPais=new JButton();
		paginaActualPais=new JTextField(2);
		siguientePaginaIndicador=new JButton();
		anteriorPaginaIndicador=new JButton();
		paginaActualIndicador=new JTextField(2);
		seleccionPais=new JTextField(3);
		seleccionIndicador=new JTextField(3);
		estadisticaPaisSeleccionado=new JTextField(3);
		estadisticaIndicadorSeleccionado=new JTextField(3);
		textDirIPConfigurada=new JTextField(16);
		textPuertoConfigurado=new JTextField(6);
		textTamPaginaConfigurado=new JTextField(3);
		labelPaisSeleccionado=new JLabel();
		labelIndicadorSeleccionado=new JLabel();
		labelIndicadorRegistrosTotales=new JLabel();
		labelIndicadorPaginasTotales=new JLabel();
		labelPaisRegistrosTotales=new JLabel();
		labelPaisPaginasTotales=new JLabel();
		textIndicadorPaginasTotales=new JTextField();
		textIndicadorRegistrosTotales=new JTextField();
		textPaisPaginasTotales=new JTextField();
		textPaisRegistrosTotales=new JTextField();
		botonReset=new JButton();
		
		
		//Configuracion
		etiquetaDirIPConfigurada.setText("DireccionIP Servidor:");
		etiquetaDirIPConfigurada.setFont(new Font("Times-Roman", Font.BOLD + Font.ITALIC, 12));
		GridBagConstraints gridConstraintsEDIP = new GridBagConstraints();
		gridConstraintsEDIP.fill=GridBagConstraints.HORIZONTAL;
		gridConstraintsEDIP.gridwidth=1;
		gridConstraintsEDIP.gridheight=1;
		gridConstraintsEDIP.anchor=GridBagConstraints.EAST;
		gridConstraintsEDIP.gridx=3;
		gridConstraintsEDIP.gridy=0;
		
		textDirIPConfigurada.setEditable(Boolean.FALSE);
		GridBagConstraints gridConstraintsTDIP = new GridBagConstraints();
		gridConstraintsTDIP.fill=GridBagConstraints.HORIZONTAL;
		gridConstraintsTDIP.gridwidth=1;
		gridConstraintsTDIP.gridheight=1;
		gridConstraintsTDIP.anchor=GridBagConstraints.CENTER;
		gridConstraintsTDIP.gridx=4;
		gridConstraintsTDIP.gridy=0;

		etiquetaPuertoConfigurado.setText("Puerto Servidor:");
		etiquetaPuertoConfigurado.setFont(new Font("Times-Roman", Font.BOLD + Font.ITALIC, 12));
		GridBagConstraints gridConstraintsEP = new GridBagConstraints();
		gridConstraintsEP.fill=GridBagConstraints.HORIZONTAL;
		gridConstraintsEP.gridwidth=1;
		gridConstraintsEP.gridheight=1;
		gridConstraintsEP.anchor=GridBagConstraints.EAST;
		gridConstraintsEP.gridx=3;
		gridConstraintsEP.gridy=1;

		textPuertoConfigurado.setEditable(Boolean.FALSE);
		GridBagConstraints gridConstraintsTP = new GridBagConstraints();
		gridConstraintsTP.fill=GridBagConstraints.HORIZONTAL;
		gridConstraintsTP.gridwidth=1;
		gridConstraintsTP.gridheight=1;
		gridConstraintsTP.anchor=GridBagConstraints.CENTER;
		gridConstraintsTP.gridx=4;
		gridConstraintsTP.gridy=1;
		
		etiquetaTamPaginaConfigurada.setText("Tamaño de Página:");
		etiquetaTamPaginaConfigurada.setFont(new Font("Times-Roman", Font.BOLD + Font.ITALIC, 12));
		GridBagConstraints gridConstraintsETPC = new GridBagConstraints();
		gridConstraintsETPC.fill=GridBagConstraints.HORIZONTAL;
		gridConstraintsETPC.gridwidth=1;
		gridConstraintsETPC.gridheight=1;
		gridConstraintsETPC.anchor=GridBagConstraints.EAST;
		gridConstraintsETPC.gridx=3;
		gridConstraintsETPC.gridy=2;

		textTamPaginaConfigurado.setEditable(Boolean.FALSE);
		GridBagConstraints gridConstraintsTTPC = new GridBagConstraints();
		gridConstraintsTTPC.fill=GridBagConstraints.HORIZONTAL;
		gridConstraintsTTPC.gridwidth=1;
		gridConstraintsTTPC.gridheight=1;
		gridConstraintsTTPC.anchor=GridBagConstraints.CENTER;
		gridConstraintsTTPC.gridx=4;
		gridConstraintsTTPC.gridy=2;

		// Paises
		etiquetaTablaPais.setText("Paises :");
		etiquetaTablaPais.setFont(new Font("Times-Roman", Font.BOLD + Font.ITALIC, 12));
		GridBagConstraints gridConstraintsETP = new GridBagConstraints();
		gridConstraintsETP.fill=GridBagConstraints.HORIZONTAL;
		gridConstraintsETP.gridwidth=1;
		gridConstraintsETP.gridheight=1;
		gridConstraintsETP.anchor=GridBagConstraints.EAST;
		gridConstraintsETP.gridx=0;
		gridConstraintsETP.gridy=4;

		seleccionPais.setText("ALL");
		seleccionPais.setEditable(Boolean.FALSE);
		GridBagConstraints gridConstraintsSP = new GridBagConstraints();
		gridConstraintsSP.fill=GridBagConstraints.HORIZONTAL;
		gridConstraintsSP.gridwidth=3;
		gridConstraintsSP.gridheight=1;
		gridConstraintsSP.anchor=GridBagConstraints.WEST;
		gridConstraintsSP.gridx=1;
		gridConstraintsSP.gridy=4;

		
		tablaPais.setModel(new ModeloTablaPaises());
		tablaPais.setBackground(Color.CYAN);
		tablaPais.setGridColor(Color.LIGHT_GRAY);
		tablaPais.setPreferredScrollableViewportSize(new Dimension(300,100));
		scrollTablaPais=new JScrollPane(tablaPais,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		GridBagConstraints gridConstraintsSTP = new GridBagConstraints();
		gridConstraintsSTP.fill=GridBagConstraints.HORIZONTAL;
		gridConstraintsSTP.gridwidth=4;
		gridConstraintsSTP.gridheight=1;
		gridConstraintsSTP.anchor=GridBagConstraints.EAST;
		gridConstraintsSTP.gridx=0;
		gridConstraintsSTP.gridy=5;

		labelPaisRegistrosTotales.setText("Tot.Registros Cons.:");
		labelPaisRegistrosTotales.setFont(new Font("Times-Roman", Font.BOLD + Font.ITALIC, 12));
		GridBagConstraints gridConstraintsLPRT = new GridBagConstraints();
		gridConstraintsLPRT.fill=GridBagConstraints.NONE;
		gridConstraintsLPRT.gridwidth=1;
		gridConstraintsLPRT.gridheight=1;
		gridConstraintsLPRT.anchor=GridBagConstraints.EAST;
		gridConstraintsLPRT.gridx=0;
		gridConstraintsLPRT.gridy=8;

		textPaisRegistrosTotales.setEditable(Boolean.FALSE);
		GridBagConstraints gridConstraintsTPRT = new GridBagConstraints();
		gridConstraintsTPRT.fill=GridBagConstraints.HORIZONTAL;
		gridConstraintsTPRT.gridwidth=1;
		gridConstraintsTPRT.gridheight=1;
		gridConstraintsTPRT.anchor=GridBagConstraints.CENTER;
		gridConstraintsTPRT.gridx=1;
		gridConstraintsTPRT.gridy=8;

		labelPaisPaginasTotales.setText("Tot.Paginas Cons.:");
		labelPaisPaginasTotales.setFont(new Font("Times-Roman", Font.BOLD + Font.ITALIC, 12));
		GridBagConstraints gridConstraintsLPPT = new GridBagConstraints();
		gridConstraintsLPPT.fill=GridBagConstraints.NONE;
		gridConstraintsLPPT.gridwidth=1;
		gridConstraintsLPPT.gridheight=1;
		gridConstraintsLPPT.anchor=GridBagConstraints.WEST;
		gridConstraintsLPPT.gridx=2;
		gridConstraintsLPPT.gridy=8;

		textPaisPaginasTotales.setEditable(Boolean.FALSE);
		GridBagConstraints gridConstraintsTPPT = new GridBagConstraints();
		gridConstraintsTPPT.fill=GridBagConstraints.HORIZONTAL;
		gridConstraintsTPPT.gridwidth=1;
		gridConstraintsTPPT.gridheight=1;
		gridConstraintsTPPT.anchor=GridBagConstraints.CENTER;
		gridConstraintsTPPT.gridx=3;
		gridConstraintsTPPT.gridy=8;

		anteriorPaginaPais.setText("Anterior Página");
		GridBagConstraints gridConstraintsAPPB = new GridBagConstraints();
		gridConstraintsAPPB.fill=GridBagConstraints.HORIZONTAL;
		gridConstraintsAPPB.gridwidth=1;
		gridConstraintsAPPB.gridheight=1;
		gridConstraintsAPPB.anchor=GridBagConstraints.EAST;
		gridConstraintsAPPB.gridx=0;
		gridConstraintsAPPB.gridy=9;
		
		paginaActualPais.setText("0");
		paginaActualPais.setEditable(Boolean.FALSE);
		GridBagConstraints gridConstraintsTRMP = new GridBagConstraints();
		gridConstraintsTRMP.fill=GridBagConstraints.HORIZONTAL;
		gridConstraintsTRMP.gridwidth=1;
		gridConstraintsTRMP.gridheight=1;
		gridConstraintsTRMP.anchor=GridBagConstraints.CENTER;
		gridConstraintsTRMP.gridx=1;
		gridConstraintsTRMP.gridy=9;

		
		siguientePaginaPais.setText("Siguiente Página");
		GridBagConstraints gridConstraintsSPPB = new GridBagConstraints();
		gridConstraintsSPPB.fill=GridBagConstraints.HORIZONTAL;
		gridConstraintsSPPB.gridwidth=1;
		gridConstraintsSPPB.gridheight=1;
		gridConstraintsSPPB.anchor=GridBagConstraints.WEST;
		gridConstraintsSPPB.gridx=2;
		gridConstraintsSPPB.gridy=9;

		
		//Reset
		botonReset.setText("Reset");
		GridBagConstraints gridConstraintsBR = new GridBagConstraints();
		gridConstraintsBR.fill=GridBagConstraints.BOTH;
		gridConstraintsBR.gridwidth=1;
		gridConstraintsBR.gridheight=1;
		gridConstraintsBR.anchor=GridBagConstraints.CENTER;
		gridConstraintsBR.gridx=4;
		gridConstraintsBR.gridy=4;
		
		
		//Indicadores
		etiquetaTablaIndicador.setText("Indicadores :");
		etiquetaTablaIndicador.setFont(new Font("Times-Roman", Font.BOLD + Font.ITALIC, 12));
		GridBagConstraints gridConstraintsETI = new GridBagConstraints();
		gridConstraintsETI.fill=GridBagConstraints.HORIZONTAL;
		gridConstraintsETI.gridwidth=1;
		gridConstraintsETI.gridheight=1;
		gridConstraintsETI.anchor=GridBagConstraints.EAST;
		gridConstraintsETI.gridx=5;
		gridConstraintsETI.gridy=4;
		
		seleccionIndicador.setText("");
		seleccionIndicador.setEditable(Boolean.FALSE);
		GridBagConstraints gridConstraintsSI = new GridBagConstraints();
		gridConstraintsSI.fill=GridBagConstraints.HORIZONTAL;
		gridConstraintsSI.gridwidth=3;
		gridConstraintsSI.gridheight=1;
		gridConstraintsSI.anchor=GridBagConstraints.WEST;
		gridConstraintsSI.gridx=6;
		gridConstraintsSI.gridy=4;

		tablaIndicador.setModel(new ModeloTablaIndicadores());
		tablaIndicador.setBackground(Color.CYAN);
		tablaIndicador.setGridColor(Color.LIGHT_GRAY);
		tablaIndicador.setPreferredScrollableViewportSize(new Dimension(300,100));
		scrollTablaIndicador=new JScrollPane(tablaIndicador,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		GridBagConstraints gridConstraintsSTI = new GridBagConstraints();
		gridConstraintsSTI.fill=GridBagConstraints.HORIZONTAL;
		gridConstraintsSTI.gridwidth=5;
		gridConstraintsSTI.gridheight=2;
		gridConstraintsSTI.anchor=GridBagConstraints.WEST;
		gridConstraintsSTI.gridx=5;
		gridConstraintsSTI.gridy=5;

		labelIndicadorRegistrosTotales.setText("Tot.Registros Cons.:");
		labelIndicadorRegistrosTotales.setFont(new Font("Times-Roman", Font.BOLD + Font.ITALIC, 12));
		GridBagConstraints gridConstraintsLIRT = new GridBagConstraints();
		gridConstraintsLIRT.fill=GridBagConstraints.NONE;
		gridConstraintsLIRT.gridwidth=1;
		gridConstraintsLIRT.gridheight=1;
		gridConstraintsLIRT.anchor=GridBagConstraints.EAST;
		gridConstraintsLIRT.gridx=5;
		gridConstraintsLIRT.gridy=8;

		textIndicadorRegistrosTotales.setEditable(Boolean.FALSE);
		GridBagConstraints gridConstraintsTIRT = new GridBagConstraints();
		gridConstraintsTIRT.fill=GridBagConstraints.HORIZONTAL;
		gridConstraintsTIRT.gridwidth=1;
		gridConstraintsTIRT.gridheight=1;
		gridConstraintsTIRT.anchor=GridBagConstraints.CENTER;
		gridConstraintsTIRT.gridx=6;
		gridConstraintsTIRT.gridy=8;

		labelIndicadorPaginasTotales.setText("Tot.Paginas Cons.:");
		labelIndicadorPaginasTotales.setFont(new Font("Times-Roman", Font.BOLD + Font.ITALIC, 12));
		GridBagConstraints gridConstraintsLIPT = new GridBagConstraints();
		gridConstraintsLIPT.fill=GridBagConstraints.NONE;
		gridConstraintsLIPT.gridwidth=1;
		gridConstraintsLIPT.gridheight=1;
		gridConstraintsLIPT.anchor=GridBagConstraints.WEST;
		gridConstraintsLIPT.gridx=7;
		gridConstraintsLIPT.gridy=8;

		textIndicadorPaginasTotales.setEditable(Boolean.FALSE);
		GridBagConstraints gridConstraintsTIPT = new GridBagConstraints();
		gridConstraintsTIPT.fill=GridBagConstraints.HORIZONTAL;
		gridConstraintsTIPT.gridwidth=2;
		gridConstraintsTIPT.gridheight=1;
		gridConstraintsTIPT.anchor=GridBagConstraints.CENTER;
		gridConstraintsTIPT.gridx=8;
		gridConstraintsTIPT.gridy=8;
		
		anteriorPaginaIndicador.setText("Anterior Página");
		GridBagConstraints gridConstraintsAPI = new GridBagConstraints();
		gridConstraintsAPI.fill=GridBagConstraints.HORIZONTAL;
		gridConstraintsAPI.gridwidth=1;
		gridConstraintsAPI.gridheight=1;
		gridConstraintsAPI.anchor=GridBagConstraints.EAST;
		gridConstraintsAPI.gridx=5;
		gridConstraintsAPI.gridy=9;
		
		paginaActualIndicador.setText("0");
		paginaActualIndicador.setEditable(Boolean.FALSE);
		GridBagConstraints gridConstraintsTRMI = new GridBagConstraints();
		gridConstraintsTRMI.fill=GridBagConstraints.HORIZONTAL;
		gridConstraintsTRMI.gridwidth=1;
		gridConstraintsTRMI.gridheight=1;
		gridConstraintsTRMI.anchor=GridBagConstraints.CENTER;
		gridConstraintsTRMI.gridx=6;
		gridConstraintsTRMI.gridy=9;

		
		siguientePaginaIndicador.setText("Siguiente Página");
		GridBagConstraints gridConstraintsSPI = new GridBagConstraints();
		gridConstraintsSPI.fill=GridBagConstraints.HORIZONTAL;
		gridConstraintsSPI.gridwidth=1;
		gridConstraintsSPI.gridheight=1;
		gridConstraintsSPI.anchor=GridBagConstraints.WEST;
		gridConstraintsSPI.gridx=7;
		gridConstraintsSPI.gridy=9;
		
		
		//Estadisticas
		etiquetaTablaEstadistica.setText("Estadisticas para :");
		etiquetaTablaEstadistica.setFont(new Font("Times-Roman", Font.BOLD + Font.ITALIC, 12));
		GridBagConstraints gridConstraintsETE = new GridBagConstraints();
		gridConstraintsETE.fill=GridBagConstraints.HORIZONTAL;
		gridConstraintsETE.gridwidth=1;
		gridConstraintsETE.gridheight=1;
		gridConstraintsETE.anchor=GridBagConstraints.EAST;
		gridConstraintsETE.gridx=0;
		gridConstraintsETE.gridy=10;

		labelPaisSeleccionado.setText("Pais Seleccionado :");
		labelPaisSeleccionado.setFont(new Font("Times-Roman", Font.BOLD + Font.ITALIC, 12));
		GridBagConstraints gridConstraintsEtPS = new GridBagConstraints();
		gridConstraintsEtPS.fill=GridBagConstraints.HORIZONTAL;
		gridConstraintsEtPS.gridwidth=1;
		gridConstraintsEtPS.gridheight=1;
		gridConstraintsEtPS.anchor=GridBagConstraints.EAST;
		gridConstraintsEtPS.gridx=0;
		gridConstraintsEtPS.gridy=11;

		estadisticaPaisSeleccionado.setEditable(Boolean.FALSE);
		GridBagConstraints gridConstraintsEPS = new GridBagConstraints();
		gridConstraintsEPS.fill=GridBagConstraints.HORIZONTAL;
		gridConstraintsEPS.gridwidth=1;
		gridConstraintsEPS.gridheight=1;
		gridConstraintsEPS.anchor=GridBagConstraints.WEST;
		gridConstraintsEPS.gridx=1;
		gridConstraintsEPS.gridy=11;

		labelIndicadorSeleccionado.setText("Indicador Seleccionado :");
		labelIndicadorSeleccionado.setFont(new Font("Times-Roman", Font.BOLD + Font.ITALIC, 12));
		GridBagConstraints gridConstraintsEtIS = new GridBagConstraints();
		gridConstraintsEtIS.fill=GridBagConstraints.HORIZONTAL;
		gridConstraintsEtIS.gridwidth=1;
		gridConstraintsEtIS.gridheight=1;
		gridConstraintsEtIS.anchor=GridBagConstraints.EAST;
		gridConstraintsEtIS.gridx=4;
		gridConstraintsEtIS.gridy=11;

		estadisticaIndicadorSeleccionado.setEditable(Boolean.FALSE);
		GridBagConstraints gridConstraintsEIS = new GridBagConstraints();
		gridConstraintsEIS.fill=GridBagConstraints.HORIZONTAL;
		gridConstraintsEIS.gridwidth=2;
		gridConstraintsEIS.gridheight=1;
		gridConstraintsEIS.anchor=GridBagConstraints.WEST;
		gridConstraintsEIS.gridx=5;
		gridConstraintsEIS.gridy=11;

		tablaEstadistica.setModel(new ModeloTablaEstadisticas());
		tablaEstadistica.setBackground(Color.CYAN);
		tablaEstadistica.setGridColor(Color.LIGHT_GRAY);
		tablaEstadistica.setPreferredScrollableViewportSize(new Dimension(300,100));
		scrollTablaEstadistica=new JScrollPane(tablaEstadistica,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		GridBagConstraints gridConstraintsSTE = new GridBagConstraints();
		gridConstraintsSTE.fill=GridBagConstraints.HORIZONTAL;
		gridConstraintsSTE.gridwidth=GridBagConstraints.REMAINDER;
		gridConstraintsSTE.gridheight=GridBagConstraints.REMAINDER;
		gridConstraintsSTE.anchor=GridBagConstraints.WEST;
		gridConstraintsSTE.gridx=0;
		gridConstraintsSTE.gridy=12;
		
		panelPpal.setLayout(new GridBagLayout());
		
		panelPpal.add(etiquetaDirIPConfigurada,gridConstraintsEDIP);
		panelPpal.add(textDirIPConfigurada,gridConstraintsTDIP);
		panelPpal.add(etiquetaPuertoConfigurado,gridConstraintsEP);
		panelPpal.add(textPuertoConfigurado,gridConstraintsTP);
		panelPpal.add(etiquetaTamPaginaConfigurada,gridConstraintsETPC);
		panelPpal.add(textTamPaginaConfigurado,gridConstraintsTTPC);
		
		panelPpal.add(etiquetaTablaPais,gridConstraintsETP);
		panelPpal.add(seleccionPais,gridConstraintsSP);
		panelPpal.add(scrollTablaPais,gridConstraintsSTP);
		panelPpal.add(labelPaisRegistrosTotales,gridConstraintsLPRT);
		panelPpal.add(textPaisRegistrosTotales,gridConstraintsTPRT);
		panelPpal.add(labelPaisPaginasTotales,gridConstraintsLPPT);
		panelPpal.add(textPaisPaginasTotales,gridConstraintsTPPT);
		panelPpal.add(anteriorPaginaPais,gridConstraintsAPPB);
		panelPpal.add(paginaActualPais,gridConstraintsTRMP);
		panelPpal.add(siguientePaginaPais,gridConstraintsSPPB);
		
		panelPpal.add(botonReset,gridConstraintsBR);
		
		panelPpal.add(etiquetaTablaIndicador,gridConstraintsETI);
		panelPpal.add(seleccionIndicador,gridConstraintsSI);
		panelPpal.add(scrollTablaIndicador,gridConstraintsSTI);
		panelPpal.add(labelIndicadorRegistrosTotales,gridConstraintsLIRT);
		panelPpal.add(textIndicadorRegistrosTotales,gridConstraintsTIRT);
		panelPpal.add(labelIndicadorPaginasTotales,gridConstraintsLIPT);
		panelPpal.add(textIndicadorPaginasTotales,gridConstraintsTIPT);
		panelPpal.add(anteriorPaginaIndicador,gridConstraintsAPI);
		panelPpal.add(paginaActualIndicador,gridConstraintsTRMI);
		panelPpal.add(siguientePaginaIndicador,gridConstraintsSPI);
		
		panelPpal.add(etiquetaTablaEstadistica,gridConstraintsETE);
		panelPpal.add(labelPaisSeleccionado,gridConstraintsEtPS);
		panelPpal.add(estadisticaPaisSeleccionado,gridConstraintsEPS);
		panelPpal.add(labelIndicadorSeleccionado,gridConstraintsEtIS);
		panelPpal.add(estadisticaIndicadorSeleccionado,gridConstraintsEIS);	
		panelPpal.add(scrollTablaEstadistica,gridConstraintsSTE);
	}
	
	

	/**
	 * Establece los paises a la tabla de paises
	 * Si los datos han sido obtenidos con un filtro fija el filtro
	 * @param paises
	 * @param filtro
	 */
	public void setPaises(Collection<Pais> paises,String filtro){
		tablaPais.setModel(new ModeloTablaPaises(paises,filtro));
	}
	
	/**
	 * Establece el contador(numero de registros) de la consulta de paises que ha cargado de la tabla
	 * Calcula las paginas de datos que tiene dicha consulta
	 * @param numeroRegistros
	 */
	public void setPaisesCount(int numeroRegistros){
		textPaisRegistrosTotales.setText(numeroRegistros+"");
		float paginas=(numeroRegistros/TAMAÑOPAGINA);
		float resto=(numeroRegistros%TAMAÑOPAGINA);
		if(resto>0)
			paginas=paginas+1;
		textPaisPaginasTotales.setText(paginas+"");
	}

	/**
	 * Establece el contador(numero de registros) de la consulta de indicadores que ha cargado de la tabla
	 * Calcula las paginas de datos que tiene dicha consulta
	 * @param numeroRegistros
	 */
	public void setIndicadoresCount(int numeroRegistros){
		textIndicadorRegistrosTotales.setText(numeroRegistros+"");
		float paginas=(numeroRegistros/TAMAÑOPAGINA);
		float resto=(numeroRegistros%TAMAÑOPAGINA);
		if(resto>0)
			paginas=paginas+1;
		textIndicadorPaginasTotales.setText(paginas+"");
	}

	/**
	 * Establece los indicadotes a la tabla de indicadores
	 * Si los datos han sido obtenidos con un filtro fija el filtro
	 * @param paises
	 * @param filtro
	 */
	public void setIndicadores(Collection<Indicador> indicadores,String filtro){
		tablaIndicador.setModel(new ModeloTablaIndicadores(indicadores,filtro));
	}
	
	/**
	 * Forma la tabla de estadisticas(No paginada) y formatea el grafico de las mismas
	 * @param estadisticas
	 */
	public void setEstadisticas(Collection<Estadistica> estadisticas){
		tablaEstadistica.setModel(new ModeloTablaEstadisticas(estadisticas));
		formatearEstadisticas(estadisticas);
	}

	/**
	 * Formatea las estadisticas a formato grafico utilizando JFreeChart
	 * @param estadisticas
	 */
	private void formatearEstadisticas(Collection<Estadistica>estadisticas){
		System.out.println("Formateando Estadisticas "+estadisticas.size());
		//Si no hay estadisticas o solo tenemos un dato(para pintar una serie necesario mas de un punto)
		if(estadisticas.size() >1){
			if (contenedorPaneles.getTabCount()==2)
			      contenedorPaneles.removeTabAt(1);
			XYSeries serieEstadistica= new XYSeries("Datos Estadisticos");
			Iterator<Estadistica>iteraEstadistica=estadisticas.iterator();
			//Itero los datos estadisticos obtenidos y formo la serie a pintar
			while(iteraEstadistica.hasNext()){
				Estadistica estadistica=iteraEstadistica.next();
				serieEstadistica.add(estadistica.getYear(),estadistica.getValor());
			}
			XYDataset visualizado=new XYSeriesCollection(serieEstadistica);
			JFreeChart grafico=ChartFactory.createXYLineChart("Estadisticas para valores de indicador: "+indicadorSeleccionado.getDescripcion() +" y pais: "+paisSeleccionado.getDescripcion(), "Años", "Valores", visualizado, PlotOrientation.VERTICAL, true, true, true);
					//createXYAreaChart("Prueba", "Años", "Valores", visualizado, PlotOrientation.VERTICAL, true, true, true);
			panelGrafico=new ChartPanel(grafico);
			contenedorPaneles.addTab("Gráficas", panelGrafico);
			contenedorPaneles.setSelectedIndex(1);
		}else
			JOptionPane.showMessageDialog(null, "No hay Estadisticas/son insuficientes para pintar sobre el indicador y/o pais seleccionado.Seleccione otro pais/indicador","Aviso",JOptionPane.WARNING_MESSAGE);
	}
	
	/**
	 * Selecciona un pais para la obtencion de estadisticas
	 * Si hay Indicador seleccionado lanza la carga de Estadisticas
	 * @param pais
	 * @throws ErrorComunicacionNegocioException 
	 */
	protected void seleccionarPaisEstadisticas(Pais pais) throws ErrorComunicacionNegocioException{
		estadisticaPaisSeleccionado.setText(pais.getCodigo());
		if(!estadisticaIndicadorSeleccionado.getText().trim().equals("")){
			cliente.obtenerEstadisticaByIndicadorPais(estadisticaIndicadorSeleccionado.getText().trim(), pais.getCodigo());
		}
	}

	/**
	 * Selecciona un indicador para la obtencion de estadisticas
	 * Si hay Pais seleccionado lanza la carga de Estadisticas
	 * @param codIndicador
	 * @throws ErrorComunicacionNegocioException 
	 */
	protected void seleccionarIndicadorEstadisticas(Indicador indicador) throws ErrorComunicacionNegocioException{
		estadisticaIndicadorSeleccionado.setText(indicador.getCodigo());
		if(!estadisticaPaisSeleccionado.getText().trim().equals("")){
			cliente.obtenerEstadisticaByIndicadorPais(indicador.getCodigo(), estadisticaPaisSeleccionado.getText().trim());
		}
	}
	
	/**
	 * Inicializa la tabla de estadisticas y elimina la pestaña grafica
	 */
	protected void deSeleccionarIndicadorEstadisticas(){
		estadisticaIndicadorSeleccionado.setText("".trim());
		tablaEstadistica.setModel(new ModeloTablaEstadisticas());
		if (contenedorPaneles.getTabCount()==2)
		      contenedorPaneles.removeTabAt(1);
	}
	
	/**
	 * Inicializa la tabla de estadisticas y elimina la pestaña grafica
	 */	
	protected void deSeleccionarPaisEstadisticas(){
		estadisticaPaisSeleccionado.setText("".trim());
		tablaEstadistica.setModel(new ModeloTablaEstadisticas());
		if (contenedorPaneles.getTabCount()==2)
		      contenedorPaneles.removeTabAt(1);
	}
	
	/**
	 * Inicializa la tabla de indicadores
	 */
	protected void inicializarTablaIndicadores(){
		tablaIndicador.setModel(new ModeloTablaIndicadores());
		seleccionIndicador.setText("");
		paginaActualIndicador.setText("");
		textIndicadorPaginasTotales.setText("");
		textIndicadorRegistrosTotales.setText("");
		estadisticaIndicadorSeleccionado.setText("");
	}
	
	/**
	 * Inicializa la tabla de paises
	 */
	protected void inicializarTablaPais(){
		tablaPais.setModel(new ModeloTablaPaises());
		seleccionPais.setText("");
		paginaActualPais.setText("");
		textPaisPaginasTotales.setText("");
		textPaisRegistrosTotales.setText("");
		estadisticaPaisSeleccionado.setText("");
	}
	
	/**
	 * Inicializa la tabla de estadisticas
	 */
	protected void inicializarTablaEstadisticas(){
		deSeleccionarIndicadorEstadisticas();
		deSeleccionarPaisEstadisticas();
    }
	
    /**
     * Carga la configuracion del applet
     * El archivo de configuracion se llama configuracion.properties y debe ubicarse en la raiz del proyecto(nivel por encima del src/bin)
     * Valores como dirIP y puerto del servidor asi como el tamaño de las paginas de datos
     * Comprueba que los valores sean correctos (puerto y tamaño pagina enteros y dirrecionIP InetAddress correcta) o lanza exception
     * @throws ErrorConfiguracionNegocioException 
     */
	protected void cargarConfiguracion() throws ErrorConfiguracionNegocioException{
	  File archivoConfiguracion;
	  try{	
		  archivoConfiguracion= new File(getCodeBase().getFile()).getParentFile();  
		  archivoConfiguracion=new File(archivoConfiguracion.getPath()+System.getProperty("file.separator")+"configuracion.properties");
		configuracion=new Properties();
		configuracion.load(new FileInputStream(archivoConfiguracion));
		//Comprobamos la correccion del la configuracion
		getDireccionIPServidor();
		getPuertoServidor();
		getTamanoPaginas();
	  }catch(FileNotFoundException fnfe){
		  fnfe.printStackTrace();
		  throw new ErrorConfiguracionNegocioException("Fichero de configuracion no encontrado.Revise su ubicacion",fnfe);
	  }catch (IOException ioe) {
		  ioe.printStackTrace();
		  throw new ErrorConfiguracionNegocioException("Error al cargar Fichero de configuracion.Revise su ubicacion",ioe);
	  }catch(AccessControlException ace){
		  ace.printStackTrace();
		  throw new ErrorConfiguracionNegocioException("Existe algun problema de permisos.Revise java.policy",ace);
	  }
	}
	
	/**
	 * Devuelve el puerto configurado donde escuchara el servidor
	 * Utilizado por el cliente para establecer la comunicacion
	 * @return
	 * @throws ErrorConfiguracionNegocioException
	 */
	public int getPuertoServidor() throws ErrorConfiguracionNegocioException{
		try{
		  return Integer.parseInt(configuracion.getProperty("puerto"));
		}catch(NumberFormatException nfe){
			nfe.printStackTrace();
			throw new ErrorConfiguracionNegocioException("Puerto del servidor erroneamente configurada.Revise Configuracion(fichero configuracion.properties)",nfe);
		}
	}
	
	/**
	 * Devuelve la direccionIP configurada donde escuchará el servidor
	 * Utilizado por el cliente para establecer la comunicacion
	 * @return
	 * @throws ErrorConfiguracionNegocioException
	 */
	public InetAddress getDireccionIPServidor() throws ErrorConfiguracionNegocioException{
		try{
		    return InetAddress.getByName(configuracion.getProperty("direccionIP"));
		}catch(UnknownHostException uhe){
			uhe.printStackTrace();
			throw new ErrorConfiguracionNegocioException("Direccion IP del servidor erroneamente configurada.Revise Configuracion(fichero configuracion.properties)",uhe);
		}
	}
	
	/**
	 * Devuelve el tamaño de pagina de datos configurado
	 * @return
	 * @throws ErrorConfiguracionNegocioException
	 */
	protected int getTamanoPaginas() throws ErrorConfiguracionNegocioException{
	    try{	
		   return Integer.parseInt(configuracion.getProperty("tamanoPagina"));
	    }catch(NumberFormatException nfe){
	    	nfe.printStackTrace();
	    	throw new ErrorConfiguracionNegocioException("Tamaño de pagina erroneamente configurado.Revise configuracion(fichero configuracion.properties)",nfe);
	    }
	}
}
