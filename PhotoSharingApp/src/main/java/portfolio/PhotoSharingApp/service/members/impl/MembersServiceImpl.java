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
	
	/*メールアドレスを使用してアカウントIdが存在するかを確認*/
	@Override
	public boolean isExistingAccountId(Accounts accounts) {
		if (membersMapper.selectByAccountsId(accounts) != null) {
			return false;
		} else {
			return true;
		}
	}
	
	/*このメールアドレスは、このグループ内にいるメンバや管理者のアドレスが
	 * データベースで重複していないかを確認*/
	@Override
	public boolean isExistingMembersId(Accounts accounts,Groups groups) {
		if (membersMapper.selectMembersId(accounts,groups) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	/*メールアドレスを使用してアカウントIDを取得する*/
	@Override
	public int selectAccountId(String emailAddress) {
		return membersMapper.selectAccountData3(emailAddress);
	}
	
	/*グループのIDとアカウントのIDを追加する*/
	@Override
	public void insertMembers(Members members) {
		membersMapper.insertMembers(members);
	}
	
	/*ーーーListMembersーーー*/
	
	/*このグループのメンバリストを取得する*/
	@Override
	public List<Members> getMembersList(int groupId){
		return membersMapper.selectMembersList(groupId);
	}
	
	@Override
	public String getAdminName(int id) {
		return membersMapper.selectByUserName2(id);
	}
	
	/*グループ利用者IDとその名前を取得*/
	@Override
	public Members getMemberName(int id) {
		return membersMapper.selectMembersName(id);
	}
	
	/*ーーーDeleteMembersーーー*/
	
	@Override
	public void deleteMember(int id) {
		membersMapper.deleteMembersId(id);
	}
}
