package wpl.spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "sharedregistry")
@Table(name = "sharedregistry")
public class SharedRegistry {
	
	public SharedRegistry(){
		
	}
	
	
	@Column(name ="Email")
	private String email;
	
	@Id
	@Column(name = "RegistryUrl")
	private String registryUrl;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRegistryUrl() {
		return registryUrl;
	}

	public void setRegistryUrl(String registryUrl) {
		this.registryUrl = registryUrl;
	}

	public SharedRegistry(String email, String registryUrl) {
		super();
		this.email = email;
		this.registryUrl = registryUrl;
	}

}
