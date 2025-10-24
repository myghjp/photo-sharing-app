package portfolio.PhotoSharingApp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import portfolio.PhotoSharingApp.entity.Accounts;
import portfolio.PhotoSharingApp.security.LoginUserDetails;
import portfolio.PhotoSharingApp.service.UserService;

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
		
		/*※↓カスタム不要か確認*/
		UserDetails userDetails = (UserDetails) new LoginUserDetails(accounts);
		return userDetails;
	}
}