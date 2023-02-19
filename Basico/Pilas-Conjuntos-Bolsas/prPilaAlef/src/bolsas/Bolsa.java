/**
 *                 E S T R U C T U R A     E S T Á T I C A
 *             ==========================================
 */

package bolsas;

import conjuntos.Conjunto;
import pilas.Par;
import pilas.PilaV1;

public class Bolsa extends Conjunto {

    /**
     * Una bolsa es un conjunto, por tanto hereda de conjunto. La diferencia es
     * que la bolsa permite tener elementos repetidos.
     *
     * Incluyo una propiedad numElementos, que me dirá el número de elementos
     * repetidos para un mismo par.
     */

    private int numElementos;

    private Conjunto bolsa;

    /**
     * Constructor Bolsa, donde inicializamos la estructura
     *
     */
    public Bolsa(){
        this.bolsa = new Conjunto();
        this.numElementos = 0;
    }

    public void incluir(Par p){
        contarRepetidos(p);
    }
    //esto está mal ... quiero que me sume los elementos de un mismo par, esto no
    //hace eso
    private int contarRepetidos(Par p){
        if (!bolsa.pertenece(p)){
            this.bolsa.incluir(p);
            this.numElementos = 1;

        }
        else{
            this.numElementos++;
        }
        return this.numElementos;
    }


    public void extraer(Par p){

    }

    public boolean equals(Object o){
        return true;

    }

    public String toString(){
        return "bolsa (estática): "+bolsa.toString();
    }
}
