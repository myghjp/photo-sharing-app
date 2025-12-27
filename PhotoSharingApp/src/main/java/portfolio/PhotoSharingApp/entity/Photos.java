package portfolio.PhotoSharingApp.entity;

import java.util.Date;

import lombok.Data;

@Data
public class Photos {
	
	private Integer id;
	private Integer albumId;/*アルバムテーブルのid*/
	private String photo; /*写真のパス情報？*/
	private Integer accountId;/*アカウントテーブルのid*/
	private Date createDateTime;
	
	private Accounts accounts;/*※*/
}