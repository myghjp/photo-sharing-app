package portfolio.PhotoSharingApp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import portfolio.PhotoSharingApp.entity.Accounts;
import portfolio.PhotoSharingApp.entity.Groups;
import portfolio.PhotoSharingApp.entity.Members;

@Mapper
public interface MembersMapper {
	
	/*ーーーAddMembersーーー*/
	
	public String selectEmailAddress(@Param("accounts") Accounts accounts);
	
	public Integer selectMembersId(@Param("accounts")Accounts accounts,@Param("groups")Groups groups);
	
	public int selectAccountId(@Param("emailAddress") String emailAddress);
	
	public void insert(@Param("members")Members members);
	
	/*ーーーListMembersーーー*/
	
	public List<Members> selectMembersItems(int groupId);
	
	public String selectUserName(@Param("id") Integer id);
	
	public Members selectMembersItems2(@Param("id")int id);
	
	/*ーーーDeleteMembersーーー*/
	
	public void delete(int id);
	
	/*比較を作成*/
	public Integer selectUserId(@Param("id") Integer id);
	
}