package portfolio.PhotoSharingApp.service.group.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import portfolio.PhotoSharingApp.entity.Group;
import portfolio.PhotoSharingApp.repository.GroupMapper;
import portfolio.PhotoSharingApp.service.group.GroupService;

@Service
public class GroupServiceImpl implements GroupService{
	
	@Autowired
	private GroupMapper groupMapper;
	
	@Override
	public void createGroup(Group group) {
		groupMapper.insert(group);
	}
	
	@Override
	public void deleteGroup(int id) {
		groupMapper.delete(id);
	}
	
	/*登録済のグループ名と重複していないかを確認*/
	@Override
	public boolean isExistsGroup(Group group) {
		if (groupMapper.existsByGroupName(group) == null) {
			return false;
		} else {
			return true;
		}
	}

	/*自身が所属しているグループIDとグループ名を取得*/
	@Override
	public List<Group> getGroupList(int id) {
		return groupMapper.selectMyGroups(id);
	}
	
	/*グループ情報を取得*/
	@Override
	public Group getGroup(int id){
		return groupMapper.selectById(id);
	}
	
	/*このグループは自身が作成したグループなのかを確認*/
	@Override
	public boolean isFindCreateMyGroup(int GroupId,int loginId) {
		if (groupMapper.existsGroupByAccountId(GroupId) == loginId) {
			return false;
		} else {
			return true;
		}
	}
}