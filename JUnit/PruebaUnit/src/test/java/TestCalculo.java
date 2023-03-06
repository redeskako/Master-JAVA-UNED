import es.uned.master.java.Calculo;
import junit.framework.*;

public class TestCalculo extends TestCase{

	public void testAdd(){
		int num1 = 3;
		int num2 = 0;
		int total = 3;
		int div = 3;
		
		div = Calculo.div(num1, num2);
		assertEquals(div, total);

	}
}
