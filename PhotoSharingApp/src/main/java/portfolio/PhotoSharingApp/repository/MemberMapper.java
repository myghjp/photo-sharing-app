package portfolio.PhotoSharingApp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import portfolio.PhotoSharingApp.entity.Group;
import portfolio.PhotoSharingApp.entity.Member;

@Mapper
public interface MemberMapper {
	
	public void insert(@Param("member")Member member);
	
	public void delete(Integer memberId);
	
	/*ーーーーーーーーーーーーーーーーーーーー*/
	
	public List<Member> selectMembers(Integer groupId);
	
	public Integer selectCountMembers(Integer groupId);
	
	public Member selectMember(Integer memberId);
	
	public Integer selectById(String email,@Param("group")Group group);
	
	public Integer selectByGroupsAccountId(Integer memberId);
}