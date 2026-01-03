package portfolio.PhotoSharingApp.entity;

import lombok.Data;

@Data
public class Group{
	
	private Integer id;
	private String groupName;
	private Integer accountId;/*アカウントテーブルのID*/
	
	private Account account;
}