package portfolio.PhotoSharingApp.service.account;

import portfolio.PhotoSharingApp.entity.Account;

public interface AccountService {
	
	public void create(Account account);
	
	public Account getLoginAccount(String user);
	
	public void update(Account account);
	
	public void remove(int userId);
	
	public boolean existsByUsername(String username);
	
	public boolean existsByEmail(String emailAddress);

	public int findById(String emailAddress);
	
}