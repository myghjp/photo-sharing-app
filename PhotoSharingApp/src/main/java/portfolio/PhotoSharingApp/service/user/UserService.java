package portfolio.PhotoSharingApp.service.user;

import portfolio.PhotoSharingApp.entity.Accounts;

public interface UserService {
	
	/*ーーーLoginーーー*/

	public Accounts getLoginAccount(String user);
	
	/*ーーーEntryAccountーーー*/

	public void createAccount(Accounts accounts);
	
	/*登録済のアカウント名と重複していないか確認*/
	public boolean isUserExisting(Accounts Accounts);
	
	/*登録済のメールアドレスと重複していないか確認*/
	public boolean isEmailAddressExisting(Accounts Accounts);
	
	/*ーーーEditAccountーーー*/
	
	public void editAccount(Accounts accounts);
	
	/*ーーーDeleteAccountーーー*/
	
	public void removeAccount(int id);
	
	/*ーーーーーーーーーーーーーーーーーーーーーーー*/
	/*idからuserNameを取得*/
	/*public Accounts selectByUserName(int id);*/
}