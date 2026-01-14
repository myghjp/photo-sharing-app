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
	
	@Override
	public void create(Account account){
		accountMapper.insert(account);
	}
	
	@Override
	public Account getLoginAccount(String user) {
		return accountMapper.selectAccount(user);
	}
	
	@Override
	public void edit(Account account) {
		accountMapper.update(account);
	}
	
	@Override
	public void remove(int userId){
		accountMapper.delete(userId);
	}
	
	/*ーーーーーーーーーーーーーーーーーーーー*/
	
	@Override
	public int findById(String emailAddress) {
		return accountMapper.selectByUserId(emailAddress);
	}
	
	@Override
	public boolean existsByUsername(String username) {
		if (accountMapper.selectByUsername(username) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public boolean existsByEmail(String emailAddress) {
		if (accountMapper.selectByEmailAddress(emailAddress) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public boolean isRegister(String emailAddress) {
		if (accountMapper.selectByUserId(emailAddress) != null) {
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public boolean isOwner(int groupAdminId,String email) {
		if (accountMapper.selectByEmailAddress2(groupAdminId).equals(email) == email.equals(email)) {
			return true;
		} else {
			return false;
		}
	}
}