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
	public Account getLoginAccount(String user) {
		return accountMapper.selectByUsername(user);
	}
	
	@Override
	public void createAccount(Account account){
		accountMapper.insert(account);
	}
	
	@Override
	public void updateAccount(Account account) {
		accountMapper.update(account);
	}
	
	@Override
	public void removeAccount(int id){
		accountMapper.delete(id);
	}
	
	/*登録済のアカウント名と重複していないかを確認*/
	@Override
	public boolean isExistsByUsername(Account account) {
		if (accountMapper.existsByUsername(account) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	/*登録済のメールアドレスと重複していないかを確認*/
	@Override
	public boolean isExistsByEmail(Account account) {
		if (accountMapper.existsByEmailAddress(account) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	/*自身が作成したグループが存在するかを確認*/
	@Override
	public boolean isFindCreateGroup(int id) {
		if (accountMapper.existsByAccountId(id) == null) {
			return false;
		} else {
			return true;
		}
	}
}