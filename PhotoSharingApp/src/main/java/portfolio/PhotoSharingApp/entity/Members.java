package portfolio.PhotoSharingApp.entity;

import lombok.Data;

@Data
public class Members {
	
	private int id;
	private int groupId;/*グループテーブルのID*/
	private int accountId;/*アカウントテーブルのID*/

}
