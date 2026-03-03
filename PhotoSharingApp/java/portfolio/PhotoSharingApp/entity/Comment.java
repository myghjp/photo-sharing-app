package portfolio.PhotoSharingApp.entity;

import java.util.Date;

import lombok.Data;

@Data
public class Comment {
	
	private Integer id;
	private Integer groupId;
	private String comment;
	private Integer accountId;
	private Date createDateTime;
	
	private Account account;
}