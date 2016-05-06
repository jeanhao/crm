package zeng.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.struts2.json.annotations.JSON;



public class SalChance implements java.io.Serializable {
	private String id;								//机会编号
	private String source;						//机会来源
	private String custName;						//客户名称
	private String title;						//概要信息
	private Integer successRate;						//成功机率
	private String Linkman;						//联系人
	private String telephone;							//电话号码
	private String description;							//机会描述
	private String CreateBy;						//创建者
	private String CreateDate;					//创建时间
	private String dueTo;						//实施者
	private String dueDate;						//实施时间
	private String status;						//机会状态
	private Set<SalPlan> salPlans = new HashSet<SalPlan>(0);			//计划记录
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getCustName() {
		return custName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public Integer getSuccessRate() {
		return successRate;
	}
	public void setSuccessRate(Integer successRate) {
		this.successRate = successRate;
	}
	public String getLinkman() {
		return Linkman;
	}
	public void setLinkman(String linkman) {
		Linkman = linkman;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCreateBy() {
		return CreateBy;
	}
	public void setCreateBy(String createBy) {
		CreateBy = createBy;
	}
	public String getDueTo() {
		return dueTo;
	}
	public void setDueTo(String dueTo) {
		this.dueTo = dueTo;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@JSON(serialize = false)
	public Set<SalPlan> getSalPlans() {
		return salPlans;
	}
	public void setSalPlans(Set<SalPlan> salPlans) {
		this.salPlans = salPlans;
	}
	public String getCreateDate() {
		return CreateDate;
	}
	public void setCreateDate(String createDate) {
		CreateDate = createDate;
	}

}