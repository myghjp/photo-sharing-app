package portfolio.PhotoSharingApp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import portfolio.PhotoSharingApp.entity.Groups;

@Mapper
public interface GroupMapper {
	
	/*重複確認*/
	public String selectGroupData(@Param("groups") Groups groups);
	
	public void insertGroupName(@Param("groups") Groups groups);
	
	/*ユーザ自身が所属しているグループリストを取得*/
	public List<Groups> selectGroupName(@Param("id") int id);
	
	
	public int selectByGroupId(@Param("id") int id);
	
	public Groups selectGroups(@Param("id") int id);
	
	public void deleteGroup(@Param("id")int id);

}
