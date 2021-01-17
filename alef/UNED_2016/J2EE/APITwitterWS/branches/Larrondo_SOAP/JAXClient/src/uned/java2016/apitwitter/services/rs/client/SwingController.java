package uned.java2016.apitwitter.services.rs.client;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.glassfish.jersey.logging.LoggingFeature;

import uned.java2016.apitwitter.services.rs.client.filters.AuthorizationFilter;
import uned.java2016.apitwitter.dao.ClinicalStudy;
//import uned.java2016.apitwitter.services.rs.jaxb.Clinicals;
//import uned.java2016.apitwitter.services.rs.jaxb.Clinicals;
import uned.java2016.apitwitter.services.*
;
/**
 * Controlador para la vista 'SwingClient'
 * 
 * @author José Barba Martínez (jbarba63)
 *
 */
public class SwingController {

	/**
	 * Contexto web en el que esta desplegado el servicio web a atacar (revisar web.xml del proyecto APITwitterTagV2)
	 */
	public static final String WS_WEBCONTEXT="/APITwitterWeb/services/rs";
	/**
	 * Frame principal
	 */
	protected JFrame frame=null;
	  public void setFrame(JFrame f){this.frame=f;}
	  public JFrame getFrame(){return this.frame;}		  	
	/**
	 * Boton para lanzar la llamada al WS  
	 */
	protected JButton goOp=null;
	  public void setGoOp(JButton j){this.goOp=j;}
	  public JButton getGoOp(){return this.goOp;}
	/**
	 * Componente para el usuario
	 */
	protected JTextField txtUser=null;
	  public void setTxtUser(JTextField t){this.txtUser=t;}
	  public JTextField getTxtUser(){return this.txtUser;}
	/**
	 * Componente para el password
	 */
	protected JTextField txtPass;
	  public JTextField getTxtPass() {return txtPass;}
	  public void setTxtPass(JTextField txtPass) {this.txtPass = txtPass;}
	/**
	 * Componente para el ID en la operacion 'getById'
	 */
	protected JTextField txtGetById_id;
	  public void setTxtGetById_id(JTextField f){this.txtGetById_id=f;}
	  public JTextField getTxtGetById_id(){return this.txtGetById_id;}
	/**
	 * Componente para el 'hashtag' en la operacion 'getByHasthag'
	 */
	protected JTextField txtGetByHashtag_hashtag;
	  public void setTxtGetByHashtag_hashtag(JTextField f){this.txtGetByHashtag_hashtag=f;}
	  public JTextField getTxtGetByHashtag_hashtag(){return this.txtGetByHashtag_hashtag;}

	  
	  /**
	 * Componente para el 'count' en la operacion 'getByHashtag'
	 */
	protected JTextField txtGetByHashtag_count;
	  public void setTxtGetByHashtag_count(JTextField f){this.txtGetByHashtag_count=f;}
	  public JTextField getTxtGetByHashtag_count(){return this.txtGetByHashtag_count;}
	  
		/**
		 * Componente para el 'hashtag' en la operacion 'getByHasthag'
		 */
	protected JTextField txtGetNeighbour_hashtag;
	  public void setTxtGetNeighbour_hashtag(JTextField f){this.txtGetNeighbour_hashtag=f;}
	  public JTextField getTxtGetNeighbour_hashtag(){return this.txtGetNeighbour_hashtag;}	  
	  
		/**
		 * Componente para el 'hashtag' en la operacion 'getByHasthagSOAP'
		 */  
		protected JTextField txtGetNeighbourSOAP_hashtag;
		  public void setTxtGetNeighbourSOAP_hashtag(JTextField f){this.txtGetNeighbourSOAP_hashtag=f;}
		  public JTextField getTxtGetNeighbourSOAP_hashtag(){return this.txtGetNeighbourSOAP_hashtag;}		  
	/**
	 * componente para mostrar el resultado de la ejecución del cliente de JAX-RS
	 */
	protected JTextArea responseRS;
	  public void setResponseRS(JTextArea a){this.responseRS=a;}
	  public JTextArea getResponseRS(){return this.responseRS;}

	/**
	 * Combo box de seleccion de operacion RS a realizar
	 */
	protected JComboBox comboOperationRS=null;
	  public void setComboOperationRS(JComboBox c){this.comboOperationRS=c;}
	  public JComboBox getComboOperationRS(){return this.comboOperationRS;}
	/**
	 * Panel en el que se muestran los parametros de la operacion seleccionada en el combo de arriba
	 */
	protected JPanel parametersWS=null;  
	  public void setParametersWS(JPanel p){this.parametersWS=p;}
	  public JPanel getParametersWS(){return this.parametersWS;}
	  
	/**
	 * URI en la que se supone desplegada la aplicacion
	 */
	protected JTextField txtURI=null;
	  public void setTxtURI(JTextField t){this.txtURI=t;}
	  public JTextField getTxtURI(){return this.txtURI;}

	/**
	 * Label en el que mostrar el contexto web al que apuntamos.  
	 */
	private JLabel lblWebContext=null;
	  public void setLblContext(JLabel l){this.lblWebContext=l;}
	  public JLabel getLblContext(){return this.lblWebContext;}	 
	  
	/**
	 * Instancia del cliente JAXRS que vamos a usar 
	 */
	protected JAXRSClient client=null;
	  protected void setClient(JAXRSClient c){this.client=c;}
	  protected JAXRSClient getClient(){return this.client;}
		
	public SwingController(){
		this.setClient(new JAXRSClient());
	}
		
	/**
	 * Este método registra todos los listeners necesarios a los componentes visuales de la vista.
	 * Requiere que todas las referencias a los componentes visuales de los que se depende estén
	 * estén valoradas correctamente.
	 */
	public void attachToView(){
		// escuchamos lo necesario para gestionar la vista propiamente dicha
		this.manageView();
		// y también para poder lanzar el negocio cuanod sea necesario
		this.manageLogic();
	}
	
	/**
	 * Registramos los listeners necesarios para poder lanzar la lógica de negocio
	 */
	protected void manageLogic(){
		// colgamos el listerer para la logica de negocio
		this.getGoOp().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingController.this.doBusiness();
			}
			
		});		
	}
	/**
	 * Registra los Listeners y operativa EXCLUSIVA para gestion de interfaz de usuario.
	 * En este caso, se gestiona el panel con los parametros de la operacion seleccionada
	 * e impide q el frame principal cambie de anchura
	 */
	protected void manageView(){
		// gestinamos el cambio en el panel de parametros de WS escuchando los 
		this.getComboOperationRS().addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				// cambiamos la vista en el panel de parametros, para mostrar los parametros
				// que correspondan al servicio seleccionado en el combo
				JPanel p=SwingController.this.getParametersWS();
				  CardLayout cl=(CardLayout) p.getLayout();
				  cl.show(p,(String) e.getItem());
			}
			
		});

		// impedimos que el frame principal se redimensione horizontalmente
		this.getFrame().addComponentListener(new ComponentAdapter(){
		     @Override
		     public void componentResized(ComponentEvent e){
		    	 JFrame f=SwingController.this.getFrame();
		    	 f.setSize(new Dimension(550,f.getHeight()));
		    	 super.componentResized(e);
		     }
		});
		// fijamos el valor del contexto web
		this.getLblContext().setText(this.WS_WEBCONTEXT);
	}
	
	/**
	 * Lanza la logica de negocio, que en este caso consiste en llamar al WS indicado por el usuario usando
	 * la instancia de JAXRSClient que referencia esta instancia, y mostrar en el area de texto el trafico
	 * generado.
	 */
	protected void doBusiness(){
		JAXRSClient c=this.getClient();
		c.setUser(this.getTxtUser().getText());
		c.setPassword(this.getTxtPass().getText());
		String objetoRecuperado=null;
		switch(this.getComboOperationRS().getSelectedItem().toString())
		{
			case "getById":
				objetoRecuperado = (c.getById(this.getTxtURI().getText()+this.WS_WEBCONTEXT,Long.parseLong(this.getTxtGetById_id().getText()))!=null ? "Tweet" : null);
				break;
			case "getByHashtag":
				objetoRecuperado = (c.getByHashtag(this.getTxtURI().getText()+this.WS_WEBCONTEXT,this.getTxtGetByHashtag_hashtag().getText(),Integer.parseInt(this.getTxtGetByHashtag_count().getText()))!=null ? "List<Tweet>" : null);
				break;
			case "getNeighbourRS":
				objetoRecuperado = (c.getNeigbour(this.getTxtURI().getText()+this.WS_WEBCONTEXT,this.getTxtGetNeighbour_hashtag().getText())!=null ? "List<String>" : null);
				break;
			case "getNeighbourWS":

				ClinicalServiceImplService neighbourService = new ClinicalServiceImplService();
				ClinicalService neighbour = neighbourService.getClinicalServiceImplPort();
				Clinicals n = neighbour.getClinicals(this.getTxtGetNeighbourSOAP_hashtag().getText());
				
				ArrayList<ClinicalStudy> tt=n.getNeighbour();
				objetoRecuperado="";
				for(int i=0;i<tt.size();++i){
					objetoRecuperado=objetoRecuperado+tt.get(i).getNctId().toString()+"\n";
					objetoRecuperado=objetoRecuperado+tt.get(i).getBriefTitle().toString()+"\n";
					objetoRecuperado=objetoRecuperado+tt.get(i).getBriefSummary().toString()+"\n";
					objetoRecuperado=objetoRecuperado+tt.get(i).getOrganization().toString()+"\n\n\n";
				}
				
				break;
		}		
		this.responseRS.setText((objetoRecuperado!=null ? "OBJETO "+objetoRecuperado+" RECUPERADO CON EXITO!\n"+c.getTraffic() : c.getTraffic()));
		c.resetTraffic();
	
	}	
}
