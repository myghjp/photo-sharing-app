package portfolio.PhotoSharingApp.entity;

import lombok.Data;

@Data
public class Albums {
	
	private Integer id;
	private Integer groupId;/*グループテーブルのID*/
	private String albumName;
}