package wpl.spring.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

	public User()
	{
		
	}
	
	@Id
	@Column(name = "Email")
	private String email;
	
	@Column(name = "Fname")
	private String firstName;
	
	@Column(name = "Lname")
	private String lastName;
	
	@Column(name = "Password")
	private String password;
	
	@Column(name = "SecurityQuestion")
	private String securityQuestion;
	
	@Column(name = "SecurityAnswer")
	private String securityAnswer;
	
	@Column(name = "Address")
	private int addressId;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSecurityQuestion() {
		return securityQuestion;
	}

	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public String getSecurityAnswer() {
		return securityAnswer;
	}

	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}

	public User(String email, String firstName, String lastName, String password, String securityQuestion,
			String securityAnswer) {
		
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.securityQuestion = securityQuestion;
		this.securityAnswer = securityAnswer;
	}
	
	
	
}
