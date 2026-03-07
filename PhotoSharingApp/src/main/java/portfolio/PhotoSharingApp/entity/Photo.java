package portfolio.PhotoSharingApp.entity;

import java.util.Date;

import lombok.Data;

@Data
public class Photo {
	
	private Integer id;
	private Integer albumId;
	private String photo;
	private Integer accountId;
	private Date createDateTime;
	
	private Account account;
}