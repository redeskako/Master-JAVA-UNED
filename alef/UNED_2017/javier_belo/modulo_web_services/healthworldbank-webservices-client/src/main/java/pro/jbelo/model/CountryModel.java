package pro.jbelo.model;

import javafx.fxml.FXML;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author jbelo
 * @date 2017 08.
 */
@XmlRootElement(name = "country")
@XmlAccessorType(XmlAccessType.FIELD)
public class CountryModel {

    @FXML
    @XmlElement(name = "countryCode")
    private String countryCode;

    @FXML
    @XmlElement(name = "countryName")
    private String countryName;

    public CountryModel() {
    }

    public CountryModel(String countryCode, String countryName) {
        this.countryCode = countryCode; //new SimpleStringProperty(countryCode);
        this.countryName = countryName; //new SimpleStringProperty(contryName);
    }

    /*public String getCountryCode() {
        return countryCode.get();
    }

    public void setCountryCode(String countryCode) {
        this.countryCode.set(countryCode);
    }

    public String getCountryName() {
        return countryName.get();
    }

    public void setCountryName(String contryName) {
        this.countryName.set(contryName);
    }*/

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
