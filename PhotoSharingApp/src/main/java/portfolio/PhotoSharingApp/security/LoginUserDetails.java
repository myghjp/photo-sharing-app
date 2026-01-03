package portfolio.PhotoSharingApp.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import portfolio.PhotoSharingApp.entity.Account;

public class LoginUserDetails implements UserDetails {
	
	private final Account accounts;
	private final Collection<? extends GrantedAuthority> authorities;

	public LoginUserDetails(Account accounts) {

		GrantedAuthority authority = new SimpleGrantedAuthority("role");
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(authority);

		this.accounts = accounts;
		this.authorities = authorities;
	}

	public Account getAccounts() {return accounts;}
	
	public int getAccountId() {return accounts.getId();}

	@Override
	public String getUsername() {return accounts.getUsername();}

	@Override
	public String getPassword() {return accounts.getPassword();}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	public boolean isAccountNonExpired() {return true;}
	public boolean isAccountNonLocked() {return true;}
	public boolean isCredentialsNonExpired() {return true;}
	public boolean isEnabled() {return true;}
}