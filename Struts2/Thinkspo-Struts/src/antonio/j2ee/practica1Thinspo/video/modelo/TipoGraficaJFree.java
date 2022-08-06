package antonio.j2ee.practica1Thinspo.video.modelo;
/**
 * Representa a un Tipo de Grafica Soportada
 * @author  Antonio Sánchez Antón
 * @since  1.0
 */
public class TipoGraficaJFree {
    protected int tipoGraficaId;
    protected String tipoGraficaValor;

    public TipoGraficaJFree(int tipoGraficaId, String tipoGraficaValor) {
		this.tipoGraficaId = tipoGraficaId;
		this.tipoGraficaValor = tipoGraficaValor;
	}

	public int getTipoGraficaId() {
		return tipoGraficaId;
	}

	public void setTipoGraficaId(int tipoGraficaId) {
		this.tipoGraficaId = tipoGraficaId;
	}

	public String getTipoGraficaValor() {
		return tipoGraficaValor;
	}

	public void setTipoGraficaValor(String tipoGraficaValor) {
		this.tipoGraficaValor = tipoGraficaValor;
	}
    
    
    
    
}
