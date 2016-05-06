package zeng.entity;

import java.util.HashSet;
import java.util.Set;

import org.apache.struts2.json.annotations.JSON;

public class Orders implements java.io.Serializable {

	// Fields
	private String id;
	private String customer;
	private String date;
	private String address;
	private String status;
	private Set<OrdersLine> ordersLines = new HashSet<OrdersLine>(0);
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setOrdersLines(Set<OrdersLine> ordersLines) {
		this.ordersLines = ordersLines;
	}

	@JSON(serialize = false)
	public Set getOrdersLines() {
		return this.ordersLines;
	}

}