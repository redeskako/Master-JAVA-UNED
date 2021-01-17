
public abstract class Edificio {

	//Atributos:
	private double superficiePlanta;
	private int numeroPlantas;
	
	//metodos constructores:
	public Edificio (double sup, int pl){
		superficiePlanta = sup;
		numeroPlantas = pl;
	}
	
	public Edificio (double sup){
		superficiePlanta = sup;
		numeroPlantas = 1;
	}
	
	public abstract int calculoAforo(); //vacío porque es abstracto
	
	//Getters:
	public double getSuperficiePlanta(){
		return superficiePlanta;
	}
	public int getNumeroPlantas(){
		return numeroPlantas;
	}
	
}
