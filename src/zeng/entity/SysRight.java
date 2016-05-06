package zeng.entity;

import java.util.HashSet;
import java.util.Set;

import org.apache.struts2.json.annotations.JSON;
/**
 * 用户权限列表
 * @author 曾豪
 *
 */
public class SysRight implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String id;	//权限编号
	private String text;	//权限内容
	private String name;//权限代码
	private Set<SysRoleRight>  sysRoleRights = new HashSet<SysRoleRight> (0);

	@JSON(serialize=false)
	public Set<SysRoleRight> getSysRoleRights() {
		return this.sysRoleRights;
	}

	public void setSysRoleRights(Set<SysRoleRight>  sysRoleRights) {
		this.sysRoleRights = sysRoleRights;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}