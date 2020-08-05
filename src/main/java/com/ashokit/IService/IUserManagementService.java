package com.ashokit.IService;

import java.util.Map;

import com.ashokit.model.ForgotPwd;
import com.ashokit.model.Login;
import com.ashokit.model.User;

public interface IUserManagementService {
	public boolean saveUserAcc(User user);
	public Map<Integer, String> getAllCountries();
	public Map<Integer, String> getStatesByCountryId(Integer countryId);
	public Map<Integer, String> getCitiesByStateId(Integer stateId);
	public String getMailVaild(String email);
	public User getUserAccntByTempPwd(String tempPwd);
	public boolean updateUserAccount(User user);
	public boolean checkLogin(Login login);
	public boolean validMail(ForgotPwd forgot);
}
