package wpl.spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "inventory")
@Table(name = "inventory")
public class Inventory {
	
	public Inventory(){
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ItemId")
	private int itemId;
	
	@Column(name = "ItemName")
	private String itemName;
	
	@Column(name = "Description")
	private String description;
	
	@Column(name = "Category")
	private String category;
	
	@Column(name = "Price")
	private double price;
	
	@Column(name = "Quantity")
	private int quantity;

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Inventory(int itemId, String itemName, String description, String category, double price, int quantity) {
		this.itemId = itemId;
		this.itemName = itemName;
		this.description = description;
		this.category = category;
		this.price = price;
		this.quantity = quantity;
	}
	
	

	
}
