package portfolio.PhotoSharingApp.service.user;

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
	
	/*アカウント名とがデータベースに存在するかを確認*/
	public boolean isExistingAccountsData1(Accounts Accounts);
	public boolean isExistingAccountsData2(Accounts Accounts);
	
	/*ーーーーーーーー*/
	/*メールアドレスで一時的にIDを取得(後で修正)*/
	public int selectAccountId(String emailAddress);
	
	/*ーーーーーーーー*/
	/*メールアドレスを使用してアカウントIdが存在するかを確認*/
	public boolean isExistingAccountId(Accounts accounts);
}