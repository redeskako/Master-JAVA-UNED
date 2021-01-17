package es.uned.master.java.healthworldbank.cliente;

import javax.swing.*;

/**
 * Created by javierbelogarcia on 01/03/2017.
 */
public class AppletTest extends JApplet{


    @Override
    public void init() {
        AppletView appletView = new AppletView();
        setContentPane(appletView.getContainer());

        AppletController appletController = new AppletController();
        appletController.setView(appletView);

        appletView.setController(appletController);

        AppletModel appletModel = new AppletModel();
        appletModel.setController(appletController);
        appletModel.setView(appletView);

        appletController.setModel(appletModel);

        // TODO revisar, deberia ser cargado de forma async
        appletController.loadInitialData();
    }


}
