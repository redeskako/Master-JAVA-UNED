package uned.java2016.apitwitter.services.rs.client;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import uned.java2016.apitwitter.services.rs.client.filters.AuthorizationFilter;

import javax.swing.border.BevelBorder;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JSplitPane;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;


/**
 * Implementa la vista de la aplicación. Incluye el metodo Main y se encarga de crear el controlador
 * y de fijarlo a la vista
 * @author José Barba Martínez (jbarba63)
 *
 */
public class SwingWSClient {
	
	//Componente global
	private JFrame frame;

	//Componentes para la pestaña Restful.
	private JTextField txtUser;
	private JTextField txtPass;
	private JTextField txtGetById_id;
	private JTextField txtGetByHashtag_hashtag;
	private JTextField txtGetByHashtag_count;
	private JTextField txtGetNeighbour_hashtag;
	 /*****INI$IGN$********/
	private JTextField txtGetEstudio_Nct_id;
	private JTextField txtGetBycEstudio_hashtag;
	private JTextField txtGetBycEstudio_count;
	 /*****FIN$IGN$********/
	private JTextArea responseRS;
	private JPanel parametersWS;
	private JTextField txtURI;
	private JComboBox comboOperationRS;
	private JButton goOp;
	
	public static final String cardOp_getById="getById";
	public static final String cardOp_getByHashtag="getByHashtag";
	public static final String cardOp_getNeighbour="getNeighbour";
	 /*****INI$IGN$********/
	public static final String cardOp_getByNCT="getByNCT";
	public static final String cardOp_getByEstudio="getByEstudio";
	 /*****FIN$IGN$********/
	private JLabel lblWebContext;

    //Componentes para la pestaña SOAP.
	
	private JTextField SPtxtUser;
	private JTextField SPtxtPass;
	private JTextField SPtxtGetById_id;
	private JTextField SPtxtGetByHashtag_hashtag;
	private JTextField SPtxtGetByHashtag_count;
	private JTextField SPtxtGetNeighbour_hashtag;
	 /*****INI$IGN$********/
	private JTextField SPtxtGetEstudio_Nct_id;
	private JTextField SPtxtGetBycEstudio_hashtag;
	private JTextField SPtxtGetBycEstudio_count;
	
	 /*****FIN$IGN$********/
	private JTextArea responseSP;
	private JPanel parametersWSSOAP;
	private JTextField SPtxtURI;
	private JComboBox comboOperationSP;
	private JButton SPgoOp;
	
	public static final String spcardOp_getById="getById";
	public static final String spcardOp_getByHashtag="getByHashtag";
	public static final String spcardOp_getNeighbour="getNeighbour";
	 /*****INI$IGN$********/
	public static final String spcardOp_getByNCT="getByNCT";
	public static final String spcardOp_getByEstudio="getByEstudio";
	 /*****FIN$IGN$********/
	private JLabel SPlblWebContext;


	
	
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SwingWSClient window = new SwingWSClient(new SwingRSController(), new SwingSOAPController());
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SwingWSClient() {
		initialize();
	}
	
	/**
	 * Constructor que permite asociar a esta vista con su controlador
	 * @param cont Instancia del controlador de esta vista.
	 */
	public SwingWSClient(SwingRSController cont1, SwingSOAPController cont2){
		initialize();
		// fijamos los componentes visuales al alcance del controlador
		cont1.setFrame(this.frame);
		cont1.setTxtUser(this.txtUser);
		cont1.setTxtPass(this.txtPass);
		cont1.setTxtGetById_id(this.txtGetById_id);
		cont1.setTxtGetByHashtag_hashtag(this.txtGetByHashtag_hashtag);
		cont1.setTxtGetByHashtag_count(this.txtGetByHashtag_count);
		cont1.setTxtGetNeighbour_hashtag(this.txtGetNeighbour_hashtag);
		 /*****INI$IGN$********/
		cont1.setTxtGetEstudio_Nct_id(this.txtGetEstudio_Nct_id);
		cont1.setTxtGetByEstudio_hashtag(this.txtGetBycEstudio_hashtag);
		cont1.setTxtGetByEstudio_count(this.txtGetBycEstudio_count);
		 /*****FIN$IGN$********/
		cont1.setResponseRS(this.responseRS);
		cont1.setParametersWS(this.parametersWS);
		cont1.setTxtURI(this.txtURI);
		cont1.setLblContext(this.lblWebContext);
		cont1.setComboOperationRS(this.comboOperationRS);
		cont1.setGoOp(this.goOp);
		// fijamos el controlador a la vista
		cont1.attachToView();
		
		// fijamos los componentes visuales al controlador de la pestaña SOAP.
		cont2.setFrame(this.frame);
		cont2.setTxtUser(this.SPtxtUser);
		cont2.setTxtPass(this.SPtxtPass);
		cont2.setTxtGetById_id(this.SPtxtGetById_id);
		cont2.setTxtGetByHashtag_hashtag(this.SPtxtGetByHashtag_hashtag);
		cont2.setTxtGetByHashtag_count(this.SPtxtGetByHashtag_count);
		cont2.setTxtGetNeighbour_hashtag(this.SPtxtGetNeighbour_hashtag);
		 /*****INI$IGN$********/
		cont2.setTxtGetEstudio_Nct_id(this.SPtxtGetEstudio_Nct_id);
		cont2.setTxtGetByEstudio_hashtag(this.SPtxtGetBycEstudio_hashtag);
		cont2.setTxtGetByEstudio_count(this.SPtxtGetBycEstudio_count);
		 /*****FIN$IGN$********/
		cont2.setResponseRS(this.responseSP);
		cont2.setParametersWS(this.parametersWSSOAP);
		cont2.setTxtURI(this.SPtxtURI);
		cont2.setLblContext(this.SPlblWebContext);
		cont2.setComboOperationRS(this.comboOperationSP);
		cont2.setGoOp(this.SPgoOp);
		// fijamos el controlador a la vista
		cont2.attachToView();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(Color.LIGHT_GRAY);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		
		//Inicializamos la primera pestaña.
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel mainRS = new JPanel();
		tabbedPane.addTab("RESTful", null, mainRS, null);
		mainRS.setLayout(new BorderLayout(0, 0));
		
		JPanel operationRS = new JPanel();
		operationRS.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		mainRS.add(operationRS, BorderLayout.NORTH);
		operationRS.setLayout(new BorderLayout(0, 0));
		
		JPanel comboWSPanel = new JPanel();
		operationRS.add(comboWSPanel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("User");
		comboWSPanel.add(lblNewLabel);
		
		txtUser = new JTextField();
		txtUser.setText("carlos");
		comboWSPanel.add(txtUser);
		txtUser.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Pass");
		comboWSPanel.add(lblNewLabel_1);
		
		txtPass = new JTextField();
		txtPass.setText("carlos");
		comboWSPanel.add(txtPass);
		txtPass.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Op");
		comboWSPanel.add(lblNewLabel_5);
		String[] comboItems={this.cardOp_getById,this.cardOp_getByHashtag,this.cardOp_getNeighbour, this.cardOp_getByNCT,this.cardOp_getByEstudio};
		comboOperationRS = new JComboBox(comboItems);
		
		comboOperationRS.setMaximumSize(new Dimension(50, 22));
		comboWSPanel.add(comboOperationRS);
		
		goOp = new JButton("GO!");
		comboWSPanel.add(goOp);
		
		JPanel hostWSPanel = new JPanel();
		operationRS.add(hostWSPanel, BorderLayout.CENTER);
		hostWSPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_6 = new JLabel("URI");
		hostWSPanel.add(lblNewLabel_6);
		
		txtURI = new JTextField();
		txtURI.setText("http://localhost:8080");
		hostWSPanel.add(txtURI);
		txtURI.setColumns(15);
		
		lblWebContext = new JLabel("New label");
		hostWSPanel.add(lblWebContext);
		
		parametersWS = new JPanel();
		parametersWS.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		operationRS.add(parametersWS, BorderLayout.SOUTH);
		parametersWS.setLayout(new CardLayout(0, 0));
		
		JPanel parameterWS_getById = new JPanel();
		parametersWS.add(parameterWS_getById, cardOp_getById);
		
		JLabel lblNewLabel_2 = new JLabel("Id");
		parameterWS_getById.add(lblNewLabel_2);
		
		txtGetById_id = new JTextField();
		parameterWS_getById.add(txtGetById_id);
		txtGetById_id.setColumns(20);
		
		JPanel parameterWS_getByHashtag = new JPanel();
		parametersWS.add(parameterWS_getByHashtag, cardOp_getByHashtag);
		
		JLabel lblNewLabel_3 = new JLabel("Hashtag");
		parameterWS_getByHashtag.add(lblNewLabel_3);
		
		txtGetByHashtag_hashtag = new JTextField();
		parameterWS_getByHashtag.add(txtGetByHashtag_hashtag);
		txtGetByHashtag_hashtag.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Count");
		parameterWS_getByHashtag.add(lblNewLabel_4);
		
		txtGetByHashtag_count = new JTextField();
		parameterWS_getByHashtag.add(txtGetByHashtag_count);
		txtGetByHashtag_count.setColumns(10);
		
		JPanel parameterWS_getNeighbour = new JPanel();
		parametersWS.add(parameterWS_getNeighbour, cardOp_getNeighbour);
		
		JLabel lblNewLabel_7 = new JLabel("Hashtag");
		parameterWS_getNeighbour.add(lblNewLabel_7);
		
		txtGetNeighbour_hashtag = new JTextField();
		parameterWS_getNeighbour.add(txtGetNeighbour_hashtag);
		txtGetNeighbour_hashtag.setColumns(20);
		
		/******INI$IGN$txtGetEstudio_Nct_id.......getByNCT*******/
		JPanel parameterWS_getByNCT = new JPanel();
		parametersWS.add(parameterWS_getByNCT, cardOp_getByNCT);
		
		JLabel lblNewLabel_8 = new JLabel("Nct_id");
		parameterWS_getByNCT.add(lblNewLabel_8);
		
		txtGetEstudio_Nct_id = new JTextField();
		parameterWS_getByNCT.add(txtGetEstudio_Nct_id);
		txtGetEstudio_Nct_id.setColumns(20);
		
		
		JPanel parameterWS_getBycEstudio = new JPanel();
		parametersWS.add(parameterWS_getBycEstudio, cardOp_getByEstudio);
		
		JLabel lblNewLabel_9 = new JLabel("Hashtag");
		parameterWS_getBycEstudio.add(lblNewLabel_9);
		
		txtGetBycEstudio_hashtag = new JTextField();
		parameterWS_getBycEstudio.add(txtGetBycEstudio_hashtag);
		txtGetBycEstudio_hashtag.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("Count");
		parameterWS_getBycEstudio.add(lblNewLabel_10);
		
		txtGetBycEstudio_count = new JTextField();
		parameterWS_getBycEstudio.add(txtGetBycEstudio_count);
		txtGetBycEstudio_count.setColumns(10);
		 /*****FIN$IGN$********/
		
		responseRS = new JTextArea();
		responseRS.setLineWrap(true);
		
		JScrollPane responseRSScroll = new JScrollPane(responseRS);
		mainRS.add(responseRSScroll, BorderLayout.CENTER);
		
		
		JPanel mainSP = new JPanel();
		tabbedPane.addTab("SOAP", null, mainSP, null);
		
		//Inicializamos la segunda pestaña.
        mainSP.setLayout(new BorderLayout(0, 0));
		
		JPanel operationSP = new JPanel();
		operationSP.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		mainSP.add(operationSP, BorderLayout.NORTH);
		operationSP.setLayout(new BorderLayout(0, 0));
		
		JPanel comboSPPanel = new JPanel();
		operationSP.add(comboSPPanel, BorderLayout.NORTH);
		
		JLabel SPlblNewLabel = new JLabel("User");
		comboSPPanel.add(SPlblNewLabel);
		
		SPtxtUser = new JTextField();
		SPtxtUser.setText("xxxxx");
		comboSPPanel.add(SPtxtUser);
		SPtxtUser.setColumns(10);
		
		JLabel SPlblNewLabel_1 = new JLabel("Pass");
		comboSPPanel.add(SPlblNewLabel_1);
		
		SPtxtPass = new JTextField();
		SPtxtPass.setText("xxxxx");
		comboSPPanel.add(SPtxtPass);
		SPtxtPass.setColumns(10);
		
		JLabel SPlblNewLabel_5 = new JLabel("Op");
		comboSPPanel.add(SPlblNewLabel_5);
		String[] comboItemsSP={this.spcardOp_getById,this.spcardOp_getByHashtag,this.spcardOp_getNeighbour, this.spcardOp_getByNCT,this.spcardOp_getByEstudio};
		comboOperationSP = new JComboBox(comboItemsSP);
		
		comboOperationSP.setMaximumSize(new Dimension(50, 22));
		comboSPPanel.add(comboOperationSP);
		
		SPgoOp = new JButton("GO!");
		comboSPPanel.add(SPgoOp);
		
		JPanel hostSPPanel = new JPanel();
		operationSP.add(hostSPPanel, BorderLayout.CENTER);
		hostSPPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel SPlblNewLabel_6 = new JLabel("URI");
		hostSPPanel.add(SPlblNewLabel_6);
		
		SPtxtURI = new JTextField();
		SPtxtURI.setText("http://localhost:8080");
		hostSPPanel.add(SPtxtURI);
		SPtxtURI.setColumns(15);
		
		SPlblWebContext = new JLabel("New label");
		hostSPPanel.add(SPlblWebContext);
		
		parametersWSSOAP = new JPanel();
		parametersWSSOAP.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		operationSP.add(parametersWSSOAP, BorderLayout.SOUTH);
		parametersWSSOAP.setLayout(new CardLayout(0, 0));
		
		JPanel parameterSP_getById = new JPanel();
		parametersWSSOAP.add(parameterSP_getById, spcardOp_getById);
		
		JLabel SPlblNewLabel_2 = new JLabel("Id");
		parameterSP_getById.add(SPlblNewLabel_2);
		
		SPtxtGetById_id = new JTextField();
		parameterSP_getById.add(SPtxtGetById_id);
		SPtxtGetById_id.setColumns(20);
		
		JPanel parameterSP_getByHashtag = new JPanel();
		parametersWSSOAP.add(parameterSP_getByHashtag, spcardOp_getByHashtag);
		
		JLabel SPlblNewLabel_3 = new JLabel("Hashtag");
		parameterSP_getByHashtag.add(SPlblNewLabel_3);
		
		SPtxtGetByHashtag_hashtag = new JTextField();
		parameterSP_getByHashtag.add(SPtxtGetByHashtag_hashtag);
		SPtxtGetByHashtag_hashtag.setColumns(10);
		
		JLabel SPlblNewLabel_4 = new JLabel("Count");
		parameterSP_getByHashtag.add(SPlblNewLabel_4);
		
		SPtxtGetByHashtag_count = new JTextField();
		parameterSP_getByHashtag.add(SPtxtGetByHashtag_count);
		SPtxtGetByHashtag_count.setColumns(10);
		
		JPanel parameterSP_getNeighbour = new JPanel();
		parametersWSSOAP.add(parameterSP_getNeighbour, spcardOp_getNeighbour);
		
		JLabel SPlblNewLabel_7 = new JLabel("Hashtag");
		parameterSP_getNeighbour.add(SPlblNewLabel_7);
		
		SPtxtGetNeighbour_hashtag = new JTextField();
		parameterSP_getNeighbour.add(SPtxtGetNeighbour_hashtag);
		SPtxtGetNeighbour_hashtag.setColumns(20);
		
		/******INI$IGN$txtGetEstudio_Nct_id.......getByNCT*******/
		JPanel parameterSP_getByNCT = new JPanel();
		parametersWSSOAP.add(parameterSP_getByNCT, spcardOp_getByNCT);
		
		JLabel SPlblNewLabel_8 = new JLabel("Nct_id");
		parameterSP_getByNCT.add(SPlblNewLabel_8);
		
		SPtxtGetEstudio_Nct_id = new JTextField();
		parameterSP_getByNCT.add(SPtxtGetEstudio_Nct_id);
		SPtxtGetEstudio_Nct_id.setColumns(20);
		
		
		JPanel parameterSP_getBycEstudio = new JPanel();
		parametersWSSOAP.add(parameterSP_getBycEstudio, spcardOp_getByEstudio);
		
		JLabel SPlblNewLabel_9 = new JLabel("Hashtag");
		parameterSP_getBycEstudio.add(SPlblNewLabel_9);
		
		SPtxtGetBycEstudio_hashtag = new JTextField();
		parameterSP_getBycEstudio.add(SPtxtGetBycEstudio_hashtag);
		SPtxtGetBycEstudio_hashtag.setColumns(10);
		
		JLabel SPlblNewLabel_10 = new JLabel("Count");
		parameterSP_getBycEstudio.add(SPlblNewLabel_10);
		
		SPtxtGetBycEstudio_count = new JTextField();
		parameterSP_getBycEstudio.add(SPtxtGetBycEstudio_count);
		SPtxtGetBycEstudio_count.setColumns(10);
		 /*****FIN$IGN$********/
		
		responseSP = new JTextArea();
		responseSP.setLineWrap(true);
		
		JScrollPane responseSPScroll = new JScrollPane(responseSP);
		mainSP.add(responseSPScroll, BorderLayout.CENTER);
		
		

		
		

		frame.setBounds(100, 100, 350, 635);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
