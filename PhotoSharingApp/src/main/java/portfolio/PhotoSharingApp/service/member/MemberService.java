package portfolio.PhotoSharingApp.service.member;

import java.util.List;

import portfolio.PhotoSharingApp.entity.Group;
import portfolio.PhotoSharingApp.entity.Member;

public interface MemberService {
	
	public void insert(Member member);
	
	public List<Member> findAllById(int groupId);
	
	public int countMembersById(int groupId);
	
	public void delete(int memberId);
	
	/*ーーーーーーーーーーーーーーーーーーーー*/
	
	public Member findById(int memberId);
	
	public boolean isMember(String email,Group group);
	
	public boolean isAdmin(int memberId,int userId);
}