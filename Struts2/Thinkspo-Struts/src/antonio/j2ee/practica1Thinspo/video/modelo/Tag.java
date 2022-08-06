package antonio.j2ee.practica1Thinspo.video.modelo;

import java.io.Serializable;

/**
 * Representa a un Tag de Video
 * @author  Antonio Sánchez Antón
 * @since  1.0
 */
public class Tag implements Serializable{
	private static final long serialVersionUID = -8904107935644940615L;
	protected String valor;
	protected String clave;
	
	
	public Tag() {
		
	}
    /**
     * Constructor
     * @param valor
     * @param clave
     */
	public Tag(String valor, String clave) {
		this.valor = valor;
		this.clave = clave;
	}

	public String getValor() {
		return valor;
	}


	public String getClave() {
		return clave;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tag other = (Tag) obj;
		if (clave == null) {
			if (other.clave != null)
				return false;
		} else if (!clave.equalsIgnoreCase(other.clave))
			return false;
		return true;
	}

	
}
