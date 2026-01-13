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
	public void delete(int memberId) {
		memberMapper.delete(memberId);
	}
	
	@Override
	public List<Member> findAllById(int groupId){
		return memberMapper.selectMyGroupsMembers(groupId);
	}
	
	/*グループ利用者IDとその名前を取得*/
	@Override
	public Member findById(int id) {
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
	public boolean isAdmin(int memberId,int userId) {
		if (memberMapper.existsGroupByAccountId(memberId) == userId) {
			return false;
		} else {
			return true;
		}
	}
}