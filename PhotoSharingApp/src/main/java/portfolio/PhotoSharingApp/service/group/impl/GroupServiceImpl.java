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
	
	@Override
	public void entryGroup(Groups groups) {
		groupMapper.insert(groups);
	}
	
	@Override
	public boolean isExistingGroupsData(Groups groups) {
		if (groupMapper.selectGroupName(groups) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	/*ーーーSelectGroupーーー*/

	@Override
	public List<Groups> getGroupList(int id) {
		return groupMapper.selectMyGroups(id);
	}
	
	@Override
	public Groups getGroupsData(int id){
		return groupMapper.select(id);
	}
	
	/*ーーーDeleteGroupーーー*/

	@Override
	public void deleteGroup(int id) {
		groupMapper.delete(id);
	}
	
	/*ーーー※DeleteAccountーーー*/
	
	/*@Override
	public boolean isCreateGroupExisting(int id) {
		if (groupMapper.groupAdmin(id) == null) {
			return false;
		} else {
			return true;
		}
	}*/
}
