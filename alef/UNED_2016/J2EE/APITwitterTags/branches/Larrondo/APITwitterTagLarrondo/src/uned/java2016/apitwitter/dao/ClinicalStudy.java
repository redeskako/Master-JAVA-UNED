package uned.java2016.apitwitter.dao;

import java.util.ArrayList;
import java.util.Date;

public class ClinicalStudy {
	protected String nctId=null;
	protected String briefTitle=null;
	protected String officialTitle=null;
	protected String briefSummary=null;
	protected String detailedDescription=null;
	protected String studyDesign=null;
	protected String primaryOutcomeMeasure=null;
	protected String overallStatus=null;
	protected Date verificationDate=null;    
	protected Date lastChangedDate=null;     
	protected Date firstReceivedDate=null;
	protected String locationFacilityFame=null;
	protected String organization=null;
	protected String oversightInfoAuthority=null; 
	protected String hashtags=null;

	protected ArrayList<String> secondaryOutcomes=null;
	protected ArrayList<String> conditions=null;
	protected ArrayList<String> keywords=null;
	//protected ArrayList<String> seriousEvent=null;
	
	protected ResponsibleParty responsibleParty=null;
	protected ArrayList<Intervention> interventions =null;
	
	public ClinicalStudy(){
	    secondaryOutcomes = new ArrayList<String>();
	    conditions = new ArrayList<String>();
	    keywords = new ArrayList<String>();
	    //seriousEvent = new ArrayList<String>();
	    interventions = new ArrayList<Intervention>();
	}	
	
	
	public void setNctId(String nctId){this.nctId=nctId;}
	public String getNctId(){return this.nctId;}

	public void setBriefTitle(String briefTitle){this.briefTitle=briefTitle;}
	public String getBriefTitle(){return this.briefTitle;}
	
	public void setBriefSummary(String briefSummary){this.briefSummary=briefSummary;}
	public String getBriefSummary(){return this.briefSummary;}
	
	public void setOfficialTitle(String officialTitle){this.officialTitle=officialTitle;}
	public String getOfficialTitle(){return this.officialTitle;}

	public void setDetailedDescription(String detailedDescription){this.detailedDescription=detailedDescription;}
	public String getDetailedDescription(){return this.detailedDescription;}

	public void setStudyDesign(String studyDesign){this.studyDesign=studyDesign;}
	public String getStudyDesign(){return this.studyDesign;}
	
	public void setPrimaryOutcomeMeasure(String primaryOutcomeMeasure){this.primaryOutcomeMeasure=primaryOutcomeMeasure;}
	public String getPrimaryOutcomeMeasure(){return this.primaryOutcomeMeasure;}

	public void setOverallStatus(String overallStatus){this.overallStatus=overallStatus;}
	public String getOverallStatus(){return this.overallStatus;}
	
	public void setVerificationDate(Date verificationDate){this.verificationDate=verificationDate;}
	public Date getVerificationDate(){return this.verificationDate;}
	
	public void setLastChangedDate(Date lastChangedDate){this.lastChangedDate=lastChangedDate;}
	public Date getLastChangedDate(){return this.lastChangedDate;}

	public void setFirstReceivedDate(Date firstReceivedDate){this.firstReceivedDate=firstReceivedDate;}
	public Date getFirstReceivedDate(){return this.firstReceivedDate;}

	public void setLocationFacilityFame(String locationFacilityFame){this.locationFacilityFame=locationFacilityFame;}
	public String getLocationFacilityFame(){return this.locationFacilityFame;}

	public void setOrganization(String organization){this.organization=organization;}
	public String getOrganization(){return this.organization;}

	public void setOversightInfoAuthority(String oversightInfoAuthority){this.oversightInfoAuthority=oversightInfoAuthority;}
	public String getOversightInfoAuthority(){return this.oversightInfoAuthority;}

	public void setHashtags(String hashtags){this.hashtags=hashtags;}
	public String getHashtags(){return this.hashtags;}	

	public void setSecondaryOutcomes(String secondaryOutcomes){this.secondaryOutcomes.add(secondaryOutcomes);}
	public  ArrayList<String>getSecondaryOutcomes(){return this.secondaryOutcomes;}	
	
	public void setConditions(String conditions){this.conditions.add(conditions);}
	public ArrayList<String> getConditions(){return this.conditions;}	
	
	public void setKeywords(String keywords){this.keywords.add(keywords);}
	public ArrayList<String> getKeywords(){return this.keywords;}	
	
	/*public void setSeriousEvent(String seriousEvent){this.seriousEvent.add(seriousEvent);}
	public ArrayList<String> getSeriousEvent(){return this.seriousEvent;}
	*/
	public void setResponsibleParty(ResponsibleParty responsibleParty){this.responsibleParty=responsibleParty;}
	public ResponsibleParty getResponsibleParty(){return this.responsibleParty;}
	
	public void setIntervention(Intervention intervention){this.interventions.add(intervention);}
	public ArrayList<Intervention> getIntervention(){return this.interventions;}

}
