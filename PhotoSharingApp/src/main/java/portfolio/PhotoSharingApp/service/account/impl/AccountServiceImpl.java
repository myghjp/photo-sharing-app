package portfolio.PhotoSharingApp.service.account.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import portfolio.PhotoSharingApp.entity.Account;
import portfolio.PhotoSharingApp.repository.AccountMapper;
import portfolio.PhotoSharingApp.service.account.AccountService;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	private AccountMapper accountMapper;
	
	/*ーーーLoginーーー*/
	
	@Override
	public Account getLoginAccount(String user) {
		return accountMapper.getSelectUser(user);
	}
	
	/*ーーーEntryAccountーーー*/
	
	@Override
	public void createAccount(Account account){
		accountMapper.insert(account);
	}
	
	@Override
	public boolean isUserExisting(Account account) {
		if (accountMapper.selectByUser(account) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public boolean isEmailAddressExisting(Account account) {
		if (accountMapper.getSelectEmailAddress(account) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	/*ーーーEditAccountーーー*/
	
	@Override
	public void editAccount(Account account) {
		accountMapper.update(account);
	}
	
	/*ーーーDeleteAccountーーー*/
	
	@Override
	public boolean isCreateGroupExisting(int id) {
		if (accountMapper.groupAdmin(id) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public void removeAccount(int id){
		accountMapper.delete(id);
	}
	
	/*ーーー比較ーーー*/
	@Override
	public int isCurrentUser(int id) {
		return accountMapper.selectUserId(id);
	}
}