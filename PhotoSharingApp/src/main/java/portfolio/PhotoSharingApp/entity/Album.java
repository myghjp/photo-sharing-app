package portfolio.PhotoSharingApp.entity;

import java.util.Date;

import lombok.Data;

@Data
public class Album {
	
	private Integer id;
	private Integer groupId;
	private String albumName;
	private Date createDateTime;
}