package portfolio.PhotoSharingApp.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import portfolio.PhotoSharingApp.entity.Groups;

@Mapper
public interface GroupMapper {
	
	public void insertGroupName(@Param("groups") Groups groups);

}
