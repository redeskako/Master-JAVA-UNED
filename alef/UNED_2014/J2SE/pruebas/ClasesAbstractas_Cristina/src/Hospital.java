
public class Hospital extends Edificio{
	
	//Métodos constructores:
	public Hospital(double sup, int pl){
		super(sup, pl);
	}
	public Hospital(double sup){
		super(sup);
	}
	
	public int calculoAforo(){
		double aforo = super.getSuperficiePlanta() * super.getNumeroPlantas() / 15;
		if (aforo%10 > 5){
			aforo++;
		} 
		return (int) aforo;
	}
}
