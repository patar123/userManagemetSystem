package com.ashokit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ashokit.IService.IUserManagementService;
import com.ashokit.constants.AppConstants;
import com.ashokit.model.Login;

@Controller
public class UserLoginCheck {
	private static final Logger logger=LoggerFactory.getLogger(UserManagementController.class);

	@Autowired
	private IUserManagementService service;

	@GetMapping("/checklogin")
	public String loadLoginForm(Model model) {
		logger.debug(AppConstants.METHOD_EXE_STARTED_STR);
		try {
			Login loginPage=new Login();
			model.addAttribute("loginPage", loginPage);
		} catch (Exception e) {
			logger.error(AppConstants.EXCE_OCCORED_STR + e.getMessage());
		}
		logger.debug(AppConstants.METHOD_EXE_ENDED_STR);
		return "loginPage";
	}

	@PostMapping("/login")
	public String loginBtnHandel(@ModelAttribute("loginPage")Login login,Model model) {
		logger.debug(AppConstants.METHOD_EXE_STARTED_STR);
		try {
			boolean checkLogin = service.checkLogin(login);
			if (checkLogin) {
				String status=login.getStatus();
				if(status.equals(AppConstants.LOCKED_STR)) {
					model.addAttribute("lockedErr", "Your Acccount Is Locked");
				}else {
					return "welcome";
				}
			}else {
				model.addAttribute("errMsg", "Invalid Cridential");
			}
		} catch (Exception e) {
			logger.error(AppConstants.EXCE_OCCORED_STR + e.getMessage());
		}
		logger.debug(AppConstants.METHOD_EXE_ENDED_STR);
		return "loginPage";
	}
}