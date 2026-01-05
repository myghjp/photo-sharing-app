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
	
	/*ーーーEntryGroupーーー*/
	
	@Override
	public void createGroup(Group group) {
		groupMapper.insert(group);
	}
	
	@Override
	public boolean isExistingGroup(Group group) {
		if (groupMapper.existsByGroupName(group) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	/*ーーーSelectGroupーーー*/

	@Override
	public List<Group> getGroupList(int id) {
		return groupMapper.selectMyGroups(id);
	}
	
	@Override
	public Group getGroup(int id){
		return groupMapper.selectById(id);
	}
	
	/*ーーーDeleteGroupーーー*/

	@Override
	public void deleteGroup(int id) {
		groupMapper.delete(id);
	}
	
	/*比較を作成*/
	@Override
	public boolean isCurrentAccount(int GroupId,int loginId) {
		if (groupMapper.existsGroupByAccountId(GroupId) == loginId) {
			return false;
		} else {
			return true;
		}
	}
}
