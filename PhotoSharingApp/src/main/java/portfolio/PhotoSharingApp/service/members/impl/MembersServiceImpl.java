package portfolio.PhotoSharingApp.service.members.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	
	
	/*重複確認*/
	@Override
	public boolean isExistingMembersData(String emailAddress) {
		if (membersMapper.selectMembersData(emailAddress) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	
	/*グループ内の利用者一覧を取得*/
	@Override
	public List<Members> getMembersList(int id){
		return membersMapper.selectMembersList(id);
	}

}
