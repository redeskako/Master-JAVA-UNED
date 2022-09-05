package uned.java2016.apitwitter.clinical.crawler.model;

public class Intervention {
	protected String type=null;
	protected String name=null;

	public void setType(String type){this.type=type;}
	public String getType(){return this.type;}

	public void setName(String name){this.name=name;}
	public String getName(){return this.name;}

	public Intervention(){
	
	}
}
