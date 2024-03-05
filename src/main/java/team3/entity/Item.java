package team3.entity;

import java.time.LocalDateTime;

public class Item {
	private int mid;
	private String name;
	private int price;
	private LocalDateTime itemTime;
	private String category;
	private String image;
	private String description;
	
	public Item() {
	}

	public Item(String name, int price, String category, String image, String description) {
		this.name = name;
		this.price = price;
		this.category = category;
		this.image = image;
		this.description = description;
	}

	public Item(String name, int price, String category, String description) {
		this.name = name;
		this.price = price;
		this.category = category;
		this.description = description;
	}

	public Item(int mid, String name, int price, LocalDateTime itemTime, String category, String image,
			String description) {
		this.mid = mid;
		this.name = name;
		this.price = price;
		this.itemTime = itemTime;
		this.category = category;
		this.image = image;
		this.description = description;
	}

	@Override
	public String toString() {
		return "Item [mid=" + mid + ", name=" + name + ", price=" + price + ", itemTime=" + itemTime + ", category="
				+ category + ", image=" + image + ", description=" + description + "]";
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public LocalDateTime getItemTime() {
		return itemTime;
	}

	public void setItemTime(LocalDateTime itemTime) {
		this.itemTime = itemTime;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
