package uned.java2016.apitwitter.dao;

public class Hashtag {

	protected String text=null;
	  public void setText(String hashtag){this.text=hashtag;}
	  public String getText(){return this.text;}
	
	protected Hashtag neighbourOf=null;
	  public void setNeighbourOf(Hashtag h){this.neighbourOf=h;}
	  public Hashtag getNeighbourgOf(){return this.neighbourOf;}
	
	protected boolean processed=false;
	  public boolean isProcessed(){return this.processed;}
	  public void setProcessed(boolean t){this.processed=t;}
	  
    public Hashtag(){}
    
	public Hashtag(String hashtag){
		this.setText(hashtag);
	}
	
	// dos hashtags son iguales si los hashtag's son iguales
	@Override
	public boolean equals(Object a){
		boolean ret=false;
		if(a instanceof Hashtag)		
		  ret=((Hashtag) a).getText().equalsIgnoreCase(this.getText());		  			
		return ret;
	}
	
	@Override
	public int hashCode(){
		return this.getText().toUpperCase().hashCode();
	}
	
}
