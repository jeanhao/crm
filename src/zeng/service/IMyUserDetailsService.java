package zeng.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface  IMyUserDetailsService extends UserDetailsService{
	public UserDetails loadUserByUsername(String userName);
}
