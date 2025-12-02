package portfolio.PhotoSharingApp.service.members.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import portfolio.PhotoSharingApp.entity.Accounts;
import portfolio.PhotoSharingApp.entity.Groups;
import portfolio.PhotoSharingApp.entity.Members;
import portfolio.PhotoSharingApp.repository.MembersMapper;
import portfolio.PhotoSharingApp.service.members.MembersService;

@Service
public class MembersServiceImpl implements MembersService{
	
	@Autowired
	private MembersMapper membersMapper;
	
	/*利用者を追加*/
	public void insertMembers(Members members) {
		membersMapper.insertMembers(members);
	}
	
	/*グループ内の利用者一覧を取得*/
	@Override
	public List<Members> getMembersList(int groupId){
		return membersMapper.selectMembersList(groupId);
	}
	
	/*重複確認*/
	@Override
	public boolean isExistingMembersId(Accounts accounts,Groups groups) {
		if (membersMapper.selectMembersId(accounts,groups) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	/*グループメンバの名前を取得*/
	@Override
	public Members getMemberName(int id) {
		return membersMapper.selectMembersName(id);
	}
	
	/*グループから利用者を削除*/
	public void deleteMember(int id) {
		membersMapper.deleteMembersId(id);
	}
}
