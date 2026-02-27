package portfolio.PhotoSharingApp.entity;

import lombok.Data;

@Data
public class Member {
	
	private Integer id;
	private Integer groupId;
	private Integer accountId;
	
	private Group group;
	private Account account;
}