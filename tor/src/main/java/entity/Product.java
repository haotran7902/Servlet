package entity;

public class Product {
	private int id;
	private String name;
	private String type;
	private double price;
	private String title;
	private String description;
	private String image;
	private String manufacturer;
	
	
	public Product(int id, String name, String type, double price, String title, String description, String image,
			String manufacturer) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.price = price;
		this.title = title;
		this.description = description;
		this.image = image;
		this.manufacturer = manufacturer;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", type=" + type + ", price=" + price + ", title=" + title
				+ ", description=" + description + ", image=" + image + ", manufacturer=" + manufacturer + "]";
	}
}
