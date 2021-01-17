package es.uned.master.java.healthworldbank.cliente;

import es.uned.master.java.healthworldbank.comunicacion.Pregunta;
import es.uned.master.java.healthworldbank.comunicacion.Respuesta;
import es.uned.master.java.healthworldbank.comunicacion.TipoPeticion;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Modelo de MVC
 * Created by javierbelogarcia on 01/03/2017.
 */
public class AppletModel implements AppletModelInterface {

    /**
     * HOST para establecer la comunicación
     */
    private static final String HOST = "127.0.0.1";

    /**
     * Puerto para establecer la comunicación
     */
    private static final int PORT = 5000;

    /**
     * Referencia al controlador
     */
    private AppletControllerInterface appletController;

    /**
     * Referencia a la vista
     */
    private AppletViewInterface appletView;


    /**
     * Constructor
     */
    public AppletModel() {
    }

    @Override
    public void setController(AppletControllerInterface appletControllerInterface) {
        appletController = appletControllerInterface;
    }

    @Override
    public void setView(AppletViewInterface appletViewInterface) {
        appletView = appletViewInterface;

    }

    @Override
    public Respuesta processRequest(Pregunta pregunta) throws ServerConnectionException{

        Socket socket = null;
        try{
            socket = new Socket( HOST, PORT);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

            objectOutputStream.writeObject(pregunta);
            objectOutputStream.flush();

            Respuesta respuesta = (Respuesta) objectInputStream.readObject();

            if(respuesta.getTipoPeticion().equals(TipoPeticion.ERROR)){
                throw new ServerConnectionException("Error en la conexión con el servidor");

            } else {
                return respuesta;
            }


        } catch (IOException e) {
            e.printStackTrace();
            throw new ServerConnectionException();
        } catch (ClassNotFoundException e){
            throw new ServerConnectionException();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch(IOException e){
                // do nothing
                }
            }
        }
    }

}
