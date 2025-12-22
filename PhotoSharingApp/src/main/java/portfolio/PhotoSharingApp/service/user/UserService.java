package portfolio.PhotoSharingApp.service.user;

import portfolio.PhotoSharingApp.entity.Accounts;

public interface UserService {
	
	/*ーーーLoginーーー*/

	public Accounts getLoginAccount(String user);
	
	/*ーーーEntryAccountーーー*/

	public void createAccount(Accounts accounts);
	
	public boolean isUserExisting(Accounts Accounts);
	
	public boolean isEmailAddressExisting(Accounts Accounts);
	
	/*ーーーEditAccountーーー*/
	
	public void editAccount(Accounts accounts);
	
	/*ーーーDeleteAccountーーー*/
	
	public boolean isCreateGroupExisting(int id);
	
	public void removeAccount(int id);
	
	/*ーーー比較ーーー*/
	public int isCurrentUser(int id);
	
}