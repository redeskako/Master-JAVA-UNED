
public class Driver {
	public static void main(String[] args){

		Vivienda edificio1 = new Vivienda(105.2, 1);
		
		System.out.printf("El edificio1 es de tipo vivienda. Tiene una superficie en planta de %.2f m2 y %d plantas. \nSu aforo es de %d personas.\n\n", 
				edificio1.getSuperficiePlanta(), edificio1.getNumeroPlantas(), edificio1.calculoAforo());

		
		Hospital edificio2 = new Hospital(1520, 4);
		
		System.out.printf("El edificio2 es un hospital. Tiene una superficie en planta de %.2f m2 y %d plantas. \nSu aforo es de %d personas.\n\n", 
				edificio2.getSuperficiePlanta(), edificio2.getNumeroPlantas(), edificio2.calculoAforo());
		
		Vivienda edificio3 = new Vivienda(105.2, 10);
		
		System.out.printf("El edificio3 es de tipo vivienda. Tiene una superficie en planta de %.2f m2 y %d plantas. \nSu aforo es de %d personas.\n\n", 
				edificio3.getSuperficiePlanta(), edificio3.getNumeroPlantas(), edificio3.calculoAforo());
	}
}
