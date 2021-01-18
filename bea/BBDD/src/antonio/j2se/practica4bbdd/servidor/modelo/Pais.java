package antonio.j2se.practica4bbdd.servidor.modelo;

import java.io.Serializable;
/**
 * Representa un Pais
 * Es la abstraccion de una tupla de la tabla Pais
 * @author asanton
 *
 */
public class Pais implements Serializable{
	private static final long serialVersionUID = 1346118503263664216L;
	protected String descripcion;
    protected String codigo;
     
    /**
     * Constructor
     * @param codigo
     * @param descripcion
     */
     public Pais(String codigo,String descripcion){
    	 this.codigo=codigo;
    	 this.descripcion=descripcion;
     }

     /**
      * Retorna la descripcion del pais
      * @return
      */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Retorna el codigo del pais
	 * @return
	 */
	public String getCodigo() {
		return codigo;
	}
     
     
}
