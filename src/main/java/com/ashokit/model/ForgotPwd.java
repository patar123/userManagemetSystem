package com.ashokit.model;

import lombok.Data;

@Data
public class ForgotPwd {
	private String email;
	private String status;
	private String mobileNo;
	private String pwd;
}
