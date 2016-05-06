package zeng.entity;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;


public class SalPlan implements java.io.Serializable {
	private String id;								//计划编号
	private SalChance salChance;					//营销机会信息
	private String date;							//计划日期
	private String todo;							//计划实施说明
	private String result;						//计划结果
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@JSON(serialize = false)
	public SalChance getSalChance() {
		return salChance;
	}
	public void setSalChance(SalChance salChance) {
		this.salChance = salChance;
	}
	public String getTodo() {
		return todo;
	}
	public void setTodo(String todo) {
		this.todo = todo;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}


}