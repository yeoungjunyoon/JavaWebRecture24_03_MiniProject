package team3.entity;

import java.time.LocalDateTime;

public class Item_order {
	private int oid;
	private String uid;
	private int totalPrice;
	private LocalDateTime orderTime;
	private int status;
	
	public Item_order() {
	}

	public Item_order(String uid) {
		this.uid = uid;
	}

	public Item_order(int oid, String uid, int totalPrice, LocalDateTime orderTime, int status) {
		this.oid = oid;
		this.uid = uid;
		this.totalPrice = totalPrice;
		this.orderTime = orderTime;
		this.status = status;
	}

	@Override
	public String toString() {
		return "Item_order [oid=" + oid + ", uid=" + uid + ", totalPrice=" + totalPrice + ", orderTime=" + orderTime
				+ ", status=" + status + "]";
	}

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public LocalDateTime getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(LocalDateTime orderTime) {
		this.orderTime = orderTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
