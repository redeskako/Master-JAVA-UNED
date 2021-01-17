package pro.jbelo.model;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;

import javax.ws.rs.client.*;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author jbelo
 * @date 2017 08.
 */
public class Model {


    Alert alert;


    private static Model modelInstance = null;

    public static Model getInstance(){
        if(modelInstance == null){
            modelInstance = new Model();
        }

        return modelInstance;
    }

    final ObservableList<CountryModel> countryList = FXCollections.observableArrayList(
    );

    private final String baseURI = "http://localhost:8080/healthworldbank-web/webresources";


    private Model() {
    }

    public ObservableList<CountryModel> getCountryList() {
        return countryList;
    }


    public void createCountry(CountryModel countryModel){
        Client client = ClientBuilder.newClient();
        Task<Void> myTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    WebTarget webTarget = client.target(baseURI);
                    WebTarget resource = webTarget.path("countries");
                    Invocation.Builder builder = resource.request(MediaType.APPLICATION_JSON);
                    Response response = builder.post(Entity.json(countryModel));

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            switch (response.getStatus()){
                                case 201:
                                    alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setTitle("Crear país");
                                    alert.setHeaderText("País ha sido creado");
                                    alert.setContentText(null);
                                    alert.showAndWait();
                                    break;
                                default:
                                    alert = new Alert(Alert.AlertType.WARNING);
                                    alert.setTitle("Crea");
                                    alert.setHeaderText("Ha habido algún problema con la creación del país");
                                    alert.setContentText(null);
                                    alert.showAndWait();
                                    break;

                            }
                        }
                    });


                } catch (Exception e) {
                    System.out.println(e.toString());
                    e.printStackTrace();
                } finally {
                    client.close();
                    return null;
                }
            }
        };

        Thread thread = new Thread(myTask);
        thread.setDaemon(false);
        thread.start();




    }


    public void readCountries(){
        Task<Void> myTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                System.out.println("entra en el void de task");
                try{
                    Client client = ClientBuilder.newClient();
                    WebTarget webTarget = client.target(baseURI);
                    WebTarget resources = webTarget.path("countries/countries");
                    Invocation.Builder builder = resources.request(MediaType.APPLICATION_JSON);
                    Invocation invocation = builder.buildGet();
                    GenericType responseType = new GenericType<List<CountryModel>>(){};
                    List<CountryModel> countryModels = (List<CountryModel>) invocation.invoke(responseType);

                    // TODO: mover a finally
                    client.close();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("se ejecuta el run de runLater");
                            countryList.clear();
                            for(CountryModel cm:countryModels){
                                countryList.add(cm);
                            }
                        }
                    });

                } catch (Exception e){
                    // TODO implementar unha mensaxe para o usuario por non poder face orequest

                } finally {
                    return null;
                }
            }

        };

        Thread thread = new Thread(myTask);
        thread.setDaemon(false);
        thread.start();
    }


    /*public void readCountry(String countryCode){
        try{
            Client client = ClientBuilder.newClient();
            WebTarget webTarget = client.target(baseURI);
            WebTarget resources = webTarget.path("countries/country");
            resources = resources.queryParam("id","ZZZ");
            Invocation.Builder builder = resources.request(MediaType.APPLICATION_JSON);
            Invocation invocation = builder.buildGet();
            GenericType respondeType = new GenericType<CountryModel>(){};
            CountryModel countryModels = (CountryModel) invocation.invoke(respondeType);

            if(countryModels != null){
                System.out.println(countryModels.getCountryName());
            }
            client.close();

        } catch (Exception e){


        }

    }*/

    public void deleteCountry(String countryCode){
        Client client = ClientBuilder.newClient();

        Task<Void> myTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                System.out.println("entra en el void de task");
                try{
                    //Client client = ClientBuilder.newClient();
                    WebTarget webTarget = client.target(baseURI);
                    WebTarget resources = webTarget.path("countries/" + countryCode);
                    Invocation.Builder builder = resources.request(MediaType.APPLICATION_JSON);

                    Response response = builder.delete();
                    GenericType responseType = new GenericType<List<CountryModel>>(){};

                    List<CountryModel> mList = (List<CountryModel>) response.readEntity(responseType);

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            switch (response.getStatus()){
                                case 200:
                                    if(mList != null) {
                                        System.out.println("se ejecuta el run de runLater");
                                        countryList.clear();
                                        for (CountryModel cm : mList) {
                                            countryList.add(cm);
                                        }
                                    }
                                    alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setTitle("Eliminación país");
                                    alert.setHeaderText("País ha sido eliminado");
                                    alert.setContentText(null);
                                    alert.showAndWait();
                                    break;
                                default:
                                    alert = new Alert(Alert.AlertType.WARNING);
                                    alert.setTitle("Elimina");
                                    alert.setHeaderText("Ha habido algún problema con la eliminación del país");
                                    alert.setContentText(null);
                                    alert.showAndWait();
                                    break;


                            }
                        }
                    });

                } catch (Exception e){
                    System.out.println("cpturada escepcion");
                    System.out.println(e.toString());


                } finally {
                    client.close();
                    return null;
                }
            }

        };

        Thread thread = new Thread(myTask);
        thread.setDaemon(false);
        thread.start();
    }
}
