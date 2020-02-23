import es.uned.master.java.basico.*;
import es.uned.master.java.coleccion.*;

public class Driver {
	public static void main(String[] arg){
		Kwic kwic= new Kwic();
		String noclaves="a ante cabe con el y y y la contra de por mi las las si segun sobre tras y con a ante con";
		String [] frases={
		                  "El caballero rojo",
		                  "El reloj de la abadia",
		                  "Dabale arroz a la zorra el abad",
		                  "Las aguas contaminadas y claras de Jaen mi tierra",
		                  "El abad esta ciego",
		                  "El AbAd estA CiegO"
		};

		kwic.computaNoClaves(noclaves);

		for (int i=0; i<frases.length;i++){
			kwic.computaIndice(frases[i]);
		}

		System.out.println("Kwic:"+kwic.toString());
	}
}
