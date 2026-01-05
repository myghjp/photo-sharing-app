package portfolio.PhotoSharingApp.entity;

import lombok.Data;

@Data
public class Member {
	
	private int id;
	private int groupId;
	private int accountId;
	
	private Group group;
	private Account account;
}