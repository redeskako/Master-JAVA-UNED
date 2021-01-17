package pro.jbelo.persistence;

/**
 * Clase representa a la entidad Country de la base de datos
 * Cada instancia representa un único registro de la tabla contry en la Base de Datos.
 * @author jbelo
 * @date 2017 04.
 */
public class CountryEntity implements Entity{
    /**
     * Códido del país
     */
    private String countryCode;

    /**
     *Nombre del país
     */
    private String countryName;

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
