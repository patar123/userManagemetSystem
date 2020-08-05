package com.ashokit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ashokit.IService.IUserManagementService;
import com.ashokit.constants.AppConstants;
import com.ashokit.model.UnlockAccount;
import com.ashokit.model.User;

@Controller
public class UnlockAccountController {

	private static final Logger logger=LoggerFactory.getLogger(UserManagementController.class);

	@Autowired
	private IUserManagementService userMasterservice;

	@GetMapping(value = "/unlockAcc")
	public String displayUnlockAccForm(@RequestParam("email") String email, Model model) {
		logger.debug(AppConstants.METHOD_EXE_STARTED_STR);
		try {
			model.addAttribute("email", email);
			UnlockAccount unlockAcc = new UnlockAccount();
			model.addAttribute("unlockAcc", unlockAcc);
		} catch (Exception e) {
			logger.error(AppConstants.EXCE_OCCORED_STR + e.getMessage());
		}
		logger.debug(AppConstants.METHOD_EXE_ENDED_STR);
		return "UnlockAccount";
	}

	@PostMapping("/unlockUserAcc")
	public String unlockUserAcc(@ModelAttribute("unlockAcc") UnlockAccount acc, Model model) {
		logger.debug(AppConstants.METHOD_EXE_STARTED_STR);
		try {
			User userAcc = userMasterservice.getUserAccntByTempPwd(acc.getTempPwd());
			if (userAcc!=null) {
				userAcc.setAccstatus("Un-Locked");
				userAcc.setUserpwd(acc.getNewPwd());
				boolean isUpdate = userMasterservice.updateUserAccount(userAcc);
				if (isUpdate) {
					return "UnlockAccountSuccess";
				}
			}
			model.addAttribute("errMsg", "Plz Enter Correct Temporary Password");
		} catch (Exception e) {
			logger.error(AppConstants.EXCE_OCCORED_STR + e.getMessage());
		}
		logger.debug(AppConstants.EXCE_OCCORED_STR);
		return "UnlockAccount";
	}
}
