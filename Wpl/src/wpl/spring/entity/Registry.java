package wpl.spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "registry")
public class Registry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RegistryID")
	private int registryId;
	
	@Column(name = "registryName")
	private String registryName;
	
	@Column(name = "EventDate")
	private String eventDate;
	
	@Column(name = "Share")
	private int share;
	
	@Column(name = "AddressId")
	private int addressId;

	
	public Registry() {
		
	}

	public int getRegistryId() {
		return registryId;
	}

	public void setRegistryId(int registryId) {
		this.registryId = registryId;
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
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public Registry(String registryName, String eventDate, int share, int addressId) {
		this.registryName = registryName;
		this.eventDate = eventDate;
		this.share = share;
		this.addressId = addressId;
	}
	
	
}
