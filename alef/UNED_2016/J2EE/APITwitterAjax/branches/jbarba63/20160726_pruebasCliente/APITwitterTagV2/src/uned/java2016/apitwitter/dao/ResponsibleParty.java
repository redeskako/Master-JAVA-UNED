package uned.java2016.apitwitter.dao;

public class ResponsibleParty {
	protected String responsiblePartyType=null;
	protected String investigatorAffilation=null;
	protected String investigatorFullName=null;
	protected String investigatorTitle=null;
	
	public void setResponsiblePartyType(String responsiblePartyType){this.responsiblePartyType=responsiblePartyType;}
	public String getResponsiblePartyType(){return this.responsiblePartyType;}

	public void setInvestigatorAffilation(String investigatorAffilation){this.investigatorAffilation=investigatorAffilation;}
	public String getInvestigatorAffilation(){return this.investigatorAffilation;}

	public void setInvestigatorFullName(String investigatorFullName){this.investigatorFullName=investigatorFullName;}
	public String getInvestigatorFullName(){return this.investigatorFullName;}

	public void setInvestigatorTitle(String investigatorTitle){this.investigatorTitle=investigatorTitle;}
	public String getInvestigatorTitle(){return this.investigatorTitle;}

	public ResponsibleParty(){
	
	}

}
