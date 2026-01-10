package portfolio.PhotoSharingApp.service.member;

import java.util.List;

import portfolio.PhotoSharingApp.entity.Group;
import portfolio.PhotoSharingApp.entity.Member;

public interface MemberService {
	
	public void insertMember(Member member);
	
	public void deleteMember(int id);
	
	public int selectAccountId(String emailAddress);
	
	public List<Member> getMembersList(int groupId);
	
	public String getAdminName(int id);
	
	public Member getMemberName(int id);
	
	public boolean isFindByAccountId(String email);
	
	public boolean isFindByMembersId(String email,Group group);
	
	public boolean isFind(int adminId, String formEmail);
	
	public boolean isFindGroupAdmin(int memberId,int loginId);
}