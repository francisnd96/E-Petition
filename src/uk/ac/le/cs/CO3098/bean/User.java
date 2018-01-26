package uk.ac.le.cs.CO3098.bean;

public class User {
	String name;
	String passwordHash;
	String fullname;
	String email;
	String firstName;
	String lastName;
	int isMP;
	
	public int getIsMP() {
		return isMP;
	}
	public void setIsMP(int isMP) {
		this.isMP = isMP;
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
	public String getPasswordHash() {
		return passwordHash;
	}
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public User(String email, String pass, String firstName, String lastName, int isMP) {
		super();
		this.email = email;
		this.passwordHash = pass;
		this.firstName = firstName;
		this.lastName = lastName;
		this.isMP = isMP;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


}