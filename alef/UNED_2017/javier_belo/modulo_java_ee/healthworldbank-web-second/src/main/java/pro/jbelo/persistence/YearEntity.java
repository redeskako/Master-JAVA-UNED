package pro.jbelo.persistence;

/**
 * Representa los años en la base de datos.
 * @author jbelo
 * @date 2017 05.
 */
public class YearEntity implements Entity{

    /**
     * Año
     */
    private int year = 0;

    /**
     * Constructor
     */
    public YearEntity() {
    }

    /**
     * Constructor
     * @param year
     */
    public YearEntity(int year) {
        this.year = year;
    }

    /**
     * Getter year
     * @return
     */
    public int getYear() {
        return year;
    }

    /**
     * Setter year
     * @param year
     */
    public void setYear(int year) {
        this.year = year;
    }
}
