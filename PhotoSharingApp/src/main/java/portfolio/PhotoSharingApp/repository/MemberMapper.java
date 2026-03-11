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
	
	public List<Member> selectByGroupId(Integer groupId);
	
	public Member selectByMemberId(Integer memberId);
	
	public Integer selectIdByEmailAndGroup(String email,@Param("group")Group group);
	
	public Integer selectAccountIdByMemberId(Integer memberId);
}