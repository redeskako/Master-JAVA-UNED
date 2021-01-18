package tikajes;
/**
 *
 * @author A L E F'08
 *
 * En esta clase tendré un par de elementos, donde el primer elemento será la
 * categoria de empleo y el segundo elemento será la jornada
 */
public class Catjor {

	//Propiedades
	private Categoria categoria;
	private Jornada jornada;

	//Constructor
	public Catjor(Categoria c,Jornada j){
		this.categoria = c;
		this.jornada = j;
	}

	//métodos
	public Categoria getCategoria(){
		return this.categoria;
	}

	public Jornada getJornada(){
		return this.jornada;
	}

	//redefinición de métodos para la clase Catjor
	public boolean equals(Object o){
		Catjor cj = (Catjor) o;
		return (this.categoria==cj.getCategoria() && this.jornada==cj.getJornada());
	}

	public boolean equals(Catjor cj){
		return (this.categoria == cj.getCategoria() && this.jornada == cj.getJornada());
	}

	public String toString(){
		return "Categoria: " + this.categoria + " --> Jornada: " + this.jornada;
	}
}
