package portfolio.PhotoSharingApp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import portfolio.PhotoSharingApp.entity.Group;

@Mapper
public interface GroupMapper {
	
	/*ーーーEntryGroupーーー*/
	
	public void insert(@Param("group")Group group);
	
	public String existsByGroupName(@Param("group")Group group);
	
	/*ーーーSelectGroupーーー*/
	
	public List<Group> selectMyGroups(@Param("id")Integer id);
	
	public Group selectById(@Param("id")Integer id);
	
	/*ーーーDeleteGroupーーー*/
	
	public void delete(@Param("id")Integer id);
	
	/*比較を作成*/
	public Integer existsGroupByAccountId(@Param("id") Integer id);
	
}