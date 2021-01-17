package predictions;
import java.io.Serializable;
public class Prediction implements Serializable{
     private String who;
     private String what;
     
     public Prediction(){
     
    	 
     }

	public String getWho() {
		return who;
	}

	public void setWho(String who) {
		this.who = who;
	}

	public String getWhat() {
		return what;
	}

	public void setWhat(String what) {
		this.what = what;
	}
     
	 
	
	
}
