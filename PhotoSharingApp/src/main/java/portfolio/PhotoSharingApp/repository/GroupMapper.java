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
	
	public List<Groups> selectGroupName();
	
	public Groups selectGroups(@Param("id") int id);
	
	public void deleteGroup(@Param("id")int id);

}
