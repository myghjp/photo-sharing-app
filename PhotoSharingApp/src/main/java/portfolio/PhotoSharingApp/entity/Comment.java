package portfolio.PhotoSharingApp.entity;

import java.util.Date;

import lombok.Data;

@Data
public class Comment {
	
	private Integer id;
	private Integer groupId;/*グループテーブルのID*/
	private String comment;
	private Integer accountId;/*アカウントテーブルのID*/
	private Date createDateTime;
	
	private Account account;
}