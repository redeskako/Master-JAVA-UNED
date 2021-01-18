package es.uned.master.java.healthworldbank.servidor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controlador del servidor
 * @author jbelo
 */
public class ServerController implements ActionListener {

    /**
     * Vista del servidor
     */
    private final OnUpdateViewInterface viewInterface;

    /**
     * Modelo del servidor
     */
    private final ServerModel serverModel;

    /**
     * Constructor
     * @param viewInterface Vista del servidor
     * @param serverModel Modelo del servidor
     */
    public ServerController(OnUpdateViewInterface viewInterface, ServerModel serverModel){
		this.viewInterface = viewInterface; 
		this.serverModel = serverModel;
	}
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		switch (actionEvent.getActionCommand()) {
		case OnUpdateViewInterface.BUTTON_START_SERVER:
            serverModel.startServer();
            viewInterface.setStatus(true, "Servidor Arrancado");
			break;
		case OnUpdateViewInterface.BUTTON_STOP_SERVER:
            viewInterface.displayMessage("Cerrando servidor...");
            serverModel.stopServer();
			break;
		}
	}
}
