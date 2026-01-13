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
		return accountMapper.selectByUsername(user);
	}
	
	@Override
	public void update(Account account) {
		accountMapper.update(account);
	}
	
	@Override
	public void remove(int userId){
		accountMapper.delete(userId);
	}
	
	/*登録済のアカウント名と重複していないかを確認*/
	@Override
	public boolean existsByUsername(String username) {
		if (accountMapper.existsByUsername(username) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	/*登録済のメールアドレスと重複していないかを確認*/
	@Override
	public boolean existsByEmail(String emailAddress) {
		if (accountMapper.existsByEmailAddress(emailAddress) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public int findById(String emailAddress) {
		return accountMapper.selectAccountById(emailAddress);
	}
}