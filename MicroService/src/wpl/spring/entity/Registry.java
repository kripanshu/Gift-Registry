package wpl.spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "registry")
@Table(name = "registry")
public class Registry {

	
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "RegistryID")
//	private int registryId;
	
	@Column(name = "registryName")
	private String registryName;
	
	@Column(name = "EventDate")
	private String eventDate;
	
	@Column(name = "Share")
	private int share;
	
	@Column(name = "Address")
	private int registryID;
	
	@Column(name = "UserEmail")
	private String userEmail;
	
	@Id
	@Column(name = "RegistryUrl")
	private String registryUrl;

	
	public Registry() {
		
	}

	public String getRegistryName() {
		return registryName;
	}

	public void setRegistryName(String registryName) {
		this.registryName = registryName;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public int getShare() {
		return share;
	}

	public void setShare(int share) {
		this.share = share;
	}

	public int getAddressId() {
		return registryID;
	}

	public void setAddressId(int addressId) {
		this.registryID = addressId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getRegistryUrl() {
		return registryUrl;
	}

	public void setRegistryUrl(String registryUrl) {
		this.registryUrl = registryUrl;
	}

	public Registry(String registryName, String eventDate, int share, int addressId, String userEmail, String registryUrl) {
		this.registryName = registryName;
		this.eventDate = eventDate;
		this.share = share;
		this.registryID = addressId;
		this.userEmail =userEmail;
		this.registryUrl = registryUrl;
	}
	
	
}
