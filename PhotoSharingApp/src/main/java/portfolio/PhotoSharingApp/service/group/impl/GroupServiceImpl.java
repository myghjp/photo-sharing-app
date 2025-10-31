package portfolio.PhotoSharingApp.service.group.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import portfolio.PhotoSharingApp.entity.Groups;
import portfolio.PhotoSharingApp.repository.GroupMapper;
import portfolio.PhotoSharingApp.service.group.GroupService;

@Service
public class GroupServiceImpl implements GroupService{
	
	@Autowired
	private GroupMapper groupMapper;
	
	public void insertEntryGroup(Groups groups) {
		groupMapper.insertGroupName(groups);
	}

}
