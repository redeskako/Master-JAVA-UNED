
public class Vivienda extends Edificio{
	
	//metodos constructores:
	public Vivienda(double sup, int pl){
		super(sup, pl);
	}
	public Vivienda(double sup){
		super(sup);
	}
	
	public int calculoAforo(){
		double superficieTotal = super.getSuperficiePlanta() * super.getNumeroPlantas();
		double aforo;
		
		if (superficieTotal <= 20){
			aforo = 1;
		}else if (superficieTotal>20 && superficieTotal<=90){
			aforo = superficieTotal/15;
		}else if (superficieTotal>90 && superficieTotal<=100){
			aforo = 6;
		}else {
			aforo = superficieTotal/20;
		}

		
		if (aforo%10>5){
			aforo++;
		}
		
		return (int) aforo;
	}
}
