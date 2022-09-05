import java.rmi.RemoteException;

import com.sun.org.apache.xerces.internal.xni.QName;

import servlets.WSSoapStub;
import servlets.WSSoapStub.Search;
import servlets.WSSoapStub.SoapSearch;
import servlets.WSSoapStub.SoapSearchResponse;


public class Test1 {

	/**
	 * @param args
	 * @throws RemoteException 
	 */
	public static void main(String[] args) throws RemoteException {
		// TODO Auto-generated method stub
		
	
	WSSoapStub searchStub = new WSSoapStub();
		//WSBennettSearchStub searchStub = new WSBennettSearchStub(); 
		//WSBennettSearch add0 = new WSBennettSearch();
		SoapSearch add0 = new SoapSearch();
		

		
		add0.setCampo("*");
		add0.setPag(2);
		add0.setPalabra("");
		add0.setCatalogo("Bennett");
	
		//Search finalvalue = searchStub.wSBennettSearch(add0).get_return();
		Search finalvalue = searchStub.soapSearch(add0).get_return();
		
		for (int i=0;  i < finalvalue.getTitle().length;  i++){
			
			System.out.println(finalvalue.getTitle()[i]);
			System.out.println(finalvalue.getURL()[i]);
			
		}
		
		
		
			
		System.out.println("Hello2");
		System.out.println();
		System.out.println("Hello3");
		/*
		 * OTRA FORMA DE HACERLO CON CAPTURA DE ERRORES
		 */
		//WSBennettSearchStub customer = null;
		WSSoapStub customer = null; //no sera Search???
		//WSBennettSearchStub.WSBennettSearch request = null;
		WSSoapStub.SoapSearch request = null;
		//WSBennettSearchStub.WSBennettSearchResponse response = null;
		WSSoapStub.SoapSearchResponse response = null;
		

		try {

			// creamos el soporte y la peticion
			//customer = new WSBennettSearchStub();
			customer = new WSSoapStub();
			request = new WSSoapStub.SoapSearch();
			//request = new WSBennettSearchStub.WSBennettSearch();
			

			// establecemos el parametro de la invocacion
			
			request.setPalabra("");
			request.setPag(2);
			request.setCampo("*");
			request.setCatalogo("Bennett"); //utilizar "Bennett" o "MedLine"

			// invocamos al web service
			//response = customer.wSBennettSearch(request);
			response = customer.soapSearch(request);
				
			System.out.println(response.get_return().getTitle()[1]);
		
			// mostramos la respuesta
			for (int i=0; i<response.get_return().getTitle().length; i++){
				
					
					System.out.println(response.get_return().getTitle()[i]);
					System.out.println(response.get_return().getURL()[i]);
			
			}
			

		} catch (RemoteException excepcionDeInvocacion) {
			System.err.println(excepcionDeInvocacion.toString());
		}
		
		
		
	}

}
