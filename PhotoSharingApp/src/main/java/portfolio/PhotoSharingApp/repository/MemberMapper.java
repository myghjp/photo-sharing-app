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
	
	public String existsByEmailAddress(@Param("account") Account account);
	
	/*※*/
	public Integer existsMembersByEmailAddress(@Param("account")Account account,@Param("group")Group group);
	
	
	public int selectAccountById(@Param("emailAddress") String emailAddress);
	
	public void insert(@Param("member")Member member);
	
	/*ーーーListMembersーーー*/
	
	public List<Member> selectMyGroupsMembers(int groupId);
	
	public String selectAccountByUsername(@Param("id") Integer id);
	
	/*ーーーDeleteMembersーーー*/
	
	public Member selectByMembersId(@Param("id")int id);
	
	public void delete(int id);
	
	/*比較を作成*/
	public Integer existsGroupByAccountId(@Param("id") Integer id);
	
}