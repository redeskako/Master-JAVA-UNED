package es.uned.colecciones;
import es.uned.colecciones.excepciones.ClaseErrores;
import es.uned.coordenadas.Par;

/* Aqui se construye el interface MetodosPilaPar.
 * El interface MetodosPilaPar será implementado por la clase PilaPar. 
 * A nivel de construcción, tener en cuenta que no se ha definido el cuerpo de los metodos
 * que forman el interface, por tanto son metodos abstractos. .
 * 
 * Por otro lado en las clases que implementen MetodosPilaPar, PilaPar en este caso,  obligatoriamente deberán de desarrollar
 * los metodos del interface MetodosPilaPar. Digamos que para que un objeto sea de tipo PilaPar debe obligatoriamente
 * tener las funcionalidades que el interface define por medio de sus metodos. 
 * Lógicamente también puede tener otras funcionalidades.
 *   
 * En el caso del metodo ordenar se ha forzado a que el programador emplee monitorización de excepciones
 * por medio de la clase ClaseErrores. 
 * 
 * 
 */
public interface MetodosPilaPar {
  public Par[] cambiar(Par[] arg);
  public Par[] ordenar (PilaPar arg, int orden) throws ClaseErrores ;
}
