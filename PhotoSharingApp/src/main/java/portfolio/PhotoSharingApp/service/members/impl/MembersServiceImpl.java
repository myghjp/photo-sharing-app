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
	
	/*ーーーAddMembersーーー*/
	
	@Override
	public boolean isExistingAccountId(Accounts accounts) {
		if (membersMapper.selectEmailAddress(accounts) != null) {
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public boolean isExistingMembersId(Accounts accounts,Groups groups) {
		if (membersMapper.selectMembersId(accounts,groups) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public int selectAccountId(String emailAddress) {
		return membersMapper.selectAccountId(emailAddress);
	}
	
	@Override
	public void insertMembers(Members members) {
		membersMapper.insert(members);
	}
	
	/*ーーーListMembersーーー*/
	
	@Override
	public List<Members> getMembersList(int groupId){
		return membersMapper.selectMembersItems(groupId);
	}
	
	@Override
	public String getAdminName(int id) {
		return membersMapper.selectUserName(id);
	}
	
	@Override
	public Members getMemberName(int id) {
		return membersMapper.selectMembersItems2(id);
	}
	
	/*ーーーDeleteMembersーーー*/
	
	@Override
	public void deleteMember(int id) {
		membersMapper.delete(id);
	}
	
	/*比較を作成*/
	@Override
	public int isCurrentUser(int id) {
		return membersMapper.selectUserId(id);
	}
}