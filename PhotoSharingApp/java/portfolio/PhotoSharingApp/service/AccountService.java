package portfolio.PhotoSharingApp.service;

import portfolio.PhotoSharingApp.entity.Account;

public interface AccountService {
	
	public void create(Account account);
	
	public Account getLoginAccount(String email);
	
	public void edit(Account account);
	
	public void remove(int userId);
	
	/*ーーーーーーーーーーーーーーーーーーーー*/
	
	/*メールでIDを探す*/
	public int findIdByEmail(String emailAddress);
	
	/*名前と重複*/
	public boolean existsByUsername(String username);
	
	/*メールと重複*/
	public boolean existsByEmail(String emailAddress);
	
	/*登録*/
	public boolean isRegister(String emailAddress);
	
	public boolean isOwner(int groupAdminId,String email);

}