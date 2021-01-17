package conjuntos;
/**
 * Es una colección que contiene elementos no duplicados.
 * Esta versión se realiza con una estructura dinámica.
 *
 */

import pilas.MiPilaException;
import pilas.Par;
import pilas.Pilav2;


public class ConjuntoDinamica {

    /**
     * defino una propiedad conjunto que será de tipo Pilav2, que se
     * corresponde con la versión dinámica
     */
    private Pilav2 conjunto;

    public ConjuntoDinamica(){
        conjunto = new Pilav2();
    }

    public boolean esVacio(){
        return this.conjunto.esVacia();
    }

    /**
     *
     * @param p es el elemento a incluir
     *
     * Añado el par sólo si no se encuentra en la pila
     */
    public void incluir(Par p){//redefinir en Bolsa

        try{

            if (!pertenece(p)){
                this.conjunto.push(p);
            }
        }catch (UnsupportedOperationException e ){
            throw new MiPilaException("ERROR: la operación no es soportada por el conjunto");
        }catch (ClassCastException e){
            throw new MiPilaException("ERROR: the class of the specified element prevents it from being added to this set");
        }catch (NullPointerException e){
            throw new MiPilaException("ERROR: el elemento apunta a null");
        }catch (IllegalArgumentException e){
            throw new MiPilaException("ERROR en alguna propiedad de la especificación del elemento a insertar");
        }
    }
    /**
     *
     * @param p es el elemento a testear en el conjunto
     * @return true si el elemento pertenece al conjunto
     *
     * Comprobaremos si un elemento pertenece o no al conjunto.
     * Si return true, el elemento pertenece al conjunto.
     * Si return false, el elemento no pertenece al conjunto.
     *
     * Para comprobar si está me creo un método donde voy
     * a realizar los cambios producidos en mi conjunto.
     *
     */
    public boolean pertenece(Par p){
        if(this.conjunto.esVacia()){
            throw new MiPilaException("No hay elementos en la pila");
        }
        else{
            return comprobarPertenece(p);
        }

    }

    /**
     *
     * @param p
     *             elemento que voy a testear en mi conjunto
     * @return
     *             true si el par pertenece a ese conjunto.
     *
     * Me creo una pila auxiliar para no perder los elementos del conjunto.
     * Voy preguntando si el par que apunta a la cabeza es igual que el par
     * que le estoy pasando por parámetro, si son iguales pongo la variable
     * booleana ok a true(que inicialmente estaba a false), y quito el elemento de la cabeza del conjunto y
     * lo meto en mi pila auxiliar. Esto lo hago hasta que la pila quede vacía.
     */
    private boolean comprobarPertenece(Par p){
        boolean ok = false;
        Pilav2 aux = new Pilav2();
        while(!conjunto.esVacia()){
            if(p == conjunto.cabeza()){
                ok = true;
            }
            this.conjunto.pop(); //saco el elemento del conjunto
            aux.push(conjunto.cabeza()); //meto el elemento del conjunto en la pila auxiliar

        }
        return ok;
    }
    public void extraer(Par p){//redefinir en Bolsa
        try{
            if (!conjunto.esVacia()){
                conjunto.pop();
            }
        }catch(ClassCastException e){
            //No es obligatorio controlar este error
            throw new MiPilaException("ERROR: El tipo del elemento no coincide con el del conjunto");
        }catch(NullPointerException e){
            //No es obligatorio controlar este error
            throw new MiPilaException("ERROR: El elemento apunta a null");
        }catch(UnsupportedOperationException e){
            throw new MiPilaException("ERROR: No se puede extraer un elemento de la pila");
        }
    }
    /**
     * Comprueba si dos conjuntos son iguales. Para que dos conjuntos sean
     * iguales, ambos conjuntos deben tener los mismos elementos, aunque no tienen
     * porqué tener el mismo orden
     */
    public boolean equals(Object o){//redefinir en Bolsa
        return true;

    }

    public String toString(){
        return "Conjunto (dinámica): "+conjunto.toString();
    }



}
