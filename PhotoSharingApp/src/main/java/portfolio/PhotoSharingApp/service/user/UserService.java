package portfolio.PhotoSharingApp.service.user;

import portfolio.PhotoSharingApp.entity.Accounts;

public interface UserService {
	
	/*ーLoginーーーーーーーーーーーーーーーーー*/

	/*ログイン(アカウント名が存在するかを確認)*/
	public Accounts getLoginAccount(String user);
	
	/*ーEntryAccountーーーーーーーーーーーーーーーーー*/

	/*アカウント登録*/
	public void createAccount(Accounts accounts);
	
	/*登録済のアカウント名と重複していないか確認*/
	public boolean isUserExisting(Accounts Accounts);
	
	/*登録済のメールアドレスと重複していないか確認*/
	public boolean isEmailAddressExisting(Accounts Accounts);
	
	/*ーEditAccountーーーーーーーーーーーーーーーーー*/
	
	/*パスワード変更*/
	public void editAccount(Accounts accounts);
	
	/*ーDeleteAccountーーーーーーーーーーーーーーーー*/
	
	/*idを使用してアカウントを削除*/
	public void removeAccount(int id);
	
	/*ーーーーーーーーーーーーーーーーーーーーーーー*/
	
	/*idからuserNameを取得*/
	public Accounts selectByUserName(int id);
	
}