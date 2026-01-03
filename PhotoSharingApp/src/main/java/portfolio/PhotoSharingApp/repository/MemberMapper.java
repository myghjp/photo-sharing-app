package portfolio.PhotoSharingApp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import portfolio.PhotoSharingApp.entity.Account;
import portfolio.PhotoSharingApp.entity.Group;
import portfolio.PhotoSharingApp.entity.Member;

@Mapper
public interface MemberMapper {
	
	/*ーーーAddMembersーーー*/
	
	public String selectEmailAddress(@Param("account") Account account);
	
	public Integer selectMembersId(@Param("account")Account account,@Param("group")Group group);
	
	public int selectAccountId(@Param("emailAddress") String emailAddress);
	
	public void insert(@Param("member")Member member);
	
	/*ーーーListMembersーーー*/
	
	public List<Member> selectMembersItems(int groupId);
	
	public String selectUserName(@Param("id") Integer id);
	
	public Member selectMembersItems2(@Param("id")int id);
	
	/*ーーーDeleteMembersーーー*/
	
	public void delete(int id);
	
	/*比較を作成*/
	public Integer selectUserId(@Param("id") Integer id);
	
}