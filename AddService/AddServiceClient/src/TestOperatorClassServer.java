import java.rmi.RemoteException;

import org.apache.ws.axis2.OperatorClassStub;
import org.apache.ws.axis2.OperatorClassStub.Add;


public class TestOperatorClassServer {

	/**
	 * @param args
	 * @throws RemoteException 
	 */
	public static void main(String[] args) throws RemoteException {
		// TODO Auto-generated method stub
		OperatorClassStub classStub = new OperatorClassStub();
		
		Add add0 = new Add();
		
		add0.setA(9);
		add0.setB(9);
		
		int finalvalue = classStub.add(add0).get_return();
		
		System.out.println(finalvalue);
	}

}
