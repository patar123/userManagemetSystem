package com.ashokit.model;

import java.util.Date;

import lombok.Data;

@Data
public class User {
	private Integer userId;
	private Integer cityId;
	private Integer countryId;
	private Integer stateId;
	private String firstName;
	private String lastName;
	private String userEmail;
	private String userPhonenumber;
	private String gender;
	private String dateOfBirth;	
	private String accstatus;
	private Date createdDate;
	private Date updatedDate;
	private String userpwd;
}
