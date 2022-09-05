package uned;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.tempuri.po.ObjectFactory;
import org.tempuri.po.PurchaseOrderType;

public class Main {

	public static void main(String args[]){
		
		try {
			JAXBContext context = JAXBContext.newInstance(ObjectFactory.class);
			
			Unmarshaller u = context.createUnmarshaller();
			JAXBElement<PurchaseOrderType> root = u.unmarshal(new StreamSource("xml/XML.xml"), PurchaseOrderType.class);
			PurchaseOrderType rootB = root.getValue();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
