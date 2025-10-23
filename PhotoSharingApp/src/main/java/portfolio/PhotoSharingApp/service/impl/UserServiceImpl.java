package portfolio.PhotoSharingApp.service.impl;

import org.springframework.stereotype.Service;

import portfolio.PhotoSharingApp.entity.Accounts;
import portfolio.PhotoSharingApp.service.UserService;

@Service
public class UserServiceImpl implements UserService  {

	/*アカウント登録*/
	@Override
	public void  insertEntryAccount(Accounts accounts){
		/*todoMapper.insert(accounts);*/
	}
}
