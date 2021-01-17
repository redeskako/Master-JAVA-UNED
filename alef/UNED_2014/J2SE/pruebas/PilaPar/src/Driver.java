/**
 * 
 */
import org.coordenadas.*;

/**
 * @author carlosl.sanchez
 *
 */
import java.util.*;

public class Driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		TreeSet<Par> treeSetPar;
		treeSetPar = new TreeSet<Par>();
		
		
		Par p1= new Par(3,5);
		Par p2= new Par(3,6);
		Par p3= new Par(40,1);
						
		treeSetPar.add(p1);
		treeSetPar.add(p2);
		treeSetPar.add(p3);
		
		
		System.out.println(treeSetPar);
		System.out.println("¿P1 es igual a P2? "+(p1.equals(p2)));
		System.out.println("Valor P1:"+p1);
		System.out.println("Valor P2:"+p2);
		System.out.println("Valor P3:"+p3);

		

	}

}
