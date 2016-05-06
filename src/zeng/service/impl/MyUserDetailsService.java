package zeng.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import zeng.entity.SysUser;
import zeng.service.IMyUserDetailsService;

public class MyUserDetailsService implements IMyUserDetailsService {

	@Autowired
	private SysUserService sysUserService;
	private GrantedAuthority[] grantedAuthArray;// 账号权限
	@Override
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException, DataAccessException {
		SysUser user = sysUserService.getUserByUserName(userName);
		if(user == null){//用户名不存在
			 throw new UsernameNotFoundException("User name is not found.");
		}else{
			List<String> userRight;
			if(user.getSysRole().getName().equals("admin")){//系统管理员默认获取所有权限
				userRight = sysUserService.query("select text from SysRight ");
			}else{
				userRight = sysUserService.getRightByUserName(userName);
			}
			this.grantedAuthArray = new GrantedAuthority[userRight.size()];
			for(int i = 0;i < userRight.size();i++){
				this.grantedAuthArray[i] = new GrantedAuthorityImpl(userRight.get(i));
			}
			 return new org.springframework.security.core.userdetails.User(userName, user.getPassword(), true, true, true, true, this.grantedAuthArray);
		}
	}
}
