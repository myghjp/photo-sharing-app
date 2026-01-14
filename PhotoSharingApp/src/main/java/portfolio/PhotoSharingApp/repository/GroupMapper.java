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
	
	public List<Group> selectGroups(Integer id);
	
	public Group selectGroup(Integer groupId);
	
	public String selectByAccountsUsername(Integer id);
	
	public String selectByGroupName(String groupName);
	
	public Integer selectByGroupsAccountId(Integer groupId);
	
	public Integer selectByAccountId(Integer id);
	
}