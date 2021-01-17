package beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * Representa un una organización relacionada con el Site
 * principal.
 * Cada 'Site' puede tener '0' o más de un valor asociado.
 * @author alef
 */
/*
public class Siteorganization implements Serializable {

	private static final long serialVersionUID = 1L;

	private int site_URL_Id;//Id. del Site relacionado

	private String organization;//Nombre de la organización

	//Constructores
	public Siteorganization() {
	}

	public Siteorganization(int u_id, String org){
		this.site_URL_Id = u_id;
		this.organization = org;
	}
	//Getters and Setters
	public int getSite_URL_Id() {
		return this.site_URL_Id;
	}
	public void setSite_URL_Id(int site_URL_Id) {
		this.site_URL_Id = site_URL_Id;
	}
	public String getOrganization() {
		return this.organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
*/
	/**
	 * Inserta en la tabla 'siteorganization' los atributos de la clase.
	 * @param conn = conexión que se utiliza para ejecutar el sql.
	 */
/*
	public void insert(Connection conn){
		PreparedStatement ps;

		try {
			ps = conn.prepareStatement("INSERT INTO `medlinebennett`.`siteorganization`(`Site_URL_Id`," +
					"`Organization`)VALUES(?,?);");
			ps.setInt(1, this.site_URL_Id);
			ps.setString(2, this.organization);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			System.out.println("Error al insertar el registro en la tabla Siteorganization");
			e.printStackTrace();
		}

	}

	//Método que devuelve una lista con los resultados de la búsqueda en la BBDD Siteorganization según el
	//criterio de búsqueda id.
	public ArrayList<Siteorganization> listadoSiteorganization(int id) throws Exception{
		ArrayList<Siteorganization> miLista;
		BBDD miBd = new BBDD();
		miBd.abrirConexion(); 
		//Vamos a utilizar PrepareStatement
		miLista=miBd.listadoSiteorganization("Select * from siteorganization where Site_URL_Id = ?", id);
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
		if (!(other instanceof Siteorganization)) {
			return false;
		}
		Siteorganization castOther = (Siteorganization)other;
		return 
				(this.site_URL_Id == castOther.site_URL_Id)
				&& this.organization.equals(castOther.organization);
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
		hash = hash * prime + this.organization.hashCode();

		return hash;
	}
}
*/