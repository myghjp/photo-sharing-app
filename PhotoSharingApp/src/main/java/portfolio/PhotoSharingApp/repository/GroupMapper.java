package portfolio.PhotoSharingApp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import portfolio.PhotoSharingApp.entity.Groups;

@Mapper
public interface GroupMapper {
	
	/*ーーーEntryGroupーーー*/
	
	public void insert(@Param("groups")Groups groups);
	
	public String selectGroupName(@Param("groups")Groups groups);
	
	/*ーーーSelectGroupーーー*/
	
	public List<Groups> selectMyGroups(@Param("id")Integer id);
	
	public Groups select(@Param("id")Integer id);
	
	/*ーーーDeleteGroupーーー*/
	
	public void delete(@Param("id")Integer id);
	
	/*比較を作成*/
	public Integer selectUserId(@Param("id") Integer id);
	
	/*比較を作成2*/
	public Integer selectGroupId(@Param("id") Integer id);
	
}