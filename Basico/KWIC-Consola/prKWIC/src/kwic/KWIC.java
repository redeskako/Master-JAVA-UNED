package kwic;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;



public class KWIC {
    /**
     * estructura del KWIC.
     * Necesito un conjunto para guardar las palabras no claves, estas serán:
     * el, la , los , las ..... las que aparecen en el driver, son las que
     * carecen de significado.El conjunto será de TituloKWIC, para que no distinga
     * entre eL y El
     *
     * Luego tendremos para generar las frases una estructura Map, donde por cada
     * palabra con significado, que será nuestro índice, habrá un conjunto de frases.
     * Estas frases relacionadas con el índice, serán aquellas frases en las que
     * aparezca la palabra índice.
     */
    private Set<TituloKWIC> noClaves;
    private Map<TituloKWIC,Set<String>> glosario;


    //Constructor
    public KWIC(){
         noClaves = new TreeSet();
         glosario = new TreeMap();
    }

    /**
     *
     * @param pns entra como parámetro String de palabras no significativas
     *
     * En este método meteré en un conjunto todas las palabras no significativas.
     *
     */
    public void palabrasNoSignificativas(String pns){
        //utilizo el Tokenizer para coger cada palabra no significativa y
        StringTokenizer st = new StringTokenizer(pns," ,:;");
        while (st.hasMoreTokens()){
                noClaves.add(new TituloKWIC(st.nextToken()));
        }

    }

    public void generarFrases(String frase){
        //utilizo Tokenizer para coger las palabras significativas que me valdrán
        //como índice para mi estructura Map
        StringTokenizer st = new StringTokenizer(frase);
        while (st.hasMoreTokens()){
            TituloKWIC palabra = (new TituloKWIC(st.nextToken()));
            //si no es una palabra no significativa, significa que voy bien,
            //lo que tengo es una palabra significativa
            if (!noClaves.contains(palabra)){
                //me voy a un método privado que llamaré frases, al que le
                //envio como parámetros el indice y la frase
                frases(palabra,frase);
            }

        }
    }
    /**
     *
     * @param indice --> palabra clave de la estructura Map
     * @param frase --> frase a introducir en el conjunto relacionado con el índice o
     *                  clave.
     *
     * Este método privado añade la frase al conjunto, conjunto relacionado con el
     * índice que le paso como parámetro
     */
    private void frases(TituloKWIC indice,String frase){
        //me creo un conjunto para el caso que ese indice no esté en mi estructura
        Set<String> s = new TreeSet<String>();
        //si mi estructura Map contiene ese indice
        if (glosario.containsKey(indice)){
        //devuelveme el conjunto de frases de ese indice
            s = glosario.get(indice);
        }
        //como tanto si está el indice en la estructura como si no está tengo que
        //añadir la frase al conjunto y luego relacionar ese indice con mi conjunto
        //s actualizado, lo saco del if, y no me hace falta poner un else
        s.add(indice.replace(frase));
        //OJO --> añado replace para que me sustituya en la frase
        //        el indice por ...
        //el método está definido en TituloKWIC
        glosario.put(indice,s);

    }

 //--------------------------------------------------------------------------------

    //retocamos el toString para imprimir las frases. Esto ha sido una
    //primera prueba, por eso queda comentada:
    /**
     * public String toString(){
        String cadena = "palabras no claves: \n" + "\t" + this.noClaves.toString();
        cadena += "glosario \n" + "\t" + this.glosario.toString();
        return cadena;
    }

     */

 //----------------------------------------------------------------------------
    /**
     * Este si será nuestro verdadero toString, es decir, así es como debe mostrarse
       realmente en pantalla, tal y como se ha puesto en el enunciado.

       utilizaremos dos métodos privados:
       imprimirNoClaves() --> para imprimir por pantalla el conjunto
                              de claves.

        imprimirGlosario() --> para imprimir por pantalla la estructura Map,
                               clave (índice) y valor (conjunto de frases relacionadas
                               y con el índice sustituido por ...)

     */
    public String toString(){
        String cadFinal = "";

        cadFinal += imprimirNoClaves();
        cadFinal += imprimirGlosario();
        return cadFinal;
    }

    /**
     *
     * @return una cadena con las palabras no significativas a imprimir
     *
     * método privado que me imprime el conjunto de las palabras no significativas.
     * Este método será utilizado por el toString()
     */
    private String imprimirNoClaves(){
        String cadNoClaves = "N O    C L A V E S: "; //inicializo la cadena
//        utilizo el iterador de conjuntos para recorrerlo e imprimirlo
        Iterator<TituloKWIC> itNoClaves = this.noClaves.iterator();
        while (itNoClaves.hasNext()){//mientras halla elementos en el conjunto
            cadNoClaves += itNoClaves.next().toString() + ", ";//imprime elementos
        }
        return cadNoClaves;
    }

    /**
     * método privado que me imprime la estructura Map<Indice,conjunto de frases>.
       Este método será utilizado por el toString()
     */
    private String imprimirGlosario(){
        String cadGlosario = "\n"+"\n"+"         G L O S A R I O    "+"\n";
        //como el Map no tiene iterator tengo que cogerlo de Map.Entry
        Iterator<Map.Entry<TituloKWIC,Set<String>>>itGlosario = this.glosario.entrySet().iterator();
        while (itGlosario.hasNext()){//mientras halla elementos en mi estructura
            Map.Entry<TituloKWIC,Set<String>> me = itGlosario.next();
            cadGlosario +="\n"+ me.getKey()+"\n"+"\n";//meto el indice
            //hago un método privado para recorrer el conjunto para ese índice
            cadGlosario += imprimirGlosario(me.getValue());
        }
        return cadGlosario;
    }

    /**
     *
     * @param s --> conjunto de cadenas, es el valor de mi estructura Map
     * @return  una cadena con los elementos del conjunto para imprimir
     *
     * método que me imprime el conjunto de frases. Este método será utilizado por
       el método imprimirGlosario()
     */

    private String imprimirGlosario(Set<String> s){
        //hago lo mismo que hice con el conjunto formado por palabras no
        //significativas
        String cjFrases = "";//inicializo
        //creo el iterador
        Iterator itCjFrases = s.iterator();
        //mientras halla elementos en mi conjunto
        while (itCjFrases.hasNext()){
            //imprimo los elementos
            cjFrases += "\t" + itCjFrases.next()+ "\n";
        }
        return cjFrases;
    }

}