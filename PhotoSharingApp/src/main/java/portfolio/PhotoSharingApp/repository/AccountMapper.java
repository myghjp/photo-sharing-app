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
	
	public Account selectByEmail(String email);
	
	public Account selectByUsername(String username);
}