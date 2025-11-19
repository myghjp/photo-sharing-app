package portfolio.PhotoSharingApp.entity;

import lombok.Data;

@Data
public class Groups{
	
	private Integer id;
	private String groupName;
	private Integer accountId;/*アカウントテーブルのID*/
	
	private Accounts accounts;

}