/** HashtagAdm es la clase que encapsula cada uno de los hashtag de manera individual
 * @ autor equipo UNED 2016
 * @ version 1.0
 * @ fecha 2016/05/12
 * @ licencia
 */
package uned.java2016.apitwitter.dao;

import java.util.Date;

public class HashtagAdm {
	/** Atributos */
		/** Constantes */
		/** Variables */
	private String hashtag_id;
	private String origen;
	private Date fecha_entrada;
	/** Constructor por defecto, inicializa un objeto de tipo hashtag totalmente vac�o
	 * @ in 
	 * @ out no procede
	 * @ error   
	 */
	public HashtagAdm(){
		this.hashtag_id=null;
		this.origen=null;
		this.fecha_entrada=null;
	}
	/** Constructor que inicializa el objeto con valores que se pasan como par�metro.
	 *  Dichos valores provendr�n de la tabla de la BBDD
	 * @ in 
	 * 		String hashtag_id con el identificador �nico de cada hashtag
	 * 		String origen que puede ser adm o vecino
	 * 		Date fecha_entrada con la fecha en la que se introdujo el hashtag en la BBDD
	 * @ out no procede
	 * @ error   
	 */
	public HashtagAdm(String hashtag_id,String origen,Date fecha_entrada){
		this.hashtag_id=hashtag_id;
		this.origen=origen;
		this.fecha_entrada=fecha_entrada;
	}
	/** M�todos de acceso I/O a los campos de la clase */
	/** El m�todo getHashtag lee el campo hashtag_id
	 * @ in 
	 * @ out String con hashtag_id
	 * @ error   
	 */
	public String getHashtag(){
		return this.hashtag_id;
	}
	/** El m�todo getOrigen lee el campo origen
	 * @ in 
	 * @ out String con origen
	 * @ error   
	 */
	public String getOrigen(){
		return this.origen;
	}
	/** El m�todo getFechaEntrada lee el campo fecha_entrada
	 * @ in 
	 * @ out Date con fecha_entrada
	 * @ error   
	 */
	public Date getFechaEntrada(){
		return this.fecha_entrada;
	}
	/** El m�todo setHashtag escribe el campo hashtag_id
	 * @ in hashtag_id
	 * @ out void
	 * @ error   
	 */
	public void setHashtag(String hashtag){
		this.hashtag_id=hashtag;
	}
	/** El m�todo setOrigen escribe el campo origen
	 * @ in hashtag_id
	 * @ out void
	 * @ error   
	 */
	public void setOrigen(String origen){
		this.origen=origen;
	}
	/** El m�todo setFechaEntrada escribe el campo fecha
	 * @ in fecha
	 * @ out void
	 * @ error   
	 */
	public void setFechaEntrada(Date fecha){
		this.fecha_entrada=fecha;
	}	
}
