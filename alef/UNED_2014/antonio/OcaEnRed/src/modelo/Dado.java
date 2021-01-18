/**
* 2014, Antonio Jesús Arquillos Álvarez
* Máster en Diseño y Desarrollo de Apicaciones Web
* Universidad Nacional de Educación a Distancia
*/

package modelo;

//TODO: Auto-generated Javadoc

/**
* Clase que representa un dado clásico de seis caras del juego de la oca (compartido).
* Lo asociamos a la partida en lugar de asignar un dado por jugador.
* Dado.
*/

public class Dado {

	/** Valor máximo que puede devolver el dado 
     * @uml.property name="numeroCaras" */
    private int numeroCaras;    

	/** valor que se ha obtenido en la última tirada
     * @uml.property name="ultimaTirada" */
	private int ultimaTirada;
    
    /**
     * Devuelve la puntuación obtenida en la última tirada 
     *
     * @return <tt>int</tt> puntuación obtenida
     * @uml.property name="ultimaTirada"
     */
    public int getUltimaTirada() { 
       return this.ultimaTirada; 
    }

    /**
     * Establece directamente puntuación obtenida en la última tirada 
     *
     * @return <tt>int</tt> puntuación obtenida
     * @uml.property name="ultimaTirada"
     */
    public void setUltimaTirada( int puntuacion ) { 
       this.ultimaTirada = puntuacion; 
    }

    /**
     * Constructor. Instancia un nuevo dado.
     *
     * @param numeroCaras número de caras del dado
     */
    public Dado ( int numeroCaras ) {
        this.numeroCaras = numeroCaras;
    }

	/**
	 * Metodo para tirar dado.
	 * Principio de separación consulta - comando (CQSP).
	 */
	public void tirar() {
		this.ultimaTirada = ( int ) ( Math.random() * 10000 ) % this.numeroCaras + 1;
	}
	
}
