package portfolio.PhotoSharingApp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import portfolio.PhotoSharingApp.entity.Accounts;
import portfolio.PhotoSharingApp.repository.UserMapper;
import portfolio.PhotoSharingApp.service.UserService;

@Service
public class UserServiceImpl implements UserService  {

	@Autowired
	private UserMapper userMapper;
	
	/*アカウント登録*/
	@Override
	public void  insertEntryAccount(Accounts accounts){
		userMapper.insertAccount(accounts);
	}
}
