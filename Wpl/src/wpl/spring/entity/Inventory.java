package wpl.spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "inventory")
public class Inventory {
	
	public Inventory() {
			
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ItemId")
	private int itemId;
	
	@Column(name = "ItemName")
	private String itemName;
	
	@Column(name = "Image")
	private String image;
	
	@Column(name = "Description")
	private String description;
	
	@Column(name = "Price")
	private float price;
	
	@Column(name = "Quantity")
	private int quantity;
	
	@Column(name = "Category")
	private String category;

	@Column(name = "Weight")
	private float weight;
	
	@Column(name = "DeliveryDate")
	private String deliveryDate;

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

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public Inventory(String itemName, String description, float price, int quantity, String category, float weight) {
		this.itemName = itemName;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		this.category = category;
		this.weight = weight;
	}
	
	
	
	
}
