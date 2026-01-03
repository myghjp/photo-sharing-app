package portfolio.PhotoSharingApp.service.member.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import portfolio.PhotoSharingApp.entity.Account;
import portfolio.PhotoSharingApp.entity.Group;
import portfolio.PhotoSharingApp.entity.Member;
import portfolio.PhotoSharingApp.repository.MemberMapper;
import portfolio.PhotoSharingApp.service.member.MemberService;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberMapper memberMapper;
	
	/*ーーーAddMembersーーー*/
	
	@Override
	public boolean isExistingAccountId(Account account) {
		if (memberMapper.selectEmailAddress(account) != null) {
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public boolean isExistingMembersId(Account account,Group group) {
		if (memberMapper.selectMembersId(account,group) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public int selectAccountId(String emailAddress) {
		return memberMapper.selectAccountId(emailAddress);
	}
	
	@Override
	public void insertMembers(Member member) {
		memberMapper.insert(member);
	}
	
	/*ーーーListMembersーーー*/
	
	@Override
	public List<Member> getMembersList(int groupId){
		return memberMapper.selectMembersItems(groupId);
	}
	
	@Override
	public String getAdminName(int id) {
		return memberMapper.selectUserName(id);
	}
	
	@Override
	public Member getMemberName(int id) {
		return memberMapper.selectMembersItems2(id);
	}
	
	/*ーーーDeleteMembersーーー*/
	
	@Override
	public void deleteMember(int id) {
		memberMapper.delete(id);
	}
	
	/*比較を作成*/
	@Override
	public int isCurrentUser(int id) {
		return memberMapper.selectUserId(id);
	}
}