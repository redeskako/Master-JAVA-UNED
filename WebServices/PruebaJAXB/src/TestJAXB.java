import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import es.uned.servicioswebs.jaxb.Libro;

public class TestJAXB {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			final JAXBContext jc = JAXBContext.newInstance(Libro.class.getPackage().getName());
			final Unmarshaller u = jc.createUnmarshaller();
			final JAXBElement<Libro> menuElement = (JAXBElement<Libro>) u.unmarshal(TestJAXB.class.getResourceAsStream("/libros.xml"));
			System.out.println("Nombre: " + menuElement.getValue().getNombre());
			System.out.println("ISBN: " + menuElement.getValue().getISBN());
		}catch (JAXBException e){
			e.printStackTrace();
		}
	}
}
