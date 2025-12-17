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
	
	/*ーーーLoginーーー*/
	
	@Override
	public Accounts getLoginAccount(String user) {
		return userMapper.getSelectUser(user);
	}
	
	/*ーーーEntryAccountーーー*/
	
	@Override
	public void createAccount(Accounts accounts){
		userMapper.insertAccount(accounts);
	}
	
	/*アカウント名がデータベースに存在するかを確認*/
	@Override
	public boolean isUserExisting(Accounts accounts) {
		if (userMapper.selectByUser(accounts) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	/*メールアドレスがデータベースに存在するかを確認*/
	@Override
	public boolean isEmailAddressExisting(Accounts accounts) {
		if (userMapper.selectByEmailAddress(accounts) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	/*ーーーEditAccountーーー*/
	
	@Override
	public void editAccount(Accounts accounts) {
		userMapper.updatePass(accounts);
	};
	
	/*ーーーDeleteAccountーーー*/
	
	@Override
	public void removeAccount(int id){
		userMapper.deleteAccount(id);
	};
	
	/*ーーーーーーーーーーーーーーーーーーーーーーーー*/
	/*idからuserNameを取得*/
	/*@Override
	public Accounts selectByUserName(int id) {
		return userMapper.selectByUserName(id);
	}*/
	
}