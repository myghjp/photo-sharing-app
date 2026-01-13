package portfolio.PhotoSharingApp.service.group;

import java.util.List;

import portfolio.PhotoSharingApp.entity.Group;

public interface GroupService {
	
	public void create(Group group);
	
	public List<Group> findAllByUserId(int id);
	
	public void delete(int groupId);
	
	/*ーーーーーーーーーーーーーーーーーーーー*/
	
	public Group findById(int groupId);
	
	public String findByUsername(int id);
	
	public boolean existsByGroupName(String groupName);
	
	public boolean isOwner(int groupId,int userId);
	
	public boolean existsByUserId(int userId);
	
}