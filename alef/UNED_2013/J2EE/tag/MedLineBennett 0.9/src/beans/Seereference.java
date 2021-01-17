package beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * Representa una referencia relacionada con el Healthtopic
 * principal.
 * Cada 'Healthtopic' puede tener '0' o más de un valor asociado.
 * @author alef
 */
/*
public class Seereference implements Serializable {

	private static final long serialVersionUID = 1L;

	private int healthTopic_Id;//Id. del Healthtopic principal

	private String reference;//Nombre del tema de referencia

	//Constructores
	public Seereference(){
	}
	
	public Seereference(int ht_id, String ref){
		this.healthTopic_Id = ht_id;
		this.reference = ref;
	}
	//Getters and Setters
	public int getHealthTopic_Id() {
		return this.healthTopic_Id;
	}
	public void setHealthTopic_Id(int healthTopic_Id) {
		this.healthTopic_Id = healthTopic_Id;
	}
	public String getReference() {
		return this.reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	*/

	/**
	 * Inserta en la tabla 'languagemappedtopic' los atributos de la clase.
	 * @param conn = conexión que se utiliza para ejecutar el sql.
	 */
/*
	public void insert(Connection conn){
		PreparedStatement ps;

		try {
			ps = conn.prepareStatement("INSERT INTO `medlinebennett`.`seereference`(`HealthTopic_Id`," +
					"`Reference`)VALUES(?,?);");
			ps.setInt(1, this.healthTopic_Id);
			ps.setString(2, this.reference);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			System.out.println("Error al insertar el registro en la tabla Seereference");
			e.printStackTrace();
		}

	}

	//Método que devuelve una lista con los resultados de la búsqueda en la BBDD AlsoCalled según el
	//criterio de búsqueda id.
	public ArrayList<Seereference> listadoSeereference(int id) throws Exception{
		ArrayList<Seereference> miLista;
		BBDD miBd = new BBDD();
		miBd.abrirConexion(); 
		//Vamos a utilizar PrepareStatement
		miLista=miBd.listadoSeereference("Select * from seereference where HealthTopic_Id = ?", id);
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
		if (!(other instanceof Seereference)) {
			return false;
		}
		Seereference castOther = (Seereference)other;
		return 
				(this.healthTopic_Id == castOther.healthTopic_Id)
				&& this.reference.equals(castOther.reference);
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
		hash = hash * prime + this.healthTopic_Id;
		hash = hash * prime + this.reference.hashCode();

		return hash;
	}
}
*/