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
	public void entryGroup(Group group) {
		groupMapper.insert(group);
	}
	
	@Override
	public boolean isExistingGroupsData(Group group) {
		if (groupMapper.selectGroupName(group) == null) {
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
	public Group getGroupsData(int id){
		return groupMapper.select(id);
	}
	
	/*ーーーDeleteGroupーーー*/

	@Override
	public void deleteGroup(int id) {
		groupMapper.delete(id);
	}
	
	/*比較を作成*/
	@Override
	public int isCurrentUser(int id) {
		return groupMapper.selectUserId(id);
	}
}
