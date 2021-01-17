package beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * Representa un una descripción relacionada con el Site
 * principal.
 * Cada 'Site' puede tener '0' o más de un valor asociado.
 * @author alef
 */
/*
public class Sitedescription implements Serializable {

	private static final long serialVersionUID = 1L;

	private int site_URL_Id;//Id del Site principal

	private String description;//Valor de la descripción

	//Constructores
	public Sitedescription() {
	}

	public Sitedescription(int u_id, String desc){
		this.site_URL_Id = u_id;
		this.description = desc;
	}
	//Getters and Setters
	public int getSite_URL_Id() {
		return this.site_URL_Id;
	}
	public void setSite_URL_Id(int site_URL_Id) {
		this.site_URL_Id = site_URL_Id;
	}
	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
*/
	/**
	 * Inserta en la tabla 'sitedescription' los atributos de la clase.
	 * @param conn = conexión que se utiliza para ejecutar el sql.
	 */
/*
	public void insert(Connection conn){
		PreparedStatement ps;

		try {
			ps = conn.prepareStatement("INSERT INTO `medlinebennett`.`sitedescription`(`Site_URL_Id`," +
					"`Description`)VALUES(?,?);");
			ps.setInt(1, this.site_URL_Id);
			ps.setString(2, this.description);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			System.out.println("Error al insertar el registro en la tabla Sitedescription");
			e.printStackTrace();
		}

	}

	//Método que devuelve una lista con los resultados de la búsqueda en la BBDD Sitedescription según el
	//criterio de búsqueda id.
	public ArrayList<Sitedescription> listadoSiteDescription(int id) throws Exception{
		ArrayList<Sitedescription> miLista;
		BBDD miBd = new BBDD();
		miBd.abrirConexion(); 
		//Vamos a utilizar PrepareStatement
		miLista=miBd.listadoSiteDescription("Select * from sitedescription where Site_URL_Id = ?", id);
		miBd.cerrarConexion();
		return miLista;		
	}
	*/

	/*
	 *  Según la implementeción de equals() por defecto en la clase Object,
	 *  lo que hace es comparar las referencias de los objetos,
	 *  es decir sus direcciones de memoria. 
	 *  Para que el método equals() funcione de manera correcto es necesario
	 *  reimplementarlo.
	 */
/*
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Sitedescription)) {
			return false;
		}
		Sitedescription castOther = (Sitedescription)other;
		return 
				(this.site_URL_Id == castOther.site_URL_Id)
				&& this.description.equals(castOther.description);
	}
	*/
	/*
	 * La función de dispersión permite localizar de forma rápida 
	 * un elemento dentro del array sin tener que recorrerlo entero.
	 * si reimplementamos el método equals, es obligatorio
	 * reimplementar el método hashCode.
	 */
/*
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.site_URL_Id;
		hash = hash * prime + this.description.hashCode();

		return hash;
	}
}
*/
