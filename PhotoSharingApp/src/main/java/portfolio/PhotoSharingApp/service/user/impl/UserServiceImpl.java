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
		userMapper.insert(accounts);
	}
	
	@Override
	public boolean isUserExisting(Accounts accounts) {
		if (userMapper.selectByUser(accounts) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public boolean isEmailAddressExisting(Accounts accounts) {
		if (userMapper.getSelectEmailAddress(accounts) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	/*ーーーEditAccountーーー*/
	
	@Override
	public void editAccount(Accounts accounts) {
		userMapper.update(accounts);
	}
	
	/*ーーーDeleteAccountーーー*/
	
	@Override
	public boolean isCreateGroupExisting(int id) {
		if (userMapper.groupAdmin(id) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public void removeAccount(int id){
		userMapper.delete(id);
	}
	
	/*ーーー比較ーーー*/
	@Override
	public int isCurrentUser(int id) {
		return userMapper.isIdAdminExisting(id);
	}
}