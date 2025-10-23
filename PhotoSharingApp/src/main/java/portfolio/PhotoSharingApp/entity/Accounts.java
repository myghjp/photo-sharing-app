package portfolio.PhotoSharingApp.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class Accounts implements Serializable{/*※直列*/

	private Integer id;
	private String user;
	private String pass;
	private String email_address;
}
