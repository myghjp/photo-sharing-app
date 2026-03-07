package portfolio.PhotoSharingApp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import portfolio.PhotoSharingApp.entity.Account;
import portfolio.PhotoSharingApp.repository.AccountMapper;
import portfolio.PhotoSharingApp.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	private AccountMapper accountMapper;
	
	@Override
	public void create(Account account){
		accountMapper.insert(account);
	}
	
	@Override
	public Account getLoginAccount(String email) {
		return accountMapper.selectAccount(email);
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
	public Account findByEmail(String emailAddress) {
		return accountMapper.selectAccount(emailAddress);
	}
	
	@Override
	public boolean existsUsername(String username) {
		if (accountMapper.selectByUsername(username) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public boolean existsEmail(String emailAddress) {
		if (accountMapper.selectAccount(emailAddress) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public boolean hasGroupOwnerEmail(int groupAdminId,String email) {
		if (accountMapper.selectByEmailAddress(groupAdminId).equals(email) == email.equals(email)) {
			return true;
		} else {
			return false;
		}
	}
}