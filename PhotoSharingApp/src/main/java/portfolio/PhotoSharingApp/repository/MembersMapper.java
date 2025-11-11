package portfolio.PhotoSharingApp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import portfolio.PhotoSharingApp.entity.Members;

@Mapper
public interface MembersMapper {
	
	/*重複確認*/
	public String selectMembersData(String emailAddress);
	
	
	/*利用者を追加*/
	public void insertMembers(Members members);
	
	
	

	public List<Members> selectMembersList(int id);
	
	
	
}