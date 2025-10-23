package portfolio.PhotoSharingApp.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import portfolio.PhotoSharingApp.entity.Accounts;

@Mapper
public interface UserMapper {
	
	/*アカウント情報を登録*/
	public void insertAccount(@Param("accounts") Accounts accounts);
}
