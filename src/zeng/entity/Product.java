package zeng.entity;

import java.util.HashSet;
import java.util.Set;

import org.apache.struts2.json.annotations.JSON;

/**
 * Product entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Product implements java.io.Serializable {
	private String id;							//产品编号
	private String name;						//产品名称
	private String type;						//产品型号
	private String batch;						//产品批次
	private String unit;						//产品单位
	private Double price;						//产品单价
	private String memo;						//产品备注
	private Set<Storage> storages = new HashSet<Storage>(0);			//仓库记录
	private Set<OrdersLine> ordersLines = new HashSet<OrdersLine>(0);		//订单详细记录

	@JSON(serialize = false)
	public Set<Storage> getStorages() {
		return storages;
	}
	@JSON(serialize = false) 
	public Set<OrdersLine> getOrdersLines() {
		return ordersLines;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public void setStorages(Set<Storage> storages) {
		this.storages = storages;
	}
	public void setOrdersLines(Set<OrdersLine> ordersLines) {
		this.ordersLines = ordersLines;
	}


}