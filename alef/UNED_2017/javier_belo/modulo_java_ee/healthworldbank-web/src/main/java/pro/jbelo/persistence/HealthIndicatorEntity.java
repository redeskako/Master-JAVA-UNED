package pro.jbelo.persistence;

/**
 * Clase representa los indicadores de la base de  datos
 * Cada instancia representa un registro
 * @author jbelo
 * @date 2017 04.
 */
public class HealthIndicatorEntity implements Entity{
    /**
     * Código de indicador
     */
    private String indicatorCode;
    /**
     * Nombre de indicador
     */
    private String indicatorName;
    /**
     * Nota sobre indicador
     */
    private String sourceNote;
    /**
     * Organización
     */
    private String sourceOrganization;

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


    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HealthIndicatorEntity that = (HealthIndicatorEntity) o;

        if (indicatorCode != null ? !indicatorCode.equals(that.indicatorCode) : that.indicatorCode != null)
            return false;
        if (indicatorName != null ? !indicatorName.equals(that.indicatorName) : that.indicatorName != null)
            return false;
        if (sourceNote != null ? !sourceNote.equals(that.sourceNote) : that.sourceNote != null) return false;
        if (sourceOrganization != null ? !sourceOrganization.equals(that.sourceOrganization) : that.sourceOrganization != null)
            return false;

        return true;
    }


    public int hashCode() {
        int result = indicatorCode != null ? indicatorCode.hashCode() : 0;
        result = 31 * result + (indicatorName != null ? indicatorName.hashCode() : 0);
        result = 31 * result + (sourceNote != null ? sourceNote.hashCode() : 0);
        result = 31 * result + (sourceOrganization != null ? sourceOrganization.hashCode() : 0);
        return result;
    }
}
