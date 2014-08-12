package nz.ac.massey.tcosy.PersonelSystem;

public class Person {
	
	private String name;
	private String firstName;
    private int studentID;
	private String email;
	private String gameID;
	private Boolean paidSub;

    // TODO Complete: Not final structure
	
	public Person() {
		super();
	}
	
	public Person(String name, String firstName, int studentID, String email, String gameID) {
		super();
		this.name = name;
		this.firstName = firstName;
        this.studentID = studentID;
		this.email = email;
		this.gameID = gameID;
		this.paidSub = false;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGameID() {
		return gameID;
	}
	public void setGameID(String gameID) {
		this.gameID = gameID;
	}
	public Boolean getPaidSub() {
		return paidSub;
	}
	public void setPaidSub(Boolean paidSub) {
		this.paidSub = paidSub;
	}
}
