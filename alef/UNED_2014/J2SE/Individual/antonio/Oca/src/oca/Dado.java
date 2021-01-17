/**
* 2014, Antonio Jesús Arquillos Álvarez
* Máster en Diseño y Desarrollo de Aplicaciones Web
* Universidad Nacional de Educación a Distancia
*/

package oca;

//TODO: Auto-generated Javadoc

/**
* Clase que representa al dado clásico de seis caras del juego de la oca (compartido).
* Lo asociamos al propio juego en lugar de asignar un dado por jugador.
* Dado.
*/

public class Dado {

	/** valor máximo que puede devolver el dado 
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
