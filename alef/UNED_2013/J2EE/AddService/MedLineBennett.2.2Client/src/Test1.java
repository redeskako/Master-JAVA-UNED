import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import servlets.Search;
import servlets.WSSoap;
import servlets.WSSoapImpl;
import servlets.WSSoapService;
import javax.xml.namespace.QName;
//import com.sun.org.apache.xerces.internal.xni.QName;
//import servlets.WSSoapStub;
//import servlets.WSSoapStub.Search;
//import servlets.WSSoapStub.SoapSearch;
//import servlets.WSSoapStub.SoapSearchResponse;

public class Test1 {
	
	private static final QName SERVICE_NAME = new QName("http://servlets/", "WSSoapService");

	/**
	 * @param args
	 * @throws RemoteException 
	 */
	public static void main(String[] args) throws RemoteException {
	/**
	 * prueba sin control de errores
	 * http://localhost:8080/MedLineBennett2.2Server/services/WSSoapPort
	 */
	
		 WSSoapService ss = null;
		try {
			ss = new WSSoapService(new URL("http://localhost:8080/MedLineBennett2.2Server/services/WSSoapPort?wsdl"), SERVICE_NAME);
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     WSSoap port = ss.getWSSoapPort();  
	
	     
	     
	     
	//WSSoapService searchStub = new WSSoapService();
	//servlets.WSSoapImpl	search = new servlets.WSSoapImpl();
		
		servlets.SoapSearch add0 = new servlets.SoapSearch();
		
		//add0.getReturn().
		add0.setArg0(1);
		add0.setArg1("*");//campo
		add0.setArg2("");//texto
		add0.setArg3("Bennett"); //usar "Bennett" o "MedLine"
		
		servlets.Search finalvalue = new Search();
			
		finalvalue = port.soapSearch(add0.getArg0(), add0.getArg1(), add0.getArg2(), add0.getArg3());
		
		for (int i=0;  i < finalvalue.getTitle().size()-1;  i++){
			
			System.out.println(finalvalue.getTitle().get(i));
			System.out.println(finalvalue.getURL().get(i));
			
		}
		
			
		System.out.println("Hello2");
		System.out.println();
		System.out.println("Hello3");
	

		
		/*
		 * PRUEBA CON CONTROL DE ERRORES
		 
		
		WSSoapService customer = null; //no sera Search???
		
		servlets.SoapSearch request = null;
		
		servlets.Search response = null;
		

	customer = new WSSoapService();
	request = new servlets.SoapSearch();
	response = new  servlets.Search();
		
		
		// establecemos el parametro de la invocacion
		
	request.setArg2("");
	request.setArg0(2);
	request.setArg1("*");
	request.setArg3("MedLine"); //utilizar "Bennett" o "MedLine"

		// invocamos al web service
		
	response = customer.soapSearch(request.getArg0(), request.getArg1(), request.getArg2(), request.getArg3());
			
	System.out.println(response.getTitle().get(0));
	
		// mostramos la respuesta
		for (int i=0; i<response.getTitle().size()-1; i++){
			
				
				System.out.println(response.getTitle().get(i));
				System.out.println(response.getURL().get(i));
		
		}
		*/
		
		
	}

}
