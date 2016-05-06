package zeng.service;

import java.util.List;

import zeng.entity.SysUser;

public interface ISysUserService {

	SysUser getUserByUserName(String userName);

	List<String> getRightByUserName(String userName);

}
