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
	public List<Group> getAffiliationGroupInfo(int id) {
		return groupMapper.selectById(id);
	}
	
	@Override
	public void delete(int groupId) {
		groupMapper.delete(groupId);
	}
	
	/*ーーーーーーーーーーーーーーーーーーーー*/
	
	
	@Override
	public Group findById(int groupId){
		return groupMapper.selectByGroupId(groupId);
	}
	
	@Override
	public Group getAdminInfo(int groupId) {
		return groupMapper.selectAccountByGroupId(groupId);
	}
	
	@Override
	public boolean isGroupNameDuplicate(String groupName) {
		if (groupMapper.selectByGroupName(groupName) == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean isGroupCreator(int groupId,int userId) {
		if (groupMapper.selectAccountIdByGroupId(groupId) == userId) {
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public boolean createdGroupExists(int id) {
		if (groupMapper.selectAccountIdById(id) == null) {
			return false;
		} else {
			return true;
		}
	}
}