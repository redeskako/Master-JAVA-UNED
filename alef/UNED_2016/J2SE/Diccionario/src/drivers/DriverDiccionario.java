/*
 * Driver para probar la aplicación, que a partir de unas palabras clave y unas frases busca en 
 * cada frase la palabra clave y la escribe aparte. Cada frase puede tener más de un palabra clave
 * pero se analiza cada caso de manera independiente. Por simplificar no he hecho tratamiento de errores.
 * @author carlos l
 * @version 1.0
 * @exception 
 * @fecha 2016/02/16
 * @licencia 
 * @detalles 
 */

package drivers;

import diccionario.Diccionario;

public class DriverDiccionario{

	/* Crear las palabras clave y las frases que las contienen
	 * Crear los objetos necesarios y arrancar la aplicación
     * @in String[] args
     * @out void
     * @err ninguno
     */
	public static void main(String[] args){

	        String palabras = "diccionario,mesa,silla,ordenador";
	        String[]frases={
	                "El usuario encendio el ordenador",
	                "El ordenador estaba sobre la mesa",
	                "El usuario estaba sentado en la silla",
	                "Apoyo la silla contra la mesa",
	                "La mesa era de madera",
	                "La silla era más cara que el ordenador",
	                "El ordenador se cayó de la mesa",
	                "El usuario se cayó de la silla",
	                "Usuario, mesa, silla, ordenador, cuantas cosas"
	            };
	        
	      /*  System.out.println(palabras);
	        for(int i=0;i<definciones.length;++i){
	        	System.out.println(definciones[i]);
	        }
	        for(int i=0;i<frases.length;++i){
	        	System.out.println(frases[i]);
	        }*/
	        
	        Diccionario midiccionario=new Diccionario();
	        midiccionario.palabrasADefinir(palabras);
	        //System.out.println("Palabras Clave: \n"+palabras.toString());
	        for(int i=0;i<frases.length;i++){
	        	midiccionario.generarFrases(frases[i]);
	        }
	        System.out.println(midiccionario.toString());
	    }
}
