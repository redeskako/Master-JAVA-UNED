package pro.jbelo.persistence;

/**
 * Clase representa un único registro tras solicitar información para un año e indicador determinado
 * @author jbelo
 * @date 2017 05.
 */
public class DataMapWrapper implements Entity {

    /**
     * Nombre del país
     */
    private String country;

    /**
     * Dato para ese país
     */
    private Double percentage;

    /**
     * Getter country
     * @return country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Setter country
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
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
        this.percentage = percentage;
    }
}
