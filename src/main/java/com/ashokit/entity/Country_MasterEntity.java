package com.ashokit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "COUNTRY_MASTER")
public class Country_MasterEntity {
	@Id
	@Column(name="COUNTRY_ID")
	@SequenceGenerator(
			name="cid_seq_gen",
			sequenceName="COUNTRY_MASTER_ID_SEQ",
			allocationSize=1
	)
	@GeneratedValue(
			generator="cid_seq_gen",
			strategy=GenerationType.SEQUENCE
	)
	private Integer countryId;
	
	@Column(name="COUNTRY_CODE")
	private String countryCode;
	
	@Column(name="COUNTRY_NAME")
	private String countryName;
}
