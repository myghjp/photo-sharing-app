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
	
	/*ーーーEntryGroupーーー*/
	
	/*グループ名とログイン中のアカウントIDを追加*/
	@Override
	public void entryGroup(Groups groups) {
		groupMapper.insertGroup(groups);
	}
	
	/*データベースに登録済のグループ名と重複していないか確認*/
	@Override
	public boolean isExistingGroupsData(Groups groups) {
		if (groupMapper.selectGroupData(groups) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	/*ーーーSelectGroupーーー*/

	/*自身が所属しているグループのIDとグループ名の一覧を取得*/
	@Override
	public List<Groups> getGroupList(int id) {
		return groupMapper.selectGroupList(id);
	}
	
	/*groupsテーブルの情報を取得*/
	@Override
	public Groups getGroupsData(int id){
		return groupMapper.selectGroups(id);
	}
	
	/*ーーーDeleteGroupーーー*/

	/*グループの削除*/
	@Override
	public void deleteGroup(int id) {
		groupMapper.deleteGroup(id);
	}
	
	/*ーーーアカウント削除ーーー*/
	
	/*自身が作成したグループが残っていないかを確認*/
	@Override
	public boolean isCreateGroupExisting(int id) {
		if (groupMapper.groupAdmin(id) == null) {
			return false;
		} else {
			return true;
		}
	}
}
