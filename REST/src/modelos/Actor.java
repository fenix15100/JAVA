package modelos;

public class Actor {
	
	
	public int id;
	public String firstname;
	public String lastName;
	
	//For GET from BD
	public Actor(int id, String firstname, String lastName) {
		this.id = id;
		this.firstname = firstname;
		this.lastName = lastName;
	}
	
	//For POST to BD
	public Actor(String firstname, String lastName) {
		this.firstname = firstname;
		this.lastName = lastName;
	}
	public Actor() {
		this(0, null, null);
	}
	
	
	
	
	
	
	
	
	

}
