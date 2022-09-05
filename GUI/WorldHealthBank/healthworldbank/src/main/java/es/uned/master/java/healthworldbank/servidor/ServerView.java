package es.uned.master.java.healthworldbank.servidor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


/**
 * Clase encargada de producir la vista de la parte cliente de la aplicación
 * Implementa la interface OnUpdateViewInterface para recibir los mensajes del Contrlador y el Modelo
 * @author jbelo
 */
public class ServerView extends JFrame implements OnUpdateViewInterface {
			
	private JPanel mainPanel;			
	private JPanel panelButtons;			
	private JLabel labelTittle;											
	private JTextArea areaNotifications;		
	private JScrollPane scrollNotifications;	
	private JButton buttonStart;				
	private JButton buttonStop;


    /**
     * Método principal de arranqque de la parte servidor de la aplicación
     * @param args
     */
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                OnUpdateViewInterface viewInterface = new ServerView();
                ServerModel serverModel = new ServerModel(viewInterface);
                ServerController serverController = new ServerController(viewInterface, serverModel);
                viewInterface.setActionListener(serverController);
                viewInterface.start();
            }
        });
    }


    /**
     * Constructor
     */
    public ServerView() {
		super("WHB Server");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout()); 		
		
		labelTittle = new JLabel("Servidor World Health Bank");
        labelTittle.setBackground(new Color(255,204,0));				
        labelTittle.setFont(new Font("Tahoma", 1, 14)); 				
        labelTittle.setHorizontalAlignment(SwingConstants.CENTER);	
        labelTittle.setOpaque(true);								
		labelTittle.setPreferredSize(new Dimension(500,25));		
		mainPanel.add(labelTittle, BorderLayout.NORTH);				

		areaNotifications = new JTextArea();						
		areaNotifications.setLineWrap(true);						
		areaNotifications.setEditable(false);						
        scrollNotifications = new JScrollPane(areaNotifications);	
        scrollNotifications.setPreferredSize(new Dimension(100,140));	
		scrollNotifications.setMinimumSize(new Dimension(100,140));	
		mainPanel.add(scrollNotifications, BorderLayout.CENTER);

		
		panelButtons = new JPanel(new GridLayout(1, 2, 10, 10)); 
        buttonStart = new JButton("Iniciar servidor");
        buttonStart.setActionCommand(BUTTON_START_SERVER);
        buttonStart.setHorizontalTextPosition(SwingConstants.LEFT);
		panelButtons.add(buttonStart);									

		buttonStop = new JButton("Parar servidor");	
		buttonStop.setActionCommand(BUTTON_STOP_SERVER);
        buttonStop.setHorizontalTextPosition(SwingConstants.CENTER);
        buttonStop.setEnabled(false);									
		panelButtons.add(buttonStop);									

		mainPanel.add(panelButtons, BorderLayout.SOUTH);
		getContentPane().add(mainPanel);
			
	}


	@Override
	public void setActionListener(ActionListener actionListener) {
		buttonStart.addActionListener(actionListener);
		buttonStop.addActionListener(actionListener);
	}

	@Override
	public void start() {
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
			
    }
	
	@Override
	public void setStatus(boolean running, String notification ){
		buttonStart.setEnabled(running?false:true);
		buttonStop.setEnabled(running?true:false);
		final String BR = (areaNotifications.getText().equals("")) ? "" : "\n";
		areaNotifications.setText(areaNotifications.getText() + BR + notification);
		areaNotifications.setCaretPosition(areaNotifications.getText().length());
		
	}

	@Override
	public void displayMessage(String message){
		final String BR = (areaNotifications.getText().equals("")) ? "" : "\n";
		areaNotifications.setText(areaNotifications.getText() + BR + message);
		areaNotifications.setCaretPosition(areaNotifications.getText().length());
	}
}
