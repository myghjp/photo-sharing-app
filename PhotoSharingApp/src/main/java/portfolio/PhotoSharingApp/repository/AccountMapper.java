package portfolio.PhotoSharingApp.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import portfolio.PhotoSharingApp.entity.Account;

@Mapper
public interface AccountMapper {
	
	/*ーーーloginーーー*/
	
	public Account selectByUsername(String user);
	
	/*ーーーEntryAccountーーー*/
	
	public void insert(@Param("account") Account account);
	
	public String existsByUsername(@Param("account") Account account);
	
	public String existsByEmailAddress(@Param("account") Account account);
	
	/*ーーーEditAccountーーー*/
	
	public void update(@Param("account") Account account);
	
	/*ーーーDeleteAccountーーー*/
	
	public Integer existsByAccountId(@Param("id")Integer id);
	
	public void delete(@Param("id") Integer id);
	
	/*ーーーー仮ーーーーーーーーーー*/
	
	public String isExistingMapper(@Param("id") Integer id);
	
}