package portfolio.PhotoSharingApp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import portfolio.PhotoSharingApp.entity.Members;

@Mapper
public interface MembersMapper {
	
	/*重複確認*/
	/*public String selectMembersData(String emailAddress);*/
	
	
	/*利用者を追加*/
	public void insertMembers(@Param("members")Members members);
	
	/*利用者一覧を表示*/
	public List<Members> selectMembersList(int groupId);
	
	/*利用者を削除*/
	public void deleteMembers(int user);
	
}