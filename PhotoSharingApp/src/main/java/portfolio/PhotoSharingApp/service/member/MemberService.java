package portfolio.PhotoSharingApp.service.member;

import java.util.List;

import portfolio.PhotoSharingApp.entity.Group;
import portfolio.PhotoSharingApp.entity.Member;

public interface MemberService {
	
	/*ーーーAddMembersーーー*/
	
	public boolean isExistingAccountId(String email);
	
	public boolean isExistingMembersId(String email,Group group);
	
	public boolean is(int adminId, String formEmail);
	
	public int selectAccountId(String emailAddress);
	
	public void insertMember(Member member);
	
	/*ーーーListMembersーーー*/
	
	public List<Member> getMembersList(int groupId);
	
	public String getAdminName(int id);
	
	/*ーーーDeleteMembersーーー*/
	
	/*グループ利用者IDとその名前を取得*/
	public Member getMemberName(int id);
	
	public void deleteMember(int id);
	
	/*比較を作成*/
	public boolean isCurrentAccount(int memberId,int loginId);
	
}