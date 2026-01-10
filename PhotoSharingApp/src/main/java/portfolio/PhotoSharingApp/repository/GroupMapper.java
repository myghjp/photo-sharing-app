package portfolio.PhotoSharingApp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import portfolio.PhotoSharingApp.entity.Group;

@Mapper
public interface GroupMapper {
	
	public void insert(@Param("group")Group group);
	
	public void delete(Integer id);
	
	public String existsByGroupName(@Param("group")Group group);
	
	public List<Group> selectMyGroups(Integer id);
	
	public Group selectById(Integer id);
	
	public Integer existsGroupByAccountId(Integer id);
}