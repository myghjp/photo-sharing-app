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
	
	public void removeAccount(int id);
	
	/*ーーーーーーーーーーーーーーーーーーーーーーー*/
	/*public Accounts selectByUserName(int id);*/
}