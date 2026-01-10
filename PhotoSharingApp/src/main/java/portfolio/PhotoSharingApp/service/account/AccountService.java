package portfolio.PhotoSharingApp.service.account;

import portfolio.PhotoSharingApp.entity.Account;

public interface AccountService {
	
	public Account getLoginAccount(String user);
	
	public void createAccount(Account account);
	
	public void updateAccount(Account account);
	
	public void removeAccount(int id);
	
	public boolean isExistsByUsername(Account Account);
	
	public boolean isExistsByEmail(Account Account);
	
	public boolean isFindCreateGroup(int id);
}