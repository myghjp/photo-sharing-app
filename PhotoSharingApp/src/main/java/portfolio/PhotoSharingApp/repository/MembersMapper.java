package portfolio.PhotoSharingApp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import portfolio.PhotoSharingApp.entity.Members;

@Mapper
public interface MembersMapper {

	public List<Members> selectMembersList(int id);
	
	
	
}