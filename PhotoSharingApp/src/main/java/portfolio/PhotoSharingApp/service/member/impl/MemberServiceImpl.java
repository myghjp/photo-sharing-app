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
	public List<Member> findAllById(int groupId){
		return memberMapper.selectMembers(groupId);
	}
	
	@Override
	public void delete(int memberId) {
		memberMapper.delete(memberId);
	}
	
	/*ーーーーーーーーーーーーーーーーーーーー*/

	@Override
	public Member findById(int memberId) {
		return memberMapper.selectMember(memberId);
	}
	
	@Override
	public boolean isMember(String email,Group group) {
		if (memberMapper.selectById(email,group) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public boolean isAdmin(int memberId,int userId) {
		if (memberMapper.selectByGroupsAccountId(memberId) == userId) {
			return false;
		} else {
			return true;
		}
	}
}