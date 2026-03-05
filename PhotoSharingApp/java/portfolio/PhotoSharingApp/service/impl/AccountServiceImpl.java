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
	
	/*データベースから特定のレコードの特定のカラムだけを取得する場合(idなど)*/
	
	/*findByIdメソッドを再利用(コントローラで再利用)*/
	
	/*レコード取得後に必要なフィールドだけ使用(各entityクラスからget)*/
	
	/*それ専用のメソッドを多数用意する必要がない(サービス？マッパー？)*/
	
	/*役割(サービスメソッド名)*/
	
	/*findByIdで１レコードに変更(マッパー？)*/
	/*findBy〇〇でまとめる？*/
	/*selectAccount*/
	
	/*findByIdの再利用/コントローラで選ぶ*/
	/*サービスとマッパーの両方*/
	
	
	@Override
	public int findIdByEmail(String emailAddress) {
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