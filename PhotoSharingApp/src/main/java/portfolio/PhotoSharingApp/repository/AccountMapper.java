package portfolio.PhotoSharingApp.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import portfolio.PhotoSharingApp.entity.Account;

@Mapper
public interface AccountMapper {
	
	public void insert(@Param("account") Account account);
	
	public Account selectByUsername(String user);
	
	public void update(@Param("account") Account account);
	
	public void delete(Integer id);
	
	public String existsByUsername(String username);
	
	public String existsByEmailAddress(String emailAddress);
	
	public Integer selectAccountById(String emailAddress);
}