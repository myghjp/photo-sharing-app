package portfolio.PhotoSharingApp.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import portfolio.PhotoSharingApp.entity.Account;

@Mapper
public interface AccountMapper {
	
	/*ーーーloginーーー*/
	
	public Account getSelectUser(String user);
	
	/*ーーーEntryAccountーーー*/
	
	public void insert(@Param("account") Account account);
	
	public String selectByUser(@Param("account") Account account);
	
	public String getSelectEmailAddress(@Param("account") Account account);
	
	/*ーーーEditAccountーーー*/
	
	public void update(@Param("account") Account account);
	
	/*ーーーDeleteAccountーーー*/
	
	public Integer groupAdmin(@Param("id")Integer id);
	
	public void delete(@Param("id") Integer id);
	
	/*ーーー比較ーーー*/
	public Integer selectUserId(@Param("id") Integer id);
	
}