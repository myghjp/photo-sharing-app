package portfolio.PhotoSharingApp.service.group;

import java.util.List;

import portfolio.PhotoSharingApp.entity.Groups;

public interface GroupService {
	
	/*ーーーEntryGroupーーー*/
	
	/*グループ名とログイン中のアカウントIDを追加*/
	public void entryGroup(Groups groups);
	
	/*データベースに登録済のグループ名と重複していないか確認*/
	public boolean isExistingGroupsData(Groups groups);
	
	/*ーーーSelectGroupーーー*/
	
	/*自身が所属しているグループのIDとグループ名の一覧を取得*/
	public List<Groups> getGroupList(int id);
	
	public Groups getGroupsData(int id);
	
	/*ーーーDeleteGroupーーー*/
	
	/*グループの削除*/
	public void deleteGroup(int id);
	
	/*ーーー※アカウント削除ーーーーーーーーーーーーーーーーー*/
	
	/*自身が作成したグループが残っていないかを確認*/
	public boolean isCreateGroupExisting(int id);
}
