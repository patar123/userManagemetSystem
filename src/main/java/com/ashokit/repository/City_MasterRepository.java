package com.ashokit.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokit.entity.City_MasterEntity;

public interface City_MasterRepository extends JpaRepository<City_MasterEntity, Serializable> {
		List<City_MasterEntity> findAllByStateId(Integer stateId);
}
