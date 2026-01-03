package portfolio.PhotoSharingApp.service.account;

import portfolio.PhotoSharingApp.entity.Account;

public interface AccountService {
	
	/*ーーーLoginーーー*/

	public Account getLoginAccount(String user);
	
	/*ーーーEntryAccountーーー*/

	public void createAccount(Account account);
	
	public boolean isUserExisting(Account Account);
	
	public boolean isEmailAddressExisting(Account Account);
	
	/*ーーーEditAccountーーー*/
	
	public void editAccount(Account account);
	
	/*ーーーDeleteAccountーーー*/
	
	public boolean isCreateGroupExisting(int id);
	
	public void removeAccount(int id);
	
	/*ーーー比較ーーー*/
	public int isCurrentUser(int id);
	
}