package portfolio.PhotoSharingApp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import portfolio.PhotoSharingApp.entity.Group;

@Mapper
public interface GroupMapper {
	
	/*ーーーEntryGroupーーー*/
	
	public void insert(@Param("group")Group group);
	
	public String selectGroupName(@Param("group")Group group);
	
	/*ーーーSelectGroupーーー*/
	
	public List<Group> selectMyGroups(@Param("id")Integer id);
	
	public Group select(@Param("id")Integer id);
	
	/*ーーーDeleteGroupーーー*/
	
	public void delete(@Param("id")Integer id);
	
	/*比較を作成*/
	public Integer selectUserId(@Param("id") Integer id);
	
	/*比較を作成2*/
	public Integer selectGroupId(@Param("id") Integer id);
	
}