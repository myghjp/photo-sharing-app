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
	public void create(Group group) {
		groupMapper.insert(group);
	}
	
	@Override
	public List<Group> findAllByUserId(int id) {
		return groupMapper.selectGroups(id);
	}
	
	@Override
	public void delete(int groupId) {
		groupMapper.delete(groupId);
	}
	
	/*ーーーーーーーーーーーーーーーーーーーー*/
	
	@Override
	public Group findById(int groupId){
		return groupMapper.selectById(groupId);
	}
	
	@Override
	public String findByUsername(int id) {
		return groupMapper.selectAccountByUsername(id);
	}
	
	@Override
	public boolean existsByGroupName(String groupName) {
		if (groupMapper.selectByGroupName(groupName) == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean isOwner(int groupId,int userId) {
		if (groupMapper.selectGroupByAccountId(groupId) == userId) {
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public boolean existsByUserId(int id) {
		if (groupMapper.selectByAccountId(id) == null) {
			return false;
		} else {
			return true;
		}
	}
}