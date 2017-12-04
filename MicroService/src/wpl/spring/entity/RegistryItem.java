package wpl.spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="registryitem")
@Table(name="registryitem")
public class RegistryItem {

	public RegistryItem()
	{
		
	}
	
	@Column(name = "RegistryUrl")
	private String registryUrl;
	
	@Id
	@Column(name = "ItemId")
	private int itemId;
	
	@Column(name = "Quantity")
	private int quantity;
	
	@Column(name = "Taken")
	private int taken;
	
	@Column(name = "Email")
	private String email;
	

	public String getRegistryUrl() {
		return registryUrl;
	}

	public void setRegistryUrl(String registryUrl) {
		this.registryUrl = registryUrl;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
	public int getTaken() {
		return taken;
	}

	public void setTaken(int taken) {
		this.taken = taken;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public RegistryItem(String registryUrl, int itemId, int quantity, int taken, String email) {
		super();
		this.registryUrl = registryUrl;
		this.itemId = itemId;
		this.quantity = quantity;
		this.taken = taken;
		this.email = email;
	}

	
	
	
}
