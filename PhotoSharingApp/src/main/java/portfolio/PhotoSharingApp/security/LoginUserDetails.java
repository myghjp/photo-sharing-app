package portfolio.PhotoSharingApp.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import portfolio.PhotoSharingApp.entity.Accounts;

public class LoginUserDetails implements UserDetails{
	
	private final Accounts accounts;
	private final Collection<? extends GrantedAuthority> authorities;

	public LoginUserDetails(Accounts accounts) {

		GrantedAuthority authority = new SimpleGrantedAuthority("role");
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(authority);

		this.accounts = accounts;
		this.authorities = authorities;
	}

	public Accounts getAccounts() {return accounts;}
	
	public int getUserId() {return accounts.getId();}

	@Override
	public String getUsername() {return accounts.getUser();}

	@Override
	public String getPassword() {return accounts.getPass();}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	public boolean isAccountNonExpired() {return true;}
	public boolean isAccountNonLocked() {return true;}
	public boolean isCredentialsNonExpired() {return true;}
	public boolean isEnabled() {return true;}
}