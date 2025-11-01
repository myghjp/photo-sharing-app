package portfolio.PhotoSharingApp.service.group.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import portfolio.PhotoSharingApp.entity.Groups;
import portfolio.PhotoSharingApp.repository.GroupMapper;
import portfolio.PhotoSharingApp.service.group.GroupService;

@Service
public class GroupServiceImpl implements GroupService{
	
	@Autowired
	private GroupMapper groupMapper;
	
	/*グループ名とログイン中のアカウントIDを追加*/
	public void insertEntryGroup(Groups groups) {
		groupMapper.insertGroupName(groups);
	}

	/*グループ名一覧を取得*/
	@Override
	public List<Groups> getGroupList() {
		return groupMapper.selectGroupName();
	}
	
	
}
