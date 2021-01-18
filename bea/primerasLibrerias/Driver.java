import es.miempresa.datos.*;
import java.util.*;
import es.miempresa.colecciones.*;

public class Driver {
	public static void main(String [] arg){
		boolean b;
		Set<Par> conjunto= new HashSet<Par>();
		Set<Par> conjunto2= new TreeSet<Par>();

		b=conjunto.add(new Par(1,4));
		b=conjunto.add(new Par(1,3));
/*

 		b=conjunto.add(new Par(2,1));
		b=conjunto.add(new Par(1,1));
		b=conjunto.add(new Par(1,2));
		b=conjunto.add(new Par(1,1));
		b=conjunto.add(new Par(1,2));
		b=conjunto.add(new Par(1,3));
		b=conjunto.add(new Par(2,1));
		b=conjunto.add(new Par(2,33));
*/
		b=conjunto2.add(new Par(1,3));
/*
		b=conjunto2.add(new Par(2,1));
		b=conjunto2.add(new Par(1,1));
		b=conjunto2.add(new Par(1,2));
		b=conjunto2.add(new Par(1,1));
		b=conjunto2.add(new Par(1,2));
		b=conjunto2.add(new Par(1,3));
		b=conjunto2.add(new Par(2,1));
		b=conjunto2.add(new Par(1,2));
		b=conjunto2.add(new Par(1,3));
		b=conjunto2.add(new Par(2,1));
		b=conjunto2.add(new Par(2,33));
		b=conjunto2.add(new Par(2,34));
*/

		System.out.println("conjunto:"+conjunto.toString());
		System.out.println("conjunto2"+conjunto2.toString());

		if (conjunto.equals(conjunto2)){
			System.out.println("Son iguales");
		}else{
			System.out.println("No son iguales");
		}
	}
}
