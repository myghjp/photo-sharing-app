package portfolio.PhotoSharingApp.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class Accounts implements Serializable{

	private Integer id;
	private String username;
	private String password;
	private String emailAddress;
}