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
	
	public Member selectById(Integer memberId);
	
	public Integer selectById2(String email,@Param("group")Group group);
	
	public String is(Integer adminId);
	
	public Integer selectGroupByAccountId(Integer memberId);
}