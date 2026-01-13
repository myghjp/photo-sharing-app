package portfolio.PhotoSharingApp.service.group.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import portfolio.PhotoSharingApp.entity.Group;
import portfolio.PhotoSharingApp.repository.GroupMapper;
import portfolio.PhotoSharingApp.service.group.GroupService;

@Service
public class GroupServiceImpl implements GroupService{
	
	@Autowired
	private GroupMapper groupMapper;
	
	@Override
	public void create(Group group) {
		groupMapper.insert(group);
	}
	
	@Override
	public void delete(int groupId) {
		groupMapper.delete(groupId);
	}
	
	/*自身が所属しているグループIDとグループ名を取得*/
	@Override
	public List<Group> findAllByUserId(int id) {
		return groupMapper.selectMyGroups(id);
	}
	
	/*グループ情報を取得*/
	@Override
	public Group findById(int groupId){
		return groupMapper.selectById(groupId);
	}
	
	/*登録済のグループ名と重複していないかを確認*/
	@Override
	public boolean existsByGroupName(String groupName) {
		if (groupMapper.existsByGroupName(groupName) == null) {
			return false;
		} else {
			return true;
		}
	}

	/*このグループは自身が作成したグループなのかを確認*/
	@Override
	public boolean isOwner(int groupId,int userId) {
		if (groupMapper.existsGroupByAccountId(groupId) == userId) {
			return false;
		} else {
			return true;
		}
	}
	
	/*済*/
	/*自身が作成したグループが存在するかを確認*/
	@Override
	public boolean existsByUserId(int id) {
		if (groupMapper.existsByAccountId(id) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public String findByUsername(int id) {
		return groupMapper.selectAccountByUsername(id);
	}

}