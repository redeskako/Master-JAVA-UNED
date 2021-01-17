package uned.java2016.apitwitter.dao;

import java.util.*;

public interface ClinicalStudyDAO {

	public void insertClinicalStudy(ClinicalStudy clinicalstudy);
	
	public void insertNtcIdHashtags(String ntcId, String hashtag);
	
	public ClinicalStudy selectClinicalStudy(String nctId);
			
	public ArrayList<ClinicalStudy> selectClinicalStudies(String hashtag);
	
	public ArrayList<ClinicalStudy> selectClinicalStudies(String hashtag, int minimo, int maximo);
	
	public int contadorClinicalStudies(String hashtag);
	
	public boolean exists(String nctId);
	
	public boolean existsHashtag(String hashtag);
	
	public boolean existsNctHashtag(String hashtag, String nctid);
	
	public boolean existsNctIntervention(String name, String nctid);

}
