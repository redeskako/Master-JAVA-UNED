/**
 * 
 */
package es.uned.master.java.healthworldbank.comunicacion;

/**
 * Clase especializada para preguntas de la tabla de estadísticas
 * @author jbelo
 */
public class PreguntaStatistics extends PreguntaPaginatedTable {

    /**
     * País del que se solicitan los datos
     */
    private String countryCode;

    /**
     * Inidcador del que se solicitan los datos
     */
    private String indicatorCode;


	public PreguntaStatistics(){
	    super(TipoPeticion.ESTADISTICAS);
    }

	public PreguntaStatistics(ActionType actionType, int offset, String countryCode, String indicatorCode) {
		super(TipoPeticion.ESTADISTICAS,actionType, offset);
		this.countryCode = countryCode;
		this.indicatorCode = indicatorCode;
	}

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getIndicatorCode() {
        return indicatorCode;
    }

    public void setIndicatorCode(String indicatorCode) {
        this.indicatorCode = indicatorCode;
    }
}
