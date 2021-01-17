package es.uned2013.parchis;



/**
 * Define las características de cada casilla que
 * forma el tablero. También mantiene información 
 * de las fichas que se encuentran en ella actualmente
 * así como su orden de llegada
 * @author alef
 */
public class Casilla {
	private Colores color; //Color de la casilla.
	private int numero; //Identificador único de la casilla, no hay dos casillas con el mismo 'numero'
	private boolean esCasa; //Indica si es una de casilla 'casa'
	private boolean esUltima; //Indica si es la casilla final del recorrido de los diferentes colores
	private boolean esSeguro; //Indica si es una casilla segura.
	private boolean esSalida; //Indica si es la primera casilla del recorrido de los diferentes colores
	private boolean esPasillo; //Indica si es una de las casillas que están entre la úlitima casilla seguro del recorrido y la casilla final
	private boolean esBarrera; //Indica si hay dos fichas en la casilla del mismo color
	private int tieneFicha; //Indica el nº de fichas que contiene, de 0 a 4.
	private Ficha ficha1; //Indica el 'identificador' de la primera ficha que llega a la casilla.
	private Ficha ficha2; //Indica el 'identificador' de la segunda ficha que llega a la casilla.
	private int siguiente; //Indica la siguiente casilla en el recorrido-->ver m�todo siguiente().
	
	/**
	 * Constructor de la clase casilla.
	 * 
	 * @param color color de la casilla en función de la ficha que contiene. Neutro si no
	 * 				hay ficha.
	 * @param numero identificador de la casilla
	 * @param esCasa define si la casilla es casa para un determinado color
	 * @param esUltima define si la casilla es la última para un determinado color
	 * @param esSeguro define si la casilla es un seguro para un determinado color
	 * @param esSalida define si la casilla es una casilla de salida para un determinado 
	 * 					color 
	 * @param esPasillo define si la casilla es una casilla pasillo para un determinado 
	 * 					color
	 * @param esBarrera define si en la casilla hay una barrera
	 * @param tieneFicha define si la casilla contiene una ficha
	 * @param ficha1 en caso de barrera almacena información de la primera ficha formando
	 * 				 dicha barrera.
	 */	
	public Casilla(Colores color, int numero, boolean esCasa, boolean esUltima,
			boolean esSeguro, boolean esSalida, boolean esPasillo,
			boolean esBarrera, int tieneFicha, Ficha ficha1) {
		// Inicializa todos los atributos excepto ficha2, inicialmente no hay
		// ninguna casilla del recorrido con dos fichas. 
		this.color = color;
		this.numero = numero;
		this.esCasa = esCasa;
		this.esUltima = esUltima;
		this.esSeguro = esSeguro;
		this.esSalida = esSalida;
		this.esPasillo = esPasillo;
		this.esBarrera = esBarrera;
		this.tieneFicha = tieneFicha;
		this.ficha1 = ficha1;
		
	}
	
	/** 
	 * Constructor. Sobrecarga para el caso en el que se crean casillas en las que
	 * no se requiere ficha
	 * 
	 * @param color color de la casilla en función de la ficha que contiene. Neutro si no
	 * 				hay ficha.
	 * @param numero identificador de la casilla
	 * @param esCasa define si la casilla es casa para un determinado color
	 * @param esUltima define si la casilla es la última para un determinado color
	 * @param esSeguro define si la casilla es un seguro para un determinado color
	 * @param esSalida define si la casilla es una casilla de salida para un determinado 
	 * 					color 
	 * @param esPasillo define si la casilla es una casilla pasillo para un determinado 
	 * 					color
	 * @param esBarrera define si en la casilla hay una barrera
	 * @param tieneFicha define si la casilla contiene una ficha
	 */
		public Casilla(Colores color, int numero, boolean esCasa, boolean esUltima,
				boolean esSeguro, boolean esSalida, boolean esPasillo,
				boolean esBarrera, int tieneFicha) {
			// Inicializa todos los atributos excepto ficha1 y ficha2. 
			this.color = color;
			this.numero = numero;
			this.esCasa = esCasa;
			this.esUltima = esUltima;
			this.esSeguro = esSeguro;
			this.esSalida = esSalida;
			this.esPasillo = esPasillo;
			this.esBarrera = esBarrera;
			this.tieneFicha = tieneFicha;
			
		}
	
	// Setters and Getters 
	/**
	* Este metodo indica el color de la casilla
	* @return color color de la casilla. Neutro si no hay color.
	*/
	public Colores getColor() {
		return color;
	}
	/**
	 * Este metodo añade un color a la casilla
	 * @param color color de la casilla
	 */
	public void setColor(Colores color) {
		this.color = color;
	}
	/**
	 * Este metodo indica a la casilla un identificador 
	 * numérico único
	 * @return numero identificador de la casilla
	 */
	public int getNumero() {
		return numero;
	}
	/**
	 * Este método añade a la casilla un identificador
	 * numérico único
	 * @param numero identificador de la casilla
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}
	/**
	 * Este metodo indica si la casilla es casa
	 * @return esCasa true si la casilla es casa
	 */
	 public boolean isEsCasa() {
		return esCasa;
	}
	 /**
	  * Este metodo establece una casilla como casa
	  * @param esCasa true si la casilla es casa
	  */
	public void setEsCasa(boolean esCasa) {
		this.esCasa = esCasa;
	}
	/**
	 * Este metodo indica si una casilla es la última (meta)	 
	 * @return esUltima true si la casilla es meta
	 */
	public boolean isEsUltima() {
		return esUltima;
	}
	/**
	 * Este metodo establece una casilla como última (meta)
	 * @param esUltima establece true si la casilla es meta
	 */
	 public void setEsUltima(boolean esUltima) {
		this.esUltima = esUltima;
	}
	 /**
	  * Este método establece una casilla como seguro
	  * @return esSeguro true si la casilla es seguro
	  */
	public boolean isEsSeguro() {
		return esSeguro;
	}
	/**
	 * Este método establece una casilla como seguro
	 * @param esSeguro establece true si la casilla es seguro
	 */
	public void setEsSeguro(boolean esSeguro) {
		this.esSeguro = esSeguro;
	}
	/**
	 * Este método indica si una casilla es salida
	 * @return esSalida true si la casilla es salida
	 */
	public boolean isEsSalida() {
		return esSalida;
	}
	
	/**
	 * Este método establece una casilla como salida	
	 * @param esSalida establece true si la casilla es salida
	 */
	public void setEsSalida(boolean esSalida) {
		this.esSalida = esSalida;
	}
	/**
	 * Este método indica si una casilla es pasillo
	 * @return esPasillo true si la casilla es pasillo
	 */
	 public boolean isEsPasillo() {
		return esPasillo;
	}
	 /**
	  * Este método establece una casilla como pasillo
	  * @param esPasillo establece true si la casilla es pasillo
	  */
	 public void setEsPasillo(boolean esPasillo) {
		this.esPasillo = esPasillo;
	}
	 /**
	  * Este método indica si una casilla es barrera
	  * @return esBarrera true si la casilla es barrera
	  */
	 public boolean isEsBarrera() {
		return this.esBarrera;
	}
	 
	/**
	* Este método establece una casilla como barrera
	* @param esBarrera establece true si la casilla es barrera
	*/
	 public void setEsBarrera(boolean esBarrera) {
		this.esBarrera = esBarrera;
	}
	 
	/**
	* Este método indica si una casilla tiene ficha
	* @return tieneFicha true si hay ficha en la casilla
	*/
	public int getTieneFicha() {
		return tieneFicha;
	}
	/**
	 * Este método establece que una casilla tiene ficha
	 * @param tieneFicha establece la casilla como ocupada por una ficha
	 */
	public void setTieneFicha(int tieneFicha) {
		this.tieneFicha = tieneFicha;
	}
	
	/**
	 * Este método nos devuelve la ficha que esta en esa casilla
	 * @return ficha1 ficha en una casilla determinada
	 */
	public Ficha getFicha1() {
		return ficha1;
	}
	/**
	 * Este metodo introduce la primera ficha en la casilla 
	 * ejemplo: casilla.setFicha1(ficha1)
	 * @param ficha1 ficha a ocupar la casilla
	 */
	public void setFicha1(Ficha ficha1) {
		this.ficha1 = ficha1;
	}
	/**
	 * Este método nos devuelve la segunda ficha que está en la casilla
	 * @return ficha2 segunda ficha en una casilla determinada en caso de existir
	 */
	public Ficha getFicha2() {
		return ficha2;
	}
	/**
	 * Este metodo introduce una segunda ficha en la casilla 
	 * ejemplo: casilla.setFicha2(ficha2)
	 * @param ficha2 segunda ficha a ocupar la casilla
	 */
	public void setFicha2(Ficha ficha2) {
		this.ficha2 = ficha2;
	}
	
	/**
	 * Este método nos indica el valor del atributo siguiente
	 * @return siguiente identificador de la siguiente casilla
	 */
	public int getSiguiente() {
		return siguiente; 
	}
	/**
	 * Este método establece el valor del atributo siguiente
	 * @param siguiente establece la siguiente casilla
	 */
	public void setSiguiente(int siguiente) {
		this.siguiente = siguiente;
	}
	
	/**
	 * Gestiona la inserción de una ficha en una casilla en función de si ya hay otra
	 * dentro o no.
	 * 
	 * Este metodo cada vez que tengo que colocar una ficha en otra casilla, 
	 * por cada una de sus diferentes opciones:. 
	 * Si casilla.tieneficha ==1 -> entonces ponerficha2 + actualizar contador de fichas 
	 * si casilla.tieneficha == 0 -> entonces ponerficha1 + actualizar contador de fichas
	 * si casilla.tieneficha >=2 -> entonces actualizar contador de fichas
	 * @param Ficha fic
	 */
	public void colocarFicha(Ficha fic){
		if (this.getTieneFicha()==1){
			this.setFicha2(fic);
			this.setTieneFicha(this.getTieneFicha()+1);
		}else if(this.getTieneFicha()==0){
			this.setFicha1(fic);
			this.setTieneFicha(this.getTieneFicha()+1);
		}else{
			this.setTieneFicha(this.getTieneFicha()+1);//para la casilla meta o casilla casa
		}
	}
	
	/**
	 *  Deja libre una casilla al mover una ficha.
	 *  Si hay dos fichas y la ficha a mover es la última en llegar, se mueve la ficha y 
	 *  si es la primera, se mueve y la segunda ficha en llegar se queda como primera
	 * @param Ficha fic
	 */
	public void quitarFicha(Ficha fic){
		switch (this.getTieneFicha()){
		case 4:
		case 3:
			this.setTieneFicha(this.getTieneFicha()-1);
			break;
		case 2:
			this.setTieneFicha(this.getTieneFicha()-1);
			if (this.getFicha1()==fic){
				this.setFicha1(this.getFicha2());
				this.setFicha2(null);
			}else{
				this.setFicha2(null);
			}
			break;
		case 1:
			this.setTieneFicha(this.getTieneFicha()-1);
			this.setFicha1(null);
			break;
		}
	}
	
	/**
	  * Devuelve el valor de la casilla siguiente para la ficha indicada en el parametro
	  * segun su casilla actual y su color como si el valor del dado fuese 1
	  * @param ficha ficha a mover
	  * @return siguiente entero correspondiente al numero de la casilla siguiente (indica ruta)
	  */
	public int siguiente(Ficha ficha){
		switch (this.numero){
		case 68:
			switch(ficha.getColor()){
			case AMARILLO: this.setSiguiente(101);
			break;
			default:
				this.setSiguiente(1);
				break;
			}
			break;
		case 17:
			switch(ficha.getColor()){
			case AZUL: this.setSiguiente(201);
			break;
			default:
				this.setSiguiente(this.getNumero()+1);
			break;
			}
			break;
		case 34:
			switch(ficha.getColor()){
			case ROJO: this.setSiguiente(301);
			break;
			default:
				this.setSiguiente(this.getNumero()+1);
			break;
			}
			break;
		case 51:
			switch(ficha.getColor()){
			case VERDE: this.setSiguiente(401);
			break;
			default:
				this.setSiguiente(this.getNumero()+1);
				break;
			}
			break;
		default:
			this.setSiguiente(this.getNumero()+1);
		   	break;
		}
		return this.getSiguiente();
		
	}
	
}


