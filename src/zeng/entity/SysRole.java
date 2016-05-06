package zeng.entity;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.struts2.json.annotations.JSON;


public class SysRole implements java.io.Serializable {

	private String id;
	private String name;
	private String description;
	private Set<SysUser> sysUsers = new HashSet<SysUser>(0);
	private Set<SysRoleRight> sysRoleRights = new HashSet<SysRoleRight>(0);
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@JSON(serialize = false)
	public Set<SysUser> getSysUsers() {
		return sysUsers;
	}
	public void setSysUsers(Set<SysUser> sysUsers) {
		this.sysUsers = sysUsers;
	}
	@JSON(serialize = false)
	public Set<SysRoleRight> getSysRoleRights() {
		return sysRoleRights;
	}
	public void setSysRoleRights(Set<SysRoleRight> sysRoleRights) {
		this.sysRoleRights = sysRoleRights;
	}


}