/**
 * 
 */
package es.uned.master.java.healthworldbank.comunicacion;

/**
 * Clase especializada para solicitar datos de mapa
 * @author jbelo
 */
public class PreguntaMap extends Pregunta {

    /**
     * Indicador para el que se solicita información
     */
    private String indicatorCode;

    /**
     * Año para el que se soliciat información
     */
    private String yearCode;


    public PreguntaMap(){
        super(TipoPeticion.MAP_VALUES);
    }

	public PreguntaMap(String indicatorCode, String yearCode) {
		super(TipoPeticion.MAP_VALUES);
		this.indicatorCode = indicatorCode;
		this.yearCode = yearCode;
	}

    public String getIndicatorCode() {
        return indicatorCode;
    }

    public void setIndicatorCode(String indicatorCode) {
        this.indicatorCode = indicatorCode;
    }

    public String getYearCode() {
        return yearCode;
    }

    public void setYearCode(String yearCode) {
        this.yearCode = yearCode;
    }
}
