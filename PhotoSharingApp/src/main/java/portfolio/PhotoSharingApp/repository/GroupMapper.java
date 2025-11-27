package portfolio.PhotoSharingApp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import portfolio.PhotoSharingApp.entity.Groups;

@Mapper
public interface GroupMapper {
	
	public void insertGroup(@Param("groups")Groups groups);
	
	/*重複確認*/
	public String selectGroupData(@Param("groups")Groups groups);
	
	/*ユーザ自身が所属しているグループリストを取得*/
	public List<Groups> selectGroupList(@Param("id")Integer id);

	public Integer selectByGroupId(@Param("id")Integer id);
	
	public Groups selectGroups(@Param("id")Integer id);
	
	public void deleteGroup(@Param("id")Integer id);

	public Integer groupAdmin(@Param("id")Integer id);
}
