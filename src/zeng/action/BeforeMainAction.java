package zeng.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

import zeng.entity.SysRight;
import zeng.entity.SysUser;
import zeng.service.ISysUserService;
import zeng.service.impl.SysUserService;
import zeng.tool.LoginInfo;

public class BeforeMainAction extends BaseAction<SysUser> implements ModelDriven<SysUser>{
	private static final long serialVersionUID = 1L;
	@Autowired
	private SysUserService sysUserService ;
	SysUser sysUser = new SysUser();
	/**
	 * 对登陆后的权限用户信息等基础数据存入session
	 */
	public void doLogin(){
		String userName = super.getLoginUserName();
		List<String> rights = super.getLoginUserRight();
		//获取当前用户信息
//	SysUser sysUser= sysUserService.getUserByUserName(userName);
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.setUsrName(userName);
		loginInfo.setRightList(rights);
		session.setAttribute("loginInfo",loginInfo);
	}
	/**
	 * 用户注册
	 * @return
	 */
	public String register(){
		if(sysUserService.getUserByUserName(sysUser.getUserName()) != null){//用户已被注册
				jsonMap.put("success",false);
				jsonMap.put("msg","用户已被注册");
		}else{
			baseService.add(sysUser);
		}
		return SUCCESS;
	}
	public void prepareRegister(){
		sysUser = new SysUser();
	}
	
	public String findPassword(){
		String userName = (String) request.getAttribute("userName");
		String email = (String) request.getAttribute("email");
		SysUser user = sysUserService.getUserByUserName(userName);
		if(user == null){
			jsonMap.put("msg","用户不存在");
			jsonMap.put("success",false);
		}else if(user.getEmail() != email){
			jsonMap.put("msg","用户与邮箱不匹配");
			jsonMap.put("success",false);
		}else{
			//发送邮箱通知
			jsonMap.put("msg","已发送重置密码链接至邮箱，请登录邮箱修改密码！");
			jsonMap.put("success",true);
		}
		return SUCCESS;
	}

	@Override
	public SysUser getModel() {
		return sysUser;
	}
}
