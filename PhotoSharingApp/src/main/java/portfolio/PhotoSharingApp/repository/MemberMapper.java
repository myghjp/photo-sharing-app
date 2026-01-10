package portfolio.PhotoSharingApp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import portfolio.PhotoSharingApp.entity.Group;
import portfolio.PhotoSharingApp.entity.Member;

@Mapper
public interface MemberMapper {
	
	public void insert(@Param("member")Member member);
	
	public void delete(Integer id);
	
	public Integer selectAccountById(String emailAddress);
	
	public List<Member> selectMyGroupsMembers(Integer groupId);
	
	public String selectAccountByUsername(Integer id);
	
	public Member selectByMembersId(Integer id);
	
	public String existsByEmailAddress(String email);
	
	public Integer existsMembersByEmailAddress(String email,@Param("group")Group group);
	
	public String is(Integer adminId);
	
	public Integer existsGroupByAccountId(Integer id);
}