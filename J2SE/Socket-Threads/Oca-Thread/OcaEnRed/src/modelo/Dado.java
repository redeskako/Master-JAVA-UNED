/**
* 2014, Antonio Jes�s Arquillos �lvarez
* M�ster en Dise�o y Desarrollo de Apicaciones Web
* Universidad Nacional de Educaci�n a Distancia
*/

package modelo;

//TODO: Auto-generated Javadoc

/**
* Clase que representa un dado cl�sico de seis caras del juego de la oca (compartido).
* Lo asociamos a la partida en lugar de asignar un dado por jugador.
* Dado.
*/

public class Dado {

	/** Valor m�ximo que puede devolver el dado 
     * @uml.property name="numeroCaras" */
    private int numeroCaras;    

	/** valor que se ha obtenido en la �ltima tirada
     * @uml.property name="ultimaTirada" */
	private int ultimaTirada;
    
    /**
     * Devuelve la puntuaci�n obtenida en la �ltima tirada 
     *
     * @return <tt>int</tt> puntuaci�n obtenida
     * @uml.property name="ultimaTirada"
     */
    public int getUltimaTirada() { 
       return this.ultimaTirada; 
    }

    /**
     * Establece directamente puntuaci�n obtenida en la �ltima tirada 
     *
     * @return <tt>int</tt> puntuaci�n obtenida
     * @uml.property name="ultimaTirada"
     */
    public void setUltimaTirada( int puntuacion ) { 
       this.ultimaTirada = puntuacion; 
    }

    /**
     * Constructor. Instancia un nuevo dado.
     *
     * @param numeroCaras n�mero de caras del dado
     */
    public Dado ( int numeroCaras ) {
        this.numeroCaras = numeroCaras;
    }

	/**
	 * Metodo para tirar dado.
	 * Principio de separaci�n consulta - comando (CQSP).
	 */
	public void tirar() {
		this.ultimaTirada = ( int ) ( Math.random() * 10000 ) % this.numeroCaras + 1;
	}
	
}
