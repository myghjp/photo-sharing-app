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
	
	/*アカウント登録*/
	@Override
	public void insertEntryAccount(Accounts accounts){
		userMapper.insertAccount(accounts);
	}
	
	/*アカウント名を取得する*/
	@Override
	public Accounts getLoginAccount(String user) {
		return userMapper.getSelectUser(user);
	}
	
	/*idを使用してパスワードを変更*/
	@Override
	public void updateEditAccount(Accounts accounts) {
		userMapper.updateAccount(accounts);
	};
	
	/*idを使用してアカウントを削除*/
	@Override
	public void deleteAccount(Accounts accounts){
		userMapper.deleteAccount(accounts);
	};
	
	/*アカウント名とデータベースがデータベースに存在するかを確認*/
	@Override
	public boolean isExistingAccountsData1(Accounts accounts) {
		if (userMapper.selectAccountsData1(accounts) == null) {
			return false;
		} else {
			return true;
		}
	}
	@Override
	public boolean isExistingAccountsData2(Accounts accounts) {
		if (userMapper.selectAccountsData2(accounts) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	/*ーーーーーーーー*/
	/*メールアドレスで一時的にIDを取得(後で修正)*/
	@Override
	public int selectAccountId(String emailAddress) {
		return userMapper.selectAccountData3(emailAddress);
	}
	
}