package portfolio.PhotoSharingApp.service.member;

import java.util.List;

import portfolio.PhotoSharingApp.entity.Group;
import portfolio.PhotoSharingApp.entity.Member;

public interface MemberService {
	
	public void insert(Member member);
	
	public void delete(int id);
	
	
	public List<Member> getMembersList(int groupId);
	
	public String getAdminName(int id);
	
	public Member getMemberName(int id);
	
	public boolean isMember(String email,Group group);
	
	public boolean is(int adminId, String formEmail);
	
	public boolean isFindGroupAdmin(int memberId,int loginId);
}