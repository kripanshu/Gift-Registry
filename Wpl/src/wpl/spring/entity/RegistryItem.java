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
	@Id
	@Column(name = "RegistryID")
	private int registrtyId;
	
	@Column(name = "ItemId")
	private int itemId;
	
	@Column(name = "Quantity")
	private int quantity;

	public int getRegistrtyId() {
		return registrtyId;
	}

	public void setRegistrtyId(int registrtyId) {
		this.registrtyId = registrtyId;
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

	public RegistryItem(int registrtyId, int itemId, int quantity) {
		this.registrtyId = registrtyId;
		this.itemId = itemId;
		this.quantity = quantity;
	}
	
	
}
