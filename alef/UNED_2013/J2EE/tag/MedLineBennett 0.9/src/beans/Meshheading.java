package beans;

import java.io.Serializable;
import java.util.ArrayList;

/*
public class Meshheading implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int healthTopic_Id;

	private String idMeshHeading;
	
	private String descriptor;

	public Meshheading() {
	}

	public Meshheading(int healthTopic_Id, String idMeshHeading,
			String descriptor) {
		super();
		this.healthTopic_Id = healthTopic_Id;
		this.idMeshHeading = idMeshHeading;
		this.descriptor = descriptor;
	}

	public String getDescriptor() {
		return descriptor;
	}

	public void setDescriptor(String descriptor) {
		this.descriptor = descriptor;
	}

	public int getHealthTopic_Id() {
		return this.healthTopic_Id;
	}
	public void setHealthTopic_Id(int healthTopic_Id) {
		this.healthTopic_Id = healthTopic_Id;
	}
	public String getIdMeshHeading() {
		return this.idMeshHeading;
	}
	public void setIdMeshHeading(String idMeshHeading) {
		this.idMeshHeading = idMeshHeading;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Meshheading)) {
			return false;
		}
		Meshheading castOther = (Meshheading)other;
		return 
			(this.healthTopic_Id == castOther.healthTopic_Id)
			&& this.idMeshHeading.equals(castOther.idMeshHeading);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.healthTopic_Id;
		hash = hash * prime + this.idMeshHeading.hashCode();
		
		return hash;
	}
	
	//Método que devuelve una lista con los resultados de la búsqueda en la BBDD AlsoCalled según el
	//criterio de búsqueda id.
	public ArrayList<Meshheading> listadoMeshheading(int id) throws Exception{
		ArrayList<Meshheading> miLista;
		BBDD miBd = new BBDD();
		miBd.abrirConexion(); 
		//Vamos a utilizar PrepareStatement
		miLista=miBd.listadoMeshheading("Select * from meshheading where HealthTopic_Id = ?", id);
		miBd.cerrarConexion();
		return miLista;		
	}
}
*/