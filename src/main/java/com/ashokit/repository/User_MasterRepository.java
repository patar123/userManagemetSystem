package com.ashokit.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ashokit.entity.User_ManagementEntity;

public interface User_MasterRepository extends JpaRepository<User_ManagementEntity, Serializable>{
	
	public User_ManagementEntity findByUserEmail(String userEmail);	
	
	public User_ManagementEntity findByUserpwd(String userpwd);
	
	@Query(value= "select * from user_accounts where user_email=? and user_pwd=?" , nativeQuery=true)
	public User_ManagementEntity checkLogin(String email,String pwd);
	
}
