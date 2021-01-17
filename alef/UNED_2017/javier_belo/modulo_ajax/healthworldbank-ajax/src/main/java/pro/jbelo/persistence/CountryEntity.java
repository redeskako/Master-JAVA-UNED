package pro.jbelo.persistence;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Clase representa a la entidad Country de la base de datos
 * Cada instancia representa un único registro de la tabla contry en la Base de Datos.
 * @author jbelo
 * @date 2017 04.
 */
@Entity
@Table(name = "country")
@XmlRootElement (name = "country")
@XmlAccessorType(XmlAccessType.FIELD)
public class CountryEntity implements Serializable, pro.jbelo.persistence.Entity{
    /**
     * Códido del país
     */
    @Id
    @Column(name = "country_code")
    @XmlElement(name = "countryCode")
    private String countryCode;

    /**
     *Nombre del país
     */
    @Column(name = "country_name")
    @XmlElement(name = "countryName")
    private String countryName;


    /**
     * Alamecena los datos de la relación con los tabla de datos
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "country", fetch = FetchType.LAZY)
    @XmlTransient
    private Set<DataEntity> dataEntitySet;


    public Set<DataEntity> getDataEntitySet() {
        return dataEntitySet;
    }

    public void setDataEntitySet(Set<DataEntity> dataEntitySet) {
        this.dataEntitySet = dataEntitySet;
    }

    /**
     * Getter countryCode
     * @return countryCode
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Setter countryCode
     * @param countryCode
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * Getter countryName
     * @return countryName
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * Setter countryName
     * @param countryName
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     *
     * @param o object para comparación
     * @return valor en caso de ser iguales
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CountryEntity that = (CountryEntity) o;

        if (countryCode != null ? !countryCode.equals(that.countryCode) : that.countryCode != null) return false;
        if (countryName != null ? !countryName.equals(that.countryName) : that.countryName != null) return false;

        return true;
    }


    public int hashCode() {
        int result = countryCode != null ? countryCode.hashCode() : 0;
        result = 31 * result + (countryName != null ? countryName.hashCode() : 0);
        return result;
    }
}
