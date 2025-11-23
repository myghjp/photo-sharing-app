package portfolio.PhotoSharingApp.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import portfolio.PhotoSharingApp.entity.Accounts;
import portfolio.PhotoSharingApp.repository.UserMapper;
import portfolio.PhotoSharingApp.service.user.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	
	/*ログイン(アカウント名が存在するかを確認)*/
	@Override
	public Accounts getLoginAccount(String user) {
		return userMapper.getSelectUser(user);
	}
	
	/*ーEntryAccountーーーーーーーーーーーーーーーーー*/
	
	/*アカウント登録*/
	@Override
	public void createAccount(Accounts accounts){
		userMapper.insertAccount(accounts);
	}
	
	/*アカウント名がデータベースに存在するかを確認*/
	@Override
	public boolean isNameExisting(Accounts accounts) {
		if (userMapper.selectByUser(accounts) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	/*メールアドレスがデータベースに存在するかを確認*/
	@Override
	public boolean isAddressExisting(Accounts accounts) {
		if (userMapper.selectByEmailAddress(accounts) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	/*ーーーーーーーーーーーーーーーーーーーーーーーー*/
	
	/*パスワード変更*/
	@Override
	public void editAccount(Accounts accounts) {
		userMapper.updatePass(accounts);
	};
	
	/*ーーーーーーーーーーーーーーーーーーーーーーーー*/
	
	/*アカウント削除*/
	@Override
	public void removeAccount(int id){
		userMapper.deleteAccount(id);
	};
	
	/*ーーーーーーーーーーーーーーーーーーーーーーーー*/
	
	
	/*メールアドレスで一時的にIDを取得(後で修正)*/
	@Override
	public int selectAccountId(String emailAddress) {
		return userMapper.selectAccountData3(emailAddress);
	}
	
	/*ーーーーーーーー*/
	/*メールアドレスを使用してアカウントIdが存在するかを確認*/
	@Override
	public boolean isExistingAccountId(Accounts accounts) {
		if (userMapper.selectByAccountsId(accounts) != null) {
			return false;
		} else {
			return true;
		}
	}
	
	/*idからuserNameを取得*/
	@Override
	public Accounts selectByUserName(int id) {
		return userMapper.selectByUserName(id);
	}
	
	/*グループのIDからアカウントIDを紐づけて管理者名を取得*/
	@Override
	public String getAdminName(int id) {
		return userMapper.selectByUserName2(id);
	}
	
}