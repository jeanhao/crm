package zeng.entity;

/**
 * BasDict entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BasDict implements java.io.Serializable {
	private String id;						//字典编号
	private String type;					//字典类别
	private String item;					//字典条目
	private String value;					//字典数据
	private String isEditable;				//是否可以编辑
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getIsEditable() {
		return isEditable;
	}
	public void setIsEditable(String isEditable) {
		this.isEditable = isEditable;
	}

}