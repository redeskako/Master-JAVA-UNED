package modelo;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * La clase Conjunto se utiliza para usar como envolvente para elementos 
 * que se van a enviar por medio de servicio web.
 * 
 * @author	Alpha UNED 2014
 * @version	RecursosWS 1.0
 * @since 	Septiembre 2014
 */

@XmlRootElement(name="conjunto")
public class Conjunto {
	
	private ArrayList<Recurso> arrayRecursos;
	private int numeroFilas;
	
	/**
	 * Constructor: inicializa los campos de la clase.
	 */
	public Conjunto() {
		this.arrayRecursos = new ArrayList<Recurso>();
		this.numeroFilas = 0;
	}


	/**
	 * @return the arrayRecursos
	 */
	public ArrayList<Recurso> getArrayRecursos() {
		return arrayRecursos;
	}

	/**
	 * @param arrayRecursos the arrayRecursos to set
	 */
	public void setArrayRecursos(ArrayList<Recurso> arrayRecursos) {
		this.arrayRecursos = arrayRecursos;
	}

	/**
	 * @return the numeroFilas
	 */
	public int getNumeroFilas() {
		return numeroFilas;
	}

	/**
	 * @param numeroFilas the numeroFilas to set
	 */
	public void setNumeroFilas(int numeroFilas) {
		this.numeroFilas = numeroFilas;
	} 
	
	

}
