package portfolio.PhotoSharingApp.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import portfolio.PhotoSharingApp.entity.Accounts;
import portfolio.PhotoSharingApp.security.LoginUserDetails;
import portfolio.PhotoSharingApp.service.user.UserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {

		Accounts accounts = userService.getLoginAccount(user);
		
		if (accounts == null) {
			throw new UsernameNotFoundException("user not found");
		}
		
		UserDetails userDetails = (UserDetails) new LoginUserDetails(accounts);
		return userDetails;
	}
}