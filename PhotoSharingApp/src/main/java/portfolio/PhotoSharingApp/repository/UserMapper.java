package portfolio.PhotoSharingApp.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import portfolio.PhotoSharingApp.entity.Accounts;

@Mapper
public interface UserMapper {
	
	/*ーーーloginーーー*/
	
	public Accounts getSelectUser(String user);
	
	/*ーーーEntryAccountーーー*/
	
	public void insert(@Param("accounts") Accounts accounts);
	
	public String selectByUser(@Param("accounts") Accounts accounts);
	
	public String getSelectEmailAddress(@Param("accounts") Accounts accounts);
	
	/*ーーーEditAccountーーー*/
	
	public void update(@Param("accounts") Accounts accounts);
	
	/*ーーーDeleteAccountーーー*/
	
	public void delete(@Param("id") Integer id);
	
}