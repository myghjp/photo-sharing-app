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
		return accountMapper.selectByUser(user);
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
	
	/*登録済のアカウント名と重複していないかを確認*/
	@Override
	public boolean existsByUsername(String username) {
		if (accountMapper.selectByUsername(username) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	/*登録済のメールアドレスと重複していないかを確認*/
	@Override
	public boolean existsByEmail(String emailAddress) {
		if (accountMapper.selectByEmailAddress(emailAddress) == null) {
			return false;
		} else {
			return true;
		}
	}
}