package portfolio.PhotoSharingApp.service;

import portfolio.PhotoSharingApp.entity.Account;

public interface AccountService {
	
	public void create(Account account);
	
	public Account getLoginAccount(String email);
	
	public void edit(Account account);
	
	public void remove(int userId);
	
	/*ーーーーーーーーーーーーーーーーーーーー*/
	
	/*メールでIDを探す*/
	public Account findByEmail(String emailAddress);
	
	public boolean usernameExists(String username);
	
	public boolean emailExists(String emailAddress);
	
	public boolean isRegister(String emailAddress);
	
	public boolean isOwner(int groupAdminId,String email);

}