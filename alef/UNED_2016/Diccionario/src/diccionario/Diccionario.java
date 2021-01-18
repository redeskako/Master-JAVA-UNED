/*
 * Clase Diccionario crear un conjunto con todas las claves y también un mapa cuyo indice
 * son esas mismas claves y el otro campo es una lista con todas las frases que contienen esa palabra
 * @author carlos l
 * @version 1.0
 * @exception 
 * @fecha 2016/02/16
 * @licencia 
 * @detalles 
 */

package diccionario;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;


public class Diccionario{
	//Atributos
    private Set<PalabraClave> palabras;
    private Map<PalabraClave,Set<String>> definciones;

    //Métodos
    /* Constructor de la clase, crea el TreSet y el TreeMap
     * @in 
     * @out no procede
     * @err ninguno
     */
    public Diccionario(){
    	palabras=new TreeSet();
    	definciones=new TreeMap();
    }
    /* Meter todas las palabras clave en un conjunto
     * Cada palabra es un objeto de la clase PalabraClave, cuya única característica ha sido pasada a 
     * mayúsculas en el momento de la creación del objeto.
     * @in String words es la frase que tiene separadas por comas todas las palabras clave
     * @out void
     * @err ninguno
     */
    public void palabrasADefinir(String words){
        StringTokenizer st=new StringTokenizer(words," ,:;");
        while (st.hasMoreTokens()){
        	palabras.add(new PalabraClave(st.nextToken()));
        }
    }
    /* Meter cada una de las frases y busco la palabra clave dentro de ella y 
     * se la paso a la siguiente función junto con la frase entera
     * @in String frase, es la frase a la que le busco la clave
     * @out void
     * @err ninguno
     */
    public void generarFrases(String frase){
        StringTokenizer st=new StringTokenizer(frase);
        while(st.hasMoreTokens()){
        	PalabraClave word=(new PalabraClave(st.nextToken()));
            if (palabras.contains(word)){
            	crearMapa(word,frase);
                //System.out.println(word);
            }
        }
       // System.out.println(frase);
    }
    /* Crear el mapa en función de la palabra clave y la frase introducida
     * hay que tener en cuenta que si la palabra ya ha sido previamente introducida
     * no debe volver a meterse
     * @in 
     * 		PalabraClave indice para el mapa con las palabras clave
     * 		String frase, es la frase a la que le busco la palabra clave
     * @out void
     * @err ninguno
     */
    private void crearMapa(PalabraClave indice,String frase){
        Set<String> s=new TreeSet<String>();
        // Si la palabra clave ya existe devuelveme el conjunto de frases que la contienen hasta el momento
        if (definciones.containsKey(indice)){
            s=definciones.get(indice);
        }
        s.add(indice.resaltar(frase));
        definciones.put(indice,s);
    }
    /* Se encarga de darle formato a como queremos que quede la impresión en pantalla
     * @in 
     * @out String con el resultado listo para imprimir en pantalla
     * @err ninguno
     */
    public String toString(){
        String cadFinal="";
        cadFinal+=imprimirPalabras();
        cadFinal+=imprimirMapa();
        return cadFinal;
    }

    /* Se encarga de sacar las palabras clave para ser impresas
     * @in 
     * @out String las palabras clave
     * @err ninguno
     */
    private String imprimirPalabras(){
        String cadPalabras="Palabra Clave: ";

        Iterator<PalabraClave> itPalabras=this.palabras.iterator();
        while(itPalabras.hasNext()){
        	cadPalabras+=itPalabras.next().toString()+", ";
        }
        return cadPalabras;
    }

    /* Se encarga de sacar el mapa con todas las frases, llama a su vez a la función
     * imprimir frases que se encarga de imprimir todas las frases que se relacionana con
     * esa clave
     * @in 
     * @out String las frases completas
     * @err ninguno
     */
    private String imprimirMapa(){
        String cadFrases="\n"+"\n"+"         FRASES   "+"\n";
        
        Iterator<Map.Entry<PalabraClave,Set<String>>>itMapa=this.definciones.entrySet().iterator();
        while (itMapa.hasNext()){
            Map.Entry<PalabraClave,Set<String>>me=itMapa.next();
            cadFrases+="\n"+me.getKey()+"\n"+"\n";
            cadFrases+=imprimirFrases(me.getValue());
        }
        return cadFrases;
    }
    /* Se encarga de sacar las frases de cada una de las palabras clave
     * @in Set<String> s conjunto de palabras clave
     * @out String las frases que corresponden a cada palabra clave
     * @err ninguno
     */
    private String imprimirFrases(Set<String> s){
        String cjFrases = "";

        Iterator itCjFrases=s.iterator();
        while (itCjFrases.hasNext()){
            cjFrases +="\t"+itCjFrases.next()+"\n";
        }
        return cjFrases;
    }
}
