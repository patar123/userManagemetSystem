package com.ashokit.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ashokit.IService.IUserManagementService;
import com.ashokit.constants.AppConstants;
import com.ashokit.model.User;

@Controller
public class UserManagementController {

	private static final Logger log=LoggerFactory.getLogger(UserManagementController.class);

	@Autowired
	private IUserManagementService userService;

	@GetMapping(value = {"/","/register"})
	public String loadForm(Model model) {
		log.debug(AppConstants.METHOD_EXE_STARTED_STR);
		try {
			User user=new User();
			Map<Integer, String> allCountries = userService.getAllCountries();
			model.addAttribute("countriesMap", allCountries);
			model.addAttribute("user", user);
		} catch (Exception e) {
			log.error(AppConstants.EXCE_OCCORED_STR + e.getMessage());
		}
		log.debug(AppConstants.METHOD_EXE_ENDED_STR);
		log.info(AppConstants.REG_FORM_STR);
		return "addUser";
	}

	@PostMapping("/userAccReg")
	public String handelForm(@ModelAttribute("user")User user,Model model) {
		log.debug(AppConstants.METHOD_EXE_STARTED_STR);
		try {			
			userService.saveUserAcc(user);
			log.info(AppConstants.USER_REG_STR);
		} catch (Exception e) {
			log.error(AppConstants.EXCE_OCCORED_STR + e.getMessage());
		}
		log.debug(AppConstants.METHOD_EXE_ENDED_STR);
		return "userAccRegSuccess";
	}

	@ResponseBody
	@RequestMapping("/getStates")
	public Map<Integer, String> getStatesByCountryId(@RequestParam("cid")Integer countryId) {
		log.debug(AppConstants.METHOD_EXE_STARTED_STR);
		Map<Integer,String> stateData=null;
		try {
			stateData=userService.getStatesByCountryId(countryId);
			log.info(AppConstants.RET_STATE_STR);
		} catch (Exception e) {
			log.error(AppConstants.EXCE_OCCORED_STR + e.getMessage());
		}
		log.debug(AppConstants.METHOD_EXE_ENDED_STR);
		return stateData;
	}

	@ResponseBody
	@RequestMapping("/getCities")
	public Map<Integer, String> getCitiesByStateId(@RequestParam("sid")Integer stateId){
		log.debug(AppConstants.METHOD_EXE_STARTED_STR);
		Map<Integer,String> citiesData=null;
		try {
			citiesData=userService.getCitiesByStateId(stateId);
			log.info(AppConstants.RET_CITY_STR);
		} catch (Exception e) {
			log.error(AppConstants.EXCE_OCCORED_STR + e.getMessage());
		}
		log.debug(AppConstants.METHOD_EXE_ENDED_STR);
		return citiesData;
	}

	@RequestMapping("/validateEmail")
	@ResponseBody
	public String mailValidate(@RequestParam("email")String email) {
		log.debug(AppConstants.METHOD_EXE_STARTED_STR);
		String emailStatus=null;
		try{
			emailStatus = userService.getMailVaild(email);
		}catch (Exception e) {
			log.error(AppConstants.EXCE_OCCORED_STR+e.getMessage());;
		}
		log.debug(AppConstants.METHOD_EXE_ENDED_STR);
		return emailStatus;
	}
}
