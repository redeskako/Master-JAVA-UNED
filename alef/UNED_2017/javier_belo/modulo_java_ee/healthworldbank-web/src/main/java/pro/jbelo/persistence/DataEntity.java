package pro.jbelo.persistence;

import org.apache.commons.math3.util.Precision;

/**
 * Clase representa un único registro tras solicitar información para un país e indicador determinado
 * @author jbelo
 * @date 2017 04.
 */
public class DataEntity implements Entity{
    /**
     * Código de indicador
     */
    private String indicatorCode;

    /**
     * Código de país
     */
    private String countryCode;

    /**
     * Año
     */
    private int year;

    /**
     * Valor
     */
    private Double percentage;

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
     * Getter year
     * @return year
     */
    public int getYear() {
        return year;
    }

    /**
     * Setter yearº
     * @param year
     */
    public void setYear(int year) {
        this.year = year;
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


    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataEntity that = (DataEntity) o;

        if (year != that.year) return false;
        if (indicatorCode != null ? !indicatorCode.equals(that.indicatorCode) : that.indicatorCode != null)
            return false;
        if (countryCode != null ? !countryCode.equals(that.countryCode) : that.countryCode != null) return false;
        if (percentage != null ? !percentage.equals(that.percentage) : that.percentage != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = indicatorCode != null ? indicatorCode.hashCode() : 0;
        result = 31 * result + (countryCode != null ? countryCode.hashCode() : 0);
        result = 31 * result + year;
        result = 31 * result + (percentage != null ? percentage.hashCode() : 0);
        return result;
    }


}
