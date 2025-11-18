package portfolio.PhotoSharingApp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import portfolio.PhotoSharingApp.entity.Accounts;
import portfolio.PhotoSharingApp.entity.Members;

@Mapper
public interface MembersMapper {
	
	/*利用者を追加*/
	public void insertMembers(@Param("members")Members members);
	
	/*利用者一覧を表示*/
	public List<Members> selectMembersList(int groupId);
	
	/*利用者を削除*/
	public void deleteMembers(int id);
	
	/*重複確認*/
	public String selectMembersId(@Param("accounts")Accounts accounts);
	
}