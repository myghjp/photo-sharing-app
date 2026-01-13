package portfolio.PhotoSharingApp.service.group;

import java.util.List;

import portfolio.PhotoSharingApp.entity.Group;

public interface GroupService {
	
	public void create(Group group);
	
	public List<Group> findAllByUserId(int id);
	
	public Group findById(int groupId);
	
	public void delete(int groupId);
	
	public boolean existsByGroupName(String groupName);
	
	public boolean isOwner(int groupId,int userId);
	
	/*※済*/
	public boolean existsByUserId(int userId);
	
	public String findByUsername(int id);
}
