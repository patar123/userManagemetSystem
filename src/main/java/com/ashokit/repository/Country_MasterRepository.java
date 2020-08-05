package com.ashokit.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokit.entity.Country_MasterEntity;

public interface Country_MasterRepository extends JpaRepository<Country_MasterEntity, Serializable> {

}
