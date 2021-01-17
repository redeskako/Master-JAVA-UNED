/** UsuariosDAOImpl es la clase que encapsula la lista de usuarios mediante persistencia
 * @ autor equipo UNED 2016
 * @ version 1.0
 * @ fecha 2016/08/01
 * @ licencia
 */

package uned.java2016.apitwitter.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table
@NamedQueries({
	@NamedQuery(query="Select p from UsuariosDAOImpl p where p.user=:usuario AND p.passwd=:contra",name="Buscar")
})

public class UsuariosDAOImpl implements UsuariosDAO {
	 	@Id
	 	@GeneratedValue(strategy=GenerationType.AUTO) 
	 	
	 	/** Atributos */
	 		/** Constantes */
	 		/** Variables */
	 	private String user;
		private String passwd;
		private String rol;
		/** Constructor que inicializa un objeto con los datos de la tabla de la BBDD
		 * @ in 
		  	* String user: con el nombre del usuario
		  	* String passwd: con la contraseña del usuario
		  	* String rol: con el papel del usuario: adm o cliente
		 * @ out no procede
		 * @ error   
		 */
		 public UsuariosDAOImpl(String user,String passwd,String rol){
			 super(); 
			 this.user=user;
			 this.passwd=passwd;
			 this.rol=rol;
		 }
		 /** Constructor por defecto, inicializa un objeto de tipo UsuariosDAOImpl totalmente vacío
		  * @ in 
		  * @ out no procede
		  * @ error   
		  */
	 	 public UsuariosDAOImpl(){
            super();  
	 	 }

	 	/** Getters y setters creados automáticamente **/
		/**
		 * @return the user
		 */
		public String getUser() {
			return user;
		}

		/**
		 * @param user the user to set
		 */
		public void setUser(String user) {
			this.user = user;
		}

		/**
		 * @return the passwd
		 */
		public String getPasswd() {
			return passwd;
		}

		/**
		 * @param passwd the passwd to set
		 */
		public void setPasswd(String passwd) {
			this.passwd = passwd;
		}

		/**
		 * @return the rol
		 */
		public String getRol() {
			return rol;
		}

		/**
		 * @param rol the rol to set
		 */
		public void setRol(String rol) {
			this.rol = rol;
		}

}
