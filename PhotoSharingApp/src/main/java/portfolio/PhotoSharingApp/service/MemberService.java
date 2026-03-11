package portfolio.PhotoSharingApp.service;

import java.util.List;

import portfolio.PhotoSharingApp.entity.Group;
import portfolio.PhotoSharingApp.entity.Member;

public interface MemberService {
	
	public void insert(Member member);
	
	public List<Member> getGroupMemberInfo(int groupId);
	
	public void delete(int memberId);
	
	/*ーーーーーーーーーーーーーーーーーーーー*/
	
	public Member findById(int memberId);
	
	public boolean isEmailAddressAdded(String email,Group group);
	
	public boolean isGroupAdmin(int memberId,int userId);
}