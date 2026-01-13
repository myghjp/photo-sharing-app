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
	
	public String selectByGroupName(String groupName);
	
	public Group selectById(Integer groupId);
	
	public Integer selectGroupByAccountId(Integer groupId);
	
	public Integer selectByAccountId(Integer id);
	
	public String selectAccountByUsername(Integer id);
}