package es.uned.master.java.model;

public class Employee{
	private String id;
	private String lastName;
	private String firstName;

	public Employee (String id, String lastName, String firstName){
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
	}

	public String getId(){
		return this.id;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getLastName(){
		return lastName;
	}

	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	public String getFirstName(){
		return firstName;
	}

	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	
	public String toString(){
		return (this.id+":"+this.firstName+", "+this.lastName);
	}
}