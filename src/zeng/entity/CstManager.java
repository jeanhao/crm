package zeng.entity;

import java.util.HashSet;
import java.util.Set;

import org.apache.struts2.json.annotations.JSON;

/**
 * CstManager entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class CstManager implements java.io.Serializable {
	private String id;							//客户经理编号
	private String Name;						//客户经理姓名
	private Set<CstCustomer> cstCustomers = new HashSet<CstCustomer>(0);	//客户信息
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	@JSON(serialize = false)
	public Set<CstCustomer> getCstCustomers() {
		return cstCustomers;
	}
	public void setCstCustomers(Set<CstCustomer> cstCustomers) {
		this.cstCustomers = cstCustomers;
	}

}