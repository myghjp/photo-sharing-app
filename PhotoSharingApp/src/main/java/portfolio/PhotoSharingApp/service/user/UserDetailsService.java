package portfolio.PhotoSharingApp.service.user;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsService {
	
	/*使われていない？*/
	public UserDetails loadUserByUsername(String user);
}
