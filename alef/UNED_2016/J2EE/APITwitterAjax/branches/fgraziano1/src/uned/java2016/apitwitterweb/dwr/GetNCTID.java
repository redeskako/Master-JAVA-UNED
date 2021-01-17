package uned.java2016.apitwitterweb.dwr;

import java.util.ArrayList;
import java.util.Iterator;
import uned.java2016.apitwitter.dao.ConnDAOImpl;
import uned.java2016.apitwitter.dao.ClinicalStudyDAOImpl;

public class GetNCTID {

	private static final long serialVersionUID = 1L;
	
	private ClinicalStudyDAOImpl miClinicalStudyDAOImpl;
	
		
	public GetNCTID(){
	}
	
	public ArrayList getNCTsByHashtag(String hashtag, String cadena){
		
		ConnDAOImpl miBd = new ConnDAOImpl();
		miBd.abrirConexion(); 
		ArrayList<String> losNCTs;
		
		
		miClinicalStudyDAOImpl=new ClinicalStudyDAOImpl(miBd.getConnection());
		losNCTs = miClinicalStudyDAOImpl.getNCTsByHashtag(hashtag, cadena);
		miBd.cerrarConexion();
		
		/*System.out.println("HASHTAG="+hashtag+" CADENA="+cadena+"==========");
		Iterator ite = losNCTs.iterator();
		while ( ite.hasNext()){
			System.out.println("Siguiente="+ite.next());
		}*/
		
		return losNCTs;
		
		 
		
	}
	
}