/*
 * Clase PalabraClave lo que hace es encapsular un string llamado palabra que va a contener
 * las palabras clave que luego se buscarán en cada una de las frases.
 * @author carlos l
 * @version 1.0
 * @exception 
 * @fecha 2016/02/16
 * @licencia 
 * @detalles 
 */

package diccionario;

import java.util.StringTokenizer;

public class PalabraClave implements Comparable<PalabraClave>{

    //Atributos
    private String palabra;

    //Métodos
    /* Constructor de la clase, inicializar la palabra a mayúsculas
     * @in String word es la palabra a la que se quiere dar la definción
     * @out no procede
     * @err ninguno
     */
    public PalabraClave(String word){
        this.palabra=word.toUpperCase();
    }
    /* Es una sobrecarga del método compareTo que está en la interfaz
     * Esta comparación no tiene en cuenta diferencias entre mayúsculas y minúsculas
     * @in PalabraClave pad que es la palabra de la cual se quiere dar la definición
     * @out int dando -1,0,1 según que palabra es mayor según el ASCII, 0 si son iguales
     * @err ninguno
     */
	@Override
	public int compareTo(PalabraClave pad){
		return this.palabra.compareToIgnoreCase(pad.palabra);
	}
	/* Para ver si dos palabras son iguales sin tener en cuenta la diferencia
	 * entre mayúsculas y minúsculas.
     * @in PalabraClave pad que es la palabra de la cual se quiere dar la definición
     * @out boolean falso si las palabras son diferentes o true si son iguales
     * @err ninguno
     */
	public boolean equals(PalabraClave pad){
        return this.palabra.equalsIgnoreCase(pad.palabra);
    }
	/* Como estas estructuras no tienen un orden predeterminado, se usa este indicador
	 * para distinguir entre ellas y evitar colisiones al almacenar. En principio si 
	 * dos palabras son iguales según el método equals deben tener el mismo hashCode
     * @in 
     * @out int con el hashCode de la palabra.
     * @err ninguno
     */
	public int hashCode(){
        return this.palabra.toUpperCase().hashCode();
    }
	/* Función para acceder la atributo palabra en mayúsculas
     * @in 
     * @out String con el atributo en mayúsculas
     * @err ninguno
     */
	public String toString(){
	       return this.palabra.toUpperCase();
	   }
	/* Función para resaltar la palabra clave dentro de la frase
     * @in String frase, con la frase a procesar
     * @out String con la frase apañada
     * @err ninguno
     */
    public String resaltar(String frase){
        StringTokenizer st=new StringTokenizer(frase);
        String salida="";
        while(st.hasMoreTokens()){
        	PalabraClave token=new PalabraClave(st.nextToken());
            if(this.palabra.equals(token.toString())){
            	salida += "... ";
            }
            else{
            	salida += token +" ";
            }
        }
        return salida;
    }
}
