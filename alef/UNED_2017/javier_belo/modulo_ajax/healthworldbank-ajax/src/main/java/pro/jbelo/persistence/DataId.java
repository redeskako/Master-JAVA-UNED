package pro.jbelo.persistence;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

/**
 * @author jbelo
 * @date 2017 07.
 */
@Embeddable
public class DataId implements Serializable {

    /**
     * Identificador del indicador
     */
    @Column (name = "indicator_code")
    @XmlElement(name = "indicatorCode")
    private String indicatorCode;

    /**
     * Identificador del país
     */
    @Column (name = "country_code")
    @XmlElement(name = "countryCode")
    private String countryCode;

    /**
     * AÑo
     */
    @Column (name = "year")
    @XmlElement(name = "year")
    private int year;

    /**
     * Constructor
     */
    public DataId() {
    }

    /**
     * Constructor
     * @param indicatorCode identificador del indicador
     * @param countryCode identificador del país
     * @param year año
     */
    public DataId(String indicatorCode, String countryCode, int year) {
        this.indicatorCode = indicatorCode;
        this.countryCode = countryCode;
        this.year = year;
    }

    /**
     * Getter identificador del indicador
     * @return
     */
    public String getIndicatorCode() {
        return indicatorCode;
    }

    /**
     * Setter identificador del indicador
     * @param indicatorCode
     */
    public void setIndicatorCode(String indicatorCode) {
        this.indicatorCode = indicatorCode;
    }

    /**
     * Getter identificador del país
     * @return
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Setter identificador del país
     * @param countryCode
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * Getter año
     * @return
     */
    public int getYear() {
        return year;
    }

    /**
     * Setter año
     * @param year
     */
    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DataId)) return false;

        DataId dataId = (DataId) o;

        if (getYear() != dataId.getYear()) return false;
        if (!getIndicatorCode().equals(dataId.getIndicatorCode())) return false;
        return getCountryCode().equals(dataId.getCountryCode());
    }

    @Override
    public int hashCode() {
        int result = getIndicatorCode().hashCode();
        result = 31 * result + getCountryCode().hashCode();
        result = 31 * result + getYear();
        return result;
    }
}
