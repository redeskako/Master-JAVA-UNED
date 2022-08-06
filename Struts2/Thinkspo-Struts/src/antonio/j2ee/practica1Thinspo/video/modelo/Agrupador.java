package antonio.j2ee.practica1Thinspo.video.modelo;

/**
 * Utilizada para las consultas de agrupamiento de las graficas JFree
 * @author user
 *
 */
public class Agrupador {
    protected String clave;
    protected Long suma;

    public Agrupador(String clave, Long suma) {
		this.clave = clave;
		this.suma = suma;
	}

	public String getClave() {
		return clave;
	}

	public Long getSuma() {
		return suma;
	}
    
    
    
    
}
