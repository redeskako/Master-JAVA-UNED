package antonio.j2se.practica4bbdd.servidor.modelo;

import java.io.Serializable;
/**
 * Representa un Indicador
 * Es la abstraccion de una tupla de la tabla Indicador
 * @author asanton
 *
 */
public class Indicador implements Serializable {
	private static final long serialVersionUID = 7071425332084788773L;
	protected String descripcion;
    protected String codigo;
    
    /**
     * Constructor
     * @param codigo
     * @param descripcion
     */
	public Indicador(String codigo,String descripcion){
		this.codigo=codigo;
		this.descripcion=descripcion;
	}

    /**
     * Retorna la descripcion del indicador
     * @return
     */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Retorna el codigo del indicador
	 * @return
	 */
	public String getCodigo() {
		return codigo;
	}
	
	

}
