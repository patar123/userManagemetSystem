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
import com.ashokit.model.ForgotPwd;
import com.ashokit.utils.PasswordSending;

@Controller
public class ForgotPwdController {

	private static final Logger logger=LoggerFactory.getLogger(UserManagementController.class);

	@Autowired
	private IUserManagementService service;

	@GetMapping("/forgotPwd")
	public String loadForgotPwdForm(Model model) {
		logger.debug(AppConstants.METHOD_EXE_STARTED_STR);
		try {
			ForgotPwd forgot=new ForgotPwd();
			model.addAttribute("forgot", forgot);
		} catch (Exception e) {
			logger.error(AppConstants.EXCE_OCCORED_STR + e.getMessage());
		}
		logger.debug(AppConstants.METHOD_EXE_ENDED_STR);
		return "ForgotPwd";
	}

	@PostMapping("/checkMail")
	public String handelBtnForgot(@ModelAttribute("forgot")ForgotPwd forgot,Model model) {
		logger.debug(AppConstants.METHOD_EXE_STARTED_STR);
		try {
			boolean validMail = service.validMail(forgot);
			if (validMail) {
				String status=forgot.getStatus();
				if (status.equals(AppConstants.LOCKED_STR)) {
					model.addAttribute("errLock", "Your Account Is Locked, Please Unlock");
				}else {
					String mobileNo=forgot.getMobileNo();
					String pwd=forgot.getPwd();
					boolean sendSms = PasswordSending.sendSms(pwd,mobileNo);
					if (sendSms) {
						model.addAttribute("succ", "Password Send to Your Register Mobile No");
					}
				}
			}else {
				model.addAttribute("errMesg", "Incorrect Email");
			}
		} catch (Exception e) {
			logger.error(AppConstants.EXCE_OCCORED_STR +e.getMessage());
		}
		logger.debug(AppConstants.METHOD_EXE_ENDED_STR);
		return "ForgotPwd";
	}
}
