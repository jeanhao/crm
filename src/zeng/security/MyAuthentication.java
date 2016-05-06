package zeng.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import zeng.entity.SysUser;
import zeng.service.impl.SysUserService;
import zeng.tool.MD5;

public class MyAuthentication {

	@Autowired
	private SysUserService sysUserService;
	public int getAuthenticationStatus(HttpServletRequest request) {
		// 获得请求的参数
		String username = request.getParameter("j_username");
		String password = request.getParameter("j_password");
		String code = request.getParameter("j_randCode");
		/**
		 * 1、判断验证码是否正确
		 * 2、判断账号和密码是否匹配
		 * 3、判断是否允许登录IP
		 * 4、判断是否超过最大连接数
		 */
		
		if (username == null || username.trim().isEmpty()) {
			return 1;// 用户名为空
		}
		
		if (password == null || password.isEmpty()) {
			return 2;// 密码为空
		}
		
			if (code == null || code.trim().isEmpty()) {
				return 3;// 验证码为空
			}
			
			String random = (String) request.getSession().getAttribute("randomCode");
			if (random == null || random.isEmpty() || !code.trim().equals(random)) {
				return 4;// 验证码错误
			}	
			
		SysUser sysUser = sysUserService.getUserByUserName(username);
		String md5Password = MD5.getMD5(password);
				
		if (sysUser == null || !md5Password.equals(sysUser.getPassword())) {
			return 5;// 用户名或者密码错误
		}
		return 0;
	}

}
