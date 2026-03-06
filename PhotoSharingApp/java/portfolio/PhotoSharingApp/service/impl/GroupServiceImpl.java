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
	public List<Group> getGroupListByUserId(int id) {
		return groupMapper.selectGroups(id);
	}
	
	@Override
	public void delete(int groupId) {
		groupMapper.delete(groupId);
	}
	
	/*ーーーーーーーーーーーーーーーーーーーー*/
	
	
	@Override
	public Group findGroupById(int groupId){
		return groupMapper.selectGroup(groupId);
	}
	
	@Override
	public Group getAdminInfoById(int groupId) {
		return groupMapper.selectByAccounts(groupId);
	}
	
	@Override
	public boolean groupNameExists(String groupName) {
		if (groupMapper.selectByGroupName(groupName) == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean isOwner(int groupId,int userId) {
		if (groupMapper.selectByGroupsAccountId(groupId) == userId) {
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public boolean groupExists(int id) {
		if (groupMapper.selectByAccountId(id) == null) {
			return false;
		} else {
			return true;
		}
	}
}