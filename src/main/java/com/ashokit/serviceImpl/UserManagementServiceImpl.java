package com.ashokit.serviceImpl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashokit.IService.IUserManagementService;
import com.ashokit.constants.AppConstants;
import com.ashokit.entity.City_MasterEntity;
import com.ashokit.entity.Country_MasterEntity;
import com.ashokit.entity.State_MasterEntity;
import com.ashokit.entity.User_ManagementEntity;
import com.ashokit.model.ForgotPwd;
import com.ashokit.model.Login;
import com.ashokit.model.User;
import com.ashokit.repository.City_MasterRepository;
import com.ashokit.repository.Country_MasterRepository;
import com.ashokit.repository.State_MasterRepository;
import com.ashokit.repository.User_MasterRepository;
import com.ashokit.utils.EmailUtils;
import com.ashokit.utils.PwdUtils;

@Service
public class UserManagementServiceImpl implements IUserManagementService{

	@Autowired
	private User_MasterRepository userRepo;

	@Autowired
	private Country_MasterRepository countryRepo;

	@Autowired
	private State_MasterRepository stateRepo;

	@Autowired
	private City_MasterRepository cityRepo;
	
	@Autowired
	private EmailUtils emailUtil;

	@Override
	public Map<Integer, String> getAllCountries() {
		Map<Integer, String> map=new LinkedHashMap<>();
		List<Country_MasterEntity> countriesList  = countryRepo.findAll();
		countriesList.forEach(countryEntity->{
			map.put(countryEntity.getCountryId(), countryEntity.getCountryName());
		});
		return map;
	}

	@Override
	public Map<Integer, String> getStatesByCountryId(Integer countryId) {
		Map<Integer, String> statesMap=new LinkedHashMap<Integer, String>();
		List<State_MasterEntity> states = stateRepo.findAllByCountryId(countryId);
		states.forEach(state->{
			statesMap.put(state.getStateId(), state.getStateName());
		});
		return statesMap;
	}

	@Override
	public Map<Integer, String> getCitiesByStateId(Integer stateId) {
		Map<Integer, String> citiesMap=new LinkedHashMap<Integer, String>();
		List<City_MasterEntity> cities = cityRepo.findAllByStateId(stateId);
		cities.forEach(city->{
			citiesMap.put(city.getCityId(), city.getCityName());
		});
		return citiesMap;
	}

	@Override
	public String getMailVaild(String email) {
		User_ManagementEntity userEmail = userRepo.findByUserEmail(email);
		if (userEmail!=null) {
			return "Duplicate";
		}
		return "Unique";
	}

	@Override
	public boolean saveUserAcc(User user) {
		user.setUserpwd(PwdUtils.generateTempPwd(AppConstants.TEMP_PWD_LENGTH));
		user.setAccstatus(AppConstants.LOCKED_STR);
		
		User_ManagementEntity entity=new User_ManagementEntity();
		BeanUtils.copyProperties(user, entity);
		User_ManagementEntity saveEntity=userRepo.save(entity);
		
		if (saveEntity.getUserId() != null) {
			return emailUtil.sendUserAccUnlockEmail(user);
		}
		return false;
	}

	@Override
	public User getUserAccntByTempPwd(String tempPwd) {
		User_ManagementEntity entity = userRepo.findByUserpwd(tempPwd);
		User user=null;
		if (entity!=null) {
			user=new User();
			BeanUtils.copyProperties(entity, user);
		}
		return user;
	}
	
	@Override
	public boolean updateUserAccount(User user) {
		User_ManagementEntity entity=new User_ManagementEntity();
		BeanUtils.copyProperties(user, entity);
		User_ManagementEntity saveEntity = userRepo.save(entity);
		return saveEntity!=null;
	}

	@Override
	public boolean checkLogin(Login login) {
		User_ManagementEntity checkLogin = userRepo.checkLogin(login.getEmail(), login.getPwd());		
		if (checkLogin!=null) {
			login.setStatus(checkLogin.getAccstatus());
			return true;
		}
		return false;
	}

	@Override
	public boolean validMail(ForgotPwd forgot) {
		User_ManagementEntity validMail = userRepo.findByUserEmail(forgot.getEmail());
		if (validMail!=null) {
			forgot.setStatus(validMail.getAccstatus());
			forgot.setMobileNo(validMail.getUserPhonenumber());
			forgot.setPwd(validMail.getUserpwd());
			return true;
		}
		return false;
	}
}
