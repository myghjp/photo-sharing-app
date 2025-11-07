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
	
	/*[グループ作成]グループ名とログイン中のアカウントIDを追加*/
	public void insertEntryGroup(Groups groups) {
		groupMapper.insertGroupName(groups);
	}

	/*[グループ一覧表示]グループ名とそのグループIDの一覧を取得*/
	@Override
	public List<Groups> getGroupList() {
		return groupMapper.selectGroupName();
	}
	
	/*[groupsテーブルの情報を取得]*/
	@Override
	public Groups getGroupsData(int id){
		return groupMapper.selectGroups(id);
	}
	
	/*グループの削除*/
	@Override
	public void deleteGroup(int id) {
		groupMapper.deleteGroup(id);
	}
	
	
}
