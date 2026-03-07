package portfolio.PhotoSharingApp.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import portfolio.PhotoSharingApp.entity.Account;

@Mapper
public interface AccountMapper {
	
	public void insert(@Param("account") Account account);
	
	public void update(@Param("account") Account account);
	
	public void delete(Integer userId);
	
	/*ーーーーーーーーーーーーーーーーーーーー*/
	
	public Account getSelectAccount(String email);
	
	public Integer getSelectId(String email);
	
	public String getSelectUsername(String username);
	
	public String getSelectEmailAddress(Integer adminId);
	
}