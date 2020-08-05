package com.ashokit.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokit.entity.State_MasterEntity;

public interface State_MasterRepository extends JpaRepository<State_MasterEntity, Serializable> {

	 List<State_MasterEntity> findAllByCountryId(Integer countryId);

}
