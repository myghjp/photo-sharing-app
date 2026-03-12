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
		return accountMapper.selectByEmail(email);
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
		return accountMapper.selectByEmail(emailAddress); 
	}
	
	@Override
	public boolean isUsernameDuplicate(String username) {
		if (accountMapper.selectUserByUser(username) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public boolean isEmailAddressDuplicate(String emailAddress) {
		if (accountMapper.selectByEmail(emailAddress) == null) {
			return false;
		} else {
			return true;
		}
	}
}