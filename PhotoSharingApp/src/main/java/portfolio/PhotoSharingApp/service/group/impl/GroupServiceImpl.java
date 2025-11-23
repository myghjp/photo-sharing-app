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
	
	/*重複確認*/
	@Override
	public boolean isExistingGroupsData(Groups groups) {
		if (groupMapper.selectGroupData(groups) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	/*[グループ作成]グループ名とログイン中のアカウントIDを追加*/
	@Override
	public void insertEntryGroup(Groups groups) {
		groupMapper.insertGroupName(groups);
	}
	
	/*ユーザIDからグループIDを取得*/
	@Override
	public int selectByGroupId(int id) {
		return groupMapper.selectByGroupId(id);
	}

	/*[グループ一覧表示]グループ名とそのグループIDの一覧を取得*/
	@Override
	public List<Groups> getGroupList(int id) {
		return groupMapper.selectGroupList(id);
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
	
	@Override
	public boolean groupAdmin(int id) {
		if (groupMapper.groupAdmin(id) == null) {
			return false;
		} else {
			return true;
		}
	}
	
}
