package portfolio.PhotoSharingApp.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import portfolio.PhotoSharingApp.entity.Account;

@Mapper
public interface AccountMapper {
	
	public Account selectByUsername(String user);
	
	public void insert(@Param("account") Account account);
	
	public void update(@Param("account") Account account);
	
	public void delete(Integer id);
	
	public String existsByUsername(@Param("account") Account account);
	
	public String existsByEmailAddress(@Param("account") Account account);
	
	public Integer existsByAccountId(Integer id);
}