package pro.jbelo.persistence;

import org.apache.commons.math3.util.Precision;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.xml.bind.annotation.*;
import java.io.Serializable;

/**
 * Clase representa un único registro tras solicitar información para un país e indicador determinado
 * @author jbelo
 * @date 2017 04.
 */
@Entity
@Table(name = "data")
@XmlRootElement (name = "data")
@XmlAccessorType(XmlAccessType.FIELD)
public class DataEntity implements Serializable, pro.jbelo.persistence.Entity{
    /**
     * Código de indicador. Se han puesto los campos de la clave única en un EmbeddedId
     */
    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "indicatorCode", column = @Column(name = "indicator_code")),
            @AttributeOverride(name = "countryCode" , column = @Column(name = "country_code")),
            @AttributeOverride(name = "year" , column = @Column (name = "year"))})
    private DataId dataId;

    /**
     * Getter del dataId
     * @return
     */
    public DataId getDataId() {
        return dataId;
    }

    /**
     * Setter de dataId
     * @param dataId
     */
    public void setDataId(DataId dataId) {
        this.dataId = dataId;
    }

    /**
     * Pais para la relación many-to-one
     * la etiqueta XMLTransient evita que sea incluido en el xml
     */
    @ManyToOne
    @JoinColumn(name = "country_code", insertable = false, updatable = false)
    @XmlTransient
    private CountryEntity country;

    /**
     * Getter para el país
     * @return
     */
    public CountryEntity getCountry() {
        return country;
    }

    /**
     * Setter para el país
     * @param country país
     */
    public void setCountry(CountryEntity country) {
        this.country = country;
    }

    /**
     * Indicador de salud para la relación many-to-one
     * La etiqueta XMLTransient hace que no sea pasado al xml
     */
    @ManyToOne
    @JoinColumn(name = "indicator_code", insertable = false, updatable = false)
    @XmlTransient
    private HealthIndicatorEntity healthIndicator;

    /**
     * Getter indicador de salud
     * @return indicador de salud
     */
    public HealthIndicatorEntity getHealthIndicator() {
        return healthIndicator;
    }

    /**
     * Setter indicador de salud
     * @param healthIndicator indicador de salud
     */
    public void setHealthIndicator(HealthIndicatorEntity healthIndicator) {
        this.healthIndicator = healthIndicator;
    }

    /**
     * Valor para el indicador
     */
    @Column(name = "percentage")
    @XmlElement(name = "percentage")
    private Double percentage;

    /**
     * Getter indicatorCode
     * @return indicatorCode
     */
    public String getIndicatorCode() {
        return dataId.getIndicatorCode();
    }

    /**
     * Setter indicatorCode
     * @param indicatorCode
     */
    public void setIndicatorCode(String indicatorCode) {
        //this.indicatorCode = indicatorCode;
        if(dataId == null) dataId = new DataId();
        dataId.setIndicatorCode(indicatorCode);
    }

    /**
     * Getter countryCode
     * @return countryCode
     */
    public String getCountryCode() {
        //return countryCode;
        if(dataId == null) dataId = new DataId();
        return dataId.getCountryCode();
    }

    /**
     * Setter countryCode
     * @param countryCode
     */
    public void setCountryCode(String countryCode) {
        //this.countryCode = countryCode;
        dataId.setCountryCode(countryCode);
    }

    /**
     * Getter year
     * @return year
     */
    public int getYear() {
    //    //return year;
        return dataId.getYear();
    }

    /**
     * Setter yearº
     * @param year
     */
    public void setYear(int year) {
        //this.year = year;
        if(dataId == null) dataId = new DataId();
        dataId.setYear(year);
    }

    /**
     * Getter percentage
     * @return percentage
     */
    public Double getPercentage() {
        return percentage;
    }

    /**
     * Setter percentage
     * @param percentage
     */
    public void setPercentage(Double percentage) {
        this.percentage = Precision.round(percentage,2);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DataEntity)) return false;

        DataEntity that = (DataEntity) o;

        if (!getDataId().equals(that.getDataId())) return false;
        if (!getCountry().equals(that.getCountry())) return false;
        return getPercentage().equals(that.getPercentage());
    }

    @Override
    public int hashCode() {
        int result = getDataId().hashCode();
        result = 31 * result + getCountry().hashCode();
        result = 31 * result + getPercentage().hashCode();
        return result;
    }
}
