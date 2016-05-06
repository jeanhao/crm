package zeng.entity;

import java.util.HashSet;
import java.util.Set;

import org.apache.struts2.json.annotations.JSON;

/**
 * Cstomer entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class CstCustomer implements java.io.Serializable {
	private String id;						//客户编号
	private CstManager cstManager;				//客户经理
	private String name;					//客户名称
	private String region;					//客户所属地区
	private Integer level;					//客户等级
	private Integer satisfy;				//客户满意度
	private Integer credit;					//客户信任度
	private String address;					//客户住址
	private String postCode;						//邮政编码
	private String telephone;						//电话号码	
	private String fax;						//传真号码
	private String website;					//网站地址
	private String licenceNo;				//营业执照号码
	public String getLicenceNo() {
		return licenceNo;
	}
	public void setLicenceNo(String licenceNo) {
		this.licenceNo = licenceNo;
	}
	private String chieftain;				//法人代表
	private Long bankroll;					//注册资金
	private Long turnover;					//年营业额
	private String bank;					//客户银行
	private String bankAccount;				//银行号码
	private String localTaxNo;				//地税登记号码
	private String nationalTaxNo;			//国税登记号码
	private String status;					//客户状态
	private Set<CstActivity> cstActivities = new HashSet<CstActivity>(0);	//客户交往记录
	private Set<CstLinkman> cstLinkmans = new HashSet<CstLinkman>(0);	//客户联系人
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public CstManager getCstManager() {
		return cstManager;
	}
	public void setCstManager(CstManager cstManager) {
		this.cstManager = cstManager;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getSatisfy() {
		return satisfy;
	}
	public void setSatisfy(Integer satisfy) {
		this.satisfy = satisfy;
	}
	public Integer getCredit() {
		return credit;
	}
	public void setCredit(Integer credit) {
		this.credit = credit;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getChieftain() {
		return chieftain;
	}
	public void setChieftain(String chieftain) {
		this.chieftain = chieftain;
	}
	public Long getBankroll() {
		return bankroll;
	}
	public void setBankroll(Long bankroll) {
		this.bankroll = bankroll;
	}
	public Long getTurnover() {
		return turnover;
	}
	public void setTurnover(Long turnover) {
		this.turnover = turnover;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	public String getLocalTaxNo() {
		return localTaxNo;
	}
	public void setLocalTaxNo(String localTaxNo) {
		this.localTaxNo = localTaxNo;
	}
	public String getNationalTaxNo() {
		return nationalTaxNo;
	}
	public void setNationalTaxNo(String nationalTaxNo) {
		this.nationalTaxNo = nationalTaxNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Set<CstActivity> getCstActivities() {
		return cstActivities;
	}
	public void setCstActivities(Set<CstActivity> cstActivities) {
		this.cstActivities = cstActivities;
	}
	@JSON(serialize = false)
	public Set<CstLinkman> getCstLinkmans() {
		return cstLinkmans;
	}
	public void setCstLinkmans(Set<CstLinkman> cstLinkmans) {
		this.cstLinkmans = cstLinkmans;
	}
	@JSON(serialize = false)
	public Set<CstLost> getCstLosts() {
		return cstLosts;
	}
	public void setCstLosts(Set<CstLost> cstLosts) {
		this.cstLosts = cstLosts;
	}
	private Set<CstLost> cstLosts = new HashSet<CstLost>(0);		//客户流失记录


}