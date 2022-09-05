package pro.jbelo;

import javafx.beans.binding.Bindings;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import pro.jbelo.model.CountryModel;
import pro.jbelo.model.Model;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static java.util.Objects.nonNull;
import static javafx.scene.control.Alert.AlertType.CONFIRMATION;
import static pro.jbelo.FXMLPage.ADD;
import static javafx.scene.control.ButtonType.OK;



/**
 * @author jbelo
 * @date 2017 08.
 */
public class ListCountriesController extends  BaseController implements Initializable{


    @FXML
    public TextField searchCountries;

    @FXML
    public Label countriesCount;

    @FXML
    public TableView countriesListTable;

    @FXML
    public TableColumn columnCode;

    @FXML
    public TableColumn columnName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       Model model = Model.getInstance();
       model.readCountries();
       columnCode.setCellValueFactory(new PropertyValueFactory<CountryModel,String>("countryCode"));
       columnName.setCellValueFactory(new PropertyValueFactory<CountryModel,String>("countryName"));

       countriesCount.textProperty().bind(Bindings.createStringBinding(()->model.getCountryList().size()
               + " Paises", model.getCountryList()));

        //Initializing the alert box
        alert = new Alert(CONFIRMATION);
        alert.setTitle("Confirmación de Acción");
        alert.setHeaderText("Confirmar borrado de país");
        alert.setContentText("¿Estás seguro de que quieres borrar este país?");

         /*
         * Attach a KeyReleased action to searchNotes field, so when typing to
         * filter the notes currently attached to the table view.
         */
        FilteredList<CountryModel> filteredData = new FilteredList<>(model.getCountryList(), n -> true);
        searchCountries.setOnKeyReleased(e -> {
            filteredData.setPredicate(n -> {

                if (searchCountries.getText() == null || searchCountries.getText().isEmpty()) {
                    return true;
                }

                return n.getCountryName().contains(searchCountries.getText())
                        || n.getCountryCode().contains(searchCountries.getText());
            });
        });

        countriesListTable.setItems(filteredData);



    }

    @FXML
    public void newCountry(ActionEvent actionEvent) throws IOException{
        System.out.println("Entra en newCountry");
        countryModel = null;
        navigate(actionEvent, ADD.getPage());

    }

    @FXML
    public void doDelete(ActionEvent actionEvent) throws IOException {
        CountryModel mModel = getItem();
        if(mModel != null) {
            System.out.println("no es nulo");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get().equals(OK)) {
                Model model = Model.getInstance();
                model.deleteCountry(mModel.getCountryCode());
            }
        }


    }

    @FXML
    public void doEdit(ActionEvent actionEvent) throws IOException {
        countryModel = null;
        countryModel = getItem();
        if(nonNull(countryModel)){
            navigate(actionEvent,ADD.getPage());
        }

    }

    private CountryModel getItem(){
        return CountryModel.class.cast(countriesListTable.getSelectionModel().getSelectedItem());
    }
}
