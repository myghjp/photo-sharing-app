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
	
	/*グループ内の利用者一覧を取得*/
	@Override
	public List<Members> getMembersList(int id){
		return membersMapper.selectMembersList(id);
	}

}
