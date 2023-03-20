package kwic;

import java.util.StringTokenizer;
/**
 *
 * @author I S A
 * esta clase me permitirá cadena de caracteres sin distinguir entre mayúsculas
 * y minúsculas
 */
public class TituloKWIC implements Comparable<TituloKWIC> {

    //propiedad
    private String titulo;

    //constructor
    public TituloKWIC(String t){
        titulo = t.toUpperCase();
    }

    /**
     * Como vamos a tener estructuras TreeMap y TreeSet, necesitamos redefinir
     * los métodos:
     * -compareTo
     * -equals
     * -hasCode
     * -toString
     */

    //método compareTo para no distinguir entre mayúsculas y minúsculas
    public int compareTo(TituloKWIC tk) {
        return this.titulo.compareToIgnoreCase(tk.getTitulo());
    }


    //método equals, que dos títulos sean iguales sin distinguir entre mayúsculas y
    //minúsculas
    public boolean equals(TituloKWIC tk) {
        return titulo.equalsIgnoreCase(tk.getTitulo());
    }

    public String getTitulo() {
    	return this.titulo;
    }
   //método hasCode, para que a la hora de insertar no halla colisiones.También
    //debemos poner toUpperCase() para que no distinga entre maýusculas y minúsculas

   public int hashCode() {
        return titulo.toUpperCase().hashCode();
    }

   public String toString(){
       return titulo.toUpperCase();
   }

    /**
     *
     * @param palabra será la palabra a reemplazar por "..."
     * @return
     *           si coinciden --> la cadena modificada
     *           si no coinciden --> la cadena sin modificar
     */
    public String replace(String palabra) {

        StringTokenizer st = new StringTokenizer(palabra);
        String destino = "";
        while(st.hasMoreTokens()){
            TituloKWIC token = new TituloKWIC(st.nextToken());
            if(titulo.equals(token.toString())){
                destino += "... ";
            }
            else{
                destino += token +" ";
            }
        }
        return destino
        ;

    }


}
