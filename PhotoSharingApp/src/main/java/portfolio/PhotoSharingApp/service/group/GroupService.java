package portfolio.PhotoSharingApp.service.group;

import java.util.List;

import portfolio.PhotoSharingApp.entity.Groups;

public interface GroupService {
	
	/*重複確認*/
	public boolean isExistingGroupsData(Groups groups);
	
	/*[グループ作成]グループ名とログイン中のアカウントIDを追加*/
	public void insertEntryGroup(Groups groups);
	
	/*ユーザIDからグループIDを取得*/
	public int selectByGroupId(int id);
	
	/*[グループ一覧表示]グループ名とそのグループIDの一覧を取得*/
	public List<Groups> getGroupList(int id);

	/*[groupsテーブルの情報を取得]*/
	public Groups getGroupsData(int id);
	
	/*グループの削除*/
	public void deleteGroup(int id);
	
	public int groupAdmin(int id);
}
