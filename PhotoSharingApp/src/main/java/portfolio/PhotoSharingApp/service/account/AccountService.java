package portfolio.PhotoSharingApp.service.account;

import portfolio.PhotoSharingApp.entity.Account;

public interface AccountService {
	
	public void create(Account account);
	
	public Account getLoginAccount(String email);
	
	public void edit(Account account);
	
	public void remove(int userId);
	
	/*ーーーーーーーーーーーーーーーーーーーー*/
	
	public int findById(String emailAddress);
	
	public boolean existsByUsername(String username);
	
	public boolean existsByEmail(String emailAddress);
	
	public boolean isRegister(String emailAddress);
	
	public boolean isOwner(int groupAdminId,String email);

}