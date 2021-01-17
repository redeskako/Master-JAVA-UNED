package pro.jbelo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import pro.jbelo.model.CountryModel;
import pro.jbelo.model.Model;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static java.util.Objects.nonNull;
import static javafx.scene.control.Alert.AlertType.WARNING;
import static pro.jbelo.FXMLPage.LIST;
/**
 * @author jbelo
 * @date 2017 08.
 */
public class AddEditController extends BaseController implements Initializable {

    @FXML
    public TextField codeTxtField;

    @FXML
    public TextField nameTxtField;

    @FXML
    public Button saveBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        alert = new Alert(WARNING);
        alert.setTitle("Advertencia !!!");
        alert.setHeaderText("Los datos introducidos no son válidos");
        alert.setContentText("Ni el código ni el nombre pueden ser nulos!");

        if (nonNull(countryModel)) {
            codeTxtField.setText(countryModel.getCountryCode());
            nameTxtField.setText(countryModel.getCountryName());
            saveBtn.setText("Update");
        }
    }

    @FXML
    public void doBack(ActionEvent actionEvent) throws IOException {
        navigate(actionEvent,LIST.getPage());


    }

    @FXML
    public void doClear(ActionEvent actionEvent) throws IOException {
        codeTxtField.clear();
        nameTxtField.clear();

    }

    @FXML
    public void doSave(ActionEvent actionEvent) throws IOException{


        if (codeTxtField.getText().trim().equals("")
                || nameTxtField.getText().trim().equals("")) {
            alert.showAndWait();
            return;
        }

        System.out.println("entra en doSave");

        Model model = Model.getInstance();
        model.createCountry(new CountryModel(codeTxtField.getText(),nameTxtField.getText()));

    }
}
