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
	
	public List<Group> selectById(Integer id);
	
	public Group selectByGroupId(Integer groupId);
	
	public Group selectAccountByGroupId(Integer groupId);
	
	public String selectGroupNameByGroupName(String groupName);
	
	public Integer selectAccountIdByGroupId(Integer groupId);
	
	public Integer selectAccountIdById(Integer id);
	
}