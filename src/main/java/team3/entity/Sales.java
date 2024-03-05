package team3.entity;

public class Sales {
	private int sid;
	private int mid;
	private int oid;
	private int quantity;
	private int price;
	private String name;
	
	public Sales() {
	}

	public Sales(int mid, int oid) {
		this.mid = mid;
		this.oid = oid;
	}
	
	public Sales(int mid, int oid, int quantity) {
		this.mid = mid;
		this.oid = oid;
		this.quantity = quantity;
	}

	public Sales(int sid, int mid, int oid, int quantity) {
		this.sid = sid;
		this.mid = mid;
		this.oid = oid;
		this.quantity = quantity;
	}

	public Sales(int sid, int mid, int oid, int quantity, int price) {
		this.sid = sid;
		this.mid = mid;
		this.oid = oid;
		this.quantity = quantity;
		this.price = price;
	}

	public Sales(int sid, int mid, int oid, int quantity, int price, String name) {
		super();
		this.sid = sid;
		this.mid = mid;
		this.oid = oid;
		this.quantity = quantity;
		this.price = price;
		this.name = name;
	}

	@Override
	public String toString() {
		return "sales [sid=" + sid + ", mid=" + mid + ", oid=" + oid + ", quantity=" + quantity + "]";
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
