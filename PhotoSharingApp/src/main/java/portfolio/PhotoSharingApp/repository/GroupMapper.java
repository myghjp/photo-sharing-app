package portfolio.PhotoSharingApp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import portfolio.PhotoSharingApp.entity.Group;

@Mapper
public interface GroupMapper {
	
	public void insert(@Param("group")Group group);
	
	public List<Group> selectMyGroups(Integer id);
	
	public String existsByGroupName(String groupName);
	
	public Group selectById(Integer groupId);
	
	public void delete(Integer groupId);
	
	public Integer existsGroupByAccountId(Integer groupId);
	
	public Integer existsByAccountId(Integer id);
	
	public String selectAccountByUsername(Integer id);
}