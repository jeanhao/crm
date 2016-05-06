package zeng.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import zeng.entity.SysUser;
import zeng.service.ISysUserService;
@Service
public class SysUserService extends BaseService<SysUser> implements ISysUserService {
	@Override
	public SysUser getUserByUserName(String userName) {
		return (SysUser) super.queryUnique("from SysUser s where s.userName = ?",userName);
	}
	@Override
	public List<String> getRightByUserName(String userName) {
		List<String> rightList;
		//管理员返回所有权限
		SysUser sysUser = this.getUserByUserName(userName);
		//1默认为系统管理员
		if(sysUser.getSysRole().getId().equals("1")){
			rightList = super.query("select text from SysRight");
		}else{
		//权限表的id等于角色权限表中权限id,角色权限表中角色id等于用户的角色id
			 rightList =super.query("select r.text from SysRight r , SysRoleRight rr , SysUser u where " +
					"r.id = rr.sysRight.id and rr.sysRole.id = u.sysRole.id and u.userName = ?",userName);
		}
		return rightList;
	}
}
