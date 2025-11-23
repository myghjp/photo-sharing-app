package portfolio.PhotoSharingApp.service.user;

import portfolio.PhotoSharingApp.entity.Accounts;

public interface UserService {
	
	/*ーLoginーーーーーーーーーーーーーーーーー*/

	/*ログイン(アカウント名が存在するかを確認)*/
	public Accounts getLoginAccount(String user);
	
	/*ーEntryAccountーーーーーーーーーーーーーーーーー*/

	/*アカウント登録*/
	public void createAccount(Accounts accounts);
	
	/*アカウント名がデータベースに存在するかを確認*/
	public boolean isNameExisting(Accounts Accounts);
	
	/*メールアドレスがデータベースに存在するかを確認*/
	public boolean isAddressExisting(Accounts Accounts);
	
	/*ーEditAccountーーーーーーーーーーーーーーーーー*/
	
	/*パスワード変更*/
	public void editAccount(Accounts accounts);
	
	/*ーDeleteAccountーーーーーーーーーーーーーーーー*/
	
	/*idを使用してアカウントを削除*/
	public void removeAccount(int id);
	
	/*ーーーーーーーーーーーーーーーーーーーーーーー*/
	
	/*メールアドレスで一時的にIDを取得(後で修正)*/
	public int selectAccountId(String emailAddress);
	
	/*メールアドレスを使用してアカウントIdが存在するかを確認*/
	public boolean isExistingAccountId(Accounts accounts);
	
	/*idからuserNameを取得*/
	public Accounts selectByUserName(int id);
	
	/*グループのIDからアカウントIDを紐づけて管理者名を取得*/
	public String getAdminName(int id);

	

}