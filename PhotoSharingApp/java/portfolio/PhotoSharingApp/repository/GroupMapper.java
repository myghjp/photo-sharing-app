package portfolio.PhotoSharingApp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import portfolio.PhotoSharingApp.entity.Group;

@Mapper
public interface GroupMapper {
	
	public void insert(@Param("group")Group group);
	
	public void delete(Integer groupId);
	
	/*ーーーーーーーーーーーーーーーーーーーー*/
	
	public List<Group> getSelectGroups(Integer id);
	
	public Group getSelectGroup(Integer groupId);
	
	public Group getSelectAccount(Integer groupId);
	
	public String getSelectGroupName(String groupName);
	
	public Integer getSelectGroupsAccountId(Integer groupId);
	
	public Integer getSelectAccountId(Integer id);
	
}