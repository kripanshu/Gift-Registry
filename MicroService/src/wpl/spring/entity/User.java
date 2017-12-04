package wpl.spring.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "user")
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
	
	@Column(name = "Street")
	private String street;
	
	@Column(name = "State")
	private String state;
	
	@Column(name = "Zipcode")
	private int zipcode;
	
	@Column(name = "Country")
	private String country;
	
	@Column(name = "UserToken")
	private String userToken;
	

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
	

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	

	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

	public User(String email, String firstName, String lastName, String password, String securityQuestion,
			String securityAnswer, String street, String state, int zipcode, String country, String userToken) {
		super();
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.securityQuestion = securityQuestion;
		this.securityAnswer = securityAnswer;
		this.street = street;
		this.state = state;
		this.zipcode = zipcode;
		this.country = country;
		this.userToken = userToken;
	}

	
	
	
	
}
