package portfolio.PhotoSharingApp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import portfolio.PhotoSharingApp.entity.Group;
import portfolio.PhotoSharingApp.repository.GroupMapper;
import portfolio.PhotoSharingApp.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService{
	
	@Autowired
	private GroupMapper groupMapper;
	
	@Override
	public void create(Group group) {
		groupMapper.insert(group);
	}
	
	@Override
	public List<Group> getGroupList(int id) {
		return groupMapper.getSelectGroups(id);
	}
	
	@Override
	public void delete(int groupId) {
		groupMapper.delete(groupId);
	}
	
	/*ーーーーーーーーーーーーーーーーーーーー*/
	
	
	@Override
	public Group findById(int groupId){
		return groupMapper.getSelectGroup(groupId);
	}
	
	@Override
	public Group getGroupAdminInfo(int groupId) {
		return groupMapper.getSelectAccount(groupId);
	}
	
	@Override
	public boolean existsGroupName(String groupName) {
		if (groupMapper.getSelectGroupName(groupName) == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean hasGroupOwner(int groupId,int userId) {
		if (groupMapper.getSelectGroupsAccountId(groupId) == userId) {
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public boolean hasCreateGroup(int id) {
		if (groupMapper.getSelectAccountId(id) == null) {
			return false;
		} else {
			return true;
		}
	}
}