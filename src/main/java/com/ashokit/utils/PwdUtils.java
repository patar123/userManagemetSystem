package com.ashokit.utils;

public class PwdUtils {
	public static final String ALFA_NUMARIC_STRING="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	
	public static String generateTempPwd(int count) {
		StringBuilder builder=new StringBuilder();
		
		while(count-- !=0) {
			int character=(int) (Math.random()*ALFA_NUMARIC_STRING.length());
			builder.append(ALFA_NUMARIC_STRING.charAt(character));
		}
		return builder.toString();
	}
}
