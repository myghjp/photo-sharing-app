package portfolio.PhotoSharingApp.service.group;

import java.util.List;

import portfolio.PhotoSharingApp.entity.Group;

public interface GroupService {
	
	public void createGroup(Group group);
	
	public boolean isExistsGroup(Group group);
	
	public List<Group> getGroupList(int id);
	
	public Group getGroup(int id);
	
	public void deleteGroup(int id);
	
	public boolean isFindCreateMyGroup(int GroupId,int loginId);
}
