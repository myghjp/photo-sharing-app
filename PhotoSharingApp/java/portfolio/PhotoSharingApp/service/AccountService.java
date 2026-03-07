package portfolio.PhotoSharingApp.service;

import portfolio.PhotoSharingApp.entity.Account;

public interface AccountService {
	
	public void create(Account account);
	
	public Account getLoginAccount(String email);
	
	public void edit(Account account);
	
	public void remove(int userId);
	
	/*ーーーーーーーーーーーーーーーーーーーー*/
	
	public Account findByEmail(String emailAddress);
	
	public boolean existsUsername(String username);
	
	public boolean existsEmail(String emailAddress);
	
	public boolean hasGroupOwnerEmail(int groupAdminId,String email);

}