package portfolio.PhotoSharingApp.service;

import portfolio.PhotoSharingApp.entity.Accounts;

public interface UserService {

	/*アカウント登録*/
	public void insertEntryAccount(Accounts accounts);
	
	/*アカウント名を取得*/
	public Accounts getLoginAccount(String user);
	
	/*idを使用してパスワードを変更*/
	public void updateEditAccount(Accounts accounts);
	
	/*idを使用してアカウントを削除*/
	public void deleteAccount(Accounts accounts);
}