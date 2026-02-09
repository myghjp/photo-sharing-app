package portfolio.PhotoSharingApp.entity;

import java.util.Date;

import lombok.Data;

@Data
public class Group{
	
	private Integer id;
	private String groupName;
	private Integer accountId;
	private Date createDateTime;
	
	private Account account;
}