package portfolio.PhotoSharingApp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import portfolio.PhotoSharingApp.entity.Group;
import portfolio.PhotoSharingApp.entity.Member;
import portfolio.PhotoSharingApp.repository.MemberMapper;
import portfolio.PhotoSharingApp.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public void insert(Member member) {
		memberMapper.insert(member);
	}
	
	@Override
	public List<Member> getGroupMemberInfo(int groupId){
		return  memberMapper.selectByGroupId(groupId);
	}
	
	@Override
	public void delete(int memberId) {
		memberMapper.delete(memberId);
	}
	
	/*ーーーーーーーーーーーーーーーーーーーー*/

	@Override
	public Member findById(int memberId) {
		return memberMapper.selectByMemberId(memberId);
	}
	
	@Override
	public boolean isEmailAddressAdded(String email,Group group) {
		if (memberMapper.selectIdByEmailAndGroup(email,group) == null) {
			return false;
		} else {
			return true;
		}
	}
}