package zeng.tool;

import java.util.List;

public class LoginInfo {
	private List<String> RightList; //登陆用户权限名集合
	private String usrName;	//用户名


	public String getUsrName() {
		return usrName;
	}

	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}

	public List<String> getRightList() {
		return RightList;
	}

	public void setRightList(List<String> rightList) {
		RightList = rightList;
	}
}
