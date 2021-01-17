/** HashtagAdm es la clase que encapsula cada uno de los hashtag de manera individual
 * @ autor equipo UNED 2016
 * @ version 1.0
 * @ fecha 2016/05/12
 * @ licencia
 */
package uned.java2016.apitwitter.dao;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class HashtagAdm {
	@Id
 	@GeneratedValue(strategy=GenerationType.AUTO) 
	/** Atributos */
		/** Constantes */
		/** Variables */
	private String hashtag_id;
	private String origen;
	private Date fecha_entrada;
	/** Constructor por defecto, inicializa un objeto de tipo hashtag totalmente vacío
	 * @ in 
	 * @ out no procede
	 * @ error   
	 */
	public HashtagAdm(){
		this.hashtag_id=null;
		this.origen=null;
		this.fecha_entrada=null;
	}
	/** Constructor que inicializa el objeto con valores que se pasan como parámetro.
	 *  Dichos valores provendrán de la tabla de la BBDD
	 * @ in 
	 * 		String hashtag_id con el identificador único de cada hashtag
	 * 		String origen que puede ser adm o vecino
	 * 		Date fecha_entrada con la fecha en la que se introdujo el hashtag en la BBDD
	 * @ out no procede
	 * @ error   
	 */
	public HashtagAdm(String hashtag_id,String origen,Date fecha_entrada){
		super();
		this.hashtag_id=hashtag_id;
		this.origen=origen;
		this.fecha_entrada=fecha_entrada;
	}
	/** Métodos de acceso I/O a los campos de la clase */
	/** El método getHashtag lee el campo hashtag_id
	 * @ in 
	 * @ out String con hashtag_id
	 * @ error   
	 */
	public String getHashtag(){
		return this.hashtag_id;
	}
	/** El método getOrigen lee el campo origen
	 * @ in 
	 * @ out String con origen
	 * @ error   
	 */
	public String getOrigen(){
		return this.origen;
	}
	/** El método getFechaEntrada lee el campo fecha_entrada
	 * @ in 
	 * @ out Date con fecha_entrada
	 * @ error   
	 */
	public Date getFechaEntrada(){
		return this.fecha_entrada;
	}
	/** El método setHashtag escribe el campo hashtag_id
	 * @ in hashtag_id
	 * @ out void
	 * @ error   
	 */
	public void setHashtag(String hashtag){
		this.hashtag_id=hashtag;
	}
	/** El método setOrigen escribe el campo origen
	 * @ in hashtag_id
	 * @ out void
	 * @ error   
	 */
	public void setOrigen(String origen){
		this.origen=origen;
	}
	/** El método setFechaEntrada escribe el campo fecha
	 * @ in fecha
	 * @ out void
	 * @ error   
	 */
	public void setFechaEntrada(Date fecha){
		this.fecha_entrada=fecha;
	}	
}
