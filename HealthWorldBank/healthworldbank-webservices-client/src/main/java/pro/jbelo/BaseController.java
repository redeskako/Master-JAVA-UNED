package pro.jbelo;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import pro.jbelo.model.CountryModel;

import java.io.IOException;
import java.net.URL;

/**
 * @author jbelo
 * @date 2017 08.
 */
public class BaseController {

    protected Alert alert;

    protected static CountryModel countryModel;


    protected void navigate(Event event, URL fxmlDocName) throws IOException {
        Parent pageParent = FXMLLoader.load(fxmlDocName);
        Scene scene = new Scene(pageParent);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.hide();
        appStage.setScene(scene);
        appStage.show();
    }

}
