package portfolio.PhotoSharingApp.service.account;

import portfolio.PhotoSharingApp.entity.Account;

public interface AccountService {
	
	public Account getLoginAccount(String user);
	
	public void createAccount(Account account);
	
	public void updateAccount(Account account);
	
	public void removeAccount(int id);
	
	public boolean isUsernameExisting(Account Account);
	
	public boolean isEmailAddressExisting(Account Account);
	
	public boolean isCreateGroupExisting(int id);
}