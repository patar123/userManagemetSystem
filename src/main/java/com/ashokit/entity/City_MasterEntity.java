package com.ashokit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "CITY_MASTER")
public class City_MasterEntity {
	@Id
	@Column(name = "CITY_ID")
	private Integer cityId;
	
	@Column(name = "STATE_ID")
	private Integer stateId;
	
	@Column(name = "CITY_NAME")
	private String cityName;
}
