package com.ashokit.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
@Table(name = "USER_ACCOUNTS")
public class User_ManagementEntity {
	@Id
	@Column(name="USER_ID")
	@SequenceGenerator(
			name="uid_seq_gen",
			sequenceName="USER_ACCOUNT_ID_SEQ",
			allocationSize=1
	)
	@GeneratedValue(
			generator="uid_seq_gen",
			strategy=GenerationType.SEQUENCE
	)
	private Integer userId;
	
	@Column(name = "CITY_ID")
	private Integer cityId;
	
	@Column(name = "COUNTRY_ID")
	private Integer countryId;
	
	@Column(name = "STATE_ID")
	private Integer stateId;
	
	@Column(name = "FIRAT_NAME")
	private String firstName;
	
	@Column(name = "LAST_NAME")
	private String lastName;
	
	@Column(name = "USER_EMAIL")
	private String userEmail;
	
	@Column(name = "USER_MOBILE")
	private String userPhonenumber;
	
	@Column(name = "GENDER")
	private String gender;
	
	@Column(name = "DOB")
	private Date dateOfBirth;
	
	@Column(name = "ACC_STATUS")
	private String accstatus;
	
	@Column(name = "CREATED_DATE",updatable = false)
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	private Date createdDate;
	
	@UpdateTimestamp
	@Temporal(TemporalType.DATE)
	@Column(name="UPDATED_DATE",insertable = false)
	private Date updatedDate;

	@Column(name = "USER_PWD")
	private String userpwd;
}
