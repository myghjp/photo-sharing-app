package portfolio.PhotoSharingApp.service.account.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import portfolio.PhotoSharingApp.entity.Account;
import portfolio.PhotoSharingApp.security.LoginUserDetails;
import portfolio.PhotoSharingApp.service.account.AccountService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private AccountService accountService;
	
	@Override
	public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {

		Account account = accountService.getLoginAccount(user);
		
		if (account == null) {
			throw new UsernameNotFoundException("user not found");
		}
		
		UserDetails userDetails = (UserDetails) new LoginUserDetails(account);
		return userDetails;
	}
}