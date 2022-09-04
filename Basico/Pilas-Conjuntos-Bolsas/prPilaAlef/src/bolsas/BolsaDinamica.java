/**
 *             E S T R U C T U R A      D I N Á M I C A
 *         ============================================
 */

package bolsas;

import conjuntos.ConjuntoDinamica;
import pilas.Par;
import pilas.Pilav2;

public class BolsaDinamica extends ConjuntoDinamica {

    /**
     * Una bolsa es un conjunto, por tanto hereda de conjunto. La diferencia es
     * que la bolsa permite tener elementos repetidos.
     *
     * Incluyo una propiedad numElementos, que me dirá el número de elementos
     * repetidos para un mismo par.
     */

    private int numElementos;

    private ConjuntoDinamica bolsa;

    /**
     * Constructor Bolsa, donde inicializamos la estructura
     *
     */
    public BolsaDinamica(){
        this.bolsa = new ConjuntoDinamica();
        this.numElementos = 0;
    }

    public void incluir(Par p){
        contarRepetidos(p);
    }

    private void contarRepetidos(Par p){
        if (!bolsa.pertenece(p)){
            this.bolsa.incluir(p);
            this.numElementos = 1;

        }
        else{

        }
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