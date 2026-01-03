package portfolio.PhotoSharingApp.entity;

import lombok.Data;

@Data
public class Member {
	
	private int id;
	private int groupId;/*グループテーブルのID*/
	private int accountId;/*アカウントテーブルのID*/
	
	private Group group;
	private Account account;
}