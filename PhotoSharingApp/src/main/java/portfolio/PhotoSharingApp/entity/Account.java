package portfolio.PhotoSharingApp.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class Account implements Serializable{

	private Integer id;
	private String username;
	private String password;
	private String emailAddress;
}