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
 * Implementa la vista de la aplicaci�n. Incluye el metodo Main y se encarga de crear el controlador
 * y de fijarlo a la vista
 * @author Jos� Barba Mart�nez (jbarba63)
 *
 */
public class SwingClient1 {

	private JFrame frame;
	private JTextField txtUser;
	private JTextField txtPass;
	private JTextField txtGetById_id;
	private JTextField txtGetByHashtag_hashtag;
	private JTextField txtGetByHashtag_count;
	private JTextField txtGetNeighbour_hashtag;
	 /*****INI$IGN$********/
	private JTextField txtGetEstudio_Nct_id;
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


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SwingClient1 window = new SwingClient1(new SwingController1());
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
	public SwingClient1() {
		initialize();
	}
	
	/**
	 * Constructor que permite asociar a esta vista con su controlador
	 * @param cont Instancia del controlador de esta vista.
	 */
	public SwingClient1(SwingController1 cont){
		initialize();
		// fijamos los componentes visuales al alcance del controlador
		cont.setFrame(this.frame);
		cont.setTxtUser(this.txtUser);
		cont.setTxtPass(this.txtPass);
		cont.setTxtGetById_id(this.txtGetById_id);
		cont.setTxtGetByHashtag_hashtag(this.txtGetByHashtag_hashtag);
		cont.setTxtGetByHashtag_count(this.txtGetByHashtag_count);
		cont.setTxtGetNeighbour_hashtag(this.txtGetNeighbour_hashtag);
		 /*****INI$IGN$********/
		cont.setTxtGetEstudio_Nct_id(this.txtGetEstudio_Nct_id);
		 /*****FIN$IGN$********/
		cont.setResponseRS(this.responseRS);
		cont.setParametersWS(this.parametersWS);
		cont.setTxtURI(this.txtURI);
		cont.setLblContext(this.lblWebContext);
		cont.setComboOperationRS(this.comboOperationRS);
		cont.setGoOp(this.goOp);
		// fijamos el controlador a la vista
		cont.attachToView();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(Color.LIGHT_GRAY);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		
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
		
		txtGetByHashtag_hashtag = new JTextField();
		parameterWS_getBycEstudio.add(txtGetByHashtag_hashtag);
		txtGetByHashtag_hashtag.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("Count");
		parameterWS_getBycEstudio.add(lblNewLabel_10);
		
		txtGetByHashtag_count = new JTextField();
		parameterWS_getBycEstudio.add(txtGetByHashtag_count);
		txtGetByHashtag_count.setColumns(10);
		 /*****FIN$IGN$********/
		
		responseRS = new JTextArea();
		responseRS.setLineWrap(true);
		
		JScrollPane responseRSScroll = new JScrollPane(responseRS);
		mainRS.add(responseRSScroll, BorderLayout.CENTER);
		
		
		JPanel mainWS = new JPanel();
		tabbedPane.addTab("SOAP", null, mainWS, null);

		frame.setBounds(100, 100, 350, 635);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
