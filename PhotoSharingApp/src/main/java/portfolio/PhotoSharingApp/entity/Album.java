package portfolio.PhotoSharingApp.entity;

import lombok.Data;

@Data
public class Album {
	
	private Integer id;
	private Integer groupId;/*グループテーブルのID*/
	private String albumName;
}