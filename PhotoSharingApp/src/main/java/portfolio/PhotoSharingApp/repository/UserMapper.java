package portfolio.PhotoSharingApp.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import portfolio.PhotoSharingApp.entity.Accounts;

@Mapper
public interface UserMapper {
	
	/*アカウント情報を登録*/
	public void insertAccount(@Param("accounts") Accounts accounts);
	
	/*formで入力されたユーザ名を検索*/
	public Accounts getSelectUser(String user);
	
	/*idを使用してパスワードを変更*/
	public void updateAccount(@Param("accounts") Accounts accounts);
	
	/*idを使用してアカウントを削除*/
	public void deleteAccount(@Param("id") int id);
	
	/*アカウント名とデータベースがデータベースに存在するかを確認*/
	public String selectAccountsData1(@Param("accounts") Accounts accounts);
	public String selectAccountsData2(@Param("accounts") Accounts accounts);
	
	
	public int selectAccountData3(@Param("emailAddress") String emailAddress);
	
	
	public String selectByAccountsId(@Param("accounts") Accounts accounts);
	
	/*idからuserNameを取得*/
	public Accounts selectByUserName(@Param("id") int id);
	
	/*idからuserNameを取得*/
	public String selectByUserName2(@Param("id") int id);
	
}