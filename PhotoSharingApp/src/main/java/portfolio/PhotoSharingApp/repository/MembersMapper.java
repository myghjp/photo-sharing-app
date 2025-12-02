package portfolio.PhotoSharingApp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import portfolio.PhotoSharingApp.entity.Accounts;
import portfolio.PhotoSharingApp.entity.Groups;
import portfolio.PhotoSharingApp.entity.Members;

@Mapper
public interface MembersMapper {
	
	/*ーAddMembersーーーーーーーーーーーーーーーーー*/
	
	/*メールアドレスを使用してアカウントIdが存在するかを確認*/
	public String selectByAccountsId(@Param("accounts") Accounts accounts);
	
	/*このメールアドレスは、このグループ内にいるメンバや管理者のアドレスが
	 * データベースで重複していないかを確認*/
	public Integer selectMembersId(@Param("accounts")Accounts accounts,@Param("groups")Groups groups);
	
	/*メールアドレスを使用してアカウントIDを取得する*/
	public int selectAccountData3(@Param("emailAddress") String emailAddress);
	
	/*グループのIDとアカウントのIDを追加する*/
	public void insertMembers(@Param("members")Members members);
	
	/*ーListMembersーーーーーーーーーーーーーーーーー*/
	
	/*このグループのメンバリストを取得する*/
	public List<Members> selectMembersList(int groupId);
	
	/*管理者名を取得*/
	public String selectByUserName2(@Param("id") Integer id);
	
	/*グループ利用者IDとその名前を取得*/
	public Members selectMembersName(@Param("id")int id);
	
	/*ーDeleteMembersーーーーーーーーーーーーーーーーー*/
	
	/*グループから利用者を削除*/
	public void deleteMembersId(int id);
	
}