package com.a105.apiServer.service;

import javax.mail.internet.MimeMessage;

public interface EmailService {
	//public static final String ePw;
	public MimeMessage createMessage(String to) throws Exception;
	public void sendSimpleMessage(String to) throws Exception;
	public void testKey() throws Exception;
	
}
