package pro.jbelo;

import java.net.URL;

/**
 * @author jbelo
 * @date 2017 08.
 */
public enum FXMLPage {

    LIST("/fxml/listCountries.fxml"),
    ADD("/fxml/addEditCountry.fxml");

    private final String location;

    FXMLPage(String location) {
        this.location = location;
    }

    public URL getPage() {
        return  getClass().getResource(location) ;

    }

}

