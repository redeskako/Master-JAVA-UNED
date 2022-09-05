package es.miempresa.colecciones;
import java.util.*;
import es.miempresa.datos.MiErrorBonitoException;
import es.miempresa.datos.Par;

public class Bolsa<K>{
	private Map<K,Integer> bolsa;

	//Método constructor Bolsa vacía.
	public Bolsa(){
		this.bolsa= new HashMap<K,Integer>();
	}
	//Devuelve true si la bolsa es vacía.
	public boolean esVacio(){
		return this.bolsa.isEmpty();
	}
	public boolean pertenece(K k){
		return this.bolsa.containsKey(k);
	}

	public int iteracion(K k){
		if(this.pertenece(k)){
			return this.bolsa.get(k).intValue();
		}
		else{
			throw new MiErrorBonitoException("error");
		}
	}
	public Iterator<K> iterator(){
		return this.bolsa.keySet().iterator();
	}

	//Inserta un K en la bolsa.
	public void añadir(K k){

		if (this.pertenece(k)){
			//Recupero la clave
			//Incremento integer
			// Recuperar el par Value sobre k.
			//Actualizar
			this.bolsa.put(k, new Integer(this.bolsa.get(k).intValue()+1));
		}else{
			//Añadir uno nuevo.
			this.bolsa.put(k, new Integer (1));
		}
	}
	public void extraer (K k){
		//
		if (this.pertenece(k)){
			//Casos 1 o más.
			Integer x= this.bolsa.get(k);
			if (x.intValue()>1){
				//Cuando hay más de 1 extricto
				//Pimero decremento y lo envuelvo
				x= new Integer(x.intValue()-1);
				//Ahora lo actualizo.
				this.bolsa.put(k, x);
			}else{
				//Caso para cuando solo hay una iteración. <k,1>
				this.bolsa.remove(k);

			}
		}else{
			//lanzo error por no haber ese elemento.
			throw new MiErrorBonitoException("No hay elementos.");
		}
	}

	public String toString(){
		String str="Bolsa: ["+"\n";
		//Encapsulamos la clave y valor de Bolsa en un MapEntry
		Set<Map.Entry<K,Integer>> s1=bolsa.entrySet();
		//Creamos un iterador para recorrer nuestro conjunto MapEntry
		Iterator <Map.Entry<K,Integer>> it=s1.iterator();
		//Recorremos el MapEntry mientras queden elementos
		int i=0;
		while (it.hasNext()){
			Map.Entry me=it.next();
			//Obtenemos la clave(en este caso elemento tipo Par) y el
			//número de repeticiones del mismo(tipo Integer)
			Par p=(Par)me.getKey();
			Integer repeticiones=(Integer)me.getValue();
			//Los añadimos a la cadena que devolvemos
			i++;
			str+="(Elemento:"+i+":"+p.toString()+"-->"+" Se repite "+
				repeticiones.intValue()+" veces)"+"\n";

		}
		str+="]";
		return str;
	}
	//Comportamiento de conjunto de comparaConjunto
	/*public boolean comparaConjunto(Bolsa<K> b){
		//igualdad de conjuntos
		//
		//return (this.bolsa.keySet().iterator().equals( b.iterator()));
		// Le pido al map su set ya puesto a pedir que estoy de rebajas
		// 8 Julio, no olvideis....
		// Que casualidad existe un metodo sobre Bolsa iterator()
		// Lo uso este va a iterarte
		Iterator<K> it= b.iterator();
		boolean paso= true;
		int cont= 0;
		while (paso && (it.hasNext())){
			K k= it.next();
			paso= this.bolsa.containsKey(k);
			cont++;
		}
		// Cuando salgo puede ser
		// paso=false
		// no hay más elementos.
/*		if (this.bolsa.size()==cont){
			return paso;
		}else{
			return false;
		}

		return (this.bolsa.size()==cont) && paso;
	}*/


	public boolean comparaConjunto(Bolsa b){
		//return ((this.bolsa.keySet().iterator().equals(b.iterator()))&&(this.bolsa.size()==b.bolsa.size()));
		return this.bolsa.keySet().iterator().equals(b.iterator());
	}
}
