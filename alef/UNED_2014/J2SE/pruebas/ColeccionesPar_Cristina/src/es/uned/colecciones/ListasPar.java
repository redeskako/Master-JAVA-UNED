package es.uned.colecciones;

import java.lang.reflect.Array;

import es.uned.colecciones.excepciones.ClaseErrores;
import es.uned.coordenadas.Par;




public class ListasPar {
	private Par[] p;
	public final int TOPE; //si deseamos que todas las listas 
						   //tengan el mismo tamaÃ±o se declara static
		
		
	// Constructores de la clase
	
	/* Inicializa la constante TOPE a 10.
	 * Define que el tamaño del array p será de TOPE elementos, esto es, diez elementos.
	 * Inicializa el p a null.
	 */
	public ListasPar(){
		this.TOPE= 10;
		this.p= new Par[this.TOPE];
		this.p=this.inicializa();
	}
	
	/* Crea el array p con tantos elementos como se indique en tope.
	 * Inicializa a null el array p.
	 */
	public ListasPar(int tope){
		this.TOPE= tope;
		this.p= new Par[this.TOPE];
		this.p=this.inicializa();
	}
	
	/* Crea el array p con 10 elementos de tipo Par.
	 * Recorre el array p inicializandolo a null.
	 * Devuelve el array p.
	 */
	
	private Par [] inicializa(){
		Par [] p= new Par[this.TOPE];//array de pares a null
		for (int i=0;i<this.TOPE;i++){
			p[i]=null;
		}
		return p;
	}
	
	
	
	
	/* Añadimos el elemento x al array p en la posicion pos 
	 * Se controla que la posición indicada sea válida para el array
	 */
	
	public void anadir(Par x, int pos) throws ClaseErrores{
	   if (pos>=0 && pos<=p.length-1){		
		  p[pos]=x;
	   }else{
		   throw new ClaseErrores("Error en anadir.");
	   }
          
	}
	
	/* Creo que  el metodo extraer y obtener devuelven el mismo valor, esto es
	 * el par almacenado en una determinada posicion del array p.Entonces, cambio
	 * el metodo extaer para que devuelva la posción de un par dado.  
	 
	   public Par extraer(int pos)throws ClaseErrores{
		  if (pos>=0 && pos<=p.length-1){		
			  return p[pos]; 
		   }else{
			  throw new ClaseErrores("Error en extraer.");
		   }
		
	    }*/
	
	    public int extraer(Par x)throws ClaseErrores{
	    	boolean encontrado = false;
			int i =0;
			
			while (!encontrado && i<p.length){
		    	if (x==p[i]){
		    		encontrado = true;
		    	}
		        i++;
		    }
		    
			if (!encontrado){
				i*=-1;
			}
	      
	        return i;
	    }
	
	
	public Par obtener(int pos)throws ClaseErrores{
		// return null; No encuentro sentido a que retorne null.
		Object obj=null; 
		if (pos>=0 && pos<=p.length-1){
			  obj= Array.get(p,pos);
			  return (Par) obj; 
		   }else{
			  throw new ClaseErrores("Error en obtener.");
		   }
	
	}
	
	/* Considero que el método debe tener la siguiente funcionalidad.
	 * Dado un objeto de la clase Par, buscarlo en el array p.
	 * Si el objeto buscado se encuentra en p entonces pertenece deberá
	 * devolver el valor true. Si el objeto NO se encuentra en p entonces
	 * pertenece deberá devolver false   
	 * 
	 */
	public boolean pertenece(Par x){
		boolean encontrado = false;
		int i =0;
		
		while (!encontrado && i<p.length){
	    	if (x==p[i]){
	    		encontrado = true;
	    	}
	        i++;
	    }
	    
		return encontrado;
	}
	
		
/*	public boolean equals(Object o){
		// 2 listas son iguales si lo son todos sus elementos
		//(sus elementos son pares)
		ListasPar l= (ListasPar) o;
		boolean esIgual=true;
		int i=0;
		if (this.p.length!=l.p.length){
			while ((esIgual) && (i<this.TOPE)){
				esIgual= (p[i].equals(l.obtener(i)));//P es un par por lo que este equals es el de los pares
				i++;
			}
			return esIgual;
		}
		else return false;
	}*/
}
