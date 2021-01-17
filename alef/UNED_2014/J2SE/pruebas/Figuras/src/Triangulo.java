
public class Triangulo extends Poligonos {
	
	/* La clase Triangulo es una subclase, de la clase Poligonos. Por tanto
	 * se dice que Poligonos es la super clase mientras que a la clase heredera,
	 * Triangulo en este caso, se la denomina subclase.
	 * Recordemos que la herencia define una relación  de tipo ES UN entre los objetos.
	 * En este caso un triangulo ES UN poligono
	 * 
	 * Trinagulo ya no es una clase abstracta, ya que no tiene ningun metodo abstracto. 
	 * Se ha definido el cuerpo del método area, siguiendo las instrucciones marcadas 
	 * por la clase abstracta Poligono.Por tanto  el método area ha dejado de ser abstracto.
	 * 
	 * Tiene definidos los setters y getters para poder acceder a los atributos de clase
	 * en este caso base y altura
	 * 
	 */
	
	
	
	private int base;
	private int altura;
	
	public Triangulo(int b, int a, String c){
		super(c);
		base=b;
		altura=a;
	}

	public double area(){
		return base*altura/2;
	}

	public int getBase() {
		return base;
	}

	public void setBase(int base) {
		this.base = base;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}
	
	
	
	
}
