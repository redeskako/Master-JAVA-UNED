package pro.jbelo.persistence;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Clase representa los indicadores de la base de  datos
 * Cada instancia representa un registro
 * @author jbelo
 * @date 2017 04.
 */
@Entity
@Table(name = "health_indicator")
@XmlRootElement(name = "healthIndicator")
@XmlAccessorType(XmlAccessType.FIELD)
public class HealthIndicatorEntity implements Serializable, pro.jbelo.persistence.Entity{
    /**
     * Código de indicador
     */
    @Id
    @Column(name = "indicator_code")
    @XmlElement(name = "indicatorCode")
    private String indicatorCode;
    /**
     * Nombre de indicador
     */
    @Column(name = "indicator_name")
    @XmlElement(name = "indicatorName")
    private String indicatorName;
    /**
     * Nota sobre indicador
     */
    @Column(name = "source_note")
    @XmlElement(name = "sourceNote")
    private String sourceNote;
    /**
     * Organización
     */
    @Column(name = "source_organization")
    @XmlElement(name = "sourceOrganization")
    private String sourceOrganization;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "healthIndicator", fetch = FetchType.LAZY)
    @XmlTransient
    private Set<DataEntity> dataEntitySet;


    public Set<DataEntity> getDataEntitySet() {
        return dataEntitySet;
    }

    public void setDataEntitySet(Set<DataEntity> dataEntitySet) {
        this.dataEntitySet = dataEntitySet;
    }

    /**
     * Getter indicatorCode
     * @return indicatorCode
     */
    public String getIndicatorCode() {
        return indicatorCode;
    }

    /**
     * Setter indicatorCode
     * @param indicatorCode
     */
    public void setIndicatorCode(String indicatorCode) {
        this.indicatorCode = indicatorCode;
    }

    /**
     * Getter indicatorCode
     * @return indicatorName
     */
    public String getIndicatorName() {
        return indicatorName;
    }

    /**
     * Setter indicatorName
     * @param indicatorName
     */
    public void setIndicatorName(String indicatorName) {
        this.indicatorName = indicatorName;
    }

    /**
     * Getter sourceNote
     * @return sourceNote
     */
    public String getSourceNote() {
        return sourceNote;
    }

    /**
     * Setter sourceNote
     * @param sourceNote
     */
    public void setSourceNote(String sourceNote) {
        this.sourceNote = sourceNote;
    }

    /**
     * Getter sourceOrganization
     * @return sourceOrganization
     */
    public String getSourceOrganization() {
        return sourceOrganization;
    }

    /**
     * Setter sourceOrganization
     * @param sourceOrganization
     */
    public void setSourceOrganization(String sourceOrganization) {
        this.sourceOrganization = sourceOrganization;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HealthIndicatorEntity)) return false;

        HealthIndicatorEntity that = (HealthIndicatorEntity) o;

        if (!getIndicatorCode().equals(that.getIndicatorCode())) return false;
        if (!getIndicatorName().equals(that.getIndicatorName())) return false;
        if (!getSourceNote().equals(that.getSourceNote())) return false;
        if (!getSourceOrganization().equals(that.getSourceOrganization())) return false;
        return getDataEntitySet().equals(that.getDataEntitySet());
    }

    @Override
    public int hashCode() {
        int result = getIndicatorCode().hashCode();
        result = 31 * result + getIndicatorName().hashCode();
        result = 31 * result + getSourceNote().hashCode();
        result = 31 * result + getSourceOrganization().hashCode();
        result = 31 * result + getDataEntitySet().hashCode();
        return result;
    }
}
