package portfolio.PhotoSharingApp.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import portfolio.PhotoSharingApp.entity.Accounts;

@Mapper
public interface UserMapper {
	
	/*ログイン(アカウント名が存在するかを確認)*/
	public Accounts getSelectUser(String user);
	
	/*ーEntryAccountーーーーーーーーーーーーーーーーー*/
	
	/*アカウント登録*/
	public void insertAccount(@Param("accounts") Accounts accounts);
	
	/*アカウント名がデータベースに存在するかを確認*/
	public String selectByUser(@Param("accounts") Accounts accounts);
	
	/*メールアドレスがデータベースに存在するかを確認*/
	public String selectByEmailAddress(@Param("accounts") Accounts accounts);
	
	/*ーーーーーーーーーーーーーーーーーーーーーーーー*/
	
	/*パスワード変更*/
	public void updatePass(@Param("accounts") Accounts accounts);
	
	/*ーーーーーーーーーーーーーーーーーーーーーーーー*/
	
	/*アカウント削除*/
	public void deleteAccount(@Param("id") Integer id);
	
	/*ーーーーーーーーーーーーーーーーーーーーーーーー*/
	

	
	
	
	/*idからuserNameを取得*/
	public Accounts selectByUserName(@Param("id") Integer id);
	
	
}