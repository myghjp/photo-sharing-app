package portfolio.PhotoSharingApp.service.group;

import java.util.List;

import portfolio.PhotoSharingApp.entity.Groups;

public interface GroupService {
	
	/*グループ名とログイン中のアカウントIDを追加*/
	public void insertEntryGroup(Groups groups);
	
	/*グループ名一覧を取得*/
	public List<Groups> getGroupList();
	

}
