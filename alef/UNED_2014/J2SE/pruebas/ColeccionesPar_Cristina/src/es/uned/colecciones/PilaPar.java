package es.uned.colecciones;
import es.uned.colecciones.excepciones.ClaseErrores;
import es.uned.coordenadas.Par;

public class PilaPar implements MetodosPilaPar{
	private Par [] lista; //Lista de pares que emular√° una pila no acotada
	private int cabeza; // La cabeza que apunta a la lista
	private static final int NULO=-1;
	private int TOPE=10;

			
	public PilaPar(){
		this.cabeza=PilaPar.NULO;
		this.lista=this.inicializa(this.TOPE);
	}
		
	private Par [] inicializa(int tope){
		Par [] res= new Par[tope];
		for (int i=0;i<tope;i++){
			res[i]=null;
		}
		return res;
	}

	/* Cambiar recibe un array con elementos de tipo Par
	 * Intercambia los elementos situados en primer y ˙ltimo lugar del array
	 * Devuelve el array recibido una vez intercambiados el primer y el ultimo elemento.
	 */
	
	public Par[] cambiar (Par[] p1){
		Par intermedio = new Par(0,0);
		intermedio = p1[this.cabeza];
		p1[this.cabeza]=p1[0];
		p1[0]=intermedio;
		return p1;
	}
	
	/* El metodo ordenar ordena los pares situados en un array de 
	 * mayor a menor o viceversa. El orden queda indicado por el 
	 * valor entero p2.1=ascendente, 2=descendente.
	 * Se considera que un par es mayor que otro, cuando es mayor  el entero resultante
	 * de sumar las coordenadas de los pares comparados.
	 */  
	
	
	public Par[] ordenar (PilaPar  p1, int p2)throws ClaseErrores {
		
		Par [] listaOrdenada;
		
		if (p2 == 1 || p2 ==2){
		   listaOrdenada=ordenarLista(p1.lista, p2,p1.cabeza ); 
		   return listaOrdenada;
		}else{	 	
	     throw new ClaseErrores ("Valor para ordenar incorrecto. 1=Acendente 2=Descendente"); 
		}
	}
	
	private Par[] ordenarLista(Par[] p1, int orden, int cabeza) {
		int intermedio;
		Par parIntermedio;
		int [] sumatorio= new int[p1.length];
	
		/*
		 * Se suman las coordenadas de cada par  
		 */
			  
		   for (int i=0; i<=cabeza; i++){
			  sumatorio [i]= p1[i].getX()+p1[i].getY(); // 
		   }
		
		   
		/* 
		 * Se ordena la secuencia de enteros resultante de sumar las coordenadas de cada par. 
		 * A la vez que se intercambian los enteros representativos de los pares se intercambian 
		 * los pares a ordenar.    
		 */
			
		for(int i=0;i<=cabeza;i++){
		  for(int j=i+1; j<=cabeza;j++){	
			 if (orden==1){ // Se ordena de menor a mayor.
				if(sumatorio[i]>sumatorio[j]){ 
				    intermedio=sumatorio[i];
				    sumatorio[i]=sumatorio[j];
				    sumatorio[j]=intermedio;
				    parIntermedio = p1[i];
				    p1[i]=p1[j];
				   	p1[j]=parIntermedio;			    
				   }    
			 }else{ // Se ordena de mayor a menor.
			    if (sumatorio[i]<sumatorio[j]){ 
				   intermedio=sumatorio[i];
				   sumatorio[i]=sumatorio[j];
				   sumatorio[j]=intermedio;
				   parIntermedio=p1[i];
				   p1[i]=p1[j];
				   p1[j]=parIntermedio;
			    }
		     } // final else mayor/menor
			  
		 } // final for [j]
									  
		}
		
	 return p1;	
	}
		
	
	
	
	
	/*
	 * A√±ade un Par a la pila
	 */
			
	public void push(Par p){
		
		if (this.cabeza<this.TOPE){
			this.lista[cabeza+1]= p;
		}else{
			// El array est√° llenito.
			this.lista=llena(this.lista,p);
		}
		this.cabeza++;
	}
	
	
	/*
	 * Metodo privado que coge una lista llena y un par y la agranda con el par integrado
	 * Se agranda el doble de tama√±o de la lista
	 */
	
		private Par[] llena(Par []lista, Par p){
			int cabezaant=this.TOPE;
			
			// crear un array de tope*2 y cambiar TOPE a TOPE*2
			
			Par[] res= this.inicializa(this.TOPE*2);
			
			this.TOPE= this.TOPE*2;
			// Asignar
			for(int i=0;i<cabezaant;i++){
				res[i]= lista[i];
			}
			res[cabezaant]= p;
			return res;
		}
	
	/*
	 * Extrae un Par de la pila y lo elimina
	 */

	public boolean equals(Object o){
		PilaPar p = (PilaPar) o;
		boolean esIgual= true;
		if (!this.esVacia() && !p.esVacia()) {

			for (int i=cabeza;i==-1;i-- ){
				if (this.lista[i]!=p.pop()){
					esIgual=false;
					i=0;
				}
			}
		}
		return esIgual;
	}
	public Par pop(){
		Par p;
		if (this.esVacia()) {
			p=null;
		}else {
			p= this.lista[cabeza];
			this.lista[cabeza]=null; //eliminamos el valor de la posicion 
			this.cabeza--;
	
		}
		return p;			
	}
	public String toString(){
		String cadena;		
		if (this.esVacia()) {
			return "La pila esta vacia";
		}else{
			cadena = "[ " ;
			/* He sustituido la instruccion for (int i=0; i< this.cabeza;i++)
			 * Por la instruccion (int i=0; i< this.cabeza;i++)
			 */
			for (int i=0; i<= this.cabeza;i++){
                                //this.lista[i] devuelve un par y hacemos uso del toString de la clase Par
				cadena = cadena + this.lista[i].toString() + " ";
				
				
			}
			cadena = cadena  + "]";
		 	
		//MGV hago que se devuelva el string construido
		//MGV	return "";
			return cadena;
			
		}		
	}

	/* 
	 * MGV aÒado el mÈtodo recuperar elemento de la lista.
	 */ 
	
	public Par recuperarElemento(int posicion){
		return lista[posicion];
	}
	
	
	
	
	
	public boolean esVacia(){
		return this.cabeza==PilaPar.NULO;
	}

	// AÒado el getter del atributo lista.
    
	public Par[] getLista(){
    	return lista;
    }

   //MGV AÒado el getter del atributo cabeza
	
	public int getCabeza(){
		return cabeza;
	}



}
