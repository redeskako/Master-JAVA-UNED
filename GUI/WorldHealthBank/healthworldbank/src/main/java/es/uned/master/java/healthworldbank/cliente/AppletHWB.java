package es.uned.master.java.healthworldbank.cliente;

import javax.swing.*;

/**
 * @author jbelo
 */
public class AppletHWB extends JApplet{


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
        appletController.loadBaseMap();
    }

    @Override
    public void start() {
        super.start();
        // TODO manejar este método
    }

    @Override
    public void stop() {
        super.stop();
        // TODO manejar este método
    }

    @Override
    public void destroy() {
        super.destroy();
        // TODO manejar este método
    }
}
