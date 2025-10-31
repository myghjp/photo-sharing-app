package portfolio.PhotoSharingApp.service.user;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsService {
	public UserDetails loadUserByUsername(String user);
}
