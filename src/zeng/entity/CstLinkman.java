package zeng.entity;

import org.apache.struts2.json.annotations.JSON;

/**
 * CstLinkman entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class CstLinkman implements java.io.Serializable {
	private String id;							//联系人编号
	private CstCustomer cstCustomer;			//客户信息
	private String name;						//联系人姓名
	private String sex;						//联系人性别
	private String postion;					//联系人职位
	private String telephone;						//办公电话
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@JSON(serialize = false)
	public CstCustomer getCstCustomer() {
		return cstCustomer;
	}
	public void setCstCustomer(CstCustomer cstCustomer) {
		this.cstCustomer = cstCustomer;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPostion() {
		return postion;
	}
	public void setPostion(String postion) {
		this.postion = postion;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	private String mobile;					//手机号码
	private String memo;						//备注信息

}