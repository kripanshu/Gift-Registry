package wpl.spring.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity(name = "admin")
@Table(name = "admin")
public class Admin {
	
	public Admin(){
		
	}
	
	@Id
	@Column( name = "Email")
	private String email;
	
	@Column(name = "Password")
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Admin(String email, String password) {
		
		this.email = email;
		this.password = password;
	}
	

}
