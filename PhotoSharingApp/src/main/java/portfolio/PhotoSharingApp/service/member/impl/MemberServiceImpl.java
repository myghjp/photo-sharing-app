package portfolio.PhotoSharingApp.service.member.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import portfolio.PhotoSharingApp.entity.Group;
import portfolio.PhotoSharingApp.entity.Member;
import portfolio.PhotoSharingApp.repository.MemberMapper;
import portfolio.PhotoSharingApp.service.member.MemberService;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public void insert(Member member) {
		memberMapper.insert(member);
	}
	
	@Override
	public void delete(int id) {
		memberMapper.delete(id);
	}
	
	
	
	@Override
	public List<Member> getMembersList(int groupId){
		return memberMapper.selectMyGroupsMembers(groupId);
	}
	
	@Override
	public String getAdminName(int id) {
		return memberMapper.selectAccountByUsername(id);
	}
	
	/*グループ利用者IDとその名前を取得*/
	@Override
	public Member getMemberName(int id) {
		return memberMapper.selectByMembersId(id);
	}
	
	/*このグループに追加済のメールアドレスではないかを確認*/
	@Override
	public boolean isMember(String email,Group group) {
		if (memberMapper.existsMembersByEmailAddress(email,group) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	/*このグループの管理者のメールアドレスではないかを確認*/
	@Override
	public boolean is(int adminId,String formEmail) {
		if (memberMapper.is(adminId).equals(formEmail) == formEmail.equals(formEmail)) {
			return true;
		} else {
			return false;
		}
	}
	
	/*このグループの管理者であるかを確認*/
	@Override
	public boolean isFindGroupAdmin(int memberId,int loginId) {
		if (memberMapper.existsGroupByAccountId(memberId) == loginId) {
			return false;
		} else {
			return true;
		}
	}
}