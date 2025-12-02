package portfolio.PhotoSharingApp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import portfolio.PhotoSharingApp.entity.Groups;

@Mapper
public interface GroupMapper {
	
	/*ーEntryGroupーーーーーーーーーーーーーーーーー*/
	
	/*グループ名とログイン中のアカウントIDを追加*/
	public void insertGroup(@Param("groups")Groups groups);
	
	/*データベースに登録済のグループ名と重複していないか確認*/
	public String selectGroupData(@Param("groups")Groups groups);
	
	/*ーSelectGroupーーーーーーーーーーーーーーーーー*/
	
	/*自身が所属しているグループのIDとグループ名の一覧を取得*/
	public List<Groups> selectGroupList(@Param("id")Integer id);
	
	/*groupsテーブルの情報を取得*/
	public Groups selectGroups(@Param("id")Integer id);
	
	/*ーDeleteGroupーーーーーーーーーーーーーーーーー*/
	
	/*グループの削除*/
	public void deleteGroup(@Param("id")Integer id);
	
	/*ーー？？？ーーーーーーーーーーーーーーーーーーーーー*/

	public Integer groupAdmin(@Param("id")Integer id);
}
