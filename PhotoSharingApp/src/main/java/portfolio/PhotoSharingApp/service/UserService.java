package portfolio.PhotoSharingApp.service;

import portfolio.PhotoSharingApp.entity.Accounts;

public interface UserService {

	/*アカウント登録*/
	public void insertEntryAccount(Accounts accounts);
	
	/*アカウント名を取得*/
	public Accounts getLoginAccount(String user);
}
